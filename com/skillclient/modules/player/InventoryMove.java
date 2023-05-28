package com.skillclient.modules.player;

import net.minecraft.client.gui.GuiChat;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class InventoryMove extends Module
{
    public ValueBoolean sprint;
    
    public InventoryMove() {
        super("InventoryMove", Register.Category.PLAYER, "Walk in Inventory");
        this.sprint = new ValueBoolean("Sprint", (Module)this, true);
    }
    
    public boolean isActive() {
        return (InventoryMove.mc.currentScreen == null || !(InventoryMove.mc.currentScreen instanceof GuiChat)) && super.isActive();
    }
}

