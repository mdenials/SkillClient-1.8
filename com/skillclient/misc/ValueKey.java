package com.skillclient.misc;

import org.lwjgl.input.Keyboard;
import com.skillclient.gui.utils.GuiButton;

public class ValueKey extends Value<Integer, GuiButton>
{
    boolean clicked;
    
    public ValueKey(final String name, final Module modul, final int value) {
        super(name, modul, Type.KEY);
        this.clicked = false;
        this.value = (V)Integer.valueOf(value);
    }
    
    @Override
    public GuiButton getGui() {
        return new GuiButton(0, 0, 0, this.getDisplayname());
    }
    
    @Override
    public void action(final GuiButton button) {
        this.clicked = !this.clicked;
        button.displayString = this.getDisplayname();
    }
    
    @Override
    public boolean keyTyped(final GuiButton m, final char typedChar, final int keyCode) {
        if (this.clicked) {
            this.clicked = false;
            ((Value<Integer, D>)this).setValue((keyCode == 1) ? 0 : keyCode);
            m.displayString = this.getDisplayname();
            return true;
        }
        return false;
    }
    
    @Override
    public void loadValue(final String s) {
        this.value = (V)Integer.valueOf(Integer.parseInt(s));
    }
    
    @Override
    public String saveValue() {
        return ((Value<Integer, D>)this).getValue().toString();
    }
    
    @Override
    public String getDisplayname() {
        return this.clicked ? ">  <" : Keyboard.getKeyName((int)((Value<Integer, D>)this).getValue());
    }
}

