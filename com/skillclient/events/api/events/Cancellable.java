package com.skillclient.events.api.events;

public interface Cancellable
{
    boolean isCancelled();
    
    void setCancelled(final boolean p0);
}


