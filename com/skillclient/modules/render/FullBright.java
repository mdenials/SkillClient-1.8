package com.skillclient.modules.render;

import net.minecraft.potion.Potion;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class FullBright extends Module
{
    public FullBright() {
        super("FullBright", Register.Category.RENDER, "Everything is light up");
    }
    
    public boolean isActive() {
        return ((FullBright)Register.getModule((Class)FullBright.class)).isToggled() || ((WallHack)Register.getModule((Class)WallHack.class)).isActive() || ((XRay)Register.getModule((Class)XRay.class)).isActive() || (FullBright.mc.thePlayer != null && FullBright.mc.thePlayer.isPotionActive(Potion.nightVision));
    }
}
