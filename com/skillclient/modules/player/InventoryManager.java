package com.skillclient.modules.player;

import net.minecraft.client.gui.GuiScreen;
import com.skillclient.CreativeTabs.GuiContainerCreative;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.block.BlockTNT;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import com.skillclient.events.api.EventTarget;
import java.util.Iterator;
import net.minecraft.item.ItemArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import com.skillclient.events.EventUpdate;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import com.skillclient.main.Register;
import java.util.ArrayList;
import net.minecraft.item.Item;
import java.util.List;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.ValueNumber;
import com.skillclient.utils.TimerUtil;
import com.skillclient.misc.Module;

public class InventoryManager extends Module
{
    TimerUtil timer;
    ValueNumber speed;
    ValueBoolean all;
    ValueBoolean autoClose;
    ValueBoolean remains;
    ValueBoolean blocks;
    ValueBoolean food;
    ValueBoolean golden_apple;
    ValueBoolean ender_pearl;
    ValueBoolean potions;
    ValueBoolean tnt;
    ValueBoolean buckets;
    ValueBoolean projectiles;
    ValueBoolean bowarrow;
    ValueBoolean ores;
    ValueBoolean armor;
    ValueBoolean swords;
    public static List<Item> oreList;
    
    static {
        InventoryManager.oreList = new ArrayList<Item>();
    }
    
    public InventoryManager() {
        super("InventoryManager", Register.Category.PLAYER, "Automatically sorts Inventory");
        this.timer = new TimerUtil();
        this.speed = new ValueNumber("Speed", (Module)this, 1000.0, 0.0, 200.0, -1);
        this.all = new ValueBoolean("All", (Module)this, true);
        this.autoClose = new ValueBoolean("AutoClose", (Module)this, true);
        this.remains = new ValueBoolean("Remains", (Module)this, false);
        this.blocks = new ValueBoolean("Blocks", (Module)this, true);
        this.food = new ValueBoolean("Food", (Module)this, true);
        this.golden_apple = new ValueBoolean("Golden Apple", (Module)this, true);
        this.ender_pearl = new ValueBoolean("EnderPearl", (Module)this, true);
        this.potions = new ValueBoolean("Potions", (Module)this, true);
        this.tnt = new ValueBoolean("TNT", (Module)this, true);
        this.buckets = new ValueBoolean("Buckets", (Module)this, true);
        this.projectiles = new ValueBoolean("Projectiles", (Module)this, false);
        this.bowarrow = new ValueBoolean("Bow & Arrow", (Module)this, false);
        this.ores = new ValueBoolean("Ores", (Module)this, true);
        this.armor = new ValueBoolean("Armor", (Module)this, true);
        this.swords = new ValueBoolean("Swords", (Module)this, true);
        InventoryManager.oreList.add(Items.diamond);
        InventoryManager.oreList.add(Items.gold_ingot);
        InventoryManager.oreList.add(Items.iron_ingot);
        InventoryManager.oreList.add(Items.emerald);
        InventoryManager.oreList.add(Items.coal);
        InventoryManager.oreList.add(Items.redstone);
        InventoryManager.oreList.add(Items.nether_star);
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.coal_ore));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.diamond_ore));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.emerald_ore));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.gold_ore));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.iron_ore));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.lapis_ore));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.redstone_ore));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.coal_block));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.diamond_block));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.emerald_block));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.gold_block));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.iron_block));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.lapis_block));
        InventoryManager.oreList.add(Item.getItemFromBlock(Blocks.redstone_block));
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (!this.timer.isDelayComplete(((Double)this.speed.getValue()).longValue()) || this.skipCurrentScreen() || InventoryManager.mc.thePlayer.isUsingItem()) {
            return;
        }
        this.timer.setLastMS();
        final boolean isInv = InventoryManager.mc.currentScreen instanceof GuiInventory || InventoryManager.mc.currentScreen == null || !(InventoryManager.mc.currentScreen instanceof GuiContainer);
        List<Slot> open_inventory;
        if (isInv) {
            open_inventory = (List<Slot>)InventoryManager.mc.thePlayer.inventoryContainer.inventorySlots;
        }
        else {
            open_inventory = (List<Slot>)((GuiContainer)InventoryManager.mc.currentScreen).inventorySlots.inventorySlots;
        }
        float bestSwordValue = 1.5f;
        int bestSwordSlot = -1;
        if (this.swords.getValue()) {
            for (final Slot slot : open_inventory) {
                if (!slot.getHasStack()) {
                    continue;
                }
                slot.getStack().getTooltip((EntityPlayer)InventoryManager.mc.thePlayer, false);
                final float attackValue = slot.getStack().attackValue;
                if (attackValue > bestSwordValue) {
                    bestSwordValue = attackValue;
                    bestSwordSlot = slot.slotNumber;
                }
                if (bestSwordValue != attackValue || open_inventory.size() - 9 <= bestSwordSlot) {
                    continue;
                }
                bestSwordSlot = slot.slotNumber;
            }
        }
        final int[] bestArmorValue = { -1, -1, -1, -1 };
        final int[] bestArmorSlot = { -1, -1, -1, -1 };
        if (this.armor.getValue()) {
            for (final Slot slot2 : open_inventory) {
                if (!slot2.getHasStack()) {
                    continue;
                }
                if (!(slot2.getStack().getItem() instanceof ItemArmor)) {
                    continue;
                }
                final ItemArmor item = (ItemArmor)slot2.getStack().getItem();
                final int pos = item.armorType;
                if (item.damageReduceAmount > bestArmorValue[pos]) {
                    bestArmorValue[pos] = item.damageReduceAmount;
                }
                if (bestArmorValue[pos] != item.damageReduceAmount) {
                    continue;
                }
                bestArmorSlot[pos] = slot2.slotNumber;
            }
            for (int i = 0; i < 4; ++i) {
                if (InventoryManager.mc.thePlayer.inventory.armorItemInSlot(3 - i) != null) {
                    final Item itemstack = InventoryManager.mc.thePlayer.inventory.armorItemInSlot(3 - i).getItem();
                    if (itemstack instanceof ItemArmor && ((ItemArmor)itemstack).damageReduceAmount >= bestArmorValue[i]) {
                        bestArmorSlot[i] = -1;
                    }
                }
            }
        }
        for (final Slot slot2 : open_inventory) {
            if (slot2.getHasStack()) {
                final boolean isInChest = open_inventory.size() - 36 > slot2.slotNumber && !isInv;
                InventoryManager.ItemValue value = this.matches(slot2.getStack());
                if (!(boolean)this.all.getValue()) {
                    if ((boolean)this.swords.getValue() && slot2.getStack().attackValue != 1.0f) {
                        value = InventoryManager.ItemValue.fromBoolean(slot2.slotNumber == bestSwordSlot);
                    }
                    if ((boolean)this.armor.getValue() && slot2.getStack().getItem() instanceof ItemArmor) {
                        final ItemArmor item2 = (ItemArmor)slot2.getStack().getItem();
                        final int pos2 = item2.armorType;
                        value = InventoryManager.ItemValue.fromBoolean(slot2.slotNumber == bestArmorSlot[pos2] || (isInv && slot2.slotNumber <= 8));
                    }
                }
                if (isInChest && value.equals((Object)InventoryManager.ItemValue.Good)) {
                    InventoryManager.mc.playerController.windowClick(InventoryManager.mc.thePlayer.openContainer.windowId, slot2.slotNumber, 0, 1, (EntityPlayer)InventoryManager.mc.thePlayer);
                    if ((double)this.speed.getValue() != 0.0) {
                        return;
                    }
                    continue;
                }
                else {
                    if (isInChest || !value.equals((Object)InventoryManager.ItemValue.Bad)) {
                        continue;
                    }
                    InventoryManager.mc.playerController.windowClick(InventoryManager.mc.thePlayer.openContainer.windowId, slot2.slotNumber, 1, 4, (EntityPlayer)InventoryManager.mc.thePlayer);
                    if ((double)this.speed.getValue() != 0.0) {
                        return;
                    }
                    continue;
                }
            }
        }
        if ((boolean)this.autoClose.getValue() && !isInv) {
            InventoryManager.mc.thePlayer.closeScreen();
        }
    }
    
    public InventoryManager.ItemValue matches(final ItemStack stack) {
        if (this.all.getValue()) {
            return InventoryManager.ItemValue.Good;
        }
        if (stack.getItem() instanceof ItemBlock && ((ItemBlock)stack.getItem()).getBlock() instanceof BlockTNT) {
            return InventoryManager.ItemValue.fromBoolean((boolean)this.tnt.getValue());
        }
        if (stack.getItem() instanceof ItemBlock) {
            return InventoryManager.ItemValue.fromBoolean((boolean)this.blocks.getValue());
        }
        if (stack.getItem() instanceof ItemAppleGold) {
            return InventoryManager.ItemValue.fromBoolean((boolean)this.golden_apple.getValue());
        }
        if (stack.getItem() instanceof ItemFood) {
            return InventoryManager.ItemValue.fromBoolean((boolean)this.food.getValue());
        }
        if (stack.getItem() instanceof ItemEnderPearl) {
            return InventoryManager.ItemValue.fromBoolean((boolean)this.ender_pearl.getValue());
        }
        if (stack.getItem() instanceof ItemPotion) {
            return InventoryManager.ItemValue.fromBoolean((boolean)this.potions.getValue());
        }
        if (stack.getItem() instanceof ItemBucket) {
            return InventoryManager.ItemValue.fromBoolean((boolean)this.buckets.getValue());
        }
        if (stack.getItem() instanceof ItemEgg || stack.getItem() instanceof ItemSnowball) {
            return InventoryManager.ItemValue.fromBoolean((boolean)this.projectiles.getValue());
        }
        if (stack.getItem() instanceof ItemBow || stack.getItem().equals(Items.arrow)) {
            return InventoryManager.ItemValue.fromBoolean((boolean)this.bowarrow.getValue());
        }
        if (InventoryManager.oreList.contains(stack.getItem())) {
            return InventoryManager.ItemValue.fromBoolean((boolean)this.ores.getValue());
        }
        return this.remains.getValue() ? InventoryManager.ItemValue.Good : InventoryManager.ItemValue.IDK;
    }
    
    public boolean skipCurrentScreen() {
        final GuiScreen screen = InventoryManager.mc.currentScreen;
        return screen != null && (screen instanceof GuiBeacon || screen instanceof GuiCrafting || screen instanceof GuiEnchantment || screen instanceof GuiMerchant || screen instanceof GuiRepair || screen instanceof GuiContainerCreative);
    }
}
