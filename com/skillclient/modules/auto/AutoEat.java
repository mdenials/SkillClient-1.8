package com.skillclient.modules.auto;

import com.skillclient.events.api.EventTarget;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemFood;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class AutoEat extends Module
{
    public static boolean eating;
    private int bestSlot;
    
    static {
        AutoEat.eating = false;
    }
    
    public AutoEat() {
        super("AutoEat", Register.Category.AUTO, "Automatically Eats");
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (AutoEat.mc.thePlayer.capabilities.isCreativeMode || AutoEat.mc.thePlayer.getFoodStats().getFoodLevel() >= 20) {
            if (AutoEat.eating) {
                AutoEat.mc.gameSettings.keyBindUseItem.pressed = false;
            }
            AutoEat.eating = false;
            return;
        }
        if (AutoEat.mc.thePlayer.getHeldItem() != null && AutoEat.mc.thePlayer.getHeldItem().getItem() instanceof ItemFood) {
            AutoEat.eating = true;
        }
        if (AutoEat.eating) {
            AutoEat.mc.gameSettings.keyBindUseItem.pressed = true;
        }
        float bestSaturation = 0.0f;
        this.bestSlot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack item = AutoEat.mc.thePlayer.inventory.getStackInSlot(i);
            if (item != null) {
                if (item.getItem() instanceof ItemFood) {
                    final float saturation = ((ItemFood)item.getItem()).getSaturationModifier(item);
                    if (saturation > bestSaturation) {
                        bestSaturation = saturation;
                        this.bestSlot = i;
                    }
                }
            }
        }
        if (this.bestSlot == -1) {
            return;
        }
        AutoEat.mc.thePlayer.inventory.currentItem = this.bestSlot;
    }
}

