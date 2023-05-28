package com.skillclient.CreativeTabs;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

class CreativeSlot extends Slot
{
    private final Slot slot;
    
    public CreativeSlot(final Slot p_i46313_2_, final int p_i46313_3_) {
        super(p_i46313_2_.inventory, p_i46313_3_, 0, 0);
        this.slot = p_i46313_2_;
    }
    
    public void onPickupFromSlot(final EntityPlayer playerIn, final ItemStack stack) {
        this.slot.onPickupFromSlot(playerIn, stack);
    }
    
    public boolean isItemValid(final ItemStack stack) {
        return this.slot.isItemValid(stack);
    }
    
    public ItemStack getStack() {
        return this.slot.getStack();
    }
    
    public boolean getHasStack() {
        return this.slot.getHasStack();
    }
    
    public void putStack(final ItemStack stack) {
        this.slot.putStack(stack);
    }
    
    public void onSlotChanged() {
        this.slot.onSlotChanged();
    }
    
    public int getSlotStackLimit() {
        return this.slot.getSlotStackLimit();
    }
    
    public int getItemStackLimit(final ItemStack stack) {
        return this.slot.getItemStackLimit(stack);
    }
    
    public String getSlotTexture() {
        return this.slot.getSlotTexture();
    }
    
    public ItemStack decrStackSize(final int amount) {
        return this.slot.decrStackSize(amount);
    }
    
    public boolean isHere(final IInventory inv, final int slotIn) {
        return this.slot.isHere(inv, slotIn);
    }
}

