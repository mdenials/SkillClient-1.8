package com.skillclient.modules.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import com.skillclient.gui.GuiSkillClientSettings;
import java.util.Collections;
import net.minecraft.entity.EntityLivingBase;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.skillclient.utils.exceptions.NoEntityFoundException;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import com.skillclient.utils.FakePlayer.EntityFakePlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import com.skillclient.misc.ValueModule;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class TargetUtil extends Module
{
    public ValueBoolean player;
    public ValueBoolean mobs;
    public ValueBoolean animals;
    public ValueBoolean items;
    public ValueBoolean other;
    public ValueBoolean ignore_own_team;
    public int waitForDamage;
    
    public TargetUtil() {
        super("Targets", Register.Category.COMBAT, "Choose Targets");
        this.player = new ValueBoolean("Player", (Module)this, true);
        this.mobs = new ValueBoolean("Mobs", (Module)this, true);
        this.animals = new ValueBoolean("Animals", (Module)this, true);
        this.items = new ValueBoolean("Items", (Module)this, true);
        this.other = new ValueBoolean("Other", (Module)this, false);
        this.ignore_own_team = new ValueBoolean("Ignore own team", (Module)this, true);
        new ValueModule("KillAura", (Module)this, (Class)KillAura.class);
        new ValueModule("BowAimBot", (Module)this, (Class)BowAimBot.class);
        new ValueModule("HitBox", (Module)this, (Class)HitBox.class);
        this.waitForDamage = 0;
        this.modes = 0;
        this.valueList.remove(1);
        this.valueList.remove(0);
    }
    
    public boolean isValidType(final Entity entity) {
        if (entity instanceof EntityPlayerSP || entity instanceof EntityFakePlayer) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            return (boolean)this.player.getValue();
        }
        if (entity instanceof EntityAmbientCreature || entity instanceof EntityAgeable || entity instanceof EntityWaterMob) {
            return (boolean)this.animals.getValue();
        }
        if (entity instanceof EntityLiving && !(entity instanceof EntityArmorStand)) {
            return (boolean)this.mobs.getValue();
        }
        if (entity instanceof EntityItem) {
            return (boolean)this.items.getValue();
        }
        return entity instanceof EntityFallingBlock || (boolean)this.other.getValue();
    }
    
    public <E extends Entity> E bestEntity(final Class<? extends E> clazz, final TargetUtil.EntityComparator<E>... comparator) throws NoEntityFoundException {
        final List<E> list = this.bestEntities(clazz, comparator);
        if (list.isEmpty()) {
            throw new NoEntityFoundException();
        }
        return list.get(0);
    }
    
    public Entity entityByName(final String name) {
        for (final Entity entity : TargetUtil.mc.theWorld.loadedEntityList) {
            if (entity.getName().contains(name)) {
                return entity;
            }
        }
        return null;
    }
    
    public <E extends Entity> ArrayList<E> bestEntities(final Class<? extends E> clazz, final TargetUtil.EntityComparator<E>... comparator) {
        final ArrayList<Entity> list = new ArrayList<Entity>(TargetUtil.mc.theWorld.loadedEntityList);
        list.removeIf(entity -> !clazz.isInstance(entity));
        list.removeIf(entity -> entity instanceof EntityLivingBase && !this.matches(entity));
        for (final TargetUtil.EntityComparator ec : comparator) {
            list.removeIf(entiy -> !ec.matches(entiy));
        }
        for (final TargetUtil.EntityComparator ec : comparator) {
            final TargetUtil.EntityComparator entityComparator;
            Collections.sort(list, (e1, e2) -> (int)(entityComparator.value(e1) - entityComparator.value(e2)));
        }
        return (ArrayList<E>)list;
    }
    
    public boolean matches(final EntityLivingBase entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof EntityPlayerSP) {
            return false;
        }
        if (entity.hurtTime > 10 - this.waitForDamage) {
            return false;
        }
        if (entity.deathTime > 0) {
            return false;
        }
        if (!this.isValidType((Entity)entity)) {
            return false;
        }
        if ((boolean)this.ignore_own_team.getValue() && this.isSameTeam(entity.getTeam(), TargetUtil.mc.thePlayer.getTeam())) {
            return false;
        }
        for (String friend : GuiSkillClientSettings.friends) {
            friend = friend.replace("&", "§").replace(" ", "");
            if (entity.getName().replace(" ", "").equalsIgnoreCase(friend)) {
                return false;
            }
            if (entity.getDisplayName().getFormattedText().replace(" ", "").equalsIgnoreCase(friend)) {
                return false;
            }
            if (entity.hasCustomName() && entity.getCustomNameTag().replace(" ", "").equalsIgnoreCase(friend)) {
                return false;
            }
        }
        return !((AntiBot)Register.getModule((Class)AntiBot.class)).isBot(entity);
    }
    
    public boolean isSameTeam(final Team t1, final Team t2) {
        return t1 != null && t2 != null && (t1.equals(t2) || (t1 instanceof ScorePlayerTeam && t2 instanceof ScorePlayerTeam && ((ScorePlayerTeam)t1).getColorPrefix().equals(((ScorePlayerTeam)t2).getColorPrefix())));
    }
}
