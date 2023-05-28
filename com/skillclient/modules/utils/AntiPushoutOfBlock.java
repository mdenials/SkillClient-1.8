package com.skillclient.modules.util;

import com.skillclient.modules.render.FreeCam;
import com.skillclient.modules.player.NoClip;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class AntiPushoutoOfBlock extends Module
{
    public AntiPushoutoOfBlock() {
        super("AntiPushoutofBlock", Register.Category.UTIL, "You are no longer pushed out of Blocks");
    }
    
    public boolean isActive() {
        return this.isToggled() || ((NoClip)Register.getModule((Class)NoClip.class)).isActive() || ((FreeCam)Register.getModule((Class)FreeCam.class)).isActive() || (AntiPushoutoOfBlock.mc.thePlayer != null && AntiPushoutoOfBlock.mc.thePlayer.noClip);
    }
}
