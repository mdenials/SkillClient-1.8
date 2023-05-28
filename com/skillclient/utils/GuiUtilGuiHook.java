package com.skillclient.utils;

import net.minecraft.client.gui.Gui;

public static class GuiHook extends Gui
{
    protected void drawGradientRect(final int left, final int top, final int right, final int bottom, final int startColor, final int endColor) {
        super.drawGradientRect(left, top, right, bottom, startColor, endColor);
    }
}

