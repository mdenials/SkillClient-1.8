package com.skillclient.modules.movement;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.block.BlockLiquid;
import com.skillclient.events.api.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import net.minecraft.util.AxisAlignedBB;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Jesus extends Module
{
    ValueNumber boost;
    ValueNumber height;
    ValueBoolean solid;
    ValueBoolean ncp;
    boolean wasWater;
    public static final AxisAlignedBB FULL_BLOCK_AABB;
    public static final AxisAlignedBB NULL_AABB;
    
    static {
        FULL_BLOCK_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        NULL_AABB = null;
    }
    
    public Jesus() {
        super("Jesus", Register.Category.MOVEMENT, "Let you walk on Water");
        this.boost = new ValueNumber("Water-Boost", (Module)this, 50.0, 0.0, 42.0, 1);
        this.height = new ValueNumber("Height", (Module)this, 1.0, -1.0, -0.2, 2);
        this.solid = new ValueBoolean("Solid", (Module)this, true);
        this.ncp = new ValueBoolean("NCP", (Module)this, false);
        this.wasWater = false;
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (!Jesus.mc.thePlayer.movementInput.sneak) {
            if (this.ncp.getValue()) {
                Jesus.mc.gameSettings.keyBindSprint.pressed = true;
                if (this.wasWater && !Jesus.mc.thePlayer.isInWater()) {
                    Jesus.mc.thePlayer.motionY = 0.41999998688697815;
                    final EntityPlayerSP thePlayer = Jesus.mc.thePlayer;
                    thePlayer.motionX *= 6.0;
                    final EntityPlayerSP thePlayer2 = Jesus.mc.thePlayer;
                    thePlayer2.motionZ *= 6.0;
                }
                else if (Jesus.mc.thePlayer.isInWater()) {
                    Jesus.mc.thePlayer.motionY = 0.10000000149011612;
                    if (!this.wasWater) {
                        final EntityPlayerSP thePlayer3 = Jesus.mc.thePlayer;
                        final EntityPlayerSP thePlayer4 = Jesus.mc.thePlayer;
                        final double n = 0.0;
                        thePlayer4.motionZ = n;
                        thePlayer3.motionX = n;
                    }
                }
                this.wasWater = Jesus.mc.thePlayer.isInWater();
            }
            else if (!Jesus.mc.thePlayer.isCollidedHorizontally && inFluid(((Double)this.height.getValue()).floatValue())) {
                Jesus.mc.thePlayer.motionY = (double)this.boost.getValue() / 100.0;
            }
        }
    }
    
    public static boolean inFluid(final float i) {
        return Jesus.mc.theWorld.getBlockState(new BlockPos(Jesus.mc.thePlayer.posX, Jesus.mc.thePlayer.posY - i, Jesus.mc.thePlayer.posZ)).getBlock() instanceof BlockLiquid;
    }
    
    public AxisAlignedBB getAxisAlignedBB(final IBlockState blockState) {
        if (Jesus.mc.thePlayer == null) {
            return Jesus.NULL_AABB;
        }
        if (!this.isToggled() || !(boolean)this.solid.getValue()) {
            return Jesus.NULL_AABB;
        }
        if (blockState.getBlock().getMaterial().equals(Material.lava)) {
            return Jesus.FULL_BLOCK_AABB;
        }
        if (!Jesus.mc.thePlayer.movementInput.sneak && !Jesus.mc.thePlayer.isInWater() && !Jesus.mc.thePlayer.isBurning() && Jesus.mc.thePlayer.fallDistance <= 2.0f) {
            return Jesus.FULL_BLOCK_AABB;
        }
        return Jesus.NULL_AABB;
    }
}
