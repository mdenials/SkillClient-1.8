package com.skillclient.utils;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.client.renderer.RenderGlobal;
import java.awt.Color;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.IImageBuffer;
import java.io.File;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.Vec3i;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3d;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

public class RenderUtils
{
    public static int enemy;
    public static int friend;
    public static int other;
    public static int target;
    public static int team;
    
    static {
        RenderUtils.enemy = 0;
        RenderUtils.friend = 1;
        RenderUtils.other = 2;
        RenderUtils.target = 3;
        RenderUtils.team = 4;
    }
    
    public static void tracerLine(final BlockPos loc, final int color) {
        final double d0 = loc.getX() - Minecraft.getMinecraft().getRenderManager().renderPosX;
        final double d2 = loc.getY() - Minecraft.getMinecraft().getRenderManager().renderPosY;
        final double d3 = loc.getZ() - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        if (color == 0) {
            GL11.glColor4d(1.0 - Minecraft.getMinecraft().thePlayer.getDistanceSqToCenter(loc) / 40.0, Minecraft.getMinecraft().thePlayer.getDistanceSqToCenter(loc) / 40.0, 0.0, 0.5);
        }
        else if (color == 1) {
            GL11.glColor4d(0.0, 0.0, 1.0, 0.5);
        }
        else if (color == 2) {
            GL11.glColor4d(1.0, 1.0, 0.0, 0.5);
        }
        else if (color == 3) {
            GL11.glColor4d(1.0, 0.0, 0.0, 0.5);
        }
        else if (color == 4) {
            GL11.glColor4d(0.0, 1.0, 0.0, 0.5);
        }
        final Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Minecraft.getMinecraft().thePlayer.rotationPitch)).rotateYaw(-(float)Math.toRadians(Minecraft.getMinecraft().thePlayer.rotationYaw));
        GL11.glBegin(1);
        GL11.glVertex3d(vec3d.xCoord, Minecraft.getMinecraft().thePlayer.getEyeHeight() + vec3d.yCoord, vec3d.zCoord);
        GL11.glVertex3d(d0, d2, d3);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void framelessBlockESP(final int x, final int y, final int z, final int xoffset, final int yoffset, final int zoffset, final float red, final float green, final float blue) {
        final double d0 = x - Minecraft.getMinecraft().getRenderManager().renderPosX;
        final double d2 = y - Minecraft.getMinecraft().getRenderManager().renderPosY;
        final double d3 = z - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, 0.15f);
        drawColorBox(new AxisAlignedBB(d0, d2, d3, d0 + xoffset, d2 + yoffset, d3 + zoffset), red, green, blue, 0.15f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void framelessBlockESP(final BlockPos pos, final Vec3i offset, final float red, final float green, final float blue) {
        final double d0 = pos.getX() - Minecraft.getMinecraft().getRenderManager().renderPosX + 0.5;
        final double d2 = pos.getY() - Minecraft.getMinecraft().getRenderManager().renderPosY + 0.5;
        final double d3 = pos.getZ() - Minecraft.getMinecraft().getRenderManager().renderPosZ + 0.5;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, 0.15f);
        final double d4 = 0.25;
        final double x = offset.getX() * (0.5 - d4);
        final double y = offset.getY() * (0.5 - d4);
        final double z = offset.getZ() * (0.5 - d4);
        drawColorBox(new AxisAlignedBB(d0 - d4 + x, d2 - d4 + y, d3 - d4 + z, d0 + d4 + x, d2 + d4 + y, d3 + d4 + z), red, green, blue, 0.15f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void renderString(final BlockPos pos, final String s) {
        GlStateManager.pushMatrix();
        final Minecraft mc = Minecraft.getMinecraft();
        final double x = pos.getX() - mc.getRenderManager().renderPosX + 0.5;
        final double y = pos.getY() - mc.getRenderManager().renderPosY + 1.0;
        final double z = pos.getZ() - mc.getRenderManager().renderPosZ + 0.5;
        GlStateManager.translate(x, y, z);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
        final float scale = 0.05f;
        GlStateManager.scale(-scale, -scale, scale);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.disableBlend();
        mc.fontRendererObj.drawString(s, -mc.fontRendererObj.getStringWidth(s) / 2, 0, -1);
        GlStateManager.popMatrix();
    }
    
    public static void drawEntityOnScreen(final int posX, final int posY, final int scale, final float mouseX, final float mouseY, final EntityLivingBase ent, final boolean rotate) {
        GlStateManager.enableDepth();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0f);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        final float f = ent.renderYawOffset;
        final float f2 = ent.rotationYaw;
        final float f3 = ent.rotationPitch;
        final float f4 = ent.prevRotationYawHead;
        final float f5 = ent.rotationYawHead;
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        if (rotate) {
            ent.renderYawOffset = (float)Math.atan((posX - mouseX) / 40.0f) * 20.0f;
            ent.rotationYaw = (float)Math.atan((posX - mouseX) / 40.0f) * 40.0f;
            GlStateManager.rotate(-(float)Math.atan((posY - mouseY) / 40.0f - (double)ent.getEyeHeight()) * 20.0f, 1.0f, 0.0f, 0.0f);
            ent.rotationPitch = -(float)Math.atan((posY - mouseY) / 40.0f - (double)ent.getEyeHeight()) * 20.0f;
        }
        else {
            ent.renderYawOffset = 0.0f;
            ent.rotationYaw = 0.0f;
            ent.rotationPitch = 0.0f;
        }
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0f);
        rendermanager.setRenderShadow(false);
        rendermanager.doRenderEntity((Entity)ent, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f2;
        ent.rotationPitch = f3;
        ent.prevRotationYawHead = f4;
        ent.rotationYawHead = f5;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.disableDepth();
    }
    
    public static ThreadDownloadImageData downloadURL(final ResourceLocation resourceLocationIn, final String url) {
        final TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        ITextureObject itextureobject = texturemanager.getTexture(resourceLocationIn);
        if (itextureobject == null) {
            itextureobject = (ITextureObject)new ThreadDownloadImageData((File)null, url, (ResourceLocation)null, (IImageBuffer)null);
            texturemanager.loadTexture(resourceLocationIn, itextureobject);
        }
        return (ThreadDownloadImageData)itextureobject;
    }
    
    public static void drawPic(final int x, final int y, final int h, final int w, final ResourceLocation pic) {
        GlStateManager.enableAlpha();
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer vertexbuffer = tessellator.getWorldRenderer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(pic);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        vertexbuffer.pos((double)x, (double)(y + w), 0.0).tex(0.0, 1.0).color(255, 255, 255, 255).endVertex();
        vertexbuffer.pos((double)(x + h), (double)(y + w), 0.0).tex(1.0, 1.0).color(255, 255, 255, 255).endVertex();
        vertexbuffer.pos((double)(x + h), (double)y, 0.0).tex(1.0, 0.0).color(255, 255, 255, 255).endVertex();
        vertexbuffer.pos((double)x, (double)y, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
        tessellator.draw();
        GlStateManager.disableAlpha();
    }
    
    public static void drawPic(final int x, final int y, final int h, final String pic) {
        drawPic(x, y, h, h, new ResourceLocation("mcmodding4k/" + pic + ".png"));
    }
    
    public static void framelessBlockESP(final BlockPos blockPos, final float red, final float green, final float blue, final float alpha) {
        final double d0 = blockPos.getX() - Minecraft.getMinecraft().getRenderManager().renderPosX;
        final double d2 = blockPos.getY() - Minecraft.getMinecraft().getRenderManager().renderPosY;
        final double d3 = blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        drawColorBox(new AxisAlignedBB(d0, d2, d3, d0 + 1.0, d2 + 1.0, d3 + 1.0), red, green, blue, alpha);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void box(double x, double y, double z, double x2, double y2, double z2, final Color color) {
        x -= Minecraft.getMinecraft().getRenderManager().renderPosX;
        y -= Minecraft.getMinecraft().getRenderManager().renderPosY;
        z -= Minecraft.getMinecraft().getRenderManager().renderPosZ;
        x2 -= Minecraft.getMinecraft().getRenderManager().renderPosX;
        y2 -= Minecraft.getMinecraft().getRenderManager().renderPosY;
        z2 -= Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        setColor(color);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        drawColorBox(new AxisAlignedBB(x, y, z, x2, y2, z2));
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x2, y2, z2), -1);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void frame(double x, double y, double z, double x2, double y2, double z2, final Color color) {
        x -= Minecraft.getMinecraft().getRenderManager().renderPosX;
        y -= Minecraft.getMinecraft().getRenderManager().renderPosY;
        z -= Minecraft.getMinecraft().getRenderManager().renderPosZ;
        x2 -= Minecraft.getMinecraft().getRenderManager().renderPosX;
        y2 -= Minecraft.getMinecraft().getRenderManager().renderPosY;
        z2 -= Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        setColor(color);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x2, y2, z2), -1);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void blockESPBox(final BlockPos blockPos) {
        final double x = blockPos.getX() - Minecraft.getMinecraft().getRenderManager().renderPosX;
        final double y = blockPos.getY() - Minecraft.getMinecraft().getRenderManager().renderPosY;
        final double z = blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        GL11.glColor4d(0.0, 1.0, 0.0, 0.15000000596046448);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        AxisAlignedBB box = new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0);
        final Block block = Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock();
        box = block.getSelectedBoundingBox((World)Minecraft.getMinecraft().theWorld, blockPos);
        drawColorBox(box);
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        RenderGlobal.drawOutlinedBoundingBox(box, -1);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void framelessBlockESP(final BlockPos blockPos, final Color color) {
        final double x = blockPos.getX() - Minecraft.getMinecraft().getRenderManager().renderPosX;
        final double y = blockPos.getY() - Minecraft.getMinecraft().getRenderManager().renderPosY;
        final double z = blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glColor4d((double)(color.getRed() / 255), (double)(color.getGreen() / 255), (double)(color.getBlue() / 255), 0.15);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        drawColorBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void emptyBlockESPBox(final BlockPos blockPos) {
        final double x = blockPos.getX() - Minecraft.getMinecraft().getRenderManager().renderPosX;
        final double y = blockPos.getY() - Minecraft.getMinecraft().getRenderManager().renderPosY;
        final double z = blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -1);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void entityESPBox(final Entity entity, final float r, final float g, final float b, final float a) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d((double)r, (double)g, (double)b, (double)a);
        Minecraft.getMinecraft().getRenderManager();
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(entity.boundingBox.minX - 0.05 - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().renderPosX), entity.boundingBox.minY - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().renderPosY), entity.boundingBox.minZ - 0.05 - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ), entity.boundingBox.maxX + 0.05 - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().renderPosX), entity.boundingBox.maxY + 0.1 - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().renderPosY), entity.boundingBox.maxZ + 0.05 - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ)), -1);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void entityESPBox(final Entity entity, final int mode) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        if (mode == 0) {
            GL11.glColor4d((double)(1.0f - Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) / 40.0f), (double)(Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) / 40.0f), 0.0, 0.5);
        }
        else if (mode == 1) {
            GL11.glColor4d(0.0, 0.0, 1.0, 0.5);
        }
        else if (mode == 2) {
            GL11.glColor4d(1.0, 1.0, 0.0, 0.5);
        }
        else if (mode == 3) {
            GL11.glColor4d(1.0, 0.0, 0.0, 0.5);
        }
        else if (mode == 4) {
            GL11.glColor4d(0.0, 1.0, 0.0, 0.5);
        }
        Minecraft.getMinecraft().getRenderManager();
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(entity.boundingBox.minX - 0.05 - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().renderPosX), entity.boundingBox.minY - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().renderPosY), entity.boundingBox.minZ - 0.05 - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ), entity.boundingBox.maxX + 0.05 - entity.posX + (entity.posX - Minecraft.getMinecraft().getRenderManager().renderPosX), entity.boundingBox.maxY + 0.1 - entity.posY + (entity.posY - Minecraft.getMinecraft().getRenderManager().renderPosY), entity.boundingBox.maxZ + 0.05 - entity.posZ + (entity.posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ)), -1);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void nukerBox(final BlockPos blockPos, final float damage) {
        final double x = blockPos.getX() - Minecraft.getMinecraft().getRenderManager().renderPosX;
        final double y = blockPos.getY() - Minecraft.getMinecraft().getRenderManager().renderPosY;
        final double z = blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        GL11.glColor4d((double)damage, (double)(1.0f - damage), 0.0, 0.15000000596046448);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        drawColorBox(new AxisAlignedBB(x + 0.5 - damage / 2.0f, y + 0.5 - damage / 2.0f, z + 0.5 - damage / 2.0f, x + 0.5 + damage / 2.0f, y + 0.5 + damage / 2.0f, z + 0.5 + damage / 2.0f));
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(x + 0.5 - damage / 2.0f, y + 0.5 - damage / 2.0f, z + 0.5 - damage / 2.0f, x + 0.5 + damage / 2.0f, y + 0.5 + damage / 2.0f, z + 0.5 + damage / 2.0f), -1);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void searchBox(final BlockPos blockPos) {
        final double x = blockPos.getX() - Minecraft.getMinecraft().getRenderManager().renderPosX;
        final double y = blockPos.getY() - Minecraft.getMinecraft().getRenderManager().renderPosY;
        final double z = blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.0f);
        final float sinus = 1.0f - MathHelper.abs(MathHelper.sin(Minecraft.getSystemTime() % 10000L / 10000.0f * 3.1415927f * 4.0f) * 1.0f);
        GL11.glColor4d((double)(1.0f - sinus), (double)sinus, 0.0, 0.15);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        drawColorBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glColor4d(0.0, 0.0, 0.0, 0.5);
        RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -1);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void drawColorBox(final AxisAlignedBB axisalignedbb) {
        drawColorBox(axisalignedbb, 255.0f, 255.0f, 255.0f, 255.0f);
    }
    
    public static void drawColorBox(final AxisAlignedBB axisalignedbb, final float red, final float green, final float blue, final float alpha) {
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer vertexbuffer = tessellator.getWorldRenderer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vertexbuffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
    }
    
    public static void tracerLine(final Entity entity, final int mode) {
        if (mode == 0) {
            tracerLine(entity, 1.0f - Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) / 40.0f, Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity) / 40.0f, 0.0, 0.5);
        }
        else if (mode == 1) {
            tracerLine(entity, 0.0, 0.0, 1.0, 0.5);
        }
        else if (mode == 2) {
            tracerLine(entity, 1.0, 1.0, 0.0, 0.5);
        }
        else if (mode == 3) {
            tracerLine(entity, 1.0, 0.0, 0.0, 0.5);
        }
        else if (mode == 4) {
            tracerLine(entity, 0.0, 1.0, 0.0, 0.5);
        }
    }
    
    public static void tracerLine(final Entity entity, final double red, final double green, final double blue, final double alpha) {
        final double d0 = entity.posX - Minecraft.getMinecraft().getRenderManager().renderPosX;
        final double d2 = entity.posY + entity.height / 2.0f - Minecraft.getMinecraft().getRenderManager().renderPosY;
        final double d3 = entity.posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d(red, green, blue, alpha);
        final Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(Minecraft.getMinecraft().thePlayer.rotationPitch)).rotateYaw(-(float)Math.toRadians(Minecraft.getMinecraft().thePlayer.rotationYaw));
        GL11.glBegin(1);
        GL11.glVertex3d(vec3d.xCoord, Minecraft.getMinecraft().thePlayer.getEyeHeight() + vec3d.yCoord, vec3d.zCoord);
        GL11.glVertex3d(d0, d2, d3);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void tracerLine(final Entity entity, final Color color) {
        final double x = entity.posX - Minecraft.getMinecraft().getRenderManager().renderPosX;
        final double y = entity.posY + entity.height / 2.0f - Minecraft.getMinecraft().getRenderManager().renderPosY;
        final double z = entity.posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        setColor(color);
        GL11.glBegin(1);
        GL11.glVertex3d(0.0, (double)Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void tracerLine(int x, int y, int z, final Color color) {
        x += (int)(0.5 - Minecraft.getMinecraft().getRenderManager().renderPosX);
        y += (int)(0.5 - Minecraft.getMinecraft().getRenderManager().renderPosY);
        z += (int)(0.5 - Minecraft.getMinecraft().getRenderManager().renderPosZ);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        setColor(color);
        GL11.glBegin(1);
        GL11.glVertex3d(0.0, (double)Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d((double)x, (double)y, (double)z);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void tracerLine(double x, double y, double z, final Color color) {
        x -= Minecraft.getMinecraft().getRenderManager().renderPosX;
        y -= Minecraft.getMinecraft().getRenderManager().renderPosY;
        z -= Minecraft.getMinecraft().getRenderManager().renderPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        setColor(color);
        GL11.glBegin(1);
        GL11.glVertex3d(0.0, (double)Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void scissorBox(final int x, final int y, final int xend, final int yend) {
        final int width = xend - x;
        final int height = yend - y;
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        final int factor = sr.getScaleFactor();
        final int bottomY = Minecraft.getMinecraft().currentScreen.height - yend;
        GL11.glScissor(x * factor, bottomY * factor, width * factor, height * factor);
    }
    
    public static void setColor(final Color c) {
        GL11.glColor4f(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
    }
    
    public static void drawLine(final double x, final double y, final double x_, final double y_, final Color color) {
        GL11.glLineWidth(5.0f);
        GL11.glDisable(3553);
        setColor(color);
        GL11.glBegin(1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x_, y_);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
}

