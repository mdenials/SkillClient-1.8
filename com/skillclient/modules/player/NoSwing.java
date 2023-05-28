package com.skillclient.modules.player;

import com.skillclient.events.api.EventTarget;
import com.skillclient.events.EventRender;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class NoSwing extends Module
{
    public NoSwing() {
        super("NoSwing", Register.Category.PLAYER, "No Swing-Animation anymore");
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        NoSwing.mc.thePlayer.isSwingInProgress = false;
    }
}
