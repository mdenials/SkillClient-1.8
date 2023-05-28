package com.skillclient.gui;

import com.skillclient.chat.Chat;

class GuiSkillClientSettings$6 extends GuiSkillClientSettings.SettingsButton {
    public void action() {
        Chat.openWebLink("https://github.com/MCmodding4K/SkillClient/issues");
    }
    
    public String name() {
        return "GitHub";
    }
}


