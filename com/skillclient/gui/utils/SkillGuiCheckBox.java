package com.skillclient.gui.utils;

import com.skillclient.utils.RenderGuiUtil;
import net.minecraft.client.Minecraft;

public class SkillGuiCheckBox extends GuiButton
{
    private boolean isChecked;
    private int boxWidth;
    
    public SkillGuiCheckBox(final int id, final int xPos, final int yPos, final String displayString, final boolean isChecked) {
        super(id, xPos, yPos, displayString);
        this.isChecked = isChecked;
        this.boxWidth = 11;
        this.height = 11;
        this.width = this.boxWidth + 2 + Minecraft.getMinecraft().fontRendererObj.getStringWidth(displayString);
    }
    
    @Override
    public void drawButton(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.visible) {
            this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.boxWidth && mouseY < this.yPosition + this.height);
            RenderGuiUtil.drawVerticalLine(this.xPosition, this.yPosition, this.yPosition + this.height - 1);
            RenderGuiUtil.drawVerticalLine(this.xPosition + this.boxWidth - 1, this.yPosition, this.yPosition + this.height - 1);
            RenderGuiUtil.drawHorizontalLine(this.xPosition, this.xPosition + this.boxWidth - 1, this.yPosition);
            RenderGuiUtil.drawHorizontalLine(this.xPosition, this.xPosition + this.boxWidth - 1, this.yPosition + this.height - 1);
            this.mouseDragged(mc, mouseX, mouseY);
            int color = 14737632;
            if (!this.enabled) {
                color = 10526880;
            }
            if (this.isChecked) {
                this.drawCenteredString(mc.fontRendererObj, "x", this.xPosition + this.boxWidth / 2 + 1, this.yPosition + 1, 14737632);
            }
            this.drawString(mc.fontRendererObj, this.displayString, this.xPosition + this.boxWidth + 2, this.yPosition + 2, color);
        }
    }
    
    public boolean isChecked() {
        return this.isChecked;
    }
    
    @Override
    public boolean mousePressed(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height) {
            this.isChecked = !this.isChecked;
            return true;
        }
        return false;
    }
    
    public void setIsChecked(final boolean isChecked) {
        this.isChecked = isChecked;
    }
}

