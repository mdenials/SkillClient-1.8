package com.skillclient.gui.click;

import com.skillclient.gui.click.components.FrameTree;
import com.skillclient.gui.click.components.FrameButton;

class FrameSettings$5 extends FrameButton {
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        super.click(mouseX, mouseY, mouseButton);
        ThemeManager.r = 40;
        ThemeManager.g = 180;
        ThemeManager.b = 50;
    }
}
