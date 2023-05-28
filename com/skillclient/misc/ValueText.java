package com.skillclient.misc;

import com.skillclient.gui.utils.GuiButton;
import net.minecraft.client.gui.GuiTextField;

public class ValueText extends Value<String, GuiTextField>
{
    public GuiTextField field;
    
    public ValueText(final String name, final Module modul, final String def) {
        super(name, modul, Type.TEXT);
        (this.field = new GuiTextField(0, ValueText.mc.fontRendererObj, 0, 0, 0, 0)).setMaxStringLength(Integer.MAX_VALUE);
        this.field.setCanLoseFocus(true);
        this.field.setText(this.getValue());
        this.setValue(def);
    }
    
    @Override
    public GuiTextField getGui() {
        this.field.setText(this.getValue());
        return this.field;
    }
    
    @Override
    public void setValue(final String value) {
        this.field.setText(value);
    }
    
    @Override
    public String getValue() {
        return this.field.getText();
    }
    
    @Override
    public void action(final GuiTextField button) {
        button.setFocused(!button.isFocused());
    }
    
    @Override
    public void loadValue(final String s) {
        this.setValue(s);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        this.field.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    @Override
    public String saveValue() {
        return this.getValue();
    }
    
    @Override
    public boolean keyTyped(final GuiTextField button, final char typedChar, final int keyCode) {
        if (button.isFocused()) {
            if (keyCode == 1) {
                button.setFocused(false);
            }
            else {
                button.textboxKeyTyped(typedChar, keyCode);
            }
            this.setValue(button.getText());
            button.setText(this.getValue());
            return true;
        }
        return false;
    }
}
