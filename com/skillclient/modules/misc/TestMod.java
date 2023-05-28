package com.skillclient.modules.misc;

import net.minecraft.client.Minecraft;
import java.util.Iterator;
import com.skillclient.utils.RenderUtils;
import java.awt.Color;
import com.skillclient.events.EventRender;
import net.minecraft.network.play.server.S07PacketRespawn;
import com.skillclient.chat.CmdNickName;
import net.minecraft.init.Items;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import com.skillclient.events.EventPacketReceived;
import com.skillclient.events.api.EventTarget;
import net.minecraft.client.entity.EntityPlayerSP;
import com.skillclient.utils.exceptions.NoEntityFoundException;
import net.minecraft.entity.Entity;
import com.skillclient.utils.Rotation;
import com.skillclient.modules.combat.TargetUtil;
import net.minecraft.entity.item.EntityItem;
import org.lwjgl.input.Keyboard;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import net.minecraft.client.entity.AbstractClientPlayer;
import com.skillclient.misc.Module;

public class TestMod extends Module
{
    public AbstractClientPlayer target;
    
    public TestMod() {
        super("TestMod", Register.Category.MISC, "Just a test");
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (Keyboard.isKeyDown(56)) {
            try {
                final EntityItem e = ((TargetUtil)Register.getModule((Class)TargetUtil.class)).bestEntity(EntityItem.class, (TargetUtil.EntityComparator<EntityItem>[])new TargetUtil.EntityComparator[] { (TargetUtil.EntityComparator)new TestMod.TestMod$1(this) });
                final Rotation rot = new Rotation((Entity)e, 2.0f);
                double yaw = (rot.getYaw() - TestMod.mc.thePlayer.rotationYaw) % 360.0f;
                if (yaw < 0.0) {
                    yaw += 360.0;
                }
                if (yaw > 180.0) {
                    yaw -= 360.0;
                }
                final float motion = 3.0f;
                final EntityPlayerSP thePlayer = TestMod.mc.thePlayer;
                thePlayer.rotationYaw += (float)(yaw / motion);
                final EntityPlayerSP thePlayer2 = TestMod.mc.thePlayer;
                thePlayer2.rotationPitch += (rot.getPitch() - TestMod.mc.thePlayer.rotationPitch - 3.0f) / motion;
            }
            catch (NoEntityFoundException ex) {}
        }
    }
    
    @EventTarget
    public void onReceived(final EventPacketReceived event) {
        if (event.getPacket() instanceof S04PacketEntityEquipment) {
            final S04PacketEntityEquipment p = (S04PacketEntityEquipment)event.getPacket();
            if (!p.getItemStack().getItem().equals(Items.iron_sword)) {
                return;
            }
            this.target = (AbstractClientPlayer)TestMod.mc.theWorld.getEntityByID(p.getEntityID());
            synchronized (CmdNickName.names) {
                CmdNickName.names.removeIf(name -> name.newname.startsWith("§4"));
                CmdNickName.names.add(new CmdNickName.Nick(this.target.getName(), "§4" + this.target.getName() + "§r"));
                // monitorexit(CmdNickName.names)
                return;
            }
        }
        if (event.getPacket() instanceof S07PacketRespawn) {
            this.target = null;
            CmdNickName.names.removeIf(name -> name.newname.startsWith("§4"));
        }
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        if (this.target != null) {
            RenderUtils.tracerLine((Entity)this.target, Color.RED);
        }
        for (final EntityItem item : ((TargetUtil)Register.getModule((Class)TargetUtil.class)).bestEntities((Class<? extends Entity>)EntityItem.class, (TargetUtil.EntityComparator<Entity>[])new TargetUtil.EntityComparator[] { (TargetUtil.EntityComparator)new TestMod.TestMod$2(this) })) {
            RenderUtils.entityESPBox((Entity)item, 0);
        }
    }
}
