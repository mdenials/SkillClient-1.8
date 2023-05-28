package com.skillclient.utils;

import java.util.ArrayList;

public class Features
{
    public static ArrayList<String> ears;
    public static boolean isDonator;
    
    static {
        Features.ears = new ArrayList<String>();
        Features.isDonator = false;
    }
    
    public static boolean isdeadmau5(final String name) {
        return Features.ears.contains(name.toLowerCase());
    }
}


