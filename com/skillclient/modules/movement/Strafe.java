package com.skillclient.modules.movement;

import com.skillclient.events.api.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Strafe extends Module
{
    ValueNumber speed;
    ValueNumber speed2;
    
    public Strafe() {
        super("Strafe", Register.Category.MOVEMENT, "Better Control in Air");
        this.speed = new ValueNumber("Speed Air", (Module)this, 500.0, 0.0, 170.0, 0);
        this.speed2 = new ValueNumber("Speed Ground", (Module)this, 500.0, 0.0, 130.0, 0);
    }
    
    public static float getSpeed() {
        if (Register.isActive((Class)Fly.class) && (!(boolean)((Fly)Register.getModule((Class)Fly.class)).cubecraft.getValue() || ((Fly)Register.getModule((Class)Fly.class)).canCubeFly)) {
            return ((Double)((Fly)Register.getModule((Class)Fly.class)).speed.getValue()).floatValue() / 3.0f;
        }
        if (Strafe.mc.thePlayer == null || Strafe.mc.thePlayer.onGround) {
            return (float)((double)((Strafe)Register.getModule((Class)Strafe.class)).speed2.getValue() / 500.0);
        }
        return (float)((double)((Strafe)Register.getModule((Class)Strafe.class)).speed.getValue() / 500.0);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        final EntityPlayerSP thePlayer = Strafe.mc.thePlayer;
        final EntityPlayerSP thePlayer2 = Strafe.mc.thePlayer;
        final double n = 0.0;
        thePlayer2.motionZ = n;
        thePlayer.motionX = n;
    }
    
    public boolean isActive() {
        return ((Fly)Register.getModule((Class)Fly.class)).isActive() || (Strafe.mc.thePlayer != null && !Strafe.mc.thePlayer.isInWater() && ((double)this.speed.getValue() != 0.0 || Strafe.mc.thePlayer.onGround || Strafe.mc.thePlayer == null) && ((double)this.speed2.getValue() != 0.0 || !Strafe.mc.thePlayer.onGround || Strafe.mc.thePlayer == null) && super.isActive());
    }
}


