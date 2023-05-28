package com.skillclient.events.api.events.callables;

import com.skillclient.events.api.events.Event;
import com.skillclient.events.api.events.Cancellable;

public abstract class EventCancellable implements Cancellable, Event
{
    private boolean cancelled;
    
    protected EventCancellable() {
    }
    
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    @Override
    public void setCancelled(final boolean state) {
        this.cancelled = state;
    }
}
