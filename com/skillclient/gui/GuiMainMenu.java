package com.skillclient.gui;

import org.lwjgl.opengl.GL11;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.skillclient.utils.Download;
import net.minecraft.client.gui.FontRenderer;
import com.skillclient.main.SkillClient;
import com.skillclient.utils.RenderUtils;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager;
import java.util.Iterator;
import org.lwjgl.input.Mouse;
import java.io.IOException;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiOptions;
import com.skillclient.chat.Chat;
import com.skillclient.gui.list.SkillListGui;
import com.skillclient.gui.utils.GuiButton;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.client.gui.GuiScreen;

public class GuiMainMenu extends GuiScreen
{
    public static boolean openedGuiNewVersion;
    public static Random r;
    public static ArrayList<GuiMainMenu.Dot> dots;
    
    static {
        GuiMainMenu.openedGuiNewVersion = false;
        GuiMainMenu.r = new Random();
        GuiMainMenu.dots = new ArrayList<GuiMainMenu.Dot>();
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.id == -1) {
            GuiMainMenu.mc.displayGuiScreen((GuiScreen)new SkillListGui());
        }
        if (button.id == -3) {
            Chat.openWebLink("https://www.youtube.com/MCModding4K?sub_confirmation=1");
        }
        if (button.id == -2) {
            GuiNewVersion.openLinks();
        }
        if (button.id == 0) {
            GuiMainMenu.mc.displayGuiScreen((GuiScreen)new GuiOptions((GuiScreen)this, GuiMainMenu.mc.gameSettings));
        }
        if (button.id == 1) {
            GuiMainMenu.mc.displayGuiScreen((GuiScreen)new GuiSelectWorld((GuiScreen)this));
        }
        if (button.id == 2) {
            GuiMainMenu.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)this));
        }
        if (button.id == 4) {
            GuiMainMenu.mc.shutdown();
        }
        if (button.id == 5) {
            GuiMainMenu.mc.displayGuiScreen((GuiScreen)new GuiChangeLog());
        }
    }
    
    public void updateScreen() {
        final int i = Mouse.getEventX() * this.width / GuiMainMenu.mc.displayWidth;
        final int j = this.height - Mouse.getEventY() * this.height / GuiMainMenu.mc.displayHeight - 1;
        if (Mouse.isButtonDown(0)) {
            GuiMainMenu.dots.add(new GuiMainMenu.Dot(i, j));
            GuiMainMenu.dots.remove(0);
        }
        for (final GuiMainMenu.Dot dot : GuiMainMenu.dots) {
            dot.tick();
        }
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void drawDefaultBackground() {
        GlStateManager.disableAlpha();
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GuiMainMenu.mc.getTextureManager().bindTexture(new ResourceLocation("mcmodding4k/background.png"));
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        worldrenderer.pos(0.0, (double)this.height, 0.0).tex(0.0, 1.0).color(255, 255, 255, 255).endVertex();
        worldrenderer.pos((double)this.width, (double)this.height, 0.0).tex(1.0, 1.0).color(255, 255, 255, 255).endVertex();
        worldrenderer.pos((double)this.width, 0.0, 0.0).tex(1.0, 0.0).color(255, 255, 255, 255).endVertex();
        worldrenderer.pos(0.0, 0.0, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
        tessellator.draw();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        final int w = this.width / 2;
        RenderUtils.drawPic(this.width / 2 - w / 2, this.height / 5, w, w / 6, new ResourceLocation("mcmodding4k/logo.png"));
        for (final GuiMainMenu.Dot dot : GuiMainMenu.dots) {
            dot.render(partialTicks);
        }
        RenderUtils.drawPic(this.width / 2 - w / 2, this.height / 5, w, w / 6, new ResourceLocation("mcmodding4k/logo.png"));
        final FontRenderer fontRendererObj = this.fontRendererObj;
        final StringBuilder sb = new StringBuilder("§a");
        GuiMainMenu.sc.getClass();
        this.drawString(fontRendererObj, sb.append("Minecraft 1.8.8 | SkillClient b8.1").toString(), 2, this.height - 10, -1);
        this.drawString(this.fontRendererObj, "§7" + "OptiFine_1.8.8_HD_U_E1".replace("_", " ") + "§r", 2, this.height - 20, -1);
        this.drawString(this.fontRendererObj, "§a" + GuiMainMenu.sc.chat.chatmodules.size() + " Commands available", 2, this.height - 30, -1);
        this.drawString(this.fontRendererObj, "§a" + SkillClient.getClient().moduleList.size() + " Mods available", 2, this.height - 40, -1);
        this.drawString(this.fontRendererObj, "§7" + GuiNewVersion.info + "§r", this.width - this.fontRendererObj.getStringWidth(GuiNewVersion.info) - 2, this.height - 10, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void initGui() {
        final SkillClient sc = SkillClient.getClient();
        if (SkillClient.HAS_NEW_VERSION.equals(Download.VersionState.update) && !GuiMainMenu.openedGuiNewVersion && !sc.indev) {
            GuiMainMenu.mc.displayGuiScreen((GuiScreen)new GuiNewVersion());
        }
        this.buttonList.add(new GuiButton(1, 10, 10, 100, 20, ChatFormatting.GOLD + "SinglePlayer"));
        this.buttonList.add(new GuiButton(2, 10, 35, 100, 20, ChatFormatting.BLUE + "MultiPlayer"));
        this.buttonList.add(new GuiButton(0, 10, 60, 100, 20, ChatFormatting.DARK_PURPLE + "Settings..."));
        this.buttonList.add(new GuiButton(-1, 10, 85, 100, 20, ChatFormatting.AQUA + "Modules"));
        this.buttonList.add(new GuiButton(5, 10, 110, 100, 20, ChatFormatting.YELLOW + "ChangeLog"));
        this.buttonList.add(new GuiButton(-2, 10, 135, 100, 20, SkillClient.HAS_NEW_VERSION.text));
        this.buttonList.add(new GuiMainMenu.GuiMainMenu$1(this, -3, 10, 160, 100, 20, ""));
        this.buttonList.add(new GuiButton(4, 10, 185, 100, 20, ChatFormatting.GRAY + "Quit"));
        GuiMainMenu.dots.clear();
        for (int i = 0; i < GuiMainMenu.mc.currentScreen.height / 2; ++i) {
            GuiMainMenu.dots.add(new GuiMainMenu.Dot(GuiMainMenu.r.nextInt(this.width), GuiMainMenu.r.nextInt(this.height)));
        }
    }
    
    public static void color(final double x, final double z) {
        GL11.glColor3d(getColor(x), getColor(x * 2.0 + z * 2.0), getColor(z));
    }
    
    private static double getColor(final double v) {
        final double d = 128.0;
        return ((v % d * 2.0 >= d) ? (d - v % d) : (v % d)) / d * 2.0;
    }
}
