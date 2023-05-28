package com.skillclient.chat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdIRC extends Cmd
{
    public CmdIRC() {
        super("give", "like /give but without OP (Creative Only)");
    }
    
    @Override
    public void action(final String[] args) {
        if (args[0].equals("start")) {
            CmdIRC.sc.chat.chatAPI.start();
        }
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        return (args.length == 1) ? Arrays.asList("start", "stop", "restart") : new ArrayList<String>();
    }
}
