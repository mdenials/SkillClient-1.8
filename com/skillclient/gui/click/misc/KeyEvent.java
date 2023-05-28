package com.skillclient.gui.click.misc;

public class KeyEvent
{
    private char typedChar;
    private int keyCode;
    private boolean callSuper;
    
    public KeyEvent(final char typedChar, final int keyCode) {
        this.callSuper = true;
        this.typedChar = typedChar;
        this.keyCode = keyCode;
    }
    
    public char getTypedChar() {
        return this.typedChar;
    }
    
    public int getKeyCode() {
        return this.keyCode;
    }
    
    public boolean isCallSuper() {
        return this.callSuper;
    }
    
    public void setTypedChar(final char typedChar) {
        this.typedChar = typedChar;
    }
    
    public void setKeyCode(final int keyCode) {
        this.keyCode = keyCode;
    }
    
    public void setCallSuper(final boolean callSuper) {
        this.callSuper = callSuper;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof KeyEvent)) {
            return false;
        }
        final KeyEvent other = (KeyEvent)o;
        return other.canEqual(this) && this.getTypedChar() == other.getTypedChar() && this.getKeyCode() == other.getKeyCode() && this.isCallSuper() == other.isCallSuper();
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof KeyEvent;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getTypedChar();
        result = result * 59 + this.getKeyCode();
        result = result * 59 + (this.isCallSuper() ? 79 : 97);
        return result;
    }
    
    @Override
    public String toString() {
        return "KeyEvent(typedChar=" + this.getTypedChar() + ", keyCode=" + this.getKeyCode() + ", callSuper=" + this.isCallSuper() + ")";
    }
}
