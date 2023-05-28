package com.skillclient.gui.click;

import com.skillclient.gui.click.components.FrameTree;
import com.skillclient.gui.click.components.FrameSlider;

class FrameSettings$3 extends FrameSlider {
    public void setValue(final double value) {
        super.setValue(value);
        ThemeManager.g = (int)value;
    }
    
    public double getValue() {
        return ThemeManager.g;
    }
    
    public double getRenderValue() {
        return ThemeManager.g / 255.0f;
    }
}
