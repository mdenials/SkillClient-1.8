package com.skillclient.gui.accountManager;

public enum AccountType
{
    CRACKED("CRACKED", 0, "cracked"), 
    SESSION("SESSION", 1, "Session"), 
    MOJANG("MOJANG", 2, "Online"), 
    MCLeaks("MCLeaks", 3, "MCLeaks");
    
    private String name;
    
    private AccountType(final String name2, final int ordinal, final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}

