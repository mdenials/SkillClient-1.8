package com.skillclient.gui.utils;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import com.skillclient.utils.RenderGuiUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import com.skillclient.utils.TimerUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.Gui;

public class GuiButton extends Gui
{
    protected static ResourceLocation BUTTON_TEXTURES;
    public int width;
    public int height;
    public int xPosition;
    public int yPosition;
    public String displayString;
    public int id;
    public boolean enabled;
    public boolean visible;
    protected boolean hovered;
    protected float state;
    TimerUtil timer;
    
    static {
        GuiButton.BUTTON_TEXTURES = new ResourceLocation("textures/gui/widgets.png");
    }
    
    public GuiButton(final int buttonId, final int x, final int y, final int widthIn, final int heightIn, final String buttonText) {
        this.state = 100.0f;
        this.timer = new TimerUtil();
        this.width = 200;
        this.height = 20;
        this.enabled = true;
        this.visible = true;
        this.id = buttonId;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonText;
    }
    
    public GuiButton(final int buttonId, final int x, final int y, final String buttonText) {
        this(buttonId, x, y, 200, 20, buttonText);
    }
    
    public void drawButton(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.visible) {
            this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
            final float delay = 5.0f;
            if (this.hovered) {
                final float a = 0.0f;
                final float n = this.state - this.timer.pastTime() / delay;
                this.state = n;
                this.state = Math.max(a, n);
            }
            else {
                final float a2 = 100.0f;
                final float n2 = this.state + this.timer.pastTime() / delay;
                this.state = n2;
                this.state = Math.min(a2, n2);
            }
            this.timer.setLastMS();
            final int offset = (int)(this.state / 8.0f);
            final int c = (int)(200.0f - this.state);
            drawRect(this.xPosition + offset, this.yPosition, this.xPosition + this.width - offset, this.yPosition + this.height, new Color(this.enabled ? c : 125, this.enabled ? c : 0, this.enabled ? c : 0, c).getRGB());
            RenderGuiUtil.drawHorizontalLine(this.xPosition, this.xPosition + this.width - 1, this.yPosition);
            RenderGuiUtil.drawHorizontalLine(this.xPosition, this.xPosition + this.width - 1, this.yPosition + this.height - 1);
            mc.getTextureManager().bindTexture(GuiButton.BUTTON_TEXTURES);
            this.mouseDragged(mc, mouseX, mouseY);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            this.drawCenteredString(mc.fontRendererObj, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, 14737632);
            GlStateManager.disableBlend();
        }
    }
    
    public void drawButtonForegroundLayer(final int mouseX, final int mouseY) {
    }
    
    public int getButtonWidth() {
        return this.width;
    }
    
    protected int getHoverState(final boolean mouseOver) {
        int i = 1;
        if (!this.enabled) {
            i = 0;
        }
        else if (mouseOver) {
            i = 2;
        }
        return i;
    }
    
    public boolean isMouseOver() {
        return this.hovered;
    }
    
    protected void mouseDragged(final Minecraft mc, final int mouseX, final int mouseY) {
    }
    
    public boolean mousePressed(final Minecraft mc, final int mouseX, final int mouseY) {
        return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }
    
    public void mouseReleased(final int mouseX, final int mouseY) {
    }
    
    public void playPressSound(final SoundHandler soundHandlerIn) {
        soundHandlerIn.playSound((ISound)PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0f));
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
}

