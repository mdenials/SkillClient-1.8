package com.skillclient.chat;

import net.minecraft.client.network.NetworkPlayerInfo;
import com.skillclient.wrapper.SkillWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import com.skillclient.gui.GuiSkillClientSettings;
import java.util.Arrays;

public class CmdFriend extends Cmd
{
    public CmdFriend() {
        super("friend", "Friends won't be attacked");
    }
    
    @Override
    public void action(final String[] args) {
        final String name = (args.length >= 2) ? String.join(" ", (CharSequence[])Arrays.copyOfRange(args, 1, args.length)) : "";
        if (args.length == 0) {
            CmdFriend.sc.chat.chat("Help for .friend");
            CmdFriend.sc.chat.chat(".friend list - List all your friends");
            CmdFriend.sc.chat.chat(".friend add <name> - Add a friend");
            CmdFriend.sc.chat.chat(".friend remove <name> - Remove a friend");
        }
        else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            CmdFriend.sc.chat.chat("All your friends: ");
            for (final String friend : GuiSkillClientSettings.friends) {
                CmdFriend.sc.chat.chat("- " + friend, ".friend remove " + friend);
            }
        }
        else if (args[0].equalsIgnoreCase("add")) {
            if (args.length >= 2) {
                if (GuiSkillClientSettings.friends.add(name)) {
                    CmdFriend.sc.chat.chat("Added " + name + " to FriendList");
                }
                else {
                    CmdFriend.sc.chat.chat("Couldn't add " + name + " to FriendList");
                }
            }
            else {
                CmdFriend.sc.chat.chat("Missing Parameter");
                CmdFriend.sc.chat.chat(".friend add <name> - Add a friend");
            }
        }
        else if (args[0].equalsIgnoreCase("remove")) {
            if (args.length >= 2) {
                if (GuiSkillClientSettings.friends.remove(name)) {
                    CmdFriend.sc.chat.chat("Removed " + name + " from FriendList");
                }
                else {
                    CmdFriend.sc.chat.chat("Couldn't remove " + name + " from FriendList");
                }
            }
            else {
                CmdFriend.sc.chat.chat("Missing Parameter");
                CmdFriend.sc.chat.chat(".friend remove <name> - Remove a friend");
            }
        }
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        final List<String> cmds = new ArrayList<String>();
        if (args.length == 1) {
            String[] array;
            for (int length = (array = new String[] { "list", "remove", "add" }).length, i = 0; i < length; ++i) {
                final String s = array[i];
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    cmds.add(s);
                }
            }
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
            for (final String friend : GuiSkillClientSettings.friends) {
                if (friend.toLowerCase().startsWith(args[1].toLowerCase())) {
                    cmds.add(friend);
                }
            }
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("add")) {
            for (final NetworkPlayerInfo p : SkillWrapper.getPlayerInfoMap()) {
                if (p.getGameProfile().getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                    cmds.add(p.getGameProfile().getName());
                }
            }
        }
        return cmds;
    }
}

