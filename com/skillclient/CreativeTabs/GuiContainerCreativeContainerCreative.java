package com.skillclient.CreativeTabs;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import java.util.List;
import net.minecraft.inventory.Container;

static class ContainerCreative extends Container
{
    public List<ItemStack> itemList;
    
    public ContainerCreative(final EntityPlayer p_i1086_1_) {
        this.itemList = (List<ItemStack>)Lists.newArrayList();
        final InventoryPlayer inventoryplayer = p_i1086_1_.inventory;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)GuiContainerCreative.access$0(), i * 9 + j, 9 + j * 18, 18 + i * 18));
            }
        }
        for (int k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot((IInventory)inventoryplayer, k, 9 + k * 18, 112));
        }
        this.scrollTo(0.0f);
    }
    
    public boolean canInteractWith(final EntityPlayer playerIn) {
        return true;
    }
    
    public void scrollTo(final float p_148329_1_) {
        final int i = (this.itemList.size() + 9 - 1) / 9 - 5;
        int j = (int)(p_148329_1_ * i + 0.5);
        if (j < 0) {
            j = 0;
        }
        for (int k = 0; k < 5; ++k) {
            for (int l = 0; l < 9; ++l) {
                final int i2 = l + (k + j) * 9;
                if (i2 >= 0 && i2 < this.itemList.size()) {
                    GuiContainerCreative.access$0().setInventorySlotContents(l + k * 9, (ItemStack)this.itemList.get(i2));
                }
                else {
                    GuiContainerCreative.access$0().setInventorySlotContents(l + k * 9, (ItemStack)null);
                }
            }
        }
    }
    
    public boolean func_148328_e() {
        return this.itemList.size() > 45;
    }
    
    protected void retrySlotClick(final int slotId, final int clickedButton, final boolean mode, final EntityPlayer playerIn) {
    }
    
    public ItemStack transferStackInSlot(final EntityPlayer playerIn, final int index) {
        if (index >= this.inventorySlots.size() - 9 && index < this.inventorySlots.size()) {
            final Slot slot = this.inventorySlots.get(index);
            if (slot != null && slot.getHasStack()) {
                slot.putStack((ItemStack)null);
            }
        }
        return null;
    }
    
    public boolean canMergeSlot(final ItemStack stack, final Slot p_94530_2_) {
        return p_94530_2_.yDisplayPosition > 90;
    }
    
    public boolean canDragIntoSlot(final Slot p_94531_1_) {
        return p_94531_1_.inventory instanceof InventoryPlayer || (p_94531_1_.yDisplayPosition > 90 && p_94531_1_.xDisplayPosition <= 162);
    }
}

