package com.skillclient.modules.combat;

import net.minecraft.client.Minecraft;
import com.skillclient.utils.RenderUtils;
import java.awt.Color;
import net.minecraft.util.BlockPos;
import com.skillclient.events.EventRender;
import net.minecraft.world.World;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.entity.player.EntityPlayer;
import com.skillclient.utils.TeleportUtil;
import net.minecraft.entity.Entity;
import com.skillclient.utils.Rotation;
import com.skillclient.utils.exceptions.NoEntityFoundException;
import com.skillclient.events.EventRotation;
import com.skillclient.events.api.EventTarget;
import java.util.Iterator;
import net.minecraft.item.ItemSword;
import com.skillclient.events.EventUpdate;
import com.skillclient.misc.ValueModule;
import com.skillclient.main.Register;
import net.minecraft.util.Vec3d;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import com.skillclient.misc.ValueArray;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class KillAura extends Module
{
    ValueBoolean ncp;
    ValueNumber hurtTime;
    ValueArray<TargetUtil.EntityComparator> healthCheck;
    public ValueNumber range;
    ValueNumber range_extended;
    ValueBoolean negative_kb;
    ValueNumber speed_average;
    EntityLivingBase entity;
    int currentDelay;
    ArrayList<Vec3d> positions;
    
    public KillAura() {
        super("KillAura", Register.Category.COMBAT, "Kills other Players.");
        new ValueModule("Targets", (Module)this, (Class)TargetUtil.class);
        this.ncp = new ValueBoolean("NCP", (Module)this, false);
        this.hurtTime = new ValueNumber("hurtTime", (Module)this, 10.0, 0.0, 7.0, 0);
        this.healthCheck = (ValueArray<TargetUtil.EntityComparator>)new KillAura.KillAura$1(this, "Priority", (Module)this);
        this.range = new ValueNumber("Range", (Module)this, 5.0, 3.5, 4.5, 1);
        this.range_extended = (ValueNumber)new KillAura.KillAura$2(this, "Extended Range", (Module)this, 50.0, 0.0, 0.0, 0);
        this.negative_kb = new ValueBoolean("Negative kb", (Module)this, false);
        this.speed_average = new ValueNumber("Speed", " ticks", (Module)this, 20.0, 0.0, 0.0, 0);
        this.currentDelay = 0;
        this.positions = new ArrayList<Vec3d>();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (KillAura.mc.thePlayer.isDead || KillAura.mc.playerController.isSpectatorMode() || !this.isActive() || (KillAura.mc.thePlayer.isUsingItem() && !(KillAura.mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemSword))) {
            return;
        }
        if (this.currentDelay > 0) {
            --this.currentDelay;
            return;
        }
        if (this.ncp.getValue()) {
            ((TargetUtil)Register.getModule((Class)TargetUtil.class)).waitForDamage = this.hurtTime.getInt();
            if (this.entity != null && ((TargetUtil)Register.getModule((Class)TargetUtil.class)).matches(this.entity)) {
                this.attack(this.entity);
                this.resetTime();
            }
        }
        else {
            ((TargetUtil)Register.getModule((Class)TargetUtil.class)).waitForDamage = this.hurtTime.getInt();
            final ArrayList<EntityLivingBase> entitys = (ArrayList<EntityLivingBase>)((TargetUtil)Register.getModule((Class)TargetUtil.class)).bestEntities((Class)EntityLivingBase.class, new TargetUtil.EntityComparator[] { (TargetUtil.EntityComparator)new KillAura.KillAura$3(this), (TargetUtil.EntityComparator)this.healthCheck.getItem() });
            for (final EntityLivingBase entity : entitys) {
                this.attack(entity);
                entity.hurtTime = 10;
                this.resetTime();
            }
        }
    }
    
    public void resetTime() {
        this.currentDelay = this.speed_average.getInt();
    }
    
    @EventTarget(0)
    public void onUpdate(final EventRotation event) {
        if (KillAura.mc.thePlayer.isDead || KillAura.mc.playerController.isSpectatorMode() || !event.isPacket()) {
            return;
        }
        if (this.ncp.getValue()) {
            if (event.isPacket()) {
                this.entity = null;
                int i = 10;
                while (i >= 0 && this.entity == null) {
                    try {
                        ((TargetUtil)Register.getModule((Class)TargetUtil.class)).waitForDamage = i--;
                        this.entity = (EntityLivingBase)((TargetUtil)Register.getModule((Class)TargetUtil.class)).bestEntity((Class)EntityLivingBase.class, new TargetUtil.EntityComparator[] { (TargetUtil.EntityComparator)new KillAura.KillAura$4(this), (TargetUtil.EntityComparator)this.healthCheck.getItem() });
                    }
                    catch (NoEntityFoundException ex) {}
                }
            }
            if (this.entity != null) {
                final Rotation rotation = new Rotation((Entity)this.entity);
                event.setPitch(rotation.getPitch());
                event.setYaw(rotation.getYaw());
                event.stop();
            }
        }
    }
    
    public static int nextRand(final int range) {
        return (range == 0) ? 0 : KillAura.random.nextInt(range);
    }
    
    public void attack(final EntityLivingBase target) {
        if ((double)this.range_extended.getValue() != 0.0) {
            final Vec3d vec = new Rotation((Entity)target).getVector();
            float d = (float)(this.negative_kb.getValue() ? -1 : 2);
            d = 0.0f;
            final double motion = 3.0;
            final double x_tp = target.posX - vec.xCoord * d;
            final double y_tp = target.posY;
            final double z_tp = target.posZ - vec.zCoord * d;
            TeleportUtil.teleport((ArrayList)(this.positions = (ArrayList<Vec3d>)TeleportUtil.teleport((int)(x_tp + target.motionX * motion), (int)(y_tp + target.motionY * motion), (int)(z_tp + target.motionZ * motion), (double)this.range.getValue())), true);
        }
        final boolean block = KillAura.mc.thePlayer.isUsingItem();
        if (block) {
            KillAura.mc.playerController.onStoppedUsingItem((EntityPlayer)KillAura.mc.thePlayer);
        }
        KillAura.mc.playerController.attackEntity((EntityPlayer)KillAura.mc.thePlayer, (Entity)target);
        target.hurtTime = 10;
        KillAura.mc.thePlayer.swingItem();
        KillAura.mc.effectRenderer.emitParticleAtEntity((Entity)target, EnumParticleTypes.CRIT_MAGIC);
        if ((double)this.range_extended.getValue() != 0.0) {
            TeleportUtil.teleport((ArrayList)this.positions, false);
            SkillWrapper.sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(KillAura.mc.thePlayer.posX, KillAura.mc.thePlayer.posY, KillAura.mc.thePlayer.posZ, true));
        }
        if (block && KillAura.mc.thePlayer.inventory.getCurrentItem() != null && KillAura.mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemSword) {
            KillAura.mc.playerController.sendUseItem((EntityPlayer)KillAura.mc.thePlayer, (World)KillAura.mc.theWorld, KillAura.mc.thePlayer.inventory.getCurrentItem());
            KillAura.mc.gameSettings.keyBindUseItem.pressed = true;
        }
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        for (final Vec3d pos : this.positions) {
            RenderUtils.framelessBlockESP(new BlockPos(pos), Color.CYAN);
        }
    }
}
