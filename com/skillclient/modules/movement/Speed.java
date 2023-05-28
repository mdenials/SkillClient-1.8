package com.skillclient.modules.movement;

import com.skillclient.events.api.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Speed extends Module
{
    public ValueNumber base_speed;
    
    public Speed() {
        super("Speed", Register.Category.MOVEMENT, "Let you become very fast. Advanced Config!");
        this.base_speed = new ValueNumber("base", (Module)this, 600.0, 0.0, 300.0, 1);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (Speed.mc.thePlayer.motionY < 0.0) {
            final EntityPlayerSP thePlayer = Speed.mc.thePlayer;
            thePlayer.motionX *= 1.2;
            final EntityPlayerSP thePlayer2 = Speed.mc.thePlayer;
            thePlayer2.motionZ *= 1.2;
        }
    }
    
    public static float getSpeed() {
        if (Register.isActive((Class)Fly.class) && (!(boolean)((Fly)Register.getModule((Class)Fly.class)).cubecraft.getValue() || ((Fly)Register.getModule((Class)Fly.class)).canCubeFly)) {
            return ((Double)((Fly)Register.getModule((Class)Fly.class)).speed.getValue()).floatValue() / 3.0f;
        }
        if (Speed.mc.thePlayer == null || Speed.mc.thePlayer.onGround) {
            return (float)((double)((Strafe)Register.getModule((Class)Strafe.class)).speed2.getValue() / 500.0);
        }
        return (float)((double)((Strafe)Register.getModule((Class)Strafe.class)).speed.getValue() / 500.0);
    }
}
