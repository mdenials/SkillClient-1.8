package com.skillclient.modules.movement;

import com.skillclient.events.api.EventTarget;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class RapidJump extends Module
{
    ValueNumber speed;
    
    public RapidJump() {
        super("RapidJump", Register.Category.MOVEMENT, "Jump Faster");
        this.speed = new ValueNumber("speed", (Module)this, 10.0, 0.0, 0.0, 1);
    }
    
    public static int getSpeed() {
        return (int)(((RapidJump)Register.getModule((Class)RapidJump.class)).isActive() ? ((RapidJump)Register.getModule((Class)RapidJump.class)).speed.getValue() : 10.0);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (RapidJump.mc.thePlayer.jumpTicks > getSpeed()) {
            RapidJump.mc.thePlayer.jumpTicks = getSpeed();
        }
    }
}

