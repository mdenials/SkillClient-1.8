package com.skillclient.gui.accountManager;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import com.skillclient.misc.SCMC;

public class AccountManager implements SCMC
{
    public static List<Account> accounts;
    public static Account account;
    public static Account.AccountSession orig;
    public static boolean bungeeHack;
    public static String fakeIP;
    
    static {
        AccountManager.accounts = new ArrayList<Account>();
        AccountManager.bungeeHack = false;
        AccountManager.fakeIP = "127.0.0.1";
    }
    
    public static void save() {
        try {
            final FileWriter writer = new FileWriter(AccountManager.sc.filemanager.accounts);
            for (final Account a : AccountManager.accounts) {
                if (!a.type.equals((Object)AccountManager.AccountType.SESSION)) {
                    writer.write(String.valueOf(a.save()) + "\n");
                }
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void load() {
        try {
            final FileReader reader = new FileReader(AccountManager.sc.filemanager.accounts);
            final BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    AccountManager.accounts.add(Account.load(line.split(";")));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            reader.close();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}

