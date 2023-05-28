package com.skillclient.modules.world;

import com.skillclient.utils.RenderUtils;
import com.skillclient.events.EventRender;
import com.skillclient.events.EventRotation;
import net.minecraft.util.Vec3d;
import com.skillclient.utils.Rotation;
import com.skillclient.events.api.EventTarget;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.BlockPos;
import com.skillclient.events.EventUpdate;
import net.minecraft.block.BlockAir;
import net.minecraft.block.Block;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueText;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Fucker extends Module
{
    private ValueNumber range;
    private ValueBoolean through_blocks;
    private ValueText ids;
    
    public Fucker() {
        super("Fucker", Register.Category.WORLD, "Destroys all matching Blocks in Range");
        this.range = new ValueNumber("Range", (Module)this, 6.0, 1.0, 5.0, 1);
        this.through_blocks = new ValueBoolean("through blocks", (Module)this, false);
        this.ids = new ValueText("IDs", (Module)this, "bed");
    }
    
    public boolean matches(final Block block) {
        if (block instanceof BlockAir) {
            return false;
        }
        if (this.ids.getValue() == null || this.ids.getValue().isEmpty()) {
            return true;
        }
        try {
            if (Block.getBlockFromName(this.ids.getValue()).equals(block)) {
                return true;
            }
            if (block.getUnlocalizedName().toLowerCase().replaceAll("\\W", "").matches(this.ids.getValue())) {
                return true;
            }
            if (Integer.valueOf(Block.getIdFromBlock(block)).toString().matches(this.ids.getValue())) {
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        for (int x = (int)(Fucker.mc.thePlayer.posX - (double)this.range.getValue() + 1.0); x < (int)(Fucker.mc.thePlayer.posX + (double)this.range.getValue()); ++x) {
            for (int y = (int)(Fucker.mc.thePlayer.posY - (double)this.range.getValue() + 1.0); y < (int)(Fucker.mc.thePlayer.posY + (double)this.range.getValue() + 1.9); ++y) {
                for (int z = (int)(Fucker.mc.thePlayer.posZ - (double)this.range.getValue() + 1.0); z < (int)(Fucker.mc.thePlayer.posZ + (double)this.range.getValue()); ++z) {
                    final BlockPos blockpos = new BlockPos(x, y, z);
                    final Block block = Fucker.mc.theWorld.getBlockState(blockpos).getBlock();
                    if (Fucker.mc.thePlayer.getDistanceSqToCenter(blockpos) <= (double)this.range.getValue() * (double)this.range.getValue() && this.matches(block)) {
                        final MovingObjectPosition result = this.rayTrace(blockpos);
                        ((CivBreak)Register.getModule((Class)CivBreak.class)).pos = (this.through_blocks.getValue() ? result.getBlockPos() : blockpos);
                        if (((CivBreak)Register.getModule((Class)CivBreak.class)).isGood()) {
                            Fucker.mc.playerController.onPlayerDamageBlock(((CivBreak)Register.getModule((Class)CivBreak.class)).pos, result.sideHit);
                            Fucker.mc.thePlayer.swingItem();
                        }
                        return;
                    }
                }
            }
        }
    }
    
    public MovingObjectPosition rayTrace(final BlockPos pos) {
        final Vec3d vec3 = Fucker.mc.thePlayer.getPositionEyes(1.0f);
        final Vec3d vec4 = new Rotation(pos).getVector();
        final Vec3d vec5 = vec3.addVector(vec4.xCoord * (double)this.range.getValue(), vec4.yCoord * (double)this.range.getValue(), vec4.zCoord * (double)this.range.getValue());
        return Fucker.mc.theWorld.rayTraceBlocks(vec3, vec5, !Fucker.mc.thePlayer.isInWater(), true, true);
    }
    
    @EventTarget(2)
    public void onUpdate(final EventRotation event) {
        if (((CivBreak)Register.getModule((Class)CivBreak.class)).isGood() && event.isPacket()) {
            final Rotation rot = new Rotation(((CivBreak)Register.getModule((Class)CivBreak.class)).pos);
            event.setPitch(rot.getPitch());
            event.setYaw(rot.getYaw());
            event.stop();
        }
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        if (((CivBreak)Register.getModule((Class)CivBreak.class)).pos != null) {
            RenderUtils.nukerBox(((CivBreak)Register.getModule((Class)CivBreak.class)).pos, Fucker.mc.playerController.curBlockDamageMP);
        }
    }
}
