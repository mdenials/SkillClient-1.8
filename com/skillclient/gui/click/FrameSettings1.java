package com.skillclient.gui.click;

import com.skillclient.gui.click.components.FrameTree;
import com.skillclient.gui.click.components.FrameButton;

class FrameSettings$1 extends FrameButton {
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        SkillClickGui.frames = null;
        FrameSettings$1.mc.displayGuiScreen(FrameSettings$1.mc.currentScreen);
    }
}
