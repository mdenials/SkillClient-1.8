package com.skillclient.gui.utils;

import java.util.Iterator;
import java.util.ArrayList;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.gui.FontRenderer;
import java.util.List;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.GlStateManager;

public class GuiUtils
{
    public static final String UNDO_CHAR = "\u21b6";
    public static final String RESET_CHAR = "\u2604";
    public static final String VALID = "\u2714";
    public static final String INVALID = "\u2715";
    public static int[] colorCodes;
    
    static {
        GuiUtils.colorCodes = new int[] { 0, 170, 43520, 43690, 11141120, 11141290, 16755200, 11184810, 5592405, 5592575, 5635925, 5636095, 16733525, 16733695, 16777045, 16777215, 0, 42, 10752, 10794, 2752512, 2752554, 2763264, 2763306, 1381653, 1381695, 1392405, 1392447, 4134165, 4134207, 4144917, 4144959 };
    }
    
    public static void drawContinuousTexturedBox(final int x, final int y, final int u, final int v, final int width, final int height, final int textureWidth, final int textureHeight, final int borderSize, final float zLevel) {
        drawContinuousTexturedBox(x, y, u, v, width, height, textureWidth, textureHeight, borderSize, borderSize, borderSize, borderSize, zLevel);
    }
    
    public static void drawContinuousTexturedBox(final int x, final int y, final int u, final int v, final int width, final int height, final int textureWidth, final int textureHeight, final int topBorder, final int bottomBorder, final int leftBorder, final int rightBorder, final float zLevel) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        final int fillerWidth = textureWidth - leftBorder - rightBorder;
        final int fillerHeight = textureHeight - topBorder - bottomBorder;
        final int canvasWidth = width - leftBorder - rightBorder;
        final int canvasHeight = height - topBorder - bottomBorder;
        final int xPasses = canvasWidth / fillerWidth;
        final int remainderWidth = canvasWidth % fillerWidth;
        final int yPasses = canvasHeight / fillerHeight;
        final int remainderHeight = canvasHeight % fillerHeight;
        drawTexturedModalRect(x, y, u, v, leftBorder, topBorder, zLevel);
        drawTexturedModalRect(x + leftBorder + canvasWidth, y, u + leftBorder + fillerWidth, v, rightBorder, topBorder, zLevel);
        drawTexturedModalRect(x, y + topBorder + canvasHeight, u, v + topBorder + fillerHeight, leftBorder, bottomBorder, zLevel);
        drawTexturedModalRect(x + leftBorder + canvasWidth, y + topBorder + canvasHeight, u + leftBorder + fillerWidth, v + topBorder + fillerHeight, rightBorder, bottomBorder, zLevel);
        for (int i = 0; i < xPasses + ((remainderWidth > 0) ? 1 : 0); ++i) {
            drawTexturedModalRect(x + leftBorder + i * fillerWidth, y, u + leftBorder, v, (i == xPasses) ? remainderWidth : fillerWidth, topBorder, zLevel);
            drawTexturedModalRect(x + leftBorder + i * fillerWidth, y + topBorder + canvasHeight, u + leftBorder, v + topBorder + fillerHeight, (i == xPasses) ? remainderWidth : fillerWidth, bottomBorder, zLevel);
            for (int j = 0; j < yPasses + ((remainderHeight > 0) ? 1 : 0); ++j) {
                drawTexturedModalRect(x + leftBorder + i * fillerWidth, y + topBorder + j * fillerHeight, u + leftBorder, v + topBorder, (i == xPasses) ? remainderWidth : fillerWidth, (j == yPasses) ? remainderHeight : fillerHeight, zLevel);
            }
        }
        for (int k = 0; k < yPasses + ((remainderHeight > 0) ? 1 : 0); ++k) {
            drawTexturedModalRect(x, y + topBorder + k * fillerHeight, u, v + topBorder, leftBorder, (k == yPasses) ? remainderHeight : fillerHeight, zLevel);
            drawTexturedModalRect(x + leftBorder + canvasWidth, y + topBorder + k * fillerHeight, u + leftBorder + fillerWidth, v + topBorder, rightBorder, (k == yPasses) ? remainderHeight : fillerHeight, zLevel);
        }
    }
    
    public static void drawContinuousTexturedBox(final ResourceLocation res, final int x, final int y, final int u, final int v, final int width, final int height, final int textureWidth, final int textureHeight, final int borderSize, final float zLevel) {
        drawContinuousTexturedBox(res, x, y, u, v, width, height, textureWidth, textureHeight, borderSize, borderSize, borderSize, borderSize, zLevel);
    }
    
    public static void drawContinuousTexturedBox(final ResourceLocation res, final int x, final int y, final int u, final int v, final int width, final int height, final int textureWidth, final int textureHeight, final int topBorder, final int bottomBorder, final int leftBorder, final int rightBorder, final float zLevel) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(res);
        drawContinuousTexturedBox(x, y, u, v, width, height, textureWidth, textureHeight, topBorder, bottomBorder, leftBorder, rightBorder, zLevel);
    }
    
    public static void drawGradientRect(final int zLevel, final int left, final int top, final int right, final int bottom, final int startColor, final int endColor) {
        final float startAlpha = (startColor >> 24 & 0xFF) / 255.0f;
        final float startRed = (startColor >> 16 & 0xFF) / 255.0f;
        final float startGreen = (startColor >> 8 & 0xFF) / 255.0f;
        final float startBlue = (startColor & 0xFF) / 255.0f;
        final float endAlpha = (endColor >> 24 & 0xFF) / 255.0f;
        final float endRed = (endColor >> 16 & 0xFF) / 255.0f;
        final float endGreen = (endColor >> 8 & 0xFF) / 255.0f;
        final float endBlue = (endColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer vertexbuffer = tessellator.getWorldRenderer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos((double)right, (double)top, (double)zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
        vertexbuffer.pos((double)left, (double)top, (double)zLevel).color(startRed, startGreen, startBlue, startAlpha).endVertex();
        vertexbuffer.pos((double)left, (double)bottom, (double)zLevel).color(endRed, endGreen, endBlue, endAlpha).endVertex();
        vertexbuffer.pos((double)right, (double)bottom, (double)zLevel).color(endRed, endGreen, endBlue, endAlpha).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawHoveringText(List<String> textLines, final int mouseX, final int mouseY, final int screenWidth, final int screenHeight, final int maxTextWidth, final FontRenderer font) {
        if (!textLines.isEmpty()) {
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int tooltipTextWidth = 0;
            for (final String textLine : textLines) {
                final int textLineWidth = font.getStringWidth(textLine);
                if (textLineWidth > tooltipTextWidth) {
                    tooltipTextWidth = textLineWidth;
                }
            }
            boolean needsWrap = false;
            int titleLinesCount = 1;
            int tooltipX = mouseX + 12;
            if (tooltipX + tooltipTextWidth + 4 > screenWidth) {
                tooltipX = mouseX - 16 - tooltipTextWidth;
                if (tooltipX < 4) {
                    if (mouseX > screenWidth / 2) {
                        tooltipTextWidth = mouseX - 12 - 8;
                    }
                    else {
                        tooltipTextWidth = screenWidth - 16 - mouseX;
                    }
                    needsWrap = true;
                }
            }
            if (maxTextWidth > 0 && tooltipTextWidth > maxTextWidth) {
                tooltipTextWidth = maxTextWidth;
                needsWrap = true;
            }
            if (needsWrap) {
                int wrappedTooltipWidth = 0;
                final List<String> wrappedTextLines = new ArrayList<String>();
                for (int i = 0; i < textLines.size(); ++i) {
                    final String textLine2 = textLines.get(i);
                    final List<String> wrappedLine = (List<String>)font.listFormattedStringToWidth(textLine2, tooltipTextWidth);
                    if (i == 0) {
                        titleLinesCount = wrappedLine.size();
                    }
                    for (final String line : wrappedLine) {
                        final int lineWidth = font.getStringWidth(line);
                        if (lineWidth > wrappedTooltipWidth) {
                            wrappedTooltipWidth = lineWidth;
                        }
                        wrappedTextLines.add(line);
                    }
                }
                tooltipTextWidth = wrappedTooltipWidth;
                textLines = wrappedTextLines;
                if (mouseX > screenWidth / 2) {
                    tooltipX = mouseX - 16 - tooltipTextWidth;
                }
                else {
                    tooltipX = mouseX + 12;
                }
            }
            int tooltipY = mouseY - 12;
            int tooltipHeight = 8;
            if (textLines.size() > 1) {
                tooltipHeight += (textLines.size() - 1) * 10;
                if (textLines.size() > titleLinesCount) {
                    tooltipHeight += 2;
                }
            }
            if (tooltipY + tooltipHeight + 6 > screenHeight) {
                tooltipY = screenHeight - tooltipHeight - 6;
            }
            final int zLevel = 300;
            final int backgroundColor = -267386864;
            drawGradientRect(300, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3, tooltipY - 3, -267386864, -267386864);
            drawGradientRect(300, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, -267386864, -267386864);
            drawGradientRect(300, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, -267386864, -267386864);
            drawGradientRect(300, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, -267386864, -267386864);
            drawGradientRect(300, tooltipX + tooltipTextWidth + 3, tooltipY - 3, tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, -267386864, -267386864);
            final int borderColorStart = 1347420415;
            final int borderColorEnd = 1344798847;
            drawGradientRect(300, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, 1347420415, 1344798847);
            drawGradientRect(300, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, 1347420415, 1344798847);
            drawGradientRect(300, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY - 3 + 1, 1347420415, 1347420415);
            drawGradientRect(300, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, 1344798847, 1344798847);
            for (int lineNumber = 0; lineNumber < textLines.size(); ++lineNumber) {
                final String line2 = textLines.get(lineNumber);
                font.drawStringWithShadow(line2, (float)tooltipX, (float)tooltipY, -1);
                if (lineNumber + 1 == titleLinesCount) {
                    tooltipY += 2;
                }
                tooltipY += 10;
            }
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
        }
    }
    
    public static void drawTexturedModalRect(final int x, final int y, final int u, final int v, final int width, final int height, final float zLevel) {
        final float uScale = 0.00390625f;
        final float vScale = 0.00390625f;
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer wr = tessellator.getWorldRenderer();
        wr.begin(7, DefaultVertexFormats.POSITION_TEX);
        wr.pos((double)x, (double)(y + height), (double)zLevel).tex((double)(u * uScale), (double)((v + height) * vScale)).endVertex();
        wr.pos((double)(x + width), (double)(y + height), (double)zLevel).tex((double)((u + width) * uScale), (double)((v + height) * vScale)).endVertex();
        wr.pos((double)(x + width), (double)y, (double)zLevel).tex((double)((u + width) * uScale), (double)(v * vScale)).endVertex();
        wr.pos((double)x, (double)y, (double)zLevel).tex((double)(u * uScale), (double)(v * vScale)).endVertex();
        tessellator.draw();
    }
    
    public static int getColorCode(final char c, final boolean isLighter) {
        return GuiUtils.colorCodes[isLighter ? "0123456789abcdef".indexOf(c) : ("0123456789abcdef".indexOf(c) + 16)];
    }
}

