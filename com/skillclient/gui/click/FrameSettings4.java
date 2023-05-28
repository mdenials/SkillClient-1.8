package com.skillclient.gui.click;

import com.skillclient.gui.click.components.FrameTree;
import com.skillclient.gui.click.components.FrameSlider;

class FrameSettings$4 extends FrameSlider {
    public void setValue(final double value) {
        super.setValue(value);
        ThemeManager.b = (int)value;
    }
    
    public double getValue() {
        return ThemeManager.b;
    }
    
    public double getRenderValue() {
        return ThemeManager.b / 255.0f;
    }
}
