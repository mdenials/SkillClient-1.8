package com.skillclient.utils;

public class GuiUtil
{
    public static void drawGradientRect(final int x, final int y, final int w, final int h, final int colour1, final int colour2) {
        new GuiUtil.GuiHook().drawGradientRect(x, y, x + w, y + h, colour1, colour2);
    }
    
    public static void drawTooltipBox(final int x, final int y, final int w, final int h) {
        final int bg = -267386864;
        drawGradientRect(x + 1, y, w - 1, 1, bg, bg);
        drawGradientRect(x + 1, y + h, w - 1, 1, bg, bg);
        drawGradientRect(x + 1, y + 1, w - 1, h - 1, bg, bg);
        drawGradientRect(x, y + 1, 1, h - 1, bg, bg);
        drawGradientRect(x + w, y + 1, 1, h - 1, bg, bg);
        final int grad1 = 1347420415;
        final int grad2 = 1344798847;
        drawGradientRect(x + 1, y + 2, 1, h - 3, grad1, grad2);
        drawGradientRect(x + w - 1, y + 2, 1, h - 3, grad1, grad2);
        drawGradientRect(x + 1, y + 1, w - 1, 1, grad1, grad1);
        drawGradientRect(x + 1, y + h - 1, w - 1, 1, grad2, grad2);
    }
}

