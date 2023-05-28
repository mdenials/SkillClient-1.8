package com.skillclient.modules.render;

import com.skillclient.events.api.EventTarget;
import java.util.Iterator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntityLockable;
import com.skillclient.events.EventRender;
import com.skillclient.utils.OutlineUtils;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import java.awt.Color;
import net.minecraft.client.model.ModelChest;
import net.minecraft.tileentity.TileEntity;
import com.skillclient.main.Register;
import net.minecraft.util.BlockPos;
import java.util.ArrayList;
import com.skillclient.misc.Module;

public class ChestESP extends Module
{
    public static ArrayList<BlockPos> openedList;
    
    static {
        ChestESP.openedList = new ArrayList<BlockPos>();
    }
    
    public ChestESP() {
        super("ChestESP", Register.Category.RENDER, "Highlights Chests");
    }
    
    public void onEnable() {
        super.onEnable();
        ChestESP.openedList.clear();
    }
    
    public void onDisable() {
        super.onDisable();
        ChestESP.openedList.clear();
    }
    
    public void renderChest(final TileEntity te, final ModelChest modelchest) {
        if (modelchest.chestLid.rotateAngleX != 0.0 && !ChestESP.openedList.contains(te.getPos())) {
            ChestESP.openedList.add(te.getPos());
        }
        if (((ChestESP)Register.getModule((Class)ChestESP.class)).isActive() && te.getWorld() != null) {
            Color colorSide = new Color(255, 255, 255);
            Color colorLine = new Color(255, 255, 255);
            final boolean opened = ChestESP.openedList.contains(te.getPos());
            if (te instanceof TileEntityEnderChest) {
                colorLine = Color.blue;
                colorSide = Color.cyan;
            }
            else if (te instanceof TileEntityChest) {
                if (opened) {
                    colorLine = Color.red;
                }
                else {
                    colorLine = Color.green;
                }
                if (((TileEntityChest)te).getChestType() == 1) {
                    colorSide = Color.red;
                }
                else {
                    colorSide = Color.green;
                }
            }
            else {
                new Exception("Called from wrong target").printStackTrace();
            }
            OutlineUtils.setColor(colorSide);
            modelchest.renderAll();
            OutlineUtils.renderOne(5.0f);
            modelchest.renderAll();
            OutlineUtils.renderTwo();
            modelchest.renderAll();
            OutlineUtils.renderThree();
            OutlineUtils.renderFour();
            OutlineUtils.setColor(colorLine);
            modelchest.renderAll();
            OutlineUtils.renderFive();
            modelchest.renderAll();
            OutlineUtils.setColor(Color.white);
        }
        else {
            modelchest.renderAll();
        }
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        for (final TileEntity tile : ChestESP.mc.theWorld.loadedTileEntityList) {
            if (tile instanceof TileEntityLockable && ((TileEntityLockable)tile).hasCustomName()) {
                final String s = ((TileEntityLockable)tile).getDisplayName().getFormattedText();
                GlStateManager.pushMatrix();
                this.rotate(tile.getPos());
                ChestESP.mc.fontRendererObj.drawString(s, -ChestESP.mc.fontRendererObj.getStringWidth(s) / 2, 0, -1);
                GlStateManager.popMatrix();
            }
        }
    }
    
    private void rotate(final BlockPos pos) {
        final double x = pos.getX() - ChestESP.mc.getRenderManager().renderPosX + 0.5;
        final double y = pos.getY() - ChestESP.mc.getRenderManager().renderPosY + 0.5;
        final double z = pos.getZ() - ChestESP.mc.getRenderManager().renderPosZ + 0.5;
        GlStateManager.translate(x, y, z);
        GlStateManager.color(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-ChestESP.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(ChestESP.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
        final float scale = 0.1f;
        GlStateManager.scale(-scale, -scale, scale);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    }
}
