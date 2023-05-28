package com.skillclient.utils;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3d;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

public class Rotation
{
    private static final Minecraft mc;
    float yaw;
    float pitch;
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public Rotation(final Entity entity) {
        final double x = entity.posX - Rotation.mc.thePlayer.posX;
        final double y = entity.posY + entity.getEyeHeight() / 3.0f * 2.0f - Rotation.mc.thePlayer.posY - Rotation.mc.thePlayer.getEyeHeight();
        final double z = entity.posZ - Rotation.mc.thePlayer.posZ;
        final double d = MathHelper.sqrt_double(x * x + z * z);
        this.yaw = (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
        this.pitch = (float)(-(Math.atan2(y, d) * 180.0 / 3.141592653589793));
    }
    
    public Rotation(final Entity entity, final float motion) {
        final double x = entity.posX - Rotation.mc.thePlayer.posX + (entity.posX - entity.lastTickPosX) * motion;
        final double y = entity.posY + entity.getEyeHeight() / 3.0f * 2.0f - Rotation.mc.thePlayer.posY - Rotation.mc.thePlayer.getEyeHeight() + (entity.posY - entity.lastTickPosY) * motion;
        final double z = entity.posZ - Rotation.mc.thePlayer.posZ + (entity.posZ - entity.lastTickPosZ) * motion;
        final double d = MathHelper.sqrt_double(x * x + z * z);
        this.yaw = (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
        this.pitch = (float)(-(Math.atan2(y, d) * 180.0 / 3.141592653589793));
    }
    
    public Rotation(final BlockPos pos) {
        this(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f);
    }
    
    public Rotation(final Vec3d pos) {
        this(pos.xCoord, pos.yCoord, pos.zCoord);
    }
    
    private static float getOffSet() {
        return 0.5f;
    }
    
    public Rotation(final BlockPos pos, final EnumFacing face) {
        this(pos.getX() + 0.5f + face.getFrontOffsetX() * getOffSet(), pos.getY() + 0.5f + face.getFrontOffsetY() * getOffSet(), pos.getZ() + 0.5f + face.getFrontOffsetZ() * getOffSet());
    }
    
    public Rotation(double x, double y, double z) {
        x -= Rotation.mc.thePlayer.posX;
        y = y - Rotation.mc.thePlayer.posY - Rotation.mc.thePlayer.getEyeHeight();
        z -= Rotation.mc.thePlayer.posZ;
        final double d = MathHelper.sqrt_double(x * x + z * z);
        this.yaw = (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
        this.pitch = (float)(-(Math.atan2(y, d) * 180.0 / 3.141592653589793));
    }
    
    public Rotation(final double x_old, final double y_old, final double z_old, final double x_new, final double y_new, final double z_new) {
        final double x = x_new - x_old;
        final double y = y_new - y_old;
        final double z = z_new - z_old;
        final double d = MathHelper.sqrt_double(x * x + z * z);
        this.yaw = (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
        this.pitch = (float)(-(Math.atan2(y, d) * 180.0 / 3.141592653589793));
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public double radius() {
        final double yaw = Math.abs((Rotation.mc.thePlayer.rotationYaw - this.getYaw() + 180.0f) % 360.0f) - 180.0f;
        final double pitch = Math.abs((Rotation.mc.thePlayer.rotationPitch - this.getPitch() + 180.0f) % 360.0f) - 180.0f;
        return Math.sqrt(yaw * yaw + pitch * pitch);
    }
    
    public Vec3d getVector() {
        final float f = MathHelper.cos(-this.getYaw() * 0.017453292f - 3.1415927f);
        final float f2 = MathHelper.sin(-this.getYaw() * 0.017453292f - 3.1415927f);
        final float f3 = -MathHelper.cos(-this.getPitch() * 0.017453292f);
        final float f4 = MathHelper.sin(-this.getPitch() * 0.017453292f);
        return new Vec3d((double)(f2 * f3), (double)f4, (double)(f * f3));
    }
    
    public EnumFacing getHorizontalFacing() {
        return EnumFacing.getHorizontal(this.floor(this.getYaw() * 4.0f / 360.0f + 0.5) & 0x3);
    }
    
    private int floor(final double value) {
        final int i = (int)value;
        return (value < i) ? (i - 1) : i;
    }
}

