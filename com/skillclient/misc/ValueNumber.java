package com.skillclient.misc;

import com.skillclient.gui.utils.GuiButton;
import com.skillclient.gui.utils.SkillGuiSlider;

public class ValueNumber extends Value<Double, SkillGuiSlider>
{
    private double min;
    private double max;
    private int round;
    private String postFix;
    
    public ValueNumber(final String name, final String postFix, final Module modul, final double max, final double min, final double value, final int round) {
        super(name, modul, Type.NUMBER);
        this.postFix = postFix;
        this.min = min;
        this.max = max;
        ((Value<Double, D>)this).setValue(value);
        this.round = round;
    }
    
    public ValueNumber(final String name, final Module modul, final double max, final double min, final double value, final int round) {
        this(name, "", modul, max, min, value, round);
    }
    
    @Override
    public SkillGuiSlider getGui() {
        final SkillGuiSlider s = (SkillGuiSlider)new ValueNumber.ValueNumber$1(this, 0, 0, 0, 0, 0, this.name, this.postFix, this.min, this.max, (double)((Value<Double, D>)this).getValue(), true, true);
        s.precision = this.round;
        s.updateSlider();
        return s;
    }
    
    @Override
    public void action(final SkillGuiSlider button) {
        ((Value<Double, D>)this).setValue(button.getValue());
        button.setValue((double)((Value<Double, D>)this).getValue());
    }
    
    @Override
    public String getDisplayname() {
        return this.name;
    }
    
    @Override
    public void loadValue(final String s) {
        try {
            ((Value<Double, D>)this).setValue(Double.parseDouble(s));
        }
        catch (NumberFormatException ex) {}
    }
    
    @Override
    public String saveValue() {
        return Double.toString(((Value<Double, D>)this).getValue());
    }
    
    public int getInt() {
        return super.getValue().intValue();
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
        return "ValueNumber(min=" + this.getMin() + ", max=" + this.getMax() + ", round=" + this.getRound() + ", postFix=" + this.getPostFix() + ")";
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ValueNumber)) {
            return false;
        }
        final ValueNumber other = (ValueNumber)o;
        if (!other.canEqual(this)) {
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
        return other instanceof ValueNumber;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
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

