package com.skillclient.chat;

import java.util.List;
import java.util.Iterator;
import net.minecraft.client.network.NetworkPlayerInfo;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.client.Minecraft;
import com.skillclient.main.Register;
import com.skillclient.modules.misc.NameProtect;
import java.util.Random;
import java.util.ArrayList;

public class CmdNickName extends Cmd
{
    public static ArrayList<CmdNickName.Nick> names;
    private static final Random random;
    
    static {
        CmdNickName.names = new ArrayList<CmdNickName.Nick>();
        random = new Random();
    }
    
    public CmdNickName() {
        super("nickname", "Protects your Name. Can be really useful if you are recording and don't want to get banned.");
    }
    
    public static String forceDisplayed(String name) {
        name = name.replace("§", "&");
        String output = "";
        char[] charArray;
        for (int length = (charArray = name.toCharArray()).length, i = 0; i < length; ++i) {
            final char c = charArray[i];
            output = String.valueOf(output) + "§r" + c + "§r";
        }
        return output;
    }
    
    public static String real(String text) {
        synchronized (CmdNickName.names) {
            for (final CmdNickName.Nick nick : CmdNickName.names) {
                text = text.replaceAll(nick.oldname, nick.newname);
            }
            if (Register.isActive((Class)NameProtect.class) && Minecraft.getMinecraft().getNetHandler() != null && SkillWrapper.getPlayerInfoMap() != null) {
                for (final NetworkPlayerInfo p : SkillWrapper.getPlayerInfoMap()) {
                    text = text.replaceAll(p.getGameProfile().getName(), ((NameProtect)Register.getModule((Class)NameProtect.class)).protections.getNickName(p.getGameProfile().getName()));
                }
            }
        }
        // monitorexit(CmdNickName.names)
        return text;
    }
    
    @Override
    public void action(final String[] args) {
        if (args.length == 0) {
            CmdNickName.sc.chat.chat("Help for .nickname");
            CmdNickName.sc.chat.chat(".nickname - List all your nicknames");
            CmdNickName.sc.chat.chat(".nickname <oldname> <newname> - Add to NickNameList");
            CmdNickName.sc.chat.chat(".nickname <oldname> - Remove from NickNameList");
            CmdNickName.sc.chat.chat("");
            CmdNickName.sc.chat.chat("NickNames: ");
            for (final CmdNickName.Nick nickname : CmdNickName.names) {
                CmdNickName.sc.chat.chat("- " + forceDisplayed(String.valueOf(nickname.oldname) + ": " + nickname.newname));
            }
            CmdNickName.sc.chat.chat("");
        }
        else if (args.length == 1) {
            if (CmdNickName.Nick.remove(args[0])) {
                CmdNickName.sc.chat.chat("Removed " + args[0] + " from NickNameList");
            }
            else {
                CmdNickName.sc.chat.chat("Couldn't remove " + args[0] + " from NickNameList");
            }
        }
        else if (args.length == 2) {
            CmdNickName.names.add(new CmdNickName.Nick(args[0], args[1].replace("%20%", " ")));
            CmdNickName.sc.chat.chat("- " + forceDisplayed(args[0]) + ": " + args[1]);
        }
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        final List<String> cmds = new ArrayList<String>();
        if (args.length == 1) {
            for (final NetworkPlayerInfo p : SkillWrapper.getPlayerInfoMap()) {
                if (p.getGameProfile().getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                    cmds.add(p.getGameProfile().getName());
                }
            }
            for (final CmdNickName.Nick nick : CmdNickName.names) {
                if (nick.oldname.toLowerCase().startsWith(args[0].toLowerCase()) || nick.newname.toLowerCase().startsWith(args[0].toLowerCase())) {
                    cmds.add(nick.oldname);
                }
            }
        }
        return cmds;
    }
}
