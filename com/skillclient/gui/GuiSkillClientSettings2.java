package com.skillclient.gui;

import org.lwjgl.input.Keyboard;
import com.skillclient.main.Register;
import com.skillclient.modules.misc.ClickGui;

class GuiSkillClientSettings$2 extends GuiSkillClientSettings.SettingsButton {
    public void action() {
        GuiSkillClientSettings.this.setKeyBind = !GuiSkillClientSettings.this.setKeyBind;
    }
    
    public String name() {
        return GuiSkillClientSettings.this.setKeyBind ? ">  <" : ("Open Gui: " + Keyboard.getKeyName(((ClickGui)Register.getModule((Class)ClickGui.class)).getKeyCode()));
    }
}
