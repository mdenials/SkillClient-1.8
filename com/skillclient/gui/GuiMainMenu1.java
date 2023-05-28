package com.skillclient.gui;

import com.skillclient.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import com.skillclient.gui.utils.GuiButton;

class GuiMainMenu$1 extends GuiButton {
    public void drawButton(final Minecraft mc, final int mouseX, final int mouseY) {
        super.drawButton(mc, mouseX, mouseY);
        RenderUtils.drawPic(this.xPosition + this.width / 2 - 24, this.yPosition - this.height / 2 - 2, 48, "youtube");
    }
}
