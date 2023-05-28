package com.skillclient.modules.world;

import com.skillclient.events.EventRotation;
import com.skillclient.events.api.EventTarget;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.EnumFacing;
import net.minecraft.init.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.item.ItemBlock;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Tower extends Module
{
    ValueNumber speed;
    ValueBoolean fast;
    
    public Tower() {
        super("Tower", Register.Category.WORLD, "Automatically Jumps and places Blocks");
        this.speed = new ValueNumber("Speed", (Module)this, 0.6000000238418579, 0.4000000059604645, 0.41999998688697815, 2);
        this.fast = new ValueBoolean("Fast", (Module)this, true);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (Tower.mc.thePlayer.getHeldItem() == null || !(Tower.mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock)) {
            for (int i = 0; i < 9; ++i) {
                final ItemStack item = Tower.mc.thePlayer.inventory.getStackInSlot(i);
                if (item != null && item.getItem() instanceof ItemBlock) {
                    Tower.mc.thePlayer.inventory.currentItem = i;
                    break;
                }
            }
        }
        if (Tower.mc.thePlayer != null && Tower.mc.thePlayer.getHeldItem() != null && Tower.mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock && !((ScaffoldWalk)Register.getModule((Class)ScaffoldWalk.class)).isActive()) {
            if (Tower.mc.thePlayer.onGround || Tower.mc.theWorld.getBlockState(new BlockPos((Entity)Tower.mc.thePlayer)).getBlock().equals(Blocks.web)) {
                Tower.mc.thePlayer.motionY = (double)this.speed.getValue();
            }
            if (Tower.mc.objectMouseOver.sideHit == EnumFacing.UP && Tower.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && Tower.mc.rightClickDelayTimer == 0 && Tower.mc.playerController.onPlayerRightClick(Tower.mc.thePlayer, Tower.mc.theWorld, Tower.mc.thePlayer.getHeldItem(), Tower.mc.objectMouseOver.getBlockPos(), Tower.mc.objectMouseOver.sideHit, Tower.mc.objectMouseOver.hitVec)) {
                Tower.mc.thePlayer.swingItem();
                Tower.mc.rightClickDelayTimer = ((FastPlace)Register.getModule((Class)FastPlace.class)).getClick();
                Tower.mc.thePlayer.motionY = (double)(this.fast.getValue() ? this.speed.getValue() : -1.0);
            }
        }
    }
    
    @EventTarget(3)
    public void onUpdate(final EventRotation event) {
        if (this.isActive() && Tower.mc.thePlayer != null && Tower.mc.thePlayer.getHeldItem() != null && Tower.mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock) {
            event.setPitch(90.0f);
            event.stop();
        }
    }
}


