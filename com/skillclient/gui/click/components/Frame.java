package com.skillclient.gui.click.components;

import com.skillclient.gui.click.ThemeManager;
import com.skillclient.gui.click.misc.KeyEvent;
import java.awt.Color;
import com.skillclient.utils.RenderGuiUtil;
import com.skillclient.main.SkillClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public abstract class Frame extends Gui
{
    protected static final Minecraft mc;
    protected static final SkillClient sc;
    private String text;
    private int posX;
    private int posY;
    private FrameTree parent;
    
    static {
        mc = Minecraft.getMinecraft();
        sc = SkillClient.getClient();
    }
    
    public Frame(final String text, final int posX, final int posY) {
        this.text = text;
        this.posX = posX;
        this.posY = posY;
    }
    
    public Frame(final String text, final FrameTree parent) {
        this(text, 0, 0);
        this.parent = parent;
    }
    
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawSquare(this.getPosX(), this.getPosX() + this.getWidth(), this.getPosY(), this.getPosY() + this.getHeight());
        Frame.mc.fontRendererObj.drawString(this.getText(), this.getPosX() + 2, this.getPosY() + 2, RenderGuiUtil.isHovered(mouseX, mouseY, this) ? Color.green.getRGB() : -1);
    }
    
    public abstract void click(final int p0, final int p1, final int p2);
    
    public void click(final int mouseX, final int mouseY, final int mouseButton, final long timeSinceLastClick) {
    }
    
    public void keyTyped(final KeyEvent event) {
    }
    
    public final int getWidth() {
        return Math.max(this.getOwnWidth(), (this.parent == null) ? 0 : (this.parent.getWidth() - 4));
    }
    
    public int getOwnWidth() {
        return Frame.mc.fontRendererObj.getStringWidth(this.getText()) + 2;
    }
    
    public int getHeight() {
        return Frame.mc.fontRendererObj.FONT_HEIGHT + 2;
    }
    
    public void offSet(int x, int y) {
        x += this.getPosX();
        y += this.getPosY();
        final int edge = -1;
        if (x > Frame.mc.currentScreen.width - this.getWidth() - edge) {
            x = Frame.mc.currentScreen.width - this.getWidth() - edge;
        }
        if (y > Frame.mc.currentScreen.height - (Frame.mc.fontRendererObj.FONT_HEIGHT + 2) - edge) {
            y = Frame.mc.currentScreen.height - (Frame.mc.fontRendererObj.FONT_HEIGHT + 2) - edge;
        }
        if (x < edge) {
            x = edge;
        }
        if (y < edge) {
            y = edge;
        }
        this.setPosX(x);
        this.setPosY(y);
    }
    
    public void drawVerticalLine(final int x, final int startY, final int endY) {
        this.drawVerticalLine(x, startY, endY, ThemeManager.getColor().getRGB());
    }
    
    public void drawHorizontalLine(final int startX, final int endX, final int y) {
        this.drawHorizontalLine(startX, endX, y, ThemeManager.getColor().getRGB());
    }
    
    public void drawSquare(final int startX, final int endX, final int startY, final int endY) {
        this.drawHorizontalLine(startX, endX, startY);
        this.drawHorizontalLine(startX, endX, endY);
        this.drawVerticalLine(startX, startY, endY);
        this.drawVerticalLine(endX, startY, endY);
    }
    
    public String getText() {
        return this.text;
    }
    
    public int getPosX() {
        return this.posX;
    }
    
    public int getPosY() {
        return this.posY;
    }
    
    public FrameTree getParent() {
        return this.parent;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public void setPosX(final int posX) {
        this.posX = posX;
    }
    
    public void setPosY(final int posY) {
        this.posY = posY;
    }
    
    public void setParent(final FrameTree parent) {
        this.parent = parent;
    }
    
    public String toString() {
        return "Frame(text=" + this.getText() + ", posX=" + this.getPosX() + ", posY=" + this.getPosY() + ", parent=" + this.getParent() + ")";
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Frame)) {
            return false;
        }
        final Frame other = (Frame)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$text = this.getText();
        final Object other$text = other.getText();
        Label_0065: {
            if (this$text == null) {
                if (other$text == null) {
                    break Label_0065;
                }
            }
            else if (this$text.equals(other$text)) {
                break Label_0065;
            }
            return false;
        }
        if (this.getPosX() != other.getPosX()) {
            return false;
        }
        if (this.getPosY() != other.getPosY()) {
            return false;
        }
        final Object this$parent = this.getParent();
        final Object other$parent = other.getParent();
        if (this$parent == null) {
            if (other$parent == null) {
                return true;
            }
        }
        else if (this$parent.equals(other$parent)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Frame;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $text = this.getText();
        result = result * 59 + (($text == null) ? 43 : $text.hashCode());
        result = result * 59 + this.getPosX();
        result = result * 59 + this.getPosY();
        final Object $parent = this.getParent();
        result = result * 59 + (($parent == null) ? 43 : $parent.hashCode());
        return result;
    }
}
