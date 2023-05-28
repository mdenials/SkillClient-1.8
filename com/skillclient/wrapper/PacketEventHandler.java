package com.skillclient.wrapper;

import com.skillclient.events.EventPacketReceived;
import com.skillclient.events.api.EventTarget;
import net.minecraft.network.Packet;
import net.minecraft.network.EnumConnectionState;
import com.skillclient.gui.accountManager.AccountManager;
import net.minecraft.network.handshake.client.C00Handshake;
import com.skillclient.modules.player.NoSwing;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import com.skillclient.modules.auto.AutoSneak;
import com.skillclient.main.Register;
import com.skillclient.modules.player.NoFall;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.client.C01PacketChatMessage;
import com.skillclient.CreativeTabs.SaveInv;
import net.minecraft.client.gui.inventory.GuiChest;
import com.skillclient.utils.ItemStackUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import com.skillclient.events.EventPacketSend;
import net.minecraft.client.Minecraft;
import com.skillclient.misc.SCMC;

public class PacketEventHandler implements SCMC
{
    public static Minecraft mc;
    
    static {
        PacketEventHandler.mc = Minecraft.getMinecraft();
    }
    
    @EventTarget
    public void onSend(final EventPacketSend event) {
        try {
            final Packet packet = event.getPacket();
            if (packet instanceof C10PacketCreativeInventoryAction) {
                ItemStackUtil.modify(((C10PacketCreativeInventoryAction)packet).getStack());
            }
            if (PacketEventHandler.mc.currentScreen instanceof GuiChest && ((GuiChest)PacketEventHandler.mc.currentScreen).lowerChestInventory instanceof SaveInv) {
                event.setCancelled(true);
            }
            if (packet instanceof C01PacketChatMessage && PacketEventHandler.sc.chat.onMessageSend((C01PacketChatMessage)packet)) {
                event.setCancelled(true);
            }
            if (packet instanceof C14PacketTabComplete && PacketEventHandler.sc.chat.onTabComplete((C14PacketTabComplete)packet)) {
                event.setCancelled(true);
            }
            if (packet instanceof C03PacketPlayer && ((NoFall)Register.getModule((Class)NoFall.class)).isNoFall()) {
                ((C03PacketPlayer)packet).onGround = true;
            }
            if (((AutoSneak)Register.getModule((Class)AutoSneak.class)).isActive() && packet instanceof C0BPacketEntityAction && ((C0BPacketEntityAction)packet).getAction().equals((Object)C0BPacketEntityAction.Action.STOP_SNEAKING)) {
                event.setCancelled(true);
            }
            if (packet instanceof C0APacketAnimation && ((NoSwing)Register.getModule((Class)NoSwing.class)).isActive()) {
                event.setCancelled(true);
            }
            if (packet instanceof C00Handshake && AccountManager.bungeeHack && ((C00Handshake)packet).getRequestedState() == EnumConnectionState.LOGIN) {
                ((C00Handshake)packet).ip = String.valueOf(((C00Handshake)packet).ip) + "\u0000" + AccountManager.fakeIP + "\u0000" + PacketEventHandler.mc.getSession().getPlayerID().replace("-", "");
            }
            event.setPacket(packet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @EventTarget
    public void onReceived(final EventPacketReceived event) {
    }
}

