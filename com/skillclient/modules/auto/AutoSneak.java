package com.skillclient.modules.auto;

import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class AutoSneak extends Module
{
    public AutoSneak() {
        super("AutoSneak", Register.Category.AUTO, "Automatically sneaks (serverside only)");
    }
    
    public void onEnable() {
        super.onEnable();
        AutoSneak.mc.getNetHandler().addToSendQueue((Packet)new C0BPacketEntityAction((Entity)AutoSneak.mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
    }
}
