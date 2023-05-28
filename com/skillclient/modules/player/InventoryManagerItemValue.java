package com.skillclient.modules.player;

public enum ItemValue
{
    Good("Good", 0), 
    Bad("Bad", 1), 
    IDK("IDK", 2);
    
    private ItemValue(final String name, final int ordinal) {
    }
    
    public static ItemValue fromBoolean(final boolean b) {
        return b ? ItemValue.Good : ItemValue.Bad;
    }
}


