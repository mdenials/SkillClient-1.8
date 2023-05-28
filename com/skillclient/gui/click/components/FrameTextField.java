package com.skillclient.gui.click.components;

import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.client.gui.GuiScreen;
import com.skillclient.gui.click.misc.KeyEvent;
import java.awt.Color;
import com.skillclient.utils.RenderGuiUtil;

public class FrameTextField extends Frame
{
    String fieldText;
    boolean isFocused;
    
    public FrameTextField(final String text, final String defaultText, final FrameTree parent) {
        super(text, parent);
        this.isFocused = false;
        this.fieldText = defaultText;
    }
    
    @Override
    public int getOwnWidth() {
        return super.getOwnWidth() + FrameTextField.mc.fontRendererObj.getStringWidth(this.fieldText) + 8;
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawSquare(this.getPosX(), this.getPosX() + this.getWidth(), this.getPosY(), this.getPosY() + this.getHeight());
        FrameTextField.mc.fontRendererObj.drawString(String.valueOf(this.getText()) + ": " + this.getFieldText(), this.getPosX() + 2, this.getPosY() + 2, RenderGuiUtil.isHovered(mouseX, mouseY, this) ? Color.green.getRGB() : -1);
        if (System.currentTimeMillis() % 500L > 250L && this.isFocused()) {
            this.drawVerticalLine(this.getPosX() + this.getOwnWidth() - 2, this.getPosY() + 1, this.getPosY() + this.getHeight() - 1, -1);
        }
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        this.setFocused(!this.isFocused());
    }
    
    @Override
    public void keyTyped(final KeyEvent event) {
        if (!this.isFocused()) {
            return;
        }
        if (GuiScreen.isKeyComboCtrlC(event.getKeyCode())) {
            GuiScreen.setClipboardString(this.getFieldText());
        }
        else if (GuiScreen.isKeyComboCtrlV(event.getKeyCode())) {
            this.setFieldText(GuiScreen.getClipboardString());
        }
        else if (GuiScreen.isKeyComboCtrlX(event.getKeyCode())) {
            if (!this.getFieldText().isEmpty()) {
                GuiScreen.setClipboardString(this.getFieldText());
            }
            this.setFieldText("");
        }
        else if (event.getKeyCode() == 1) {
            this.setFocused(false);
        }
        else if (event.getKeyCode() == 14) {
            if (!this.getFieldText().isEmpty()) {
                this.setFieldText(this.getFieldText().substring(0, this.getFieldText().length() - 1));
            }
        }
        else if (ChatAllowedCharacters.isAllowedCharacter(event.getTypedChar())) {
            this.setFieldText(String.valueOf(this.getFieldText()) + Character.toString(event.getTypedChar()));
        }
        event.setCallSuper(false);
    }
    
    public String getFieldText() {
        return this.fieldText;
    }
    
    public boolean isFocused() {
        return this.isFocused;
    }
    
    public void setFieldText(final String fieldText) {
        this.fieldText = fieldText;
    }
    
    public void setFocused(final boolean isFocused) {
        this.isFocused = isFocused;
    }
    
    @Override
    public String toString() {
        return "FrameTextField(fieldText=" + this.getFieldText() + ", isFocused=" + this.isFocused() + ")";
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FrameTextField)) {
            return false;
        }
        final FrameTextField other = (FrameTextField)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$fieldText = this.getFieldText();
        final Object other$fieldText = other.getFieldText();
        if (this$fieldText == null) {
            if (other$fieldText == null) {
                return this.isFocused() == other.isFocused();
            }
        }
        else if (this$fieldText.equals(other$fieldText)) {
            return this.isFocused() == other.isFocused();
        }
        return false;
    }
    
    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof FrameTextField;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $fieldText = this.getFieldText();
        result = result * 59 + (($fieldText == null) ? 43 : $fieldText.hashCode());
        result = result * 59 + (this.isFocused() ? 79 : 97);
        return result;
    }
}

