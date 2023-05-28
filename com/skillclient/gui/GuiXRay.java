package com.skillclient.gui;

import net.minecraft.inventory.Slot;
import java.util.Iterator;
import net.minecraft.client.renderer.GlStateManager;
import java.io.IOException;
import net.minecraft.block.Block;
import com.skillclient.main.Register;
import com.skillclient.modules.render.XRay;
import com.skillclient.gui.utils.GuiButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiXRay extends GuiScreen
{
    private Minecraft mc;
    private GuiButton selectedButton;
    private XRay xray;
    int w_;
    int h_;
    
    public GuiXRay() {
        this.mc = Minecraft.getMinecraft();
        this.xray = (XRay)Register.getModule((Class)XRay.class);
        this.w_ = 25;
        this.h_ = 25;
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (this.xray.xray_blocks.get(button.id).action()) {
            Block[] blocks;
            for (int length = (blocks = this.xray.xray_blocks.get(button.id).getBlocks()).length, i = 0; i < length; ++i) {
                final Block block = blocks[i];
                XRay.map.put(block, !XRay.map.get(block));
            }
        }
        this.mc.displayGuiScreen((GuiScreen)new GuiXRay());
        super.actionPerformed(button);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCreativeTabHoveringText("Gui for X-Ray", this.width / 2 - 60, 25);
        final int modh = (this.height - 20) / this.h_;
        final int modw = (this.width - 20) / this.w_;
        int pos = 0;
        for (final XRay.XRay_Block xray_block : this.xray.xray_blocks) {
            final int w = 12 + pos / modh * this.w_;
            final int h = 32 + pos % modh * this.h_;
            this.itemRender.renderItemAndEffectIntoGUI(xray_block.getItemstack(), w, h);
            ++pos;
        }
        pos = 0;
        for (final XRay.XRay_Block xray_block : this.xray.xray_blocks) {
            final int w = 12 + pos / modh * this.w_;
            final int h = 32 + pos % modh * this.h_;
            if (xray_block.getItemstack() != null && this.isPointInRegion(w, h, 16, 16, mouseX, mouseY)) {
                this.drawCreativeTabHoveringText(xray_block.getName(), mouseX, mouseY);
            }
            ++pos;
        }
    }
    
    public void initGui() {
        final int modh = (this.height - 20) / this.h_;
        final int modw = (this.width - 20) / this.w_;
        int pos = 0;
        for (final XRay.XRay_Block xray_block : this.xray.xray_blocks) {
            final int w = 10 + pos / modh * this.w_;
            final int h = 30 + pos % modh * this.h_;
            final boolean active = (xray_block.getBlocks().length != 0) ? XRay.map.get(xray_block.getBlocks()[0]) : xray_block.toggled();
            this.buttonList.add(new GuiXRay.GuiXRay$1(this, pos, w, h, 20, 20, "", active));
            ++pos;
        }
    }
    
    private boolean isMouseOverSlot(final Slot slotIn, final int mouseX, final int mouseY) {
        return this.isPointInRegion(slotIn.xDisplayPosition, slotIn.yDisplayPosition, 16, 16, mouseX, mouseY);
    }
    
    protected boolean isPointInRegion(final int left, final int top, final int right, final int bottom, int pointX, int pointY) {
        final int i = 0;
        final int j = 0;
        pointX -= i;
        pointY -= j;
        return pointX >= left - 1 && pointX < left + right + 1 && pointY >= top - 1 && pointY < top + bottom + 1;
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode == 1) {
            super.keyTyped(typedChar, keyCode);
        }
        else {
            this.mc.displayGuiScreen((GuiScreen)new GuiXRay());
        }
    }
    
    public void onGuiClosed() {
        XRay.loadRenders();
        super.onGuiClosed();
    }
}

