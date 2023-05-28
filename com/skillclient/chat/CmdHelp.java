package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import com.mojang.realmsclient.gui.ChatFormatting;

public class CmdHelp extends Cmd
{
    public CmdHelp() {
        super("help", "Help for the ChatModules.");
    }
    
    @Override
    public void action(final String[] args) {
        for (final Cmd c : CmdHelp.sc.chat.list) {
            CmdHelp.sc.chat.chat(ChatFormatting.BLUE + "." + c.getCommand() + " " + ChatFormatting.RESET + c.getDescription(), "." + c.getCommand());
        }
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        return new ArrayList<String>();
    }
}


