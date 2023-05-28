package com.skillclient.gui.utils;

public class HoverChecker
{
    private int top;
    private int bottom;
    private int left;
    private int right;
    private int threshold;
    private GuiButton button;
    private long hoverStart;
    
    public HoverChecker(final GuiButton button, final int threshold) {
        this.button = button;
        this.threshold = threshold;
    }
    
    public HoverChecker(final int top, final int bottom, final int left, final int right, final int threshold) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.threshold = threshold;
        this.hoverStart = -1L;
    }
    
    public boolean checkHover(final int mouseX, final int mouseY) {
        return this.checkHover(mouseX, mouseY, true);
    }
    
    public boolean checkHover(final int mouseX, final int mouseY, boolean canHover) {
        if (this.button != null) {
            this.top = this.button.yPosition;
            this.bottom = this.button.yPosition + this.button.height;
            this.left = this.button.xPosition;
            this.right = this.button.xPosition + this.button.width;
            canHover = (canHover && this.button.visible);
        }
        if (canHover && this.hoverStart == -1L && mouseY >= this.top && mouseY <= this.bottom && mouseX >= this.left && mouseX <= this.right) {
            this.hoverStart = System.currentTimeMillis();
        }
        else if (!canHover || mouseY < this.top || mouseY > this.bottom || mouseX < this.left || mouseX > this.right) {
            this.resetHoverTimer();
        }
        return canHover && this.hoverStart != -1L && System.currentTimeMillis() - this.hoverStart >= this.threshold;
    }
    
    public void resetHoverTimer() {
        this.hoverStart = -1L;
    }
    
    public void updateBounds(final int top, final int bottom, final int left, final int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }
}

