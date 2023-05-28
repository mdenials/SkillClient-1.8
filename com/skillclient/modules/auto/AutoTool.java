package com.skillclient.modules.auto;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class AutoTool extends Module
{
    public AutoTool() {
        super("AutoTool", Register.Category.AUTO, "Automatically uses right tool");
    }
    
    public void setSlot(final BlockPos blockPos) {
        if (!this.isActive()) {
            return;
        }
        float bestSpeed = 1.0f;
        int bestSlot = -1;
        if (AutoEat.eating || AutoTool.mc.playerController.isInCreativeMode()) {
            return;
        }
        final Block block = AutoTool.mc.theWorld.getBlockState(blockPos).getBlock();
        for (int i = 0; i < 9; ++i) {
            final ItemStack item = AutoTool.mc.thePlayer.inventory.getStackInSlot(i);
            if (item != null) {
                final float speed = item.getStrVsBlock(block.getDefaultState().getBlock());
                if (speed > bestSpeed) {
                    bestSpeed = speed;
                    bestSlot = i;
                }
            }
        }
        if (bestSlot != -1) {
            AutoTool.mc.thePlayer.inventory.currentItem = bestSlot;
        }
    }
    
    public void setSlot(final EntityLiving blockPos) {
        if (!this.isActive()) {
            return;
        }
        float bestSwordValue = 1.0f;
        int bestSwordSlot = -1;
        if (AutoEat.eating || AutoTool.mc.playerController.isInCreativeMode()) {
            return;
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack item = AutoTool.mc.thePlayer.inventory.getStackInSlot(i);
            if (item != null) {
                item.getTooltip((EntityPlayer)AutoTool.mc.thePlayer, false);
                final float attackValue = item.attackValue;
                if (attackValue > bestSwordValue) {
                    bestSwordValue = attackValue;
                    bestSwordSlot = i;
                }
            }
        }
        if (bestSwordSlot != -1) {
            AutoTool.mc.thePlayer.inventory.currentItem = bestSwordSlot;
        }
    }
}

