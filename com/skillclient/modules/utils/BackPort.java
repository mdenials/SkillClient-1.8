package com.skillclient.modules.util;

import com.skillclient.events.api.EventTarget;
import com.skillclient.events.EventUpdate;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class BackPort extends Module
{
    static double x;
    static double y;
    static double z;
    
    static {
        BackPort.x = 0.0;
        BackPort.y = 0.0;
        BackPort.z = 0.0;
    }
    
    public BackPort() {
        super("BackPort", Register.Category.UTIL, "Set you to your old Position");
        this.modes = 1;
    }
    
    public void onEnable() {
        BackPort.mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(BackPort.x, BackPort.y + 5.0, BackPort.z, true));
        super.onEnable();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (BackPort.mc.thePlayer != null && BackPort.mc.thePlayer.onGround) {
            BackPort.x = BackPort.mc.thePlayer.prevChasingPosX;
            BackPort.y = BackPort.mc.thePlayer.prevChasingPosY;
            BackPort.z = BackPort.mc.thePlayer.prevChasingPosZ;
        }
    }
}

