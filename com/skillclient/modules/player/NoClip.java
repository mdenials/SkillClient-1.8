package com.skillclient.modules.player;

import com.skillclient.modules.render.FreeCam;
import net.minecraft.entity.Entity;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class NoClip extends Module
{
    public NoClip() {
        super("NoClip", Register.Category.PLAYER, "NoClip throgh Walls");
    }
    
    public static boolean isNoClip(final Entity entity) {
        return (((NoClip)Register.getModule((Class)NoClip.class)).isActive() && NoClip.mc.thePlayer != null && (NoClip.mc.thePlayer.ridingEntity == null || entity == NoClip.mc.thePlayer.ridingEntity)) || entity.noClip;
    }
    
    public boolean isActive() {
        return super.isActive() || ((FreeCam)Register.getModule((Class)FreeCam.class)).isActive();
    }
}
