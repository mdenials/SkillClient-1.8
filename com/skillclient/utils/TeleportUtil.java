package com.skillclient.utils;

import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.Vec3d;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;

public class TeleportUtil
{
    private static final Minecraft mc;
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public static ArrayList<Vec3d> teleport(final int x_new, final int y_new, final int z_new, final double range) {
        final ArrayList<Vec3d> positions = new ArrayList<Vec3d>();
        final int pref = 3;
        final int x_old = (int)TeleportUtil.mc.thePlayer.posX;
        final int y_old = (int)TeleportUtil.mc.thePlayer.posY;
        final int z_old = (int)TeleportUtil.mc.thePlayer.posZ;
        final double distance = Math.sqrt(Math.pow(x_old - x_new, 2.0) + Math.pow(y_old - y_new, 2.0) + Math.pow(z_old - z_new, 2.0));
        for (int i = 0; i < distance; i += 3) {
            positions.add(new Vec3d(x_old + (x_new - x_old) / distance * i, y_old + (y_new - y_old) / distance * i, z_old + (z_new - z_old) / distance * i));
        }
        return positions;
    }
    
    public static void teleport(final ArrayList<Vec3d> positions, final boolean direction) {
        if (direction) {
            for (int i = 0; i < positions.size(); ++i) {
                SkillWrapper.sendPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(positions.get(i).xCoord, positions.get(i).yCoord, positions.get(i).zCoord, true));
            }
        }
        else {
            for (int i = positions.size() - 2; i > 0; --i) {
                SkillWrapper.sendPacketSoon((Packet)new C03PacketPlayer.C04PacketPlayerPosition(positions.get(i).xCoord, positions.get(i).yCoord, positions.get(i).zCoord, true));
            }
        }
    }
}

