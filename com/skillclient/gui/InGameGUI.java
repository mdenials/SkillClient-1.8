package com.skillclient.gui;

import com.skillclient.misc.Value;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import org.lwjgl.input.Keyboard;
import com.skillclient.main.SkillClient;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import com.skillclient.modules.render.ArmorHUD;
import net.minecraft.client.Minecraft;
import com.skillclient.utils.RenderUtils;
import net.minecraft.util.ResourceLocation;
import com.skillclient.main.Register;
import com.skillclient.modules.misc.HUD;
import net.minecraft.client.gui.ScaledResolution;
import java.util.List;
import java.awt.Color;
import com.skillclient.misc.Module;
import java.util.ArrayList;
import com.skillclient.utils.TimerUtil;
import com.skillclient.misc.SCMC;
import net.minecraft.client.gui.Gui;

public class InGameGUI extends Gui implements SCMC
{
    private static InGameGUI thegui;
    Integer[] c;
    int cmode;
    TimerUtil ColorTimer;
    static int[] height;
    static int[] selected;
    static int row;
    static ArrayList<Module> modules;
    
    static {
        InGameGUI.thegui = new InGameGUI();
        InGameGUI.height = new int[2];
        InGameGUI.selected = new int[2];
        InGameGUI.row = -1;
    }
    
    public static InGameGUI getInGameGUI() {
        return InGameGUI.thegui;
    }
    
    public static void refresh() {
        InGameGUI.thegui = new InGameGUI();
    }
    
    public InGameGUI() {
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
    
    public void renderScreen(final ScaledResolution scaledresolution) {
        if (!((HUD)Register.getModule((Class)HUD.class)).isActive()) {
            return;
        }
        while (this.ColorTimer.everyDelay(50L)) {
            ++this.cmode;
        }
        RenderUtils.drawPic(3, 1, 128, 16, new ResourceLocation("mcmodding4k/logo.png"));
        final FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
        InGameGUI.sc.getClass();
        fontRendererObj.drawString("b8.1", 125, 11, (int)this.c[(this.cmode + this.c.length / 2) % this.c.length]);
        if (InGameGUI.row == -1) {
            this.renderList();
        }
        else {
            this.renderTabGUI();
        }
        ((ArmorHUD)Register.getModule((Class)ArmorHUD.class)).render(scaledresolution);
        if (((Value<Boolean, D>)((HUD)Register.getModule((Class)HUD.class)).skin).getValue()) {
            RenderUtils.drawEntityOnScreen(scaledresolution.getScaledWidth() / 4 * 3, scaledresolution.getScaledHeight() - 10, 30, 0.0f, 0.0f, (EntityLivingBase)Minecraft.getMinecraft().thePlayer, false);
        }
        GlStateManager.enableDepth();
    }
    
    public void renderList() {
        final ArrayList<String> strings = new ArrayList<String>();
        for (final Module module : SkillClient.getClient().moduleList) {
            if (!module.invisible && module.isToggled()) {
                strings.add(String.valueOf((module.getKeyBinding().getKeyCode() == 0) ? "" : new StringBuilder("[").append(Keyboard.getKeyName(module.getKeyBinding().getKeyCode())).append("]").toString()) + module.getDisplayName());
            }
        }
        Collections.sort(strings, (Comparator<? super String>)new InGameGUI.InGameGUI$1(this));
        final int j = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
        int count = 0;
        for (final String s : strings) {
            final int k = Minecraft.getMinecraft().fontRendererObj.getStringWidth(s);
            final int i1 = 20 + count * (j + 1);
            final Color cl = new Color(this.c[(this.cmode + count) % this.c.length]);
            drawRect(1, i1 - 1, 3 + k, i1 + j - 1, new Color(128, 128, 128, 128).getRGB());
            Minecraft.getMinecraft().fontRendererObj.drawString(s, 2, i1, (int)this.c[(this.cmode + count) % this.c.length]);
            ++count;
        }
    }
    
    public void renderTabGUI() {
        final int j = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
        int count = 0;
        Register.Category[] normal;
        for (int length = (normal = Register.Category.normal).length, k = 0; k < length; ++k) {
            final Register.Category g = normal[k];
            final int i1 = 15 + count * (j + 2);
            drawRect(1, i1 - 1, (count == InGameGUI.selected[0]) ? ((InGameGUI.row == 0) ? 60 : 52) : 50, i1 + j - 1, -1873784752);
            Minecraft.getMinecraft().fontRendererObj.drawString(g.getName(), 2, i1, (int)this.c[(this.cmode + count) % this.c.length]);
            ++count;
        }
        if (InGameGUI.row >= 1) {
            count = 0;
            InGameGUI.height[1] = InGameGUI.modules.size();
            for (final Module modul : InGameGUI.modules) {
                final int i2 = 15 + (count + InGameGUI.selected[0]) * (j + 2);
                drawRect(52, i2 - 1, (count == InGameGUI.selected[1]) ? 170 : 160, i2 + j - 1, modul.isToggled() ? Color.WHITE.getRGB() : -1873784752);
                Minecraft.getMinecraft().fontRendererObj.drawString(modul.getDisplayName(), 52, i2, (int)this.c[(this.cmode + count) % this.c.length]);
                ++count;
            }
        }
    }
    
    public static void handleKeyboardInput() {
        Keyboard.enableRepeatEvents(true);
        if (!Keyboard.getEventKeyState()) {
            return;
        }
        if (!((Value<Boolean, D>)((HUD)Register.getModule((Class)HUD.class)).arrowkeys).getValue()) {
            return;
        }
        InGameGUI.height[0] = Register.Category.normal.length;
        final int key = Keyboard.getEventKey();
        if (key == 200 || key == 203 || key == 205 || key == 208 || key == 28) {
            if (InGameGUI.row == -1) {
                if (key != 205) {
                    return;
                }
            }
            try {
                switch (key) {
                    case 205: {
                        if (InGameGUI.row == 1) {
                            InGameGUI.modules.get(InGameGUI.selected[1]).toggle();
                            break;
                        }
                        ++InGameGUI.row;
                        InGameGUI.selected[InGameGUI.row] = 0;
                        Collections.sort(InGameGUI.modules = (ArrayList<Module>)Register.Category.normal[InGameGUI.selected[0]].getModules(), (Comparator<? super Module>)new InGameGUI.InGameGUI$2());
                        break;
                    }
                    case 203: {
                        if (key == 203) {
                            --InGameGUI.row;
                        }
                        InGameGUI.row = Math.min(InGameGUI.height.length - 1, InGameGUI.row);
                        if (InGameGUI.row == -1) {
                            return;
                        }
                        break;
                    }
                    case 208: {
                        final int[] selected = InGameGUI.selected;
                        final int row = InGameGUI.row;
                        ++selected[row];
                        break;
                    }
                    case 200: {
                        final int[] selected2 = InGameGUI.selected;
                        final int row2 = InGameGUI.row;
                        --selected2[row2];
                        break;
                    }
                }
            }
            catch (Exception e) {
                InGameGUI.selected[0] = (InGameGUI.selected[1] = 1);
            }
            InGameGUI.selected[InGameGUI.row] = Math.max(0, InGameGUI.selected[InGameGUI.row]);
            InGameGUI.selected[InGameGUI.row] = Math.min(InGameGUI.height[InGameGUI.row] - 1, InGameGUI.selected[InGameGUI.row]);
        }
    }
}

