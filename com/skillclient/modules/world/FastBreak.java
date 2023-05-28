package com.skillclient.modules.world;

import com.skillclient.events.api.EventTarget;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class FastBreak extends Module
{
    public FastBreak() {
        super("FastBreak", Register.Category.WORLD, "Breaks Blocks faster");
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (FastBreak.mc.playerController.getIsHittingBlock()) {
            SkillWrapper.sendPacket((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, FastBreak.mc.objectMouseOver.getBlockPos(), FastBreak.mc.objectMouseOver.sideHit));
        }
        if (FastBreak.mc.playerController.curBlockDamageMP > 0.75f) {
            FastBreak.mc.playerController.curBlockDamageMP = 1.0f;
        }
    }
}

