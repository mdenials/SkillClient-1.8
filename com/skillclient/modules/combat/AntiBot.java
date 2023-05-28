package com.skillclient.modules.combat;

import com.skillclient.events.EventPacketReceived;
import com.skillclient.events.api.EventTarget;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.wrapper.SkillWrapper;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S14PacketEntity;

public class AntiBot
extends Module {
    public ValueBoolean entityID = new ValueBoolean("EntityID", (Module)this, false);
    public ValueBoolean tablist = new ValueBoolean("TabList", (Module)this, false);
    public ValueBoolean ground = new ValueBoolean("Ground", (Module)this, false);
    public ValueBoolean swing = new ValueBoolean("Swing", (Module)this, false);
    public ValueBoolean velocity = new ValueBoolean("Velocity", (Module)this, false);
    public ValueBoolean invisible = new ValueBoolean("Invisible", (Module)this, false);
    public static Map<Integer, PlayerInfo> infos = new HashMap<Integer, PlayerInfo>();

    public AntiBot() {
        super("AntiBot", Register.Category.COMBAT, "Don't hit some Entitys");
    }

    public boolean isBot(EntityLivingBase entity) {
        if (!this.isActive()) {
            return false;
        }
        if (((Boolean)this.entityID.getValue()).booleanValue() && entity.getEntityId() > 10) {
            return true;
        }
        if (((Boolean)this.tablist.getValue()).booleanValue() && entity instanceof AbstractClientPlayer && !this.isInTab((AbstractClientPlayer)entity)) {
            return true;
        }
        if (((Boolean)this.ground.getValue()).booleanValue() && infos.containsKey(entity.getEntityId()) && !AntiBot.infos.get((Object)Integer.valueOf((int)entity.getEntityId())).ground) {
            return true;
        }
        if (((Boolean)this.swing.getValue()).booleanValue() && infos.containsKey(entity.getEntityId()) && !AntiBot.infos.get((Object)Integer.valueOf((int)entity.getEntityId())).swing) {
            return true;
        }
        if (((Boolean)this.velocity.getValue()).booleanValue() && infos.containsKey(entity.getEntityId()) && !AntiBot.infos.get((Object)Integer.valueOf((int)entity.getEntityId())).velocity) {
            return true;
        }
        return (Boolean)this.invisible.getValue() != false && entity.isInvisible();
    }

    public boolean isInTab(AbstractClientPlayer entity) {
        for (NetworkPlayerInfo p : SkillWrapper.getPlayerInfoMap()) {
            if (!p.equals((Object)entity.getPlayerInfo())) continue;
            return true;
        }
        return false;
    }

    @EventTarget
    public void onReceive(EventPacketReceived event) {
        try {
            if (event.getPacket() instanceof S0CPacketSpawnPlayer) {
                infos.put(((S0CPacketSpawnPlayer)event.getPacket()).getEntityID(), new PlayerInfo());
            } else if (event.getPacket() instanceof S13PacketDestroyEntities) {
                for (int i : ((S13PacketDestroyEntities)event.getPacket()).getEntityIDs()) {
                    infos.remove(i);
                }
            } else if (event.getPacket() instanceof S01PacketJoinGame) {
                infos.clear();
            } else if (event.getPacket() instanceof S0BPacketAnimation) {
                if (infos.containsKey(((S0BPacketAnimation)event.getPacket()).getEntityID())) {
                    AntiBot.infos.get((Object)Integer.valueOf((int)((S0BPacketAnimation)event.getPacket()).getEntityID())).swing = true;
                }
            } else if (event.getPacket() instanceof S14PacketEntity) {
                if (((S14PacketEntity)event.getPacket()).getOnGround() && infos.containsKey(((S14PacketEntity)event.getPacket()).entityId)) {
                    AntiBot.infos.get((Object)Integer.valueOf((int)((S14PacketEntity)event.getPacket()).entityId)).ground = true;
                }
            } else if (event.getPacket() instanceof S12PacketEntityVelocity && infos.containsKey(((S12PacketEntityVelocity)event.getPacket()).getEntityID())) {
                AntiBot.infos.get((Object)Integer.valueOf((int)((S12PacketEntityVelocity)event.getPacket()).getEntityID())).ground = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class PlayerInfo {
        public boolean ground = false;
        public boolean swing = false;
        public boolean velocity = false;
    }
}