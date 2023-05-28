package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;

public class CmdReload extends Cmd
{
    public CmdReload() {
        super("reload", "Testing only");
    }
    
    @Override
    public void action(final String[] args) {
        CmdReload.sc.chat = new Chat();
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        return new ArrayList<String>();
    }
}

