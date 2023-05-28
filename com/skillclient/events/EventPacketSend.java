package com.skillclient.events;

import net.minecraft.network.Packet;
import com.skillclient.events.api.events.callables.EventCancellable;

public class EventPacketSend extends EventCancellable
{
    private Packet packet;
    
    public EventPacketSend(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public void setPacket(final Packet packet) {
        this.packet = packet;
    }
}

