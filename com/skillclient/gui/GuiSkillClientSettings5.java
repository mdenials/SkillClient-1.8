package com.skillclient.gui;

import com.skillclient.chat.Chat;

class GuiSkillClientSettings$5 extends GuiSkillClientSettings.SettingsButton {
    public void action() {
        Chat.openWebLink("https://twitter.com/MCmodding4K");
    }
    
    public String name() {
        return "Twitter";
    }
}
