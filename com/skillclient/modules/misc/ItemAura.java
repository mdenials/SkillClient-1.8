package com.skillclient.modules.misc;

import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C03PacketPlayer;
import com.skillclient.utils.TeleportUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import com.skillclient.events.EventUpdate;
import com.skillclient.events.api.EventTarget;
import java.util.Iterator;
import com.skillclient.utils.RenderUtils;
import net.minecraft.util.BlockPos;
import com.skillclient.events.EventRender;
import com.skillclient.main.Register;
import net.minecraft.util.Vec3d;
import java.util.ArrayList;
import com.skillclient.misc.Module;

public class ItemAura extends Module
{
    ArrayList<Vec3d> positions;
    
    public ItemAura() {
        super("ItemAura", Register.Category.MISC, "Just a test");
        this.positions = new ArrayList<Vec3d>();
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        for (final Vec3d pos : this.positions) {
            RenderUtils.blockESPBox(new BlockPos(pos));
        }
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        for (final Entity e : ItemAura.mc.theWorld.loadedEntityList) {
            if (e.getDistanceToEntity((Entity)ItemAura.mc.thePlayer) < 20.0f && e instanceof EntityItem && e.ticksExisted > 20 && ItemAura.mc.thePlayer.canEntityBeSeen(e)) {
                e.ticksExisted = 0;
                TeleportUtil.teleport((ArrayList)(this.positions = (ArrayList<Vec3d>)TeleportUtil.teleport((int)e.posX, (int)e.posY, (int)e.posZ, 0.0)), true);
                SkillWrapper.sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(e.posX, e.posY + 0.10000000149011612, e.posZ, true));
                TeleportUtil.teleport((ArrayList)this.positions, false);
            }
        }
    }
}
