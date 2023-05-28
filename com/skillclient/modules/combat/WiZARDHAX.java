package com.skillclient.modules.combat;

import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import net.minecraft.util.MathHelper;
import com.skillclient.events.api.EventTarget;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.entity.Entity;
import com.skillclient.utils.Rotation;
import net.minecraft.util.Vec3d;
import com.skillclient.utils.Features;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.client.gui.inventory.GuiChest;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class WiZARDHAX extends Module
{
    public WiZARDHAX() {
        super("WiZARDHAX Fight-Bot", Register.Category.MISC, "Just a test");
    }
    
    @EventTarget(3)
    public void onUpdate(final EventUpdate event) {
        if (WiZARDHAX.mc.currentScreen != null && WiZARDHAX.mc.currentScreen instanceof GuiChest) {
            int sword = -1;
            try {
                sword = this.findItem((Item)Items.diamond_chestplate, ((GuiChest)WiZARDHAX.mc.currentScreen).inventorySlots.inventorySlots);
            }
            catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            if (sword == -1) {
                return;
            }
            WiZARDHAX.mc.playerController.windowClick(WiZARDHAX.mc.thePlayer.openContainer.windowId, sword, 0, 4, (EntityPlayer)WiZARDHAX.mc.thePlayer);
        }
        else {
            if (this.findItem(Items.iron_sword, WiZARDHAX.mc.thePlayer.inventoryContainer.inventorySlots) == -1) {
                return;
            }
            if (!Features.isDonator && WiZARDHAX.mc.thePlayer.getDistance(-1858.0, 93.0, 1131.0) < 8.0) {
                return;
            }
            final EntityLivingBase entity = bestEntity();
            if (entity == null) {
                return;
            }
            final float distance = 0.0f;
            final float motion = 5.0f;
            final Vec3d entity_look = this.getRandom();
            Vec3d pos = new Vec3d(entity.posX + entity_look.xCoord * distance + (entity.posX - entity.lastTickPosX) * motion, entity.posY + (entity.posY - entity.lastTickPosY) * motion, entity.posZ + entity_look.zCoord * distance + (entity.posZ - entity.lastTickPosZ) * motion);
            double strength = WiZARDHAX.mc.thePlayer.getDistance(pos.xCoord, pos.yCoord, pos.zCoord) / 2.0;
            if (strength > 20.0) {
                strength = 20.0;
            }
            if (WiZARDHAX.mc.thePlayer.getDistance(-1858.0, 93.0, 1131.0) < 8.0) {
                pos = new Vec3d(-1854.0, 0.0, 1075.0);
                strength = 0.5;
            }
            final Vec3d vec = new Rotation(pos).getVector();
            WiZARDHAX.mc.thePlayer.motionX = vec.xCoord * strength;
            WiZARDHAX.mc.thePlayer.motionY = vec.yCoord * strength;
            WiZARDHAX.mc.thePlayer.motionZ = vec.zCoord * strength;
            final Rotation rotation = new Rotation((Entity)entity);
            WiZARDHAX.mc.thePlayer.rotationPitch = rotation.getPitch();
            WiZARDHAX.mc.thePlayer.rotationYaw = rotation.getYaw();
            WiZARDHAX.mc.gameSettings.keyBindUseItem.pressed = true;
            if (entity.hurtTime <= 8) {
                SkillWrapper.sendPacket((Packet)new C02PacketUseEntity((Entity)entity, C02PacketUseEntity.Action.ATTACK));
                entity.hurtTime = 10;
            }
        }
    }
    
    protected final Vec3d getRandom() {
        final float yaw = (float)WiZARDHAX.random.nextInt(360);
        final float f = MathHelper.cos(-yaw * 0.017453292f - 3.1415927f);
        final float f2 = MathHelper.sin(-yaw * 0.017453292f - 3.1415927f);
        return new Vec3d((double)(-f2), 0.0, (double)(-f));
    }
    
    public static EntityLivingBase bestEntity() {
        ((TargetUtil)Register.getModule((Class)TargetUtil.class)).waitForDamage = 0;
        final ArrayList<EntityLivingBase> list = new ArrayList<EntityLivingBase>();
        for (final Entity e3 : WiZARDHAX.mc.theWorld.loadedEntityList) {
            if (e3 instanceof EntityLivingBase && ((TargetUtil)Register.getModule((Class)TargetUtil.class)).matches((EntityLivingBase)e3) && e3.getDistance(-1858.0, 93.0, 1136.0) > 12.0) {
                list.add((EntityLivingBase)e3);
                if (WiZARDHAX.mc.thePlayer.getDistanceSqToEntity(e3) >= 12.0 || ((EntityLivingBase)e3).hurtTime > 8) {
                    continue;
                }
                ((EntityLivingBase)e3).hurtTime = 10;
                SkillWrapper.sendPacket((Packet)new C02PacketUseEntity(e3, C02PacketUseEntity.Action.ATTACK));
            }
        }
        Collections.sort(list, (e1, e2) -> (int)(TargetUtil.EntityComparator.armor.value(e1) - TargetUtil.EntityComparator.armor.value(e2)));
        return list.isEmpty() ? null : list.get(0);
    }
    
    private int findItem(final Item item, final List<Slot> slots) {
        for (final Slot s : slots) {
            if (s.getStack() != null && s.getStack().getItem() == item) {
                return s.slotNumber;
            }
        }
        return -1;
    }
}

