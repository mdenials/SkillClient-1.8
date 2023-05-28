package com.skillclient.gui.accountManager;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import com.skillclient.utils.RenderUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;

private class GuiList extends GuiSlot
{
    private int selectedSlot;
    
    public GuiList(final GuiScreen prevGui) {
        super(GuiAccountManager.mc, GuiAccountManager.this.width, GuiAccountManager.this.height, 40, GuiAccountManager.this.height - 56, 30);
    }
    
    protected boolean isSelected(final int id) {
        return this.selectedSlot == id;
    }
    
    protected int getSelectedSlot() {
        if (this.selectedSlot > AccountManager.accounts.size()) {
            this.selectedSlot = -1;
        }
        return this.selectedSlot;
    }
    
    protected int getSize() {
        return AccountManager.accounts.size();
    }
    
    protected void elementClicked(final int var1, final boolean doubleClick, final int var3, final int var4) {
        this.selectedSlot = var1;
        if (doubleClick) {
            AccountManager.account = AccountManager.accounts.get(GuiAccountManager.access$0(GuiAccountManager.this).getSelectedSlot());
        }
    }
    
    protected void drawSlot(final int id, final int x, final int y, final int var4, final int var5, final int var6) {
        final Account alt = AccountManager.accounts.get(id);
        RenderUtils.drawEntityOnScreen(x + ((id % 2 == 0) ? 0 : 20), y + var4 + 16, 26, (float)this.mouseX, (float)this.mouseY, (EntityLivingBase)alt.getPlayer(), true);
        if (alt.type.equals(AccountManager.AccountType.SESSION)) {
            RenderUtils.drawPic(x + 30, y + 5, 16, "lock");
        }
        if (alt.equals(AccountManager.account)) {
            RenderUtils.drawPic(x + 50, y + 5, 16, "haken");
        }
        this.mc.fontRendererObj.drawString(alt.line1, x + 71, y + 3, 10526880);
        this.mc.fontRendererObj.drawString(alt.line2, x + 71, y + 15, 10526880);
    }
    
    public int getListWidth() {
        return super.getListWidth() + 100;
    }
    
    protected void drawSelectionBox(final int insideLeft, final int insideTop, final int mouseXIn, final int mouseYIn) {
        final int i = this.getSize();
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer vertexbuffer = tessellator.getWorldRenderer();
        for (int j = 0; j < i; ++j) {
            final int k = insideTop + j * this.slotHeight + this.headerPadding;
            final int l = this.slotHeight - 4;
            if (k > this.bottom || k + l < this.top) {
                this.func_178040_a(j, insideLeft, k);
            }
            if (this.showSelectionBox && this.isSelected(j)) {
                final int i2 = this.left + (this.width / 2 - this.getListWidth() / 2) + 69;
                final int j2 = this.left + this.width / 2 + this.getListWidth() / 2;
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                GlStateManager.disableTexture2D();
                vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                vertexbuffer.pos((double)i2, (double)(k + l + 2), 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
                vertexbuffer.pos((double)j2, (double)(k + l + 2), 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
                vertexbuffer.pos((double)j2, (double)(k - 2), 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
                vertexbuffer.pos((double)i2, (double)(k - 2), 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
                vertexbuffer.pos((double)(i2 + 1), (double)(k + l + 1), 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
                vertexbuffer.pos((double)(j2 - 1), (double)(k + l + 1), 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
                vertexbuffer.pos((double)(j2 - 1), (double)(k - 1), 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
                vertexbuffer.pos((double)(i2 + 1), (double)(k - 1), 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
                GlStateManager.enableTexture2D();
            }
            this.drawSlot(j, insideLeft, k, l, mouseXIn, mouseYIn);
        }
    }
    
    protected void drawBackground() {
    }
}

