package com.skillclient.modules.movement;

import com.skillclient.events.api.EventTarget;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.utils.TimerUtil;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class Step extends Module
{
    ValueBoolean ncp;
    ValueNumber height;
    TimerUtil timer;
    
    public Step() {
        super("Step", Register.Category.MOVEMENT, "Let you walk up Blocks very fast");
        this.ncp = new ValueBoolean("NCP", (Module)this, false);
        this.height = new ValueNumber("stepheight", (Module)this, 12.0, 0.0, 1.0, 1);
        this.timer = new TimerUtil();
    }
    
    public static void setStep(final float step) {
        if (Step.mc.thePlayer != null) {
            if (Step.mc.thePlayer.isRiding()) {
                Step.mc.thePlayer.ridingEntity.stepHeight = step;
            }
            else {
                Step.mc.thePlayer.stepHeight = step;
            }
        }
    }
    
    public void onDisable() {
        setStep(0.6f);
        super.onDisable();
    }
    
    public void onEnable() {
        this.timer.setLastMS();
        super.onEnable();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (this.ncp.getValue()) {
            setStep(0.6f);
            if (Step.mc.thePlayer.isCollidedHorizontally && Step.mc.thePlayer.onGround) {
                Step.mc.thePlayer.jump();
                Step.mc.thePlayer.onUpdate();
                Step.mc.thePlayer.onUpdate();
                Step.mc.thePlayer.onUpdate();
            }
        }
        else {
            setStep((float)(0.10000000149011612 + (double)this.height.getValue()));
        }
    }
}
