package com.skillclient.events;

import net.minecraft.network.Packet;
import com.skillclient.events.api.events.callables.EventCancellable;

public class EventPacketReceived extends EventCancellable
{
    private Packet packet;
    
    public EventPacketReceived(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public void setPacket(final Packet packet) {
        this.packet = packet;
    }
}
