package com.skillclient.gui.click;

import com.skillclient.gui.click.components.FrameTree;
import com.skillclient.gui.click.components.FrameSlider;

class FrameSettings$2 extends FrameSlider {
    public void setValue(final double value) {
        super.setValue(value);
        ThemeManager.r = (int)value;
    }
    
    public double getValue() {
        return ThemeManager.r;
    }
    
    public double getRenderValue() {
        return ThemeManager.r / 255.0f;
    }
}


