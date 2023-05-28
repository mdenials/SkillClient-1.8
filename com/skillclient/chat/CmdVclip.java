package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;

public class CmdVclip extends Cmd
{
    public CmdVclip() {
        super("vclip", "Teleports you in y-direction");
    }
    
    @Override
    public void action(final String[] args) {
        if (args.length == 0) {
            CmdVclip.sc.chat.chat("Help for .vclip");
            CmdVclip.sc.chat.chat(".vclip [amount] : Teleport up");
            CmdVclip.sc.chat.chat(".vclip -[amount] : Teleport down");
        }
        else {
            try {
                CmdVclip.mc.thePlayer.setEntityBoundingBox(CmdVclip.mc.thePlayer.getEntityBoundingBox().offset(0.0, Double.parseDouble(args[0]), 0.0));
            }
            catch (Exception e) {
                CmdVclip.sc.chat.chat("[SkillClient:Vclip] Try 5 or -6 instead");
            }
        }
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        return new ArrayList<String>();
    }
}

