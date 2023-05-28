package com.skillclient.gui.click.components;

public class FrameButton extends Frame
{
    public FrameButton(final String text, final FrameTree parent) {
        super(text, parent);
    }
    
    public FrameButton(final String text, final int posX, final int posY) {
        super(text, posX, posY);
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    @Override
    public String toString() {
        return "FrameButton()";
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FrameButton)) {
            return false;
        }
        final FrameButton other = (FrameButton)o;
        return other.canEqual(this);
    }
    
    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof FrameButton;
    }
    
    @Override
    public int hashCode() {
        final int result = 1;
        return result;
    }
}

