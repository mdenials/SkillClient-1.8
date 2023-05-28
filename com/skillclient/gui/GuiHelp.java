package com.skillclient.gui;

import java.io.IOException;
import com.skillclient.chat.Chat;
import com.skillclient.gui.utils.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiHelp extends GuiScreen
{
    GuiScreen parent;
    
    public GuiHelp(final GuiScreen parent) {
        this.parent = parent;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void initGui() {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, 30, 200, 20, "YouTube-Comments"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, 55, 200, 20, "Discord"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, 80, 200, 20, "skillclient.tk"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, 105, 200, 20, "Twitter"));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, 130, 200, 20, "Issues"));
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        switch (button.id) {
            case 1: {
                Chat.openWebLink("https://www.youtube.com/MCModding4K?sub_confirmation=1");
                break;
            }
            case 2: {
                Chat.openWebLink("https://discord.gg/ZGhYcF8");
                break;
            }
            case 3: {
                Chat.openWebLink("http://skillclient.tk/download.html");
                break;
            }
            case 4: {
                Chat.openWebLink("https://twitter.com/MCmodding4K");
                break;
            }
            case 5: {
                Chat.openWebLink("https://github.com/MCmodding4K/SkillClient/issues/new?title=Issues" + (int)(Math.random() * 1000.0) + "&body=Inhalt/Content:  ");
                break;
            }
        }
    }
}


