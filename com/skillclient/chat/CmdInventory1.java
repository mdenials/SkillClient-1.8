package com.skillclient.chat;

import net.minecraft.entity.player.EntityPlayer;
import com.skillclient.utils.ItemStackUtil;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.IInventory;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.client.gui.inventory.GuiChest;

class CmdInventory$1 extends GuiChest {
    private final /* synthetic */ InventoryBasic val$inv;
    private final /* synthetic */ ItemStack val$skull;
    private final /* synthetic */ Entity val$e;
    
    protected void handleMouseClick(final Slot slotIn, final int slotId, final int clickedButton, final int clickType) {
    }
    
    public void updateScreen() {
        this.val$inv.clear();
        this.val$inv.func_174894_a(this.val$skull);
        this.val$inv.func_174894_a(ItemStackUtil.empty);
        this.val$inv.func_174894_a(((EntityPlayer)this.val$e).getCurrentEquippedItem());
        ItemStack[] armorInventory;
        for (int length = (armorInventory = ((EntityPlayer)this.val$e).inventory.armorInventory).length, i = 0; i < length; ++i) {
            final ItemStack item = armorInventory[i];
            this.val$inv.func_174894_a(item);
        }
    }
}
