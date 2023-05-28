package com.skillclient.modules.player;

import com.skillclient.modules.movement.Fly;
import com.skillclient.modules.movement.Glide;
import com.skillclient.events.api.EventTarget;
import net.minecraft.init.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class NoFall extends Module
{
    ValueBoolean aac3;
    byte fall;
    
    public NoFall() {
        super("NoFall", Register.Category.PLAYER, "Disables Fall-Damage");
        this.aac3 = new ValueBoolean("AAC3", (Module)this, false);
        this.fall = 0;
    }
    
    public String getDisplayName() {
        return String.valueOf(super.getDisplayName()) + (((boolean)this.aac3.getValue()) ? Byte.valueOf(this.fall) : "");
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (NoFall.mc.thePlayer.isInWater() || NoFall.mc.theWorld.getBlockState(new BlockPos((Entity)NoFall.mc.thePlayer)).getBlock().equals(Blocks.ladder) || NoFall.mc.theWorld.getBlockState(new BlockPos((Entity)NoFall.mc.thePlayer)).getBlock().equals(Blocks.web)) {
            this.fall = 0;
        }
        if ((boolean)this.aac3.getValue() && this.fall > 0 && NoFall.mc.thePlayer.onGround) {
            NoFall.mc.thePlayer.jump(0.62f);
            --this.fall;
        }
    }
    
    public boolean isActive() {
        return super.isActive() || ((Glide)Register.getModule((Class)Glide.class)).isActive() || ((Fly)Register.getModule((Class)Fly.class)).isActive();
    }
    
    public boolean isNoFall() {
        if (!this.isActive()) {
            return false;
        }
        if ((boolean)this.aac3.getValue() && NoFall.mc.thePlayer.fallDistance > 2.0f) {
            NoFall.mc.thePlayer.fallDistance = 0.0f;
            if (this.fall == 0) {
                this.fall = 2;
            }
            ++this.fall;
            return true;
        }
        return !(boolean)this.aac3.getValue();
    }
    
    public void onEnable() {
        super.onEnable();
        this.fall = 0;
    }
}

