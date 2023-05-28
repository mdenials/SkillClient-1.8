package com.skillclient.modules.auto;

import com.skillclient.events.api.EventTarget;
import net.minecraft.entity.Entity;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class AutoJump extends Module
{
    ValueBoolean edge;
    
    public AutoJump() {
        super("AutoJump", Register.Category.AUTO, "Automatically Jumps");
        this.edge = new ValueBoolean("Edge", (Module)this, true);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if ((boolean)this.edge.getValue() && AutoJump.mc.thePlayer.onGround && !AutoJump.mc.thePlayer.isSneaking() && !AutoJump.mc.gameSettings.keyBindSneak.pressed && AutoJump.mc.theWorld.getCollidingBoundingBoxes((Entity)AutoJump.mc.thePlayer, AutoJump.mc.thePlayer.getEntityBoundingBox().offset(0.0, -0.1, 0.0)).isEmpty()) {
            AutoJump.mc.thePlayer.jump();
        }
        else if (AutoJump.mc.thePlayer.onGround && AutoJump.mc.thePlayer.moveForward > 0.0f && !AutoJump.mc.thePlayer.isJumping) {
            AutoJump.mc.thePlayer.jump();
        }
    }
}
