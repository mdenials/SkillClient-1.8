package com.skillclient.gui.click.components;

import com.skillclient.gui.click.ThemeManager;
import com.skillclient.gui.click.misc.KeyEvent;
import java.util.Iterator;
import java.awt.Color;
import com.skillclient.utils.RenderGuiUtil;
import java.util.ArrayList;

public class FrameTree extends Frame
{
    private boolean expanded;
    private ArrayList<Frame> subFrames;
    
    public FrameTree(final String text, final int posX, final int posY) {
        super(text, posX, posY);
        this.subFrames = new ArrayList<Frame>();
    }
    
    public FrameTree(final String text) {
        this(text, 0, 0);
    }
    
    public void addFrame(final Frame frame) {
        this.getSubFrames().add(frame);
    }
    
    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawSquare(this.getPosX(), this.getPosX() + this.getWidth(), this.getPosY(), this.getPosY() + this.getHeight());
        FrameTree.mc.fontRendererObj.drawString(this.getText(), this.getPosX() + 2, this.getPosY() + 2, RenderGuiUtil.isHovered(mouseX, mouseY, this) ? Color.green.getRGB() : -1);
        if (this.isExpanded()) {
            int pos = 11;
            for (final Frame f : this.getSubFrames()) {
                f.setPosX(this.getPosX() + 2);
                f.setPosY(this.getPosY() + pos);
                pos += f.getHeight() + 2;
                f.render(mouseX, mouseY, partialTicks);
            }
        }
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        if ((mouseButton == 1 || this.getParent() != null) && mouseY - this.getPosY() < super.getHeight()) {
            this.setExpanded(!this.isExpanded());
        }
        for (final Frame frame : this.getSubFrames()) {
            if (RenderGuiUtil.isHovered(mouseX, mouseY, frame)) {
                frame.click(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int mouseButton, final long timeSinceLastClick) {
        for (final Frame frame : this.getSubFrames()) {
            if (RenderGuiUtil.isHovered(mouseX, mouseY, frame)) {
                frame.click(mouseX, mouseY, mouseButton, timeSinceLastClick);
            }
        }
    }
    
    @Override
    public void keyTyped(final KeyEvent event) {
        for (final Frame frame : this.getSubFrames()) {
            frame.keyTyped(event);
        }
    }
    
    @Override
    public int getHeight() {
        int h = super.getHeight();
        if (this.isExpanded()) {
            for (final Frame frame : this.getSubFrames()) {
                h += frame.getHeight() + 2;
            }
        }
        return h;
    }
    
    @Override
    public int getOwnWidth() {
        if (this.isExpanded()) {
            int c = super.getOwnWidth();
            for (final Frame f : this.getSubFrames()) {
                c = Math.max(c, f.getOwnWidth() + 4);
            }
            return c;
        }
        return super.getOwnWidth();
    }
    
    @Override
    public void drawVerticalLine(final int x, final int startY, final int endY) {
        this.drawVerticalLine(x, startY, endY, ThemeManager.getColor().getRGB());
    }
    
    @Override
    public void drawHorizontalLine(final int startX, final int endX, final int y) {
        this.drawHorizontalLine(startX, endX, y, ThemeManager.getColor().getRGB());
    }
    
    public void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }
    
    public boolean isExpanded() {
        return this.expanded;
    }
    
    public void setSubFrames(final ArrayList<Frame> subFrames) {
        this.subFrames = subFrames;
    }
    
    public ArrayList<Frame> getSubFrames() {
        return this.subFrames;
    }
}
