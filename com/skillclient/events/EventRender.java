package com.skillclient.events;

import com.skillclient.events.api.events.Event;

public class EventRender implements Event
{
    private float partialTicks;
    
    public EventRender(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
}
