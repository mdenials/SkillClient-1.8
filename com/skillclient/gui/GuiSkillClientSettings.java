package com.skillclient.gui;

import com.skillclient.main.Register;
import com.skillclient.modules.misc.ClickGui;
import java.io.IOException;
import com.skillclient.gui.utils.GuiButton;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class GuiSkillClientSettings extends GuiScreen
{
    GuiScreen parent;
    boolean setKeyBind;
    public static ArrayList<String> friends;
    
    static {
        GuiSkillClientSettings.friends = new ArrayList<String>();
    }
    
    public GuiSkillClientSettings(final GuiScreen parent) {
        this.setKeyBind = false;
        this.parent = parent;
    }
    
    public void initGui() {
        this.buttonList.add(new GuiSkillClientSettings.GuiSkillClientSettings$1(this, 30, true));
        this.buttonList.add(new GuiSkillClientSettings.GuiSkillClientSettings$2(this, 30, false));
        this.buttonList.add(new GuiSkillClientSettings.GuiSkillClientSettings$3(this, 170, true));
        this.buttonList.add(new GuiSkillClientSettings.GuiSkillClientSettings$4(this, 170, false));
        this.buttonList.add(new GuiSkillClientSettings.GuiSkillClientSettings$5(this, 200, true));
        this.buttonList.add(new GuiSkillClientSettings.GuiSkillClientSettings$6(this, 200, false));
        this.buttonList.add(new GuiSkillClientSettings.GuiSkillClientSettings$7(this, 240, true));
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        ((GuiSkillClientSettings.SettingsButton)button).action();
        button.displayString = ((GuiSkillClientSettings.SettingsButton)button).name();
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (this.setKeyBind) {
            if (keyCode == 1) {
                return;
            }
            this.setKeyBind = false;
            ((ClickGui)Register.getModule((Class)ClickGui.class)).setKeyCode(keyCode);
            this.buttonList.get(1).displayString = this.buttonList.get(1).name();
        }
        else {
            super.keyTyped(typedChar, keyCode);
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

