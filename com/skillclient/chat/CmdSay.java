package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;

public class CmdSay extends Cmd
{
    public static final String FORCE_SEND = "force-send-by-skillclient";
    
    public CmdSay() {
        super("say", "Forces something to be send.");
    }
    
    @Override
    public void action(final String[] args) {
        CmdSay.mc.thePlayer.sendChatMessage("force-send-by-skillclient" + String.join(" ", (CharSequence[])args));
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        return new ArrayList<String>();
    }
}
