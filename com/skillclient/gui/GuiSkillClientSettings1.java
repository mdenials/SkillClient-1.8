package com.skillclient.gui;

import com.skillclient.misc.Value;
import com.skillclient.main.Register;
import com.skillclient.modules.misc.ClickGui;

class GuiSkillClientSettings$1 extends GuiSkillClientSettings.SettingsButton {
    public void action() {
        ((Value<Boolean, D>)((ClickGui)Register.getModule((Class)ClickGui.class)).gui_type).setValue(!((Value<Boolean, D>)((ClickGui)Register.getModule((Class)ClickGui.class)).gui_type).getValue());
    }
    
    public String name() {
        return ((Value<Boolean, D>)((ClickGui)Register.getModule((Class)ClickGui.class)).gui_type).getValue() ? "Gui: NEW" : "Gui: OLD";
    }
}
