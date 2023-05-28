package com.skillclient.utils;

public enum VersionState
{
    up_to_date("up_to_date", 0, "No Updates"), 
    can_update("can_update", 1, "New Version available!"), 
    update("update", 2, "New Version available!"), 
    shutdown("shutdown", 3, "http://skillclient.com/    https://youtube.com/MCmodding4K    https://discord.gg/d5HBwVZ"), 
    unknown("unknown", 4, "Maybe new Update?");
    
    public String text;
    
    private VersionState(final String name, final int ordinal, final String text) {
        this.text = text;
    }
}
