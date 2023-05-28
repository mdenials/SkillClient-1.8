package com.skillclient.modules.combat;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import com.skillclient.events.api.EventTarget;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemFood;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.utils.TimerUtil;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class FastUse extends Module
{
    ValueNumber bow;
    ValueNumber food;
    TimerUtil timer;
    
    public FastUse() {
        super("FastUse", Register.Category.COMBAT, "Eat and shoot Faster (1.8 only)");
        this.bow = new ValueNumber("Bow", " Packets", (Module)this, 20.0, 0.0, 20.0, 0);
        this.food = new ValueNumber("Food", " Packets", (Module)this, 20.0, 0.0, 20.0, 0);
        this.timer = new TimerUtil();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (FastUse.mc.thePlayer.isUsingItem()) {
            final Item item = FastUse.mc.thePlayer.getHeldItem().getItem();
            if (item instanceof ItemFood || item instanceof ItemPotion) {
                this.send(this.food.getInt());
            }
            else if (item instanceof ItemBow) {
                if (72000 - FastUse.mc.thePlayer.getItemInUseCount() >= 20) {
                    FastUse.mc.playerController.onStoppedUsingItem((EntityPlayer)FastUse.mc.thePlayer);
                }
                else {
                    this.send(this.bow.getInt());
                }
            }
        }
        else {
            this.timer.setLastMS();
        }
    }
    
    public void send(final int speed) {
        if (speed == 0) {
            return;
        }
        for (int i = 0; i < speed; ++i) {
            final EntityPlayerSP thePlayer = FastUse.mc.thePlayer;
            --thePlayer.itemInUseCount;
            FastUse.mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer(true));
        }
    }
}
