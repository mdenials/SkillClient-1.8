package com.skillclient.gui.click;

import com.skillclient.gui.click.components.Frame;
import com.skillclient.gui.click.components.FrameTree;

public class FrameSettings extends FrameTree
{
    public FrameSettings(final int posX, final int posY) {
        super("Settings", posX, posY);
    }
    
    public FrameSettings init() {
        this.addFrame((Frame)new FrameSettings.FrameSettings$1(this, "Reset", (FrameTree)this));
        final FrameTree colors = new FrameTree("Colors");
        colors.addFrame((Frame)new FrameSettings.FrameSettings$2(this, "Red", (FrameTree)this, (double)ThemeManager.r, 0.0, 255.0, 0));
        colors.addFrame((Frame)new FrameSettings.FrameSettings$3(this, "Green", (FrameTree)this, (double)ThemeManager.g, 0.0, 255.0, 0));
        colors.addFrame((Frame)new FrameSettings.FrameSettings$4(this, "Blue", (FrameTree)this, (double)ThemeManager.b, 0.0, 255.0, 0));
        colors.addFrame((Frame)new FrameSettings.FrameSettings$5(this, "Green", (FrameTree)this));
        colors.addFrame((Frame)new FrameSettings.FrameSettings$6(this, "Gray", (FrameTree)this));
        colors.addFrame((Frame)new FrameSettings.FrameSettings$7(this, "WiZARD", (FrameTree)this));
        this.addFrame((Frame)colors);
        return this;
    }
}

