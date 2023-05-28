package com.skillclient.gui;

import com.skillclient.gui.utils.GuiButton;

public abstract static class SettingsButton extends GuiButton
{
    public SettingsButton(final int y, final boolean left) {
        super(0, left ? 20 : (SettingsButton.mc.currentScreen.width / 2 + 10), y, SettingsButton.mc.currentScreen.width / 2 - 40, 20, "");
        this.displayString = this.name();
    }
    
    public abstract void action();
    
    public abstract String name();
}


