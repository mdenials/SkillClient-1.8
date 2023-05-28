package com.skillclient.utils;

import java.net.SocketException;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import java.security.Key;
import net.minecraft.client.Minecraft;
import java.math.BigInteger;
import java.util.Base64;
import net.minecraft.util.CryptManager;
import java.util.Arrays;
import com.skillclient.gui.accountManager.AccountManager;
import com.skillclient.gui.accountManager.Account;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import com.skillclient.misc.SCMC;

public class ClientChatAPI implements SCMC
{
    Socket socket;
    private boolean connected;
    public static final String splitter = "\u1337";
    DataOutputStream writer;
    DataInputStream reader;
    
    public ClientChatAPI() {
        this.connected = false;
    }
    
    public void start() {
        if (this.connected) {
            System.err.println("already connected to Chat!");
            return;
        }
        try {
            this.socket = new Socket("localhost", 13377);
        }
        catch (Exception e) {
            ClientChatAPI.sc.chat.chat("Failed to connect. Server down? " + e.getMessage());
            e.printStackTrace();
            return;
        }
        this.connected = true;
        try {
            this.writer = new DataOutputStream(this.socket.getOutputStream());
            this.reader = new DataInputStream(this.socket.getInputStream());
        }
        catch (IOException e2) {
            e2.printStackTrace();
            this.connected = false;
            return;
        }
        ClientChatAPI.sc.chat.chat("Connecting to chat...");
        try {
            this.write("username", AccountManager.accounts.get(0).username);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("3");
    }
    
    public void sendChat(final String msg) {
        if (this.connected) {
            this.write("chat-message", msg);
        }
    }
    
    public void tick() {
        if (this.connected) {
            this.write("keep-alive");
            try {
                while (this.reader.available() > 0) {
                    final String line;
                    if ((line = this.reader.readUTF()) == null) {
                        break;
                    }
                    final String[] args = line.split("\u1337");
                    System.out.println("read: " + Arrays.toString(args));
                    if (args[0].equals("server-stopped")) {
                        System.out.println("Server stopped!!! " + args[1]);
                    }
                    if (args[0].equals("chat-message")) {
                        ClientChatAPI.sc.chat.chat(args[1]);
                    }
                    if (!args[0].equals("encryption-request")) {
                        continue;
                    }
                    try {
                        System.out.println("encryption-request");
                        final SecretKey secretkey = CryptManager.createNewSharedKey();
                        final String s = args[2];
                        final PublicKey publickey = CryptManager.decodePublicKey(Base64.getDecoder().decode(args[1]));
                        final String s2 = new BigInteger(CryptManager.getServerIdHash(s, publickey, secretkey)).toString(16);
                        final Minecraft mc = Minecraft.getMinecraft();
                        System.out.println(mc.getSession().getProfile());
                        System.out.println(mc.getSession().getToken());
                        System.out.println(s2);
                        try {
                            mc.getSessionService().joinServer(mc.getSession().getProfile(), mc.getSession().getToken(), s2);
                        }
                        catch (Exception e) {
                            ClientChatAPI.sc.chat.chat("failed to auth " + e.getMessage());
                            e.printStackTrace();
                        }
                        final byte[] secretKeyEncrypted = CryptManager.encryptData((Key)publickey, secretkey.getEncoded());
                        final byte[] verifyTokenEncrypted = CryptManager.encryptData((Key)publickey, Base64.getDecoder().decode(args[2]));
                        this.write("encryption-response", Base64.getEncoder().encodeToString(secretKeyEncrypted), Base64.getEncoder().encodeToString(verifyTokenEncrypted));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            catch (IOException e3) {
                e3.printStackTrace();
                this.connected = false;
            }
        }
    }
    
    public void stop() {
        if (!this.connected) {
            System.out.println("Already disconnected!");
            return;
        }
        try {
            this.reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.connected = false;
    }
    
    public void write(final String... strings) {
        final StringBuilder result = new StringBuilder();
        for (final String s : strings) {
            result.append(String.valueOf(s) + "\u1337");
        }
        try {
            this.writer.writeUTF(result.toString());
        }
        catch (SocketException e2) {
            System.err.println("Server down?");
            this.connected = false;
        }
        catch (IOException e) {
            e.printStackTrace();
            this.connected = false;
        }
    }
    
    public boolean isConnected() {
        return this.connected;
    }
}

