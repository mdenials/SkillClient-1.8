package com.skillclient.events.api.events.callables;

import com.skillclient.events.api.events.Typed;
import com.skillclient.events.api.events.Event;

public abstract class EventTyped implements Event, Typed
{
    private final byte type;
    
    protected EventTyped(final byte eventType) {
        this.type = eventType;
    }
    
    @Override
    public byte getType() {
        return this.type;
    }
}

