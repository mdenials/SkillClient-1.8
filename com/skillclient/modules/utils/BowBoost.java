package com.skillclient.modules.util;

import com.skillclient.events.api.EventTarget;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.item.ItemBow;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class BowBoost extends Module
{
    public BowBoost() {
        super("BowBoost", Register.Category.UTIL, "Craszy Speed-Bypass");
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (BowBoost.mc.thePlayer.getHeldItem() != null && BowBoost.mc.thePlayer.getHeldItem().getItem() instanceof ItemBow) {
            BowBoost.mc.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(BowBoost.mc.thePlayer.getHeldItem()));
        }
    }
}

