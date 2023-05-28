package com.skillclient.events.api;

public final class EventAPI
{
    public static final String VERSION;
    public static final String[] AUTHORS;
    
    static {
        VERSION = String.format("%s-%s", "0.7", "beta");
        AUTHORS = new String[] { "DarkMagician6" };
    }
    
    private EventAPI() {
    }
}


