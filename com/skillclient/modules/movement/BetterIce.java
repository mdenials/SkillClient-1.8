package com.skillclient.modules.movement;

import com.skillclient.events.api.EventTarget;
import com.skillclient.events.EventUpdate;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class BetterIce extends Module
{
    ValueNumber speed;
    
    public BetterIce() {
        super("BetterIce", Register.Category.MOVEMENT, "Faster on ice");
        this.speed = new ValueNumber("Speed", (Module)this, 100.0, 20.0, 40.0, 1);
    }
    
    public void onDisable() {
        final Block ice = Blocks.ice;
        final Block packed_ice = Blocks.packed_ice;
        final float n = 0.98f;
        packed_ice.slipperiness = n;
        ice.slipperiness = n;
        super.onDisable();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        final Block ice = Blocks.ice;
        final Block packed_ice = Blocks.packed_ice;
        final float n = (float)((double)this.speed.getValue() / 100.0);
        packed_ice.slipperiness = n;
        ice.slipperiness = n;
    }
}
