package com.skillclient.modules.render;

import net.minecraft.stats.StatFileWriter;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.world.World;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

class FreeCam$1 extends EntityPlayerSP {
    public void onUpdateWalkingPlayer() {
    }
    
    public boolean isSpectator() {
        return true;
    }
}
