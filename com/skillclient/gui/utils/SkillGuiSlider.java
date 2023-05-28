package com.skillclient.gui.utils;

import com.skillclient.utils.RenderGuiUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;

public class SkillGuiSlider extends GuiButton
{
    public double sliderValue;
    public String prefix;
    public boolean dragging;
    public boolean showDecimal;
    public double minValue;
    public double maxValue;
    public int precision;
    public SkillGuiSlider.ISlider parent;
    public boolean drawString;
    
    public SkillGuiSlider(final int id, final int xPos, final int yPos, final int width, final int height, final String prefix, final double minVal, final double maxVal, final double currentVal, final boolean showDec, final boolean drawStr, final SkillGuiSlider.ISlider par) {
        super(id, xPos, yPos, width, height, prefix);
        this.sliderValue = 1.0;
        this.prefix = "";
        this.dragging = false;
        this.showDecimal = true;
        this.minValue = 0.0;
        this.maxValue = 5.0;
        this.precision = 1;
        this.parent = null;
        this.drawString = true;
        this.minValue = minVal;
        this.maxValue = maxVal;
        this.sliderValue = (currentVal - this.minValue) / (this.maxValue - this.minValue);
        this.prefix = prefix;
        this.parent = par;
        this.showDecimal = showDec;
        if (this.showDecimal) {
            final String val = Double.toString(this.sliderValue * (this.maxValue - this.minValue) + this.minValue);
            this.precision = Math.min(val.substring(val.indexOf(".") + 1).length(), 4);
        }
        else {
            final String val = Integer.toString((int)Math.round(this.sliderValue * (this.maxValue - this.minValue) + this.minValue));
            this.precision = 0;
        }
        this.displayString = prefix;
        if (!(this.drawString = drawStr)) {
            this.displayString = "";
        }
    }
    
    public SkillGuiSlider(final int id, final int xPos, final int yPos, final int width, final int height, final String prefix, final String suf, final double minVal, final double maxVal, final double currentVal, final boolean showDec, final boolean drawStr) {
        this(id, xPos, yPos, width, height, prefix, minVal, maxVal, currentVal, showDec, drawStr, null);
    }
    
    public SkillGuiSlider(final int id, final int xPos, final int yPos, final String displayStr, final double minVal, final double maxVal, final double currentVal, final SkillGuiSlider.ISlider par) {
        this(id, xPos, yPos, 150, 20, displayStr, minVal, maxVal, currentVal, true, true, par);
    }
    
    public int getHoverState(final boolean par1) {
        return 0;
    }
    
    public double getValue() {
        return this.sliderValue * (this.maxValue - this.minValue) + this.minValue;
    }
    
    public int getValueInt() {
        return (int)Math.round(this.sliderValue * (this.maxValue - this.minValue) + this.minValue);
    }
    
    @Override
    protected void mouseDragged(final Minecraft par1Minecraft, final int par2, final int par3) {
        if (this.visible) {
            if (this.dragging) {
                this.sliderValue = (par2 - (this.xPosition + 4)) / (float)(this.width - 8);
                this.updateSlider();
            }
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            RenderGuiUtil.drawVerticalLine(this.xPosition + (int)(this.sliderValue * (this.width - 8)) + 7, this.yPosition + 1, this.yPosition + this.height - 2);
            RenderGuiUtil.drawVerticalLine(this.xPosition + (int)(this.sliderValue * (this.width - 8)), this.yPosition + 1, this.yPosition + this.height - 2);
        }
    }
    
    @Override
    public boolean mousePressed(final Minecraft par1Minecraft, final int par2, final int par3) {
        if (super.mousePressed(par1Minecraft, par2, par3)) {
            this.sliderValue = (par2 - (this.xPosition + 4)) / (float)(this.width - 8);
            this.updateSlider();
            return this.dragging = true;
        }
        return false;
    }
    
    @Override
    public void mouseReleased(final int par1, final int par2) {
        this.dragging = false;
    }
    
    public void setValue(final double d) {
        this.sliderValue = (d - this.minValue) / (this.maxValue - this.minValue);
    }
    
    public void updateSlider() {
        if (this.sliderValue < 0.0) {
            this.sliderValue = 0.0;
        }
        if (this.sliderValue > 1.0) {
            this.sliderValue = 1.0;
        }
        String val;
        if (this.showDecimal) {
            val = Double.toString(this.sliderValue * (this.maxValue - this.minValue) + this.minValue);
            if (val.substring(val.indexOf(".") + 1).length() > this.precision) {
                val = val.substring(0, val.indexOf(".") + this.precision + 1);
                if (val.endsWith(".")) {
                    val = val.substring(0, val.indexOf(".") + this.precision);
                }
            }
            else {
                while (val.substring(val.indexOf(".") + 1).length() < this.precision) {
                    val = String.valueOf(val) + "0";
                }
            }
        }
        else {
            val = Integer.toString((int)Math.round(this.sliderValue * (this.maxValue - this.minValue) + this.minValue));
        }
        this.setValue(Float.parseFloat(val));
        if (this.drawString) {
            this.displayString = String.valueOf(this.prefix) + ": " + val;
        }
        if (this.parent != null) {
            this.parent.onChangeSliderValue(this);
        }
    }
}

