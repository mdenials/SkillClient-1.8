package com.skillclient.modules.combat;

import net.minecraft.network.play.client.C03PacketPlayer;
import com.skillclient.events.EventPacketSend;
import com.skillclient.events.api.EventTarget;
import com.skillclient.events.EventPacketReceived;
import com.skillclient.main.Register;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Velocity extends Module
{
    ValueNumber value_xz;
    ValueNumber value_y;
    ValueBoolean onlyPositiveY;
    ValueBoolean serverside;
    S12PacketEntityVelocity last;
    
    public Velocity() {
        super("Velocity", Register.Category.COMBAT, "Reduces KnockBack");
        this.value_xz = new ValueNumber("KnockBack xz", (Module)this, 2.0, -1.0, 0.0, 2);
        this.value_y = new ValueNumber("KnockBack y", (Module)this, 2.0, 0.0, 0.0, 2);
        this.onlyPositiveY = new ValueBoolean("OnlyPositive Y", (Module)this, true);
        this.serverside = new ValueBoolean("Serverside", (Module)this, false);
    }
    
    @EventTarget
    public void onRotate(final EventPacketReceived event) {
        if (event.getPacket() instanceof S12PacketEntityVelocity) {
            final S12PacketEntityVelocity p = (S12PacketEntityVelocity)event.getPacket();
            if (p.getEntityID() == Velocity.mc.thePlayer.getEntityId()) {
                if (this.serverside.getValue()) {
                    this.last = p;
                    event.setCancelled(true);
                }
                else {
                    final S12PacketEntityVelocity s12PacketEntityVelocity = p;
                    s12PacketEntityVelocity.motionX *= (int)(double)this.value_xz.getValue();
                    final S12PacketEntityVelocity s12PacketEntityVelocity2 = p;
                    s12PacketEntityVelocity2.motionZ *= (int)(double)this.value_xz.getValue();
                    final S12PacketEntityVelocity s12PacketEntityVelocity3 = p;
                    s12PacketEntityVelocity3.motionY *= (int)(double)this.value_y.getValue();
                    if (this.onlyPositiveY.getValue()) {
                        p.motionY = Math.abs(p.motionY);
                    }
                }
            }
        }
    }
    
    @EventTarget
    public void onRotate(final EventPacketSend event) {
        if (event.getPacket() instanceof C03PacketPlayer && this.last != null) {
            final C03PacketPlayer p = (C03PacketPlayer)event.getPacket();
            p.setX(p.getX() + this.last.motionX / 8000.0);
            p.setY(p.getY() + this.last.motionY / 8000.0);
            p.setZ(p.getZ() + this.last.motionZ / 8000.0);
            this.last = null;
        }
    }
}
