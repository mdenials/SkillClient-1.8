package com.skillclient.modules.world;

import com.skillclient.utils.Rotation;
import com.skillclient.events.EventRotation;
import com.skillclient.utils.RenderUtils;
import com.skillclient.events.EventRender;
import net.minecraft.init.Blocks;
import com.skillclient.events.api.EventTarget;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import net.minecraft.util.BlockPos;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class CivBreak extends Module
{
    public ValueBoolean legit;
    BlockPos pos;
    
    public CivBreak() {
        super("CivBreak", Register.Category.WORLD, "Breaks Blocks faster. Try \"legit\" for AC's");
        this.legit = new ValueBoolean("Legit", (Module)this, false);
    }
    
    public void setPos(final BlockPos pos) {
        this.pos = pos;
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (CivBreak.mc.playerController.getIsHittingBlock()) {
            this.pos = CivBreak.mc.playerController.currentBlock;
        }
        if (this.isGood()) {
            if (this.legit.getValue()) {
                CivBreak.mc.playerController.onPlayerDamageBlock(this.pos, CivBreak.mc.thePlayer.getHorizontalFacing());
                CivBreak.mc.thePlayer.swingItem();
            }
            else {
                SkillWrapper.sendPacket((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.pos, (CivBreak.mc.objectMouseOver.sideHit == null) ? CivBreak.mc.thePlayer.getHorizontalFacing() : CivBreak.mc.objectMouseOver.sideHit));
                CivBreak.mc.thePlayer.swingItem();
            }
        }
    }
    
    boolean isGood() {
        return this.pos != null && (CivBreak.mc.thePlayer.getDistanceSq(this.pos) < Math.pow(CivBreak.mc.playerController.getBlockReachDistance(), 2.0) || CivBreak.mc.playerController.curBlockDamageMP > 0.0f) && !CivBreak.mc.theWorld.getBlockState(this.pos).getBlock().equals(Blocks.air);
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        if (this.isGood()) {
            RenderUtils.nukerBox(this.pos, (CivBreak.mc.playerController.curBlockDamageMP == 0.0f) ? 1.0f : CivBreak.mc.playerController.curBlockDamageMP);
        }
    }
    
    @EventTarget(2)
    public void onUpdate(final EventRotation event) {
        if (this.isGood() && event.isPacket()) {
            final Rotation rot = new Rotation(this.pos);
            event.setPitch(rot.getPitch());
            event.setYaw(rot.getYaw());
            event.stop();
        }
    }
    
    public void onEnable() {
        super.onEnable();
        this.pos = null;
    }
}
