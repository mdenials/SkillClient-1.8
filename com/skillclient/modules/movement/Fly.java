package com.skillclient.modules.movement;

import com.skillclient.events.EventRotation;
import com.skillclient.events.api.EventTarget;
import net.minecraft.item.ItemStack;
import com.skillclient.modules.world.FastPlace;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.ItemBlock;
import com.skillclient.events.EventUpdate;
import com.skillclient.modules.player.NoClip;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Fly extends Module
{
    ValueNumber speed;
    ValueNumber speed_;
    ValueBoolean cubecraft;
    ValueBoolean vanillaBypass;
    boolean canCubeFly;
    
    public Fly() {
        super("Fly", Register.Category.MOVEMENT, "Vanilla Flight");
        this.speed = new ValueNumber("Speed [xz]", (Module)this, 10.0, 0.0, 3.0, 1);
        this.speed_ = new ValueNumber("Speed [y]", (Module)this, 10.0, 0.0, 3.0, 1);
        this.cubecraft = new ValueBoolean("Cubecraft", (Module)this, false);
        this.vanillaBypass = new ValueBoolean("Anti Vanilla Kick", (Module)this, false);
        this.canCubeFly = false;
    }
    
    public boolean isActive() {
        return super.isActive() || Register.isActive((Class)NoClip.class);
    }
    
    public void onEnable() {
        super.onEnable();
        this.canCubeFly = false;
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (Fly.mc.thePlayer.motionY < -0.5) {
            this.canCubeFly = true;
        }
        if ((boolean)this.cubecraft.getValue() && !this.canCubeFly && !Register.isActive((Class)NoClip.class)) {
            if (Fly.mc.thePlayer.getHeldItem() != null && Fly.mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock) {
                if (Fly.mc.thePlayer.onGround) {
                    Fly.mc.thePlayer.jump();
                }
                if (Fly.mc.objectMouseOver.sideHit == EnumFacing.UP && Fly.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && Fly.mc.rightClickDelayTimer == 0 && Fly.mc.playerController.onPlayerRightClick(Fly.mc.thePlayer, Fly.mc.theWorld, Fly.mc.thePlayer.getHeldItem(), Fly.mc.objectMouseOver.getBlockPos(), Fly.mc.objectMouseOver.sideHit, Fly.mc.objectMouseOver.hitVec)) {
                    Fly.mc.thePlayer.swingItem();
                    Fly.mc.rightClickDelayTimer = ((FastPlace)Register.getModule((Class)FastPlace.class)).getClick();
                    this.canCubeFly = true;
                    Fly.mc.thePlayer.inventory.currentItem = 0;
                }
            }
            else {
                for (int i = 0; i < 9; ++i) {
                    final ItemStack item = Fly.mc.thePlayer.inventory.getStackInSlot(i);
                    if (item != null && item.getItem() instanceof ItemBlock) {
                        Fly.mc.thePlayer.inventory.currentItem = i;
                        return;
                    }
                }
            }
            if (!this.canCubeFly) {
                return;
            }
        }
        if (((boolean)this.cubecraft.getValue() || (boolean)this.vanillaBypass.getValue()) && !Register.isActive((Class)NoClip.class)) {
            if (Fly.mc.thePlayer.fallDistance > 2.0f) {
                Fly.mc.thePlayer.motionY = 2.0;
                Fly.mc.thePlayer.fallDistance = 0.0f;
            }
            else {
                Fly.mc.thePlayer.motionY = -0.0784000015258789;
            }
        }
        else {
            Fly.mc.thePlayer.motionY = 0.0;
        }
        if (Fly.mc.gameSettings.keyBindSneak.isKeyDown()) {
            Fly.mc.thePlayer.motionY = -(double)this.speed_.getValue() / 5.0;
        }
        if (Fly.mc.gameSettings.keyBindJump.isKeyDown()) {
            Fly.mc.thePlayer.motionY = (double)this.speed_.getValue() / 5.0;
        }
    }
    
    @EventTarget(3)
    public void onUpdate(final EventRotation event) {
        if (this.isActive() && Fly.mc.thePlayer != null && Fly.mc.thePlayer.getHeldItem() != null && Fly.mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock && !this.canCubeFly) {
            event.setPitch(90.0f);
            event.stop();
        }
    }
}

