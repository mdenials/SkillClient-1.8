package com.skillclient.chat;

import java.net.URI;
import net.minecraft.client.gui.GuiChat;
import java.util.Collections;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import java.util.Iterator;
import com.skillclient.misc.Module;
import java.util.ArrayList;
import java.util.HashMap;
import com.skillclient.utils.ClientChatAPI;
import java.util.List;
import java.util.Map;
import com.skillclient.misc.SCMC;

public class Chat implements SCMC
{
    public Map<String, Cmd> chatmodules;
    public List<Cmd> list;
    public final ClientChatAPI chatAPI;
    
    public Chat() {
        this.chatmodules = new HashMap<String, Cmd>();
        this.list = new ArrayList<Cmd>();
        this.chatAPI = new ClientChatAPI();
        if (Chat.sc.indev) {
            this.registerChat((Cmd)new CmdReload());
        }
        for (final Module m : Chat.sc.moduleList) {
            this.registerChat((Cmd)new CmdValue(m));
        }
        this.registerChat((Cmd)new CmdHelp());
        this.registerChat((Cmd)new CmdSay());
        this.registerChat((Cmd)new CmdGive());
        this.registerChat((Cmd)new CmdFriend());
        this.registerChat((Cmd)new CmdVclip());
        this.registerChat((Cmd)new CmdNickName());
        this.registerChat((Cmd)new CmdExploit());
        this.registerChat((Cmd)new CmdSettings());
        this.registerChat((Cmd)new CmdBind());
        this.registerChat((Cmd)new CmdRename());
        this.registerChat((Cmd)new CmdLore());
        this.registerChat((Cmd)new CmdNBT());
        this.registerChat((Cmd)new CmdEnchant());
        this.registerChat((Cmd)new CmdBook());
        this.registerChat((Cmd)new CmdHideTags());
        this.registerChat((Cmd)new CmdSpawnEgg());
        this.registerChat((Cmd)new CmdInventory());
    }
    
    public void chat(final String... message) {
        for (final String m : message) {
            this.chat(m);
        }
    }
    
    public void chat(final String text, final String execute) {
        final ChatComponentText itextcomponent = new ChatComponentText(text);
        itextcomponent.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, execute));
        itextcomponent.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("[click]")));
        this.chat((IChatComponent)itextcomponent);
    }
    
    public void chat(final String text) {
        this.chat((IChatComponent)new ChatComponentText(text));
    }
    
    public void chat(final IChatComponent itextcomponent) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("§7[§6SkillClient§7]§r ").appendSibling(itextcomponent));
    }
    
    public boolean onMessageSend(final C01PacketChatMessage packet) {
        try {
            final String msg = packet.getMessage();
            if (msg.startsWith(".")) {
                final Cmd cmd = this.chatmodules.get(msg.toLowerCase().split(" ")[0].substring(1));
                if (cmd != null) {
                    final String[] s = msg.contains(" ") ? msg.substring(msg.indexOf(" ") + 1).split(" ") : new String[0];
                    cmd.action(s);
                }
                else {
                    this.chat("That command doesn't exist. Try .help :)");
                }
                return true;
            }
            if (msg.startsWith("force-send-by-skillclient")) {
                packet.message = msg.replaceFirst("force-send-by-skillclient", "");
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
    
    public boolean onTabComplete(final C14PacketTabComplete packet) {
        try {
            final String msg = packet.getMessage();
            if (msg.startsWith(".")) {
                final Cmd cmd = this.chatmodules.get(msg.toLowerCase().split(" ")[0].substring(1));
                List<String> tab = new ArrayList<String>();
                if (cmd == null) {
                    final List<String> cmds = new ArrayList<String>();
                    for (final Cmd c : this.list) {
                        if (!cmds.contains("." + c.getCommand()) && c.getCommand().toLowerCase().startsWith(msg.toLowerCase().substring(1))) {
                            cmds.add("." + c.getCommand());
                        }
                    }
                    tab = cmds;
                }
                else {
                    final String[] s = msg.contains(" ") ? msg.substring(msg.indexOf(" ") + 1).split(" ", -1) : new String[0];
                    tab = (List<String>)cmd.tabComplete(s);
                }
                Collections.sort(tab);
                if (Chat.mc.currentScreen instanceof GuiChat) {
                    ((GuiChat)Chat.mc.currentScreen).onAutocompleteResponse((String[])tab.toArray(new String[tab.size()]));
                }
                return true;
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
    
    public static void openWebLink(final String url_s) {
        try {
            final URI url = new URI(url_s.replace(" ", "%20"));
            final Class<?> oclass = Class.forName("java.awt.Desktop");
            final Object object = oclass.getMethod("getDesktop", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
            oclass.getMethod("browse", URI.class).invoke(object, url);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private void registerChat(final Cmd chatmodule) {
        this.chatmodules.put(chatmodule.getCommand().toLowerCase(), chatmodule);
        this.list.add(chatmodule);
    }
}
