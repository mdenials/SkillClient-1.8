package com.skillclient.misc;

import com.skillclient.gui.utils.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public abstract class ValueGui extends Value<GuiScreen, GuiButton>
{
    public ValueGui(final String name, final Module modul) {
        super(name, modul, Type.OTHER);
    }
    
    @Override
    public void action(final GuiButton button) {
        if (this.screen() != null) {
            ValueGui.mc.displayGuiScreen(this.screen());
        }
    }
    
    @Override
    public GuiButton getGui() {
        return new GuiButton(0, 0, 0, this.name);
    }
    
    public abstract GuiScreen screen();
    
    @Override
    public String getDisplayname() {
        return this.name;
    }
    
    @Override
    public void loadValue(final String s) {
    }
    
    @Override
    public boolean reloadScreen() {
        return false;
    }
    
    @Override
    public String saveValue() {
        return "";
    }
}

