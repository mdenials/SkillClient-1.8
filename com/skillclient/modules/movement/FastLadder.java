package com.skillclient.modules.movement;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.network.play.client.C03PacketPlayer;
import com.skillclient.events.EventPacketSend;
import com.skillclient.events.api.EventTarget;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class FastLadder extends Module
{
    public ValueNumber expand;
    ValueNumber speedY;
    
    public FastLadder() {
        super("FastLadder", Register.Category.MOVEMENT, "Climp a ladder faster");
        this.expand = new ValueNumber("Expand", (Module)this, 1.0, 0.1, 1.0, 2);
        this.speedY = new ValueNumber("SpeedY", (Module)this, 100.0, 10.0, 40.0, 0);
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (!FastLadder.mc.gameSettings.keyBindSneak.isKeyDown() && !FastLadder.mc.thePlayer.isSpectator()) {
            if (this.isCollidedwithLadder(0.5) || FastLadder.mc.thePlayer.isOnLadder()) {
                FastLadder.mc.thePlayer.motionY = this.speedY.getInt() / 100.0f;
            }
            else if (this.isCollidedwithLadder(0.0)) {
                FastLadder.mc.thePlayer.motionY = 0.10000000149011612;
            }
        }
    }
    
    public void on(final EventPacketSend event) {
        if (event.getPacket() instanceof C03PacketPlayer) {
            ((C03PacketPlayer)event.getPacket()).onGround = true;
        }
    }
    
    public boolean isCollidedwithLadder(final double d) {
        final double value = 0.9 - (double)this.expand.getValue();
        final AxisAlignedBB bb = FastLadder.mc.thePlayer.boundingBox;
        final int i = MathHelper.floor_double(bb.minX + value);
        final int j = MathHelper.ceiling_double_int(bb.maxX - value);
        final int k = MathHelper.floor_double(bb.minY + d);
        final int l = MathHelper.ceiling_double_int(bb.maxY);
        final int i2 = MathHelper.floor_double(bb.minZ + value);
        final int j2 = MathHelper.ceiling_double_int(bb.maxZ - value);
        for (int k2 = i; k2 < j; ++k2) {
            for (int l2 = k; l2 < l; ++l2) {
                for (int i3 = i2; i3 < j2; ++i3) {
                    if (FastLadder.mc.theWorld.getBlockState(new BlockPos(k2, l2, i3)).getBlock().equals(Blocks.ladder)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

