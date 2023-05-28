package com.skillclient.modules.auto;

import com.skillclient.events.api.EventTarget;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.client.gui.inventory.GuiInventory;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.utils.TimerUtil;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class AutoArmor extends Module
{
    public ValueNumber speed;
    TimerUtil timer;
    
    public AutoArmor() {
        super("AutoArmor", Register.Category.AUTO, "Equip Armor");
        this.speed = new ValueNumber("Speed", (Module)this, 10.0, 1.0, 1.0, 1);
        this.timer = new TimerUtil();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (!(AutoArmor.mc.currentScreen instanceof GuiInventory) && AutoArmor.mc.currentScreen != null) {
            return;
        }
        final int[] bestArmorSlot = { -1, -1, -1, -1 };
        final int[] bestArmorAmount = { -1, -1, -1, -1 };
        if (this.timer.isDelayComplete((long)(this.speed.getInt() * 100))) {
            for (int i = 0; i < 36; ++i) {
                final ItemStack itemstack = AutoArmor.mc.thePlayer.inventory.getStackInSlot(i);
                if (itemstack != null && itemstack.getItem() instanceof ItemArmor) {
                    final ItemArmor armor = (ItemArmor)itemstack.getItem();
                    if (armor.damageReduceAmount > bestArmorAmount[armor.armorType]) {
                        bestArmorAmount[armor.armorType] = armor.damageReduceAmount;
                        bestArmorSlot[armor.armorType] = i;
                    }
                }
            }
            for (int i = 0; i < 4; ++i) {
                final ItemStack itemstack = AutoArmor.mc.thePlayer.inventory.armorItemInSlot(3 - i);
                ItemArmor currentArmor;
                if (itemstack != null && itemstack.getItem() instanceof ItemArmor) {
                    currentArmor = (ItemArmor)itemstack.getItem();
                }
                else {
                    currentArmor = null;
                }
                ItemArmor bestArmor;
                try {
                    bestArmor = (ItemArmor)AutoArmor.mc.thePlayer.inventory.getStackInSlot(bestArmorSlot[i]).getItem();
                }
                catch (Exception e) {
                    bestArmor = null;
                }
                if (bestArmor != null && (currentArmor == null || bestArmor.damageReduceAmount > currentArmor.damageReduceAmount)) {
                    AutoArmor.mc.playerController.windowClick(0, (bestArmorSlot[i] < 9) ? (36 + bestArmorSlot[i]) : bestArmorSlot[i], 0, 0, (EntityPlayer)AutoArmor.mc.thePlayer);
                    AutoArmor.mc.playerController.windowClick(0, 5 + i, 0, 0, (EntityPlayer)AutoArmor.mc.thePlayer);
                    AutoArmor.mc.playerController.windowClick(0, (bestArmorSlot[i] < 9) ? (36 + bestArmorSlot[i]) : bestArmorSlot[i], 0, 0, (EntityPlayer)AutoArmor.mc.thePlayer);
                    this.timer.setLastMS();
                    return;
                }
            }
        }
    }
}
