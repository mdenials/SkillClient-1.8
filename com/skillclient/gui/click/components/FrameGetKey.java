package com.skillclient.gui.click.components;

import com.skillclient.gui.click.misc.KeyEvent;
import org.lwjgl.input.Keyboard;

public class FrameGetKey extends Frame
{
    int keyCode;
    boolean isFocused;
    
    public FrameGetKey(final String text, final int keyCode, final FrameTree parent) {
        super(text, parent);
        this.isFocused = false;
        this.keyCode = keyCode;
    }
    
    @Override
    public int getOwnWidth() {
        return super.getOwnWidth();
    }
    
    @Override
    public String getText() {
        return this.isFocused() ? "> <" : (String.valueOf(super.getText()) + " [" + Keyboard.getKeyName(this.getKeyCode()) + "]");
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        this.setFocused(!this.isFocused());
    }
    
    @Override
    public void keyTyped(final KeyEvent event) {
        if (this.isFocused()) {
            this.setKeyCode((event.getKeyCode() == 1) ? 0 : event.getKeyCode());
            this.setFocused(false);
            event.setCallSuper(false);
        }
    }
    
    public int getKeyCode() {
        return this.keyCode;
    }
    
    public boolean isFocused() {
        return this.isFocused;
    }
    
    public void setKeyCode(final int keyCode) {
        this.keyCode = keyCode;
    }
    
    public void setFocused(final boolean isFocused) {
        this.isFocused = isFocused;
    }
    
    @Override
    public String toString() {
        return "FrameGetKey(keyCode=" + this.getKeyCode() + ", isFocused=" + this.isFocused() + ")";
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FrameGetKey)) {
            return false;
        }
        final FrameGetKey other = (FrameGetKey)o;
        return other.canEqual(this) && this.getKeyCode() == other.getKeyCode() && this.isFocused() == other.isFocused();
    }
    
    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof FrameGetKey;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getKeyCode();
        result = result * 59 + (this.isFocused() ? 79 : 97);
        return result;
    }
}

