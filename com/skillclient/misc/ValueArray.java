package com.skillclient.misc;

import com.skillclient.gui.utils.GuiButton;

public abstract class ValueArray<Item extends ValueArray.loadsave> extends Value<Integer, GuiButton>
{
    public ValueArray(final String name, final Module modul) {
        super(name, modul, Type.ARRAY);
        this.setValue(0);
    }
    
    public abstract Item[] array();
    
    @Override
    public void action(final GuiButton button) {
        this.setValue((this.getValue() + 1) % this.array().length);
        button.displayString = this.getDisplayname();
    }
    
    @Override
    public GuiButton getGui() {
        return new GuiButton(0, 0, 0, this.getDisplayname());
    }
    
    @Override
    public String saveValue() {
        return Integer.toString(this.getValue());
    }
    
    @Override
    public void loadValue(final String s) {
        try {
            this.setValue(Integer.parseInt(s));
        }
        catch (NumberFormatException ex) {}
    }
    
    public Item getItem() {
        return this.array()[this.getValue()];
    }
    
    @Override
    public String getDisplayname() {
        return String.valueOf(this.name) + ": " + this.getItem().getDisplayname();
    }
}

