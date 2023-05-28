package com.skillclient.modules.world;

import java.util.Collection;
import java.util.Iterator;
import com.skillclient.utils.Rotation;
import net.minecraft.entity.Entity;
import java.util.ArrayList;
import com.skillclient.events.EventRotation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.Vec3d;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.BlockPos;
import com.skillclient.events.api.EventTarget;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.item.ItemBlock;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.utils.TimerUtil;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class ScaffoldWalk extends Module
{
    public ValueBoolean sprint;
    ValueBoolean superSave;
    ValueBoolean pre;
    ValueBoolean keepRotation;
    ValueNumber floor;
    ValueNumber hollow;
    float yaw;
    float pitch;
    TimerUtil timer;
    
    public ScaffoldWalk() {
        super("Scaffold Walk", Register.Category.WORLD, "Walk in the air");
        this.sprint = new ValueBoolean("Sprint", (Module)this, true);
        this.superSave = new ValueBoolean("superSave", (Module)this, false);
        this.pre = new ValueBoolean("Pre", (Module)this, true);
        this.keepRotation = new ValueBoolean("Keep rotation", (Module)this, false);
        this.floor = (ValueNumber)new ScaffoldWalk.ScaffoldWalk$1(this, "Floor", (Module)this, 5.0, 0.0, 0.0, 0);
        this.hollow = (ValueNumber)new ScaffoldWalk.ScaffoldWalk$2(this, "Hollow", (Module)this, 5.0, 0.0, 0.0, 0);
        this.yaw = Float.NaN;
        this.pitch = Float.NaN;
        this.timer = new TimerUtil();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (ScaffoldWalk.mc.thePlayer.getHeldItem() == null || !(ScaffoldWalk.mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock)) {
            for (int i = 0; i < 9; ++i) {
                final ItemStack item = ScaffoldWalk.mc.thePlayer.inventory.getStackInSlot(i);
                if (item != null && item.getItem() instanceof ItemBlock) {
                    ScaffoldWalk.mc.thePlayer.inventory.currentItem = i;
                    break;
                }
            }
        }
        if ((boolean)this.superSave.getValue() && ScaffoldWalk.mc.thePlayer.posY - 1.0 >= ScaffoldWalk.mc.objectMouseOver.getBlockPos().getY() && ScaffoldWalk.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && ScaffoldWalk.mc.rightClickDelayTimer == 0 && ScaffoldWalk.mc.thePlayer != null && ScaffoldWalk.mc.thePlayer.getHeldItem() != null && ScaffoldWalk.mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock && ScaffoldWalk.mc.currentScreen == null && ScaffoldWalk.mc.playerController.onPlayerRightClick(ScaffoldWalk.mc.thePlayer, ScaffoldWalk.mc.theWorld, ScaffoldWalk.mc.thePlayer.getHeldItem(), ScaffoldWalk.mc.objectMouseOver.getBlockPos(), ScaffoldWalk.mc.objectMouseOver.sideHit, ScaffoldWalk.mc.objectMouseOver.hitVec)) {
            ScaffoldWalk.mc.thePlayer.swingItem();
            ScaffoldWalk.mc.rightClickDelayTimer = ((FastPlace)Register.getModule((Class)FastPlace.class)).getClick();
        }
    }
    
    public EnumFacing place(final BlockPos pos) {
        EnumFacing[] values;
        for (int length = (values = EnumFacing.values()).length, i = 0; i < length; ++i) {
            final EnumFacing face = values[i];
            if (!ScaffoldWalk.mc.theWorld.getBlockState(pos.offset(face)).getBlock().equals(Blocks.air) && this.onPlayerRightClick(ScaffoldWalk.mc.thePlayer, ScaffoldWalk.mc.theWorld, ScaffoldWalk.mc.thePlayer.getHeldItem(), pos.offset(face), face.getOpposite(), new Vec3d(0.0, 0.0, 0.0))) {
                return face;
            }
        }
        return null;
    }
    
    public boolean onPlayerRightClick(final EntityPlayerSP player, final WorldClient worldIn, final ItemStack heldStack, final BlockPos hitPos, final EnumFacing side, final Vec3d hitVec) {
        ScaffoldWalk.mc.playerController.syncCurrentPlayItem();
        final float f = (float)(hitVec.xCoord - hitPos.getX());
        final float f2 = (float)(hitVec.yCoord - hitPos.getY());
        final float f3 = (float)(hitVec.zCoord - hitPos.getZ());
        boolean flag = false;
        if (!ScaffoldWalk.mc.theWorld.getWorldBorder().contains(hitPos)) {
            return false;
        }
        if (ScaffoldWalk.mc.playerController.getCurrentGameType() != WorldSettings.GameType.SPECTATOR) {
            final IBlockState iblockstate = worldIn.getBlockState(hitPos);
            if ((!player.isSneaking() || player.getHeldItem() == null) && iblockstate.getBlock().onBlockActivated((World)worldIn, hitPos, iblockstate, (EntityPlayer)player, side, f, f2, f3)) {
                flag = true;
            }
            if (!flag && heldStack != null && heldStack.getItem() instanceof ItemBlock) {
                final ItemBlock itemblock = (ItemBlock)heldStack.getItem();
                if (!itemblock.canPlaceBlockOnSide((World)worldIn, hitPos, side, (EntityPlayer)player, heldStack)) {
                    return false;
                }
            }
        }
        SkillWrapper.sendPacketSoon((Packet)new C08PacketPlayerBlockPlacement(hitPos, side.getIndex(), player.inventory.getCurrentItem(), f, f2, f3));
        if (flag || ScaffoldWalk.mc.playerController.getCurrentGameType() == WorldSettings.GameType.SPECTATOR) {
            return true;
        }
        if (heldStack == null) {
            return false;
        }
        if (ScaffoldWalk.mc.playerController.getCurrentGameType().isCreative()) {
            final int i = heldStack.getMetadata();
            final int j = heldStack.stackSize;
            final boolean flag2 = heldStack.onItemUse((EntityPlayer)player, (World)worldIn, hitPos, side, f, f2, f3);
            heldStack.setItemDamage(i);
            heldStack.stackSize = j;
            return flag2;
        }
        return heldStack.onItemUse((EntityPlayer)player, (World)worldIn, hitPos, side, f, f2, f3);
    }
    
    public double mod(final double d) {
        return (d % 1.0 + 1.0) % 1.0;
    }
    
    @EventTarget(2)
    public void onUpdate(final EventRotation event) {
        if (this.isActive() && ScaffoldWalk.mc.thePlayer != null && ScaffoldWalk.mc.rightClickDelayTimer == 0 && ScaffoldWalk.mc.thePlayer.getHeldItem() != null && ScaffoldWalk.mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock) {
            if (this.superSave.getValue()) {
                event.setYaw(ScaffoldWalk.mc.thePlayer.rotationYaw + 140.0f);
                event.setPitch(80.5f);
                event.stop();
            }
            else if (event.isPacket()) {
                final ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
                positions.add(new BlockPos((Entity)ScaffoldWalk.mc.thePlayer).add(0, -1, 0));
                if (this.pre.getValue()) {
                    positions.add(new BlockPos((Entity)ScaffoldWalk.mc.thePlayer).add(0, -1, 0).offset(ScaffoldWalk.mc.thePlayer.getHorizontalFacing()));
                }
                for (int i = 0; i < (double)this.floor.getValue(); ++i) {
                    this.spread(positions, EnumFacing.HORIZONTALS);
                }
                for (int i = 0; i < (double)this.hollow.getValue(); ++i) {
                    this.spread(positions, EnumFacing.values());
                }
                for (final BlockPos pos : positions) {
                    if (ScaffoldWalk.mc.theWorld.getBlockState(pos).getBlock().equals(Blocks.air)) {
                        final EnumFacing face = this.place(pos);
                        if (face != null) {
                            final Rotation rot = new Rotation(pos, face);
                            if (this.keepRotation.getValue()) {
                                if (face.getHorizontalIndex() == -1) {
                                    this.yaw = Float.NaN;
                                    event.setPitch(this.pitch = 90.0f);
                                }
                                else {
                                    event.setYaw(this.yaw = rot.getYaw());
                                    event.setPitch(this.pitch = rot.getPitch());
                                }
                                this.timer.setLastMS();
                            }
                            else {
                                event.setYaw(rot.getYaw());
                                event.setPitch(rot.getPitch());
                            }
                            event.stop();
                            ScaffoldWalk.mc.thePlayer.swingItem();
                            ScaffoldWalk.mc.rightClickDelayTimer = ((FastPlace)Register.getModule((Class)FastPlace.class)).getClick();
                            return;
                        }
                        continue;
                    }
                }
                if ((boolean)this.keepRotation.getValue() && !this.timer.isDelayComplete(1000L)) {
                    if (!Float.isNaN(this.yaw)) {
                        event.setYaw(this.yaw);
                    }
                    event.setPitch(this.pitch);
                }
            }
        }
    }
    
    public void spread(final ArrayList<BlockPos> positions, final EnumFacing... array) {
        for (final BlockPos pos : new ArrayList<BlockPos>(positions)) {
            for (final EnumFacing face : array) {
                final BlockPos newPos = pos.offset(face);
                if (!positions.contains(newPos)) {
                    positions.add(newPos);
                }
            }
        }
    }
}


