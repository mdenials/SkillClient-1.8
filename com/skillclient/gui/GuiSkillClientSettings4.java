package com.skillclient.gui;

import com.skillclient.chat.Chat;

class GuiSkillClientSettings$4 extends GuiSkillClientSettings.SettingsButton {
    public void action() {
        Chat.openWebLink("https://www.youtube.com/MCmodding4K/videos?sub_confirmation=1");
    }
    
    public String name() {
        return "Videos";
    }
}
