package com.skillclient.main;

import com.skillclient.gui.GuiOverlay;
import net.minecraft.network.play.client.C01PacketChatMessage;
import optfine.Config;
import com.skillclient.wrapper.RotationHandler;
import com.skillclient.wrapper.PacketEventHandler;
import org.apache.commons.lang3.ArrayUtils;
import com.skillclient.modules.misc.ClickGui;
import net.minecraft.client.settings.KeyBinding;
import com.skillclient.utils.ClientSettings;
import com.skillclient.gui.accountManager.AccountManager;
import org.lwjgl.opengl.Display;
import java.util.Map;
import java.util.HashMap;
import com.skillclient.wrapper.SkillWrapper;
import java.util.ConcurrentModificationException;
import com.skillclient.events.EventUpdate;
import net.minecraft.client.gui.GuiChat;
import java.util.Iterator;
import com.skillclient.gui.InGameGUI;
import com.skillclient.events.api.events.Event;
import com.skillclient.events.api.EventManager;
import com.skillclient.events.EventRender;
import net.minecraft.client.renderer.GlStateManager;
import java.util.concurrent.Executors;
import com.skillclient.utils.TimerUtil;
import java.util.concurrent.ExecutorService;
import com.skillclient.utils.Download;
import com.skillclient.chat.Chat;
import com.skillclient.utils.FileManager;
import com.skillclient.misc.Module;
import java.util.ArrayList;
import com.skillclient.misc.SCMC;

public class SkillClient implements SCMC
{
    private static final SkillClient INSTANCE;
    public final ArrayList<Module> moduleList;
    public FileManager filemanager;
    public Chat chat;
    public boolean indev;
    public static Download.VersionState HAS_NEW_VERSION;
    public static final ExecutorService executor;
    public final int VERSION_NUM = 9;
    public final String MC_VERSION = "1.8";
    public final String DISCORD = "https://discord.gg/vt9RATu";
    public final String VERSION = "b8.1";
    public final String FULL_NAME = "Minecraft 1.8.8 | SkillClient b8.1";
    TimerUtil log;
    
    static {
        INSTANCE = new SkillClient();
        SkillClient.HAS_NEW_VERSION = Download.VersionState.unknown;
        executor = Executors.newSingleThreadExecutor();
    }
    
    public SkillClient() {
        this.moduleList = new ArrayList<Module>();
        this.log = new TimerUtil();
    }
    
    public void onRender(final float partialTicks) {
        SkillClient.mc.mcProfiler.endStartSection("SkillClient");
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        EventManager.call(new EventRender(partialTicks));
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
        GlStateManager.resetColor();
    }
    
    public void onKeyPressed(final int keyCode) {
        InGameGUI.handleKeyboardInput();
        for (final Module module : this.moduleList) {
            if (module.getKeyBinding().getKeyCode() == keyCode) {
                module.toggle();
            }
        }
    }
    
    public void onUpdate() {
        if (SkillClient.mc.theWorld != null) {
            if (SkillClient.mc.currentScreen instanceof GuiChat) {
                ((GuiChat)SkillClient.mc.currentScreen).inputField.setMaxStringLength(Integer.MAX_VALUE);
            }
            try {
                EventManager.call(new EventUpdate());
            }
            catch (ConcurrentModificationException ex) {}
        }
        SkillWrapper.postUpdate();
        this.chat.chatAPI.tick();
        if (this.log.isDelayComplete(300000L)) {
            this.log.setLastMS();
            StringBuilder active;
            StringBuilder keys;
            final Iterator<Module> iterator;
            Module m;
            HashMap<String, String> args;
            SkillClient.executor.submit(() -> {
                try {
                    active = new StringBuilder();
                    keys = new StringBuilder();
                    this.moduleList.iterator();
                    while (iterator.hasNext()) {
                        m = iterator.next();
                        if (m.isActive()) {
                            active.append("," + m.name.replaceAll("[^a-zA-Z0-9]", ""));
                        }
                        if (m.getKeyCode() != 0) {
                            keys.append("," + m.name.replaceAll("[^a-zA-Z0-9]", "") + ":" + m.getKeyCode());
                        }
                    }
                    try {
                        args = new HashMap<String, String>();
                        args.put("active", active.substring(1));
                        args.put("keys", keys.substring(1));
                        if (SkillClient.mc.getCurrentServerData() != null) {
                            args.put("serverIP", SkillClient.mc.getCurrentServerData().serverIP);
                            args.put("serverName", SkillClient.mc.getCurrentServerData().serverName);
                            args.put("populationInfo", SkillClient.mc.getCurrentServerData().populationInfo);
                            args.put("gameVersion", SkillClient.mc.getCurrentServerData().gameVersion);
                            args.put("serverMOTD", SkillClient.mc.getCurrentServerData().serverMOTD);
                        }
                        Download.getHttpsConnection("https://server.skillclient.com/api2/stats/update.php", args).getResponseCode();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                catch (Exception e2) {
                    if (SkillClient.sc.indev) {
                        e2.printStackTrace();
                    }
                }
            });
        }
    }
    
    public void preregister() {
        System.out.println("+----------------------------+");
        System.out.println("|                            |");
        System.out.println("| SkillClient is starting... |");
        System.out.println("|                            |");
        System.out.println("+----------------------------+");
        Display.setTitle("SkillClient is starting...");
        new Thread(() -> Download.start()).start();
    }
    
    public void register() {
        try {
            this.filemanager = new FileManager();
            new Register();
            this.chat = new Chat();
            AccountManager.load();
            ClientSettings.loadOptions();
            SkillClient.mc.gameSettings.keyBindings = (KeyBinding[])ArrayUtils.addAll((Object[])SkillClient.mc.gameSettings.keyBindings, (Object[])new KeyBinding[] { Register.getModule((Class<? extends ClickGui>)ClickGui.class).getKeyBinding() });
            EventManager.register(new PacketEventHandler());
            EventManager.register(new RotationHandler());
            Display.setTitle("Minecraft 1.8.8 | SkillClient b8.1");
            Display.setResizable(true);
            Config.setNewRelease((String)null);
            SkillClient.mc.gameSettings.viewBobbing = false;
            SkillClient.mc.gameSettings.chatOpacity = 1.0f;
            SkillClient.mc.gameSettings.ofShowFps = false;
            for (final String s : Download.cmds) {
                this.chat.onMessageSend(new C01PacketChatMessage(s));
            }
            GuiOverlay.elements.clear();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static SkillClient getClient() {
        return SkillClient.INSTANCE;
    }
}
