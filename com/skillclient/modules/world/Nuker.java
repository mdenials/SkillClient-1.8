package com.skillclient.modules.world;

import com.skillclient.utils.RenderUtils;
import com.skillclient.events.EventRender;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import java.util.Iterator;
import net.minecraft.util.EnumFacing;
import com.skillclient.events.EventUpdate;
import com.skillclient.events.api.EventTarget;
import com.skillclient.utils.Rotation;
import java.util.List;
import java.util.Collections;
import com.skillclient.events.EventRotation;
import net.minecraft.init.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import java.util.ArrayList;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Nuker extends Module
{
    ValueNumber range;
    ValueBoolean rotate;
    
    public Nuker() {
        super("Nuker", Register.Category.WORLD, "Destroys all matching Blocks in Range");
        this.range = new ValueNumber("Range", (Module)this, 6.0, 1.0, 5.0, 1);
        this.rotate = new ValueBoolean("Rotate", (Module)this, false);
    }
    
    public ArrayList<BlockPos> getPos() {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        for (int i = this.range.getInt() + 1, x = -i; x <= i; ++x) {
            for (int y = -i; y <= i; ++y) {
                for (int z = -i; z <= i; ++z) {
                    final BlockPos pos = new BlockPos((Entity)Nuker.mc.thePlayer).add(x, y, z);
                    if (!Nuker.mc.theWorld.getBlockState(pos).getBlock().equals(Blocks.air) && Nuker.mc.thePlayer.getDistanceSq(pos) < this.range.getInt() * this.range.getInt()) {
                        list.add(pos);
                    }
                }
            }
        }
        return list;
    }
    
    @EventTarget
    public void onRotate(final EventRotation event) {
        if (event.isPacket() && (boolean)this.rotate.getValue()) {
            final ArrayList<BlockPos> list = this.getPos();
            Collections.sort(list, (a1, a2) -> (int)(Nuker.mc.thePlayer.getDistanceSq(a1) - Nuker.mc.thePlayer.getDistanceSq(a2)));
            if (!list.isEmpty()) {
                final BlockPos pos = list.get(0);
                final Rotation rot = new Rotation(pos);
                event.setRotation(rot);
                mine(pos, rot.getHorizontalFacing());
            }
        }
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (!(boolean)this.rotate.getValue()) {
            for (final BlockPos pos : this.getPos()) {
                mine(pos, EnumFacing.DOWN);
            }
        }
    }
    
    public static void mine(final BlockPos pos, final EnumFacing face) {
        SkillWrapper.sendPacket((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, face));
        SkillWrapper.sendPacket((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, face));
        Nuker.mc.theWorld.setBlockState(pos, Blocks.air.getDefaultState());
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        for (final BlockPos pos : this.getPos()) {
            RenderUtils.framelessBlockESP(pos, 1.0f, 0.5f, 0.0f, 0.15f);
        }
    }
}
