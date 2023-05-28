package com.skillclient.gui;

import com.skillclient.chat.Chat;
import com.skillclient.main.SkillClient;

class GuiSkillClientSettings$3 extends GuiSkillClientSettings.SettingsButton {
    public void action() {
        SkillClient.getClient().getClass();
        Chat.openWebLink("https://discord.gg/vt9RATu");
    }
    
    public String name() {
        return "Discord";
    }
}


