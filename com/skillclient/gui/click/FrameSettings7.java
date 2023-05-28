package com.skillclient.gui.click;

import com.skillclient.gui.click.components.FrameTree;
import com.skillclient.gui.click.components.FrameButton;

class FrameSettings$7 extends FrameButton {
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        super.click(mouseX, mouseY, mouseButton);
        ThemeManager.r = 127;
        ThemeManager.g = 0;
        ThemeManager.b = 255;
    }
}
