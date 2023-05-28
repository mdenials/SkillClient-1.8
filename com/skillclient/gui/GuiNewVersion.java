package com.skillclient.gui;

import net.minecraft.client.Minecraft;
import com.skillclient.chat.Chat;
import java.io.IOException;
import com.skillclient.gui.utils.GuiButton;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager;
import java.util.List;
import java.awt.Color;
import java.util.ArrayList;
import com.skillclient.utils.TimerUtil;
import net.minecraft.client.gui.GuiScreen;

public class GuiNewVersion extends GuiScreen
{
    Integer[] c;
    int cmode;
    TimerUtil ColorTimer;
    public static String info;
    
    static {
        GuiNewVersion.info = "";
    }
    
    public GuiNewVersion() {
        this.cmode = 0;
        this.ColorTimer = new TimerUtil();
        final int strength = 20;
        final List<Integer> colors = new ArrayList<Integer>();
        for (int r = 0; r < strength; ++r) {
            colors.add(new Color(r * 255 / strength, 255, 0).getRGB());
        }
        for (int g = strength; g > 0; --g) {
            colors.add(new Color(255, g * 255 / strength, 0).getRGB());
        }
        for (int b = 0; b < strength; ++b) {
            colors.add(new Color(255, 0, b * 255 / strength).getRGB());
        }
        for (int r = strength; r > 0; --r) {
            colors.add(new Color(r * 255 / strength, 0, 255).getRGB());
        }
        for (int g = 0; g < strength; ++g) {
            colors.add(new Color(0, g * 255 / strength, 255).getRGB());
        }
        for (int b = strength; b > 0; --b) {
            colors.add(new Color(0, 255, b * 255 / strength).getRGB());
        }
        this.c = colors.toArray(new Integer[colors.size()]);
    }
    
    public void drawDefaultBackground() {
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GuiNewVersion.mc.getTextureManager().bindTexture(new ResourceLocation("mcmodding4k/skillclient-logo.png"));
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldrenderer.pos(0.0, (double)this.height, 0.0).tex(0.0, 1.0).color(255, 255, 255, 255).endVertex();
        worldrenderer.pos((double)this.width, (double)this.height, 0.0).tex(1.0, 1.0).color(255, 255, 255, 255).endVertex();
        worldrenderer.pos((double)this.width, 0.0, 0.0).tex(1.0, 0.0).color(255, 255, 255, 255).endVertex();
        worldrenderer.pos(0.0, 0.0, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
        tessellator.draw();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        while (this.ColorTimer.everyDelay(50L)) {
            ++this.cmode;
        }
        GlStateManager.disableAlpha();
        this.drawDefaultBackground();
        int pos = 0;
        char[] charArray;
        for (int length = (charArray = "New Version available".toCharArray()).length, i = 0; i < length; ++i) {
            final char ch = charArray[i];
            this.drawCenteredString(GuiNewVersion.mc.fontRendererObj, new StringBuilder().append(ch).toString(), this.width / 2 + pos * 6 - 63, 20, (int)this.c[(pos * 2 + this.cmode + this.c.length / 2) % this.c.length]);
            ++pos;
        }
        this.drawCenteredString(GuiNewVersion.mc.fontRendererObj, GuiNewVersion.info, this.width / 2, 40, (int)this.c[(pos * 2 + this.cmode + this.c.length / 2) % this.c.length]);
        this.drawCenteredString(GuiNewVersion.mc.fontRendererObj, GuiNewVersion.info, this.width / 2, 60, (int)this.c[(pos * 2 + this.cmode + this.c.length / 2) % this.c.length]);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void initGui() {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 2 + 10, 200, 20, "Update"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 2 + 40, 200, 20, "Ask me later"));
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        switch (button.id) {
            case 1: {
                openLinks();
                break;
            }
            case 2: {
                GuiMainMenu.openedGuiNewVersion = true;
                GuiNewVersion.mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
                break;
            }
        }
    }
    
    public static void openLinks() {
        Chat.openWebLink("http://www.wizardhax.com/skillclient");
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            Chat.openWebLink("http://skillclient.tk/download.html");
            Minecraft.getMinecraft().shutdown();
        }).start();
    }
}

