package com.skillclient.wrapper;

import com.skillclient.modules.world.CivBreak;
import com.skillclient.modules.combat.BowAimBot;
import com.skillclient.modules.player.ChestAura;
import com.skillclient.modules.world.ScaffoldWalk;
import com.skillclient.modules.world.Tower;
import com.skillclient.main.Register;
import com.skillclient.modules.combat.KillAura;
import com.skillclient.events.api.EventTarget;
import com.skillclient.events.api.events.Event;
import com.skillclient.events.api.EventManager;
import com.skillclient.events.EventRotation;
import net.minecraft.network.play.client.C03PacketPlayer;
import com.skillclient.events.EventPacketSend;

public class RotationHandler
{
    @EventTarget(4)
    public void onMove(final EventPacketSend event) {
        if (event.getPacket() instanceof C03PacketPlayer) {
            final C03PacketPlayer packet = (C03PacketPlayer)event.getPacket();
            if (packet.getRotating()) {
                final EventRotation eventRot = new EventRotation(packet.getPitch(), packet.getYaw(), true);
                EventManager.call((Event)eventRot);
                packet.setPitch(eventRot.getPitch());
                packet.setYaw(eventRot.getYaw());
            }
        }
    }
    
    public static boolean sendEveryPacket() {
        return ((KillAura)Register.getModule((Class)KillAura.class)).isActive() || ((Tower)Register.getModule((Class)Tower.class)).isActive() || ((ScaffoldWalk)Register.getModule((Class)ScaffoldWalk.class)).isActive() || ((ChestAura)Register.getModule((Class)ChestAura.class)).isActive() || ((BowAimBot)Register.getModule((Class)BowAimBot.class)).isActive() || ((CivBreak)Register.getModule((Class)CivBreak.class)).isActive();
    }
}

