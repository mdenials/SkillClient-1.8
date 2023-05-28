package com.skillclient.modules.movement;

import com.skillclient.events.api.EventTarget;
import net.minecraft.block.material.Material;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Glide extends Module
{
    ValueNumber motionY;
    
    public Glide() {
        super("Glide", Register.Category.MOVEMENT, "Slower fall");
        this.motionY = new ValueNumber("MotionY", (Module)this, 100.0, 0.0, 12.0, 1);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (Glide.mc.thePlayer.motionY < 0.0 && !Glide.mc.thePlayer.isInWater() && !Glide.mc.thePlayer.isOnLadder() && !Glide.mc.thePlayer.isInsideOfMaterial(Material.lava) && !Glide.mc.thePlayer.movementInput.sneak && !Glide.mc.thePlayer.onGround) {
            Glide.mc.thePlayer.motionY = -(double)this.motionY.getValue() / 100.0;
        }
    }
}
