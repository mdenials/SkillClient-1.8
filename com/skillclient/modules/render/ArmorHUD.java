package com.skillclient.modules.render;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.item.ItemStack;
import net.minecraft.client.entity.EntityPlayerSP;
import com.skillclient.utils.GuiUtil;
import net.minecraft.client.gui.ScaledResolution;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class ArmorHUD extends Module
{
    ValueNumber LocX;
    ValueNumber LocY;
    ValueBoolean isVertical;
    ValueBoolean showbox;
    public static int LocOffsetX;
    public static int LocOffsetY;
    public static int hight;
    public static int width;
    public static double scale;
    public static float scaleF;
    
    public ArmorHUD() {
        super("ArmorHUD", Register.Category.RENDER, "Displays your Items and Armor ingame");
        this.LocX = (ValueNumber)new ArmorHUD.ArmorHUD$1(this, "LocX", (Module)this, (double)ArmorHUD.mc.currentScreen.width, 0.0, 10.0, 1);
        this.LocY = (ValueNumber)new ArmorHUD.ArmorHUD$2(this, "LocY", (Module)this, (double)ArmorHUD.mc.currentScreen.height, 0.0, 100.0, 1);
        this.isVertical = new ValueBoolean("Vertical", (Module)this, true);
        this.showbox = new ValueBoolean("ShowBox", (Module)this, true);
        this.invisible = true;
    }
    
    public void render(final ScaledResolution res) {
        if (!this.isActive()) {
            return;
        }
        final EntityPlayerSP player = ArmorHUD.mc.thePlayer;
        this.renderItemStack(player.getCurrentArmor(3), this.LocX.getInt() + 5 * ArmorHUD.LocOffsetX, this.LocY.getInt() + 5 * ArmorHUD.LocOffsetY);
        this.renderItemStack(player.getCurrentArmor(2), this.LocX.getInt() + 4 * ArmorHUD.LocOffsetX, this.LocY.getInt() + 4 * ArmorHUD.LocOffsetY);
        this.renderItemStack(player.getCurrentArmor(1), this.LocX.getInt() + 3 * ArmorHUD.LocOffsetX, this.LocY.getInt() + 3 * ArmorHUD.LocOffsetY);
        this.renderItemStack(player.getCurrentArmor(0), this.LocX.getInt() + 2 * ArmorHUD.LocOffsetX, this.LocY.getInt() + 2 * ArmorHUD.LocOffsetY);
        this.renderItemStack(player.getHeldItem(), this.LocX.getInt() + 1 * ArmorHUD.LocOffsetX, this.LocY.getInt() + 1 * ArmorHUD.LocOffsetY);
        if (this.showbox.getValue()) {
            GuiUtil.drawTooltipBox(this.LocX.getInt() + ArmorHUD.LocOffsetX * 5, this.LocY.getInt() + ArmorHUD.LocOffsetY * 5, ArmorHUD.hight, ArmorHUD.width);
        }
        if (this.isVertical.getValue()) {
            ArmorHUD.LocOffsetY = -15;
            ArmorHUD.LocOffsetX = 0;
            ArmorHUD.hight = 17;
            ArmorHUD.width = 93;
        }
        else {
            ArmorHUD.LocOffsetY = 0;
            ArmorHUD.LocOffsetX = -15;
            ArmorHUD.hight = 93;
            ArmorHUD.width = 17;
        }
    }
    
    public void renderItemStack(final ItemStack stack, final int x, final int y) {
        if (stack != null) {
            GL11.glEnable(3042);
            GL11.glPushMatrix();
            GL11.glBlendFunc(770, 771);
            RenderHelper.setColorBuffer(1.0, 1.0, 1.0, 1.0);
            final RenderItem itemRenderer = ArmorHUD.mc.getRenderItem();
            itemRenderer.renderItemAndEffectIntoGUI(stack, x, y);
            itemRenderer.renderItemOverlayIntoGUI(ArmorHUD.mc.fontRendererObj, stack, x, y + 1, "");
            GL11.glDisable(2896);
            GL11.glPopMatrix();
        }
    }
}

