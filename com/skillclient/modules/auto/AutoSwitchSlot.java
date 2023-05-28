package com.skillclient.modules.auto;

import com.skillclient.events.api.EventTarget;
import net.minecraft.entity.player.InventoryPlayer;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class AutoSwitchSlot extends Module
{
    public AutoSwitchSlot() {
        super("AutoSwitchSlot", Register.Category.AUTO, "Automatically switches Slot");
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (((AutoEat)Register.getModule((Class)AutoEat.class)).isActive() || ((AutoTool)Register.getModule((Class)AutoTool.class)).isActive()) {
            return;
        }
        if (AutoSwitchSlot.mc.thePlayer.inventory.currentItem == 8) {
            AutoSwitchSlot.mc.thePlayer.inventory.currentItem = 0;
        }
        else {
            final InventoryPlayer inventory = AutoSwitchSlot.mc.thePlayer.inventory;
            ++inventory.currentItem;
        }
    }
}


