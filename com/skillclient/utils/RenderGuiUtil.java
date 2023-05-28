package com.skillclient.utils;

import com.skillclient.gui.click.ThemeManager;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.Minecraft;
import com.skillclient.gui.click.components.Frame;

public final class RenderGuiUtil
{
    public static boolean isHovered(final int x, final int y, final int width, final int height, final int mouseX, final int mouseY) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
    
    public static boolean isHovered(final int mouseX, final int mouseY, final Frame frame) {
        return isHovered(frame.getPosX(), frame.getPosY(), frame.getWidth(), frame.getHeight(), mouseX, mouseY);
    }
    
    public static boolean isDraggingHovered(final int mouseX, final int mouseY, final Frame frame) {
        return isHovered(frame.getPosX(), frame.getPosY(), frame.getWidth(), Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2, mouseX, mouseY);
    }
    
    public static void drawRect(final int left, final int top, final int right, final int bottom, final int color) {
        Gui.drawRect(left, top, right, bottom, color);
    }
    
    public static void drawVerticalLine(final int x, int startY, int endY, final int color) {
        if (endY < startY) {
            final int i = startY;
            startY = endY;
            endY = i;
        }
        drawRect(x, startY + 1, x + 1, endY, color);
    }
    
    public static void drawHorizontalLine(int startX, int endX, final int y, final int color) {
        if (endX < startX) {
            final int i = startX;
            startX = endX;
            endX = i;
        }
        drawRect(startX, y, endX + 1, y + 1, color);
    }
    
    public static void drawVerticalLine(final int x, final int startY, final int endY) {
        drawVerticalLine(x, startY, endY, ThemeManager.getColor().getRGB());
    }
    
    public static void drawHorizontalLine(final int startX, final int endX, final int y) {
        drawHorizontalLine(startX, endX, y, ThemeManager.getColor().getRGB());
    }
}

