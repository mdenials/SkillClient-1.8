package com.skillclient.modules.player;

import com.skillclient.events.api.EventTarget;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiChest;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class ChestLoot extends Module
{
    int noSpam;
    ValueNumber speed;
    
    public ChestLoot() {
        super("ChestLoot", Register.Category.PLAYER, "Automatically loots Chests");
        this.speed = new ValueNumber("Speed", (Module)this, 10.0, 0.0, 1.0, 0);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (ChestLoot.mc.currentScreen instanceof GuiChest || ChestLoot.mc.currentScreen instanceof GuiDispenser || ChestLoot.mc.currentScreen instanceof GuiHopper || ChestLoot.mc.currentScreen instanceof GuiFurnace) {
            ++this.noSpam;
            if (this.noSpam >= this.speed.getInt()) {
                this.noSpam = 0;
                final GuiContainer chest = (GuiContainer)ChestLoot.mc.currentScreen;
                for (final Slot slot : chest.inventorySlots.inventorySlots) {
                    if (slot.getHasStack() && chest.inventorySlots.inventorySlots.size() - 36 > slot.slotNumber) {
                        ChestLoot.mc.playerController.windowClick(ChestLoot.mc.thePlayer.openContainer.windowId, slot.slotNumber, 0, 1, (EntityPlayer)ChestLoot.mc.thePlayer);
                        return;
                    }
                }
                ChestLoot.mc.thePlayer.closeScreen();
            }
        }
        else {
            this.noSpam = 0;
        }
    }
}

