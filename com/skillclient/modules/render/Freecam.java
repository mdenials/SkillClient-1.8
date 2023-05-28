package com.skillclient.modules.render;

import com.skillclient.events.EventUpdate;
import net.minecraft.network.play.client.C03PacketPlayer;
import com.skillclient.events.EventPacketSend;
import net.minecraft.util.MovementInputFromOptions;
import com.skillclient.events.api.EventTarget;
import com.skillclient.utils.RenderUtils;
import com.skillclient.events.EventRender;
import net.minecraft.util.MovementInput;
import net.minecraft.world.World;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.entity.Entity;
import com.skillclient.main.Register;
import net.minecraft.client.entity.EntityPlayerSP;
import com.skillclient.misc.Module;

public class FreeCam extends Module
{
    public EntityPlayerSP fakePlayer;
    
    public FreeCam() {
        super("FreeCam", Register.Category.RENDER, "Free camera-movement");
        this.fakePlayer = null;
    }
    
    public void onDisable() {
        if (FreeCam.mc.thePlayer != null) {
            this.fakePlayer.setDead();
            FreeCam.mc.theWorld.removeEntity((Entity)this.fakePlayer);
            FreeCam.mc.setRenderViewEntity((Entity)FreeCam.mc.thePlayer);
        }
        super.onDisable();
    }
    
    public void onEnable() {
        if (FreeCam.mc.thePlayer == null) {
            this.onDisable();
            return;
        }
        (this.fakePlayer = (EntityPlayerSP)new FreeCam.FreeCam$1(this, FreeCam.mc, (World)FreeCam.mc.theWorld, FreeCam.mc.getNetHandler(), new StatFileWriter())).copyLocationAndAnglesFrom((Entity)FreeCam.mc.thePlayer);
        this.fakePlayer.movementInput = new MovementInput();
        FreeCam.mc.theWorld.addEntityToWorld(-69, (Entity)this.fakePlayer);
        FreeCam.mc.setRenderViewEntity((Entity)this.fakePlayer);
        super.onEnable();
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        RenderUtils.tracerLine((Entity)FreeCam.mc.thePlayer, 4);
    }
    
    public static void updatePlayerMoveState(final MovementInputFromOptions mifo) {
        final FreeCam freecam = (FreeCam)Register.getModule((Class)FreeCam.class);
        if (!freecam.isActive()) {
            return;
        }
        freecam.fakePlayer.motionY = 0.0;
        if (FreeCam.mc.gameSettings.keyBindJump.pressed) {
            final EntityPlayerSP fakePlayer = freecam.fakePlayer;
            ++fakePlayer.motionY;
        }
        if (FreeCam.mc.gameSettings.keyBindSneak.pressed) {
            final EntityPlayerSP fakePlayer2 = freecam.fakePlayer;
            --fakePlayer2.motionY;
        }
        freecam.fakePlayer.movementInput.moveForward = 0.0f;
        freecam.fakePlayer.movementInput.moveStrafe = 0.0f;
        if (mifo.gameSettings.keyBindForward.isKeyDown()) {
            final MovementInput movementInput = freecam.fakePlayer.movementInput;
            ++movementInput.moveForward;
        }
        if (mifo.gameSettings.keyBindBack.isKeyDown()) {
            final MovementInput movementInput2 = freecam.fakePlayer.movementInput;
            --movementInput2.moveForward;
        }
        if (mifo.gameSettings.keyBindLeft.isKeyDown()) {
            final MovementInput movementInput3 = freecam.fakePlayer.movementInput;
            ++movementInput3.moveStrafe;
        }
        if (mifo.gameSettings.keyBindRight.isKeyDown()) {
            final MovementInput movementInput4 = freecam.fakePlayer.movementInput;
            --movementInput4.moveStrafe;
        }
        freecam.fakePlayer.movementInput.jump = mifo.gameSettings.keyBindJump.isKeyDown();
        freecam.fakePlayer.movementInput.sneak = mifo.gameSettings.keyBindSneak.isKeyDown();
        freecam.fakePlayer.rotationPitch = FreeCam.mc.thePlayer.rotationPitch;
        freecam.fakePlayer.rotationYaw = FreeCam.mc.thePlayer.rotationYaw;
        freecam.fakePlayer.prevRotationPitch = FreeCam.mc.thePlayer.prevRotationPitch;
        freecam.fakePlayer.prevRotationYaw = FreeCam.mc.thePlayer.prevRotationYaw;
    }
    
    @EventTarget
    public void onSend(final EventPacketSend event) {
        if (event.getPacket() instanceof C03PacketPlayer) {
            event.setCancelled(true);
        }
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        this.fakePlayer.inventory = FreeCam.mc.thePlayer.inventory;
    }
}
