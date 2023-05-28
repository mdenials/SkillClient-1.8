package com.skillclient.gui;

import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import com.skillclient.utils.RenderUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;
import com.skillclient.gui.utils.GuiButton;

public class GuiButtonPicture extends GuiButton
{
    private String title;
    private String url_part;
    int level;
    
    public GuiButtonPicture(final int buttonId, final int x, final int y, final String url_part, final String title) {
        super(buttonId, x, y, 98, 65, "");
        this.level = 0;
        this.url_part = url_part;
        this.title = title;
    }
    
    public String getURL() {
        return "https://www.youtube.com/watch?v=" + this.url_part;
    }
    
    public String getImage() {
        return "https://img.youtube.com/vi/" + this.url_part + "/maxresdefault.jpg";
    }
    
    public void drawButton(final Minecraft mc, final int mouseX, final int mouseY) {
        if (this.visible) {
            this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
            if (this.hovered) {
                if (this.level < 20) {
                    ++this.level;
                }
            }
            else if (this.level >= 0) {
                --this.level;
            }
            final ResourceLocation loc = new ResourceLocation("mcmodding4k/news/" + this.title + ".png");
            RenderUtils.downloadURL(loc, this.getImage());
            RenderUtils.drawPic(this.xPosition, this.yPosition, this.width, this.height, loc);
            if (this.level > 0) {
                final int i = this.level * 255 / 20;
                GlStateManager.enableAlpha();
                this.drawCenteredString(mc.fontRendererObj, this.title, this.xPosition + this.width / 2, this.yPosition - 18 + this.level, new Color(i, i, i, 255).getRGB());
            }
        }
    }
}

