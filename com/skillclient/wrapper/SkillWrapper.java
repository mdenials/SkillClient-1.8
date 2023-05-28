package com.skillclient.wrapper;

import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.Vec3d;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.BlockPos;
import java.util.Iterator;
import java.util.Collection;
import com.skillclient.modules.render.CaveFinder;
import com.skillclient.modules.render.XRay;
import com.skillclient.modules.movement.Strafe;
import com.skillclient.main.Register;
import com.skillclient.modules.movement.Speed;
import net.minecraft.network.Packet;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;

public class SkillWrapper
{
    public static Minecraft mc;
    static ArrayList<Packet> packets;
    
    static {
        SkillWrapper.mc = Minecraft.getMinecraft();
        SkillWrapper.packets = new ArrayList<Packet>();
    }
    
    public static float getSpeed(float f8) {
        if (SkillWrapper.mc.thePlayer == null) {
            return f8;
        }
        if (((Speed)Register.getModule((Class)Speed.class)).isActive()) {
            Register.getModule((Class)Speed.class);
            return Speed.getSpeed();
        }
        if (((Strafe)Register.getModule((Class)Strafe.class)).isActive()) {
            Register.getModule((Class)Strafe.class);
            return Strafe.getSpeed();
        }
        if (((Speed)Register.getModule((Class)Speed.class)).isActive() && !SkillWrapper.mc.thePlayer.onGround) {
            f8 *= ((Speed)Register.getModule((Class)Speed.class)).base_speed.getInt() / 100.0f;
        }
        return f8;
    }
    
    public static boolean renderAllBlocks() {
        return ((XRay)Register.getModule((Class)XRay.class)).isActive() || (((CaveFinder)Register.getModule((Class)CaveFinder.class)).isActive() && (boolean)((CaveFinder)Register.getModule((Class)CaveFinder.class)).allBlocks.getValue());
    }
    
    public static void sendPacket(final Packet p) {
        SkillWrapper.mc.getNetHandler().addToSendQueue(p);
    }
    
    public static void sendPacketSoon(final Packet p) {
        SkillWrapper.packets.add(p);
    }
    
    public static void postUpdate() {
        if (SkillWrapper.mc.getNetHandler() == null) {
            return;
        }
        final ArrayList<Packet> n = new ArrayList<Packet>(SkillWrapper.packets);
        SkillWrapper.packets.clear();
        for (final Packet p : n) {
            sendPacket(p);
        }
    }
    
    public static boolean rightClickBlock(final BlockPos hitPos, final EnumFacing side, final Vec3d hitVec) {
        return SkillWrapper.mc.playerController.onPlayerRightClick(SkillWrapper.mc.thePlayer, SkillWrapper.mc.theWorld, SkillWrapper.mc.thePlayer.getHeldItem(), hitPos, side, hitVec);
    }
    
    public static Collection<NetworkPlayerInfo> getPlayerInfoMap() {
        return (Collection<NetworkPlayerInfo>)Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
    }
}

