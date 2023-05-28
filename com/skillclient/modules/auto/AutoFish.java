package com.skillclient.modules.auto;

import com.skillclient.events.api.EventTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.EntityFishHook;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class AutoFish extends Module
{
    public AutoFish() {
        super("AutoFish", Register.Category.AUTO, "Automatically fishes");
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (AutoFish.mc.thePlayer.fishEntity == null) {
            AutoFish.mc.rightClickMouse();
            AutoFish.mc.thePlayer.fishEntity = new EntityFishHook((World)AutoFish.mc.theWorld, (EntityPlayer)AutoFish.mc.thePlayer);
        }
        else if (AutoFish.mc.thePlayer.fishEntity.motionX == 0.0 && AutoFish.mc.thePlayer.fishEntity.motionZ == 0.0 && AutoFish.mc.thePlayer.fishEntity.motionY != 0.0) {
            AutoFish.mc.rightClickMouse();
            AutoFish.mc.thePlayer.fishEntity = null;
        }
    }
}

