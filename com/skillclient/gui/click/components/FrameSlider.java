package com.skillclient.gui.click.components;

import net.minecraft.util.MathHelper;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.awt.Color;
import com.skillclient.utils.RenderGuiUtil;

public class FrameSlider extends Frame
{
    private double value;
    private double renderValue;
    private double min;
    private double max;
    private int round;
    private String postFix;
    
    public FrameSlider(final String text, final FrameTree parent, final double value, final double min, final double max, final int round) {
        this(text, parent, value, min, max, round, "");
    }
    
    public FrameSlider(final String text, final FrameTree parent, final double value, final double min, final double max, final int round, final String postFix) {
        super(text, parent);
        this.value = value;
        this.min = min;
        this.max = max;
        this.round = round;
        this.postFix = postFix;
        this.renderValue = (this.getValue() - this.getMin()) / (this.getMax() - this.getMin());
    }
    
    @Override
    public int getOwnWidth() {
        return super.getOwnWidth() + 54;
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawSquare(this.getPosX(), this.getPosX() + this.getWidth(), this.getPosY(), this.getPosY() + this.getHeight());
        this.drawHorizontalLine(this.getPosX() + this.getWidth() - 52, this.getPosX() + this.getWidth() - 2, this.getPosY() + 2);
        this.drawHorizontalLine(this.getPosX() + this.getWidth() - 52, this.getPosX() + this.getWidth() - 2, this.getPosY() + this.getHeight() - 2);
        FrameSlider.mc.fontRendererObj.drawString(String.valueOf(this.getValue()) + this.getPostFix(), this.getPosX() + this.getWidth() - 40, this.getPosY() + 2, RenderGuiUtil.isHovered(mouseX, mouseY, this) ? Color.green.getRGB() : -1);
        this.drawVerticalLine(this.getPosX() + this.getWidth() - 52 + (int)(50.0 * this.getRenderValue()), this.getPosY() + 1, this.getPosY() + this.getHeight() - 1, RenderGuiUtil.isHovered(mouseX, mouseY, this) ? -1 : Color.green.getRGB());
        FrameSlider.mc.fontRendererObj.drawString(this.getText(), this.getPosX() + 2, this.getPosY() + 2, RenderGuiUtil.isHovered(mouseX, mouseY, this) ? Color.green.getRGB() : -1);
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    public double round(final double v) {
        return new BigDecimal(v).setScale(this.round, RoundingMode.HALF_EVEN).doubleValue();
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int mouseButton, final long timeSinceLastClick) {
        this.renderValue = MathHelper.clamp_double((mouseX - this.getPosX() - this.getWidth() + 52) / 50.0, 0.0, 1.0);
        this.setValue(this.round(this.min + (this.max - this.min) * this.renderValue));
    }
    
    public double getValue() {
        return this.value;
    }
    
    public double getRenderValue() {
        return this.renderValue;
    }
    
    public double getMin() {
        return this.min;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public int getRound() {
        return this.round;
    }
    
    public String getPostFix() {
        return this.postFix;
    }
    
    public void setValue(final double value) {
        this.value = value;
    }
    
    public void setRenderValue(final double renderValue) {
        this.renderValue = renderValue;
    }
    
    public void setMin(final double min) {
        this.min = min;
    }
    
    public void setMax(final double max) {
        this.max = max;
    }
    
    public void setRound(final int round) {
        this.round = round;
    }
    
    public void setPostFix(final String postFix) {
        this.postFix = postFix;
    }
    
    @Override
    public String toString() {
        return "FrameSlider(value=" + this.getValue() + ", renderValue=" + this.getRenderValue() + ", min=" + this.getMin() + ", max=" + this.getMax() + ", round=" + this.getRound() + ", postFix=" + this.getPostFix() + ")";
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FrameSlider)) {
            return false;
        }
        final FrameSlider other = (FrameSlider)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (Double.compare(this.getValue(), other.getValue()) != 0) {
            return false;
        }
        if (Double.compare(this.getRenderValue(), other.getRenderValue()) != 0) {
            return false;
        }
        if (Double.compare(this.getMin(), other.getMin()) != 0) {
            return false;
        }
        if (Double.compare(this.getMax(), other.getMax()) != 0) {
            return false;
        }
        if (this.getRound() != other.getRound()) {
            return false;
        }
        final Object this$postFix = this.getPostFix();
        final Object other$postFix = other.getPostFix();
        if (this$postFix == null) {
            if (other$postFix == null) {
                return true;
            }
        }
        else if (this$postFix.equals(other$postFix)) {
            return true;
        }
        return false;
    }
    
    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof FrameSlider;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $value = Double.doubleToLongBits(this.getValue());
        result = result * 59 + (int)($value ^ $value >>> 32);
        final long $renderValue = Double.doubleToLongBits(this.getRenderValue());
        result = result * 59 + (int)($renderValue ^ $renderValue >>> 32);
        final long $min = Double.doubleToLongBits(this.getMin());
        result = result * 59 + (int)($min ^ $min >>> 32);
        final long $max = Double.doubleToLongBits(this.getMax());
        result = result * 59 + (int)($max ^ $max >>> 32);
        result = result * 59 + this.getRound();
        final Object $postFix = this.getPostFix();
        result = result * 59 + (($postFix == null) ? 43 : $postFix.hashCode());
        return result;
    }
}


