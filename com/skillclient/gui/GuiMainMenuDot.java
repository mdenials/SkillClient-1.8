package com.skillclient.gui;

import java.util.Iterator;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;

public static class Dot
{
    Minecraft mc;
    private float x;
    private float y;
    private float motionx;
    private float motiony;
    
    Dot(final int x, final int y) {
        this.mc = Minecraft.getMinecraft();
        this.x = (float)x;
        this.y = (float)y;
        this.motionx = (float)(GuiMainMenu.r.nextInt(8) - 4);
        this.motiony = (float)(GuiMainMenu.r.nextInt(8) - 4);
    }
    
    public void tick() {
        final float m = 2.0f;
        final float speed = 0.5f;
        if (this.motionx >= m) {
            this.motionx = m;
        }
        if (this.motiony >= m) {
            this.motiony = m;
        }
        if (this.motionx <= -m) {
            this.motionx = -m;
        }
        if (this.motiony <= -m) {
            this.motiony = -m;
        }
        this.x += this.motionx;
        this.y += this.motiony;
        if (this.x < 0.0f) {
            this.motionx = m;
        }
        if (this.y < 0.0f) {
            this.motiony = m;
        }
        if (this.x > Minecraft.getMinecraft().currentScreen.width) {
            this.motionx = -m;
        }
        if (this.y > Minecraft.getMinecraft().currentScreen.height) {
            this.motiony = -m;
        }
        this.motionx += (GuiMainMenu.r.nextBoolean() ? speed : (-speed));
        this.motiony += (GuiMainMenu.r.nextBoolean() ? speed : (-speed));
    }
    
    public void render(final float partialTicks) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GL11.glEnable(3008);
        GL11.glAlphaFunc(517, 0.0f);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2832);
        GL11.glPointSize(5.0f);
        GL11.glBegin(0);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GuiMainMenu.color(this.x + this.motionx * partialTicks, this.y + this.motiony * partialTicks);
        GL11.glVertex2f(this.x + this.motionx * partialTicks, this.y + this.motiony * partialTicks);
        GL11.glEnd();
        GL11.glDisable(2832);
        GL11.glDisable(3042);
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        final double d = this.mc.currentScreen.height * this.mc.currentScreen.width / 100;
        for (final Dot dot : GuiMainMenu.dots) {
            final double d_ = Math.pow(dot.x - this.x, 2.0) + Math.pow(dot.y - this.y, 2.0);
            if (d_ < d && dot != this) {
                GL11.glLineWidth(1.0f);
                GL11.glBegin(1);
                GuiMainMenu.color(this.x + this.motionx * partialTicks, this.y + this.motiony * partialTicks);
                GL11.glVertex2d((double)(this.x + this.motionx * partialTicks), (double)(this.y + this.motiony * partialTicks));
                GuiMainMenu.color(dot.x + dot.motionx * partialTicks, dot.y + dot.motiony * partialTicks);
                GL11.glVertex2d((double)(dot.x + dot.motionx * partialTicks), (double)(dot.y + dot.motiony * partialTicks));
                GL11.glEnd();
            }
        }
    }
    
    public Minecraft getMc() {
        return this.mc;
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public float getMotionx() {
        return this.motionx;
    }
    
    public float getMotiony() {
        return this.motiony;
    }
    
    public void setMc(final Minecraft mc) {
        this.mc = mc;
    }
    
    public void setX(final float x) {
        this.x = x;
    }
    
    public void setY(final float y) {
        this.y = y;
    }
    
    public void setMotionx(final float motionx) {
        this.motionx = motionx;
    }
    
    public void setMotiony(final float motiony) {
        this.motiony = motiony;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Dot)) {
            return false;
        }
        final Dot other = (Dot)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$mc = this.getMc();
        final Object other$mc = other.getMc();
        if (this$mc == null) {
            if (other$mc == null) {
                return Float.compare(this.getX(), other.getX()) == 0 && Float.compare(this.getY(), other.getY()) == 0 && Float.compare(this.getMotionx(), other.getMotionx()) == 0 && Float.compare(this.getMotiony(), other.getMotiony()) == 0;
            }
        }
        else if (this$mc.equals(other$mc)) {
            return Float.compare(this.getX(), other.getX()) == 0 && Float.compare(this.getY(), other.getY()) == 0 && Float.compare(this.getMotionx(), other.getMotionx()) == 0 && Float.compare(this.getMotiony(), other.getMotiony()) == 0;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Dot;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $mc = this.getMc();
        result = result * 59 + (($mc == null) ? 43 : $mc.hashCode());
        result = result * 59 + Float.floatToIntBits(this.getX());
        result = result * 59 + Float.floatToIntBits(this.getY());
        result = result * 59 + Float.floatToIntBits(this.getMotionx());
        result = result * 59 + Float.floatToIntBits(this.getMotiony());
        return result;
    }
    
    @Override
    public String toString() {
        return "GuiMainMenu.Dot(mc=" + this.getMc() + ", x=" + this.getX() + ", y=" + this.getY() + ", motionx=" + this.getMotionx() + ", motiony=" + this.getMotiony() + ")";
    }
}

