package com.skillclient.gui.click;

import java.awt.Color;

public class ThemeManager
{
    public static int r;
    public static int g;
    public static int b;
    
    static {
        ThemeManager.r = 64;
        ThemeManager.g = 192;
        ThemeManager.b = 64;
    }
    
    public static Color getColor() {
        return new Color(ThemeManager.r, ThemeManager.g, ThemeManager.b);
    }
    
    public static float getLineWidth() {
        return 4.0f;
    }
}
