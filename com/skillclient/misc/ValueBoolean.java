package com.skillclient.misc;

import com.skillclient.gui.utils.GuiButton;
import com.skillclient.gui.utils.SkillGuiCheckBox;

public class ValueBoolean extends Value<Boolean, SkillGuiCheckBox>
{
    public ValueBoolean(final String name, final Module modul, final boolean value) {
        super(name, modul, Type.BOOLEAN);
        this.value = (V)Boolean.valueOf(value);
    }
    
    @Override
    public SkillGuiCheckBox getGui() {
        return new SkillGuiCheckBox(0, 0, 0, this.getDisplayname(), (boolean)((Value<Boolean, D>)this).getValue());
    }
    
    @Override
    public void action(final SkillGuiCheckBox button) {
        ((Value<Boolean, D>)this).setValue(button.isChecked());
        button.setIsChecked((boolean)((Value<Boolean, D>)this).getValue());
    }
    
    @Override
    public void loadValue(final String s) {
        this.value = (V)Boolean.valueOf(Boolean.parseBoolean(s));
    }
    
    @Override
    public String saveValue() {
        return Boolean.toString(((Value<Boolean, D>)this).getValue());
    }
    
    @Override
    public String getDisplayname() {
        return this.name;
    }
}

