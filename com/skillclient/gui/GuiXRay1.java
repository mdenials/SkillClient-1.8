package com.skillclient.gui;

import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;
import com.skillclient.gui.utils.GuiButton;

class GuiXRay$1 extends GuiButton {
    private final /* synthetic */ boolean val$active;
    
    public void drawButton(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.val$active) {
            GuiXRay$1.BUTTON_TEXTURES = new ResourceLocation("mcmodding4k/widgets2.png");
        }
        else {
            GuiXRay$1.BUTTON_TEXTURES = new ResourceLocation("textures/gui/widgets.png");
        }
        super.drawButton(mc, mouseX, mouseY);
    }
}
