package com.skillclient.gui.click.components;

import com.skillclient.utils.RenderGuiUtil;
import com.skillclient.utils.RenderUtils;
import java.awt.Color;

public class FrameToggleButton extends Frame
{
    private boolean isChecked;
    
    public FrameToggleButton(final String text, final boolean isChecked, final int posX, final int posY) {
        super(text, posX, posY);
        this.setChecked(isChecked);
    }
    
    public FrameToggleButton(final String text, final boolean isChecked, final FrameTree parent) {
        super(text, parent);
        this.setChecked(isChecked);
    }
    
    public FrameToggleButton(final String text, final FrameTree parent) {
        super(text, parent);
    }
    
    @Override
    public int getOwnWidth() {
        return super.getOwnWidth() + 10;
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawSquare(this.getPosX(), this.getPosX() + this.getWidth(), this.getPosY(), this.getPosY() + this.getHeight());
        this.drawSquare(this.getPosX() + 2, this.getPosX() + 9, this.getPosY() + 2, this.getPosY() + 9);
        if (this.isChecked()) {
            RenderUtils.drawLine(this.getPosX() + 5, this.getPosY() + 10, this.getPosX() + 10, this.getPosY() + 1, Color.green);
            RenderUtils.drawLine(this.getPosX() + 2, this.getPosY() + 5, this.getPosX() + 5, this.getPosY() + 10, Color.green);
        }
        FrameToggleButton.mc.fontRendererObj.drawString(this.getText(), this.getPosX() + 12, this.getPosY() + 2, RenderGuiUtil.isHovered(mouseX, mouseY, this) ? Color.green.getRGB() : -1);
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        this.setChecked(!this.isChecked());
    }
    
    public boolean isChecked() {
        return this.isChecked;
    }
    
    public void setChecked(final boolean isChecked) {
        this.isChecked = isChecked;
    }
    
    @Override
    public String toString() {
        return "FrameToggleButton(isChecked=" + this.isChecked() + ")";
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FrameToggleButton)) {
            return false;
        }
        final FrameToggleButton other = (FrameToggleButton)o;
        return other.canEqual(this) && this.isChecked() == other.isChecked();
    }
    
    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof FrameToggleButton;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isChecked() ? 79 : 97);
        return result;
    }
}
