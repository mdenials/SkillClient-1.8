package com.skillclient.misc;

public enum Type
{
    NUMBER("NUMBER", 0), 
    TEXT("TEXT", 1), 
    BOOLEAN("BOOLEAN", 2), 
    ARRAY("ARRAY", 3), 
    KEY("KEY", 4), 
    OTHER("OTHER", 5);
    
    private Type(final String name, final int ordinal) {
    }
}
