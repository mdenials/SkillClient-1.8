package com.skillclient.gui;

import net.minecraft.client.gui.GuiScreen;

class GuiSkillClientSettings$7 extends GuiSkillClientSettings.SettingsButton {
    public void action() {
        GuiSkillClientSettings$7.mc.displayGuiScreen((GuiScreen)new GuiXRay());
    }
    
    public String name() {
        return "Xray";
    }
}
