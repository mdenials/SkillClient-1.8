package com.skillclient.misc;

import net.minecraft.client.gui.GuiScreen;
import com.skillclient.main.Register;
import com.skillclient.gui.list.SkillListGui;
import com.skillclient.gui.utils.GuiButton;

public class ValueModule extends Value<Class<? extends Module>, GuiButton>
{
    public ValueModule(final String name, final Module modul, final Class<? extends Module> other) {
        super(name, modul, Type.OTHER);
        ((Value<Class<? extends Module>, D>)this).setValue(other);
    }
    
    @Override
    public void action(final GuiButton button) {
        if (ValueModule.mc.currentScreen instanceof SkillListGui) {
            ((SkillListGui)ValueModule.mc.currentScreen).module = Register.getModule((Class)((Value<Class, D>)this).getValue());
            ((SkillListGui)ValueModule.mc.currentScreen).needs_update = true;
        }
        else {
            final SkillListGui gui = new SkillListGui();
            gui.module = Register.getModule((Class)((Value<Class, D>)this).getValue());
            ValueModule.mc.displayGuiScreen((GuiScreen)gui);
        }
    }
    
    @Override
    public GuiButton getGui() {
        return new GuiButton(0, 0, 0, this.name);
    }
    
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


