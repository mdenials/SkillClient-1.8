package com.skillclient.modules.world;

import com.skillclient.events.api.EventTarget;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Timer extends Module
{
    ValueNumber speed;
    
    public Timer() {
        super("Timer", Register.Category.WORLD, "Speeds up the entire game up");
        this.speed = new ValueNumber("Timer", (Module)this, 5.0, 0.1, 1.5, 2);
    }
    
    public void onDisable() {
        Timer.mc.timer.timerSpeed = 1.0f;
        super.onDisable();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        Timer.mc.timer.timerSpeed = ((Double)this.speed.getValue()).floatValue();
    }
}
