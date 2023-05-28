package com.skillclient.gui.click.components;

import java.awt.Color;
import com.skillclient.utils.RenderGuiUtil;
import com.skillclient.gui.click.SkillClickGui;

class FrameButtonModule$1 extends FrameTree {
    public boolean isExpanded() {
        return true;
    }
    
    public void setExpanded(final boolean expanded) {
        SkillClickGui.removeFrame((Frame)this);
        SkillClickGui.moduleFrames.remove(FrameButtonModule.access$0(FrameButtonModule.this));
    }
    
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        FrameButtonModule$1.mc.fontRendererObj.drawString("§4\u2718", this.getPosX() + this.getWidth() - 7, this.getPosY() + 2, RenderGuiUtil.isHovered(mouseX, mouseY, (Frame)this) ? Color.green.getRGB() : -1);
    }
}
