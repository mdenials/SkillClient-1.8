package com.skillclient.modules.movement;

import com.skillclient.events.api.EventTarget;
import net.minecraft.block.BlockSlime;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class SlimeJump extends Module
{
    ValueNumber value;
    
    public SlimeJump() {
        super("SlimeJump", Register.Category.MOVEMENT, "Jump really high on Slime-Blocks");
        this.value = new ValueNumber("Value", (Module)this, 40.0, 0.0, 35.0, 1);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (SlimeJump.mc.thePlayer.onGround && SlimeJump.mc.theWorld.getBlockState(SlimeJump.mc.thePlayer.getPosition().add(0, -1, 0)).getBlock() instanceof BlockSlime && !SlimeJump.mc.gameSettings.keyBindSneak.isKeyDown()) {
            SlimeJump.mc.thePlayer.motionY = (double)this.value.getValue() / 10.0;
        }
    }
}
