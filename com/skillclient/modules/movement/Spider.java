package com.skillclient.modules.movement;

import com.skillclient.events.api.EventTarget;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Spider extends Module
{
    ValueNumber speed;
    
    public Spider() {
        super("Spider", Register.Category.MOVEMENT, "Climb up Walls like Spiderman");
        this.speed = new ValueNumber("Speed", (Module)this, 100.0, 0.0, 20.0, 1);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (Spider.mc.thePlayer.isCollidedHorizontally) {
            Spider.mc.thePlayer.motionY = (double)this.speed.getValue() / 100.0;
        }
    }
}


