package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class CmdLore extends Cmd
{
    public CmdLore() {
        super("lore", "Set lore your held item in gm1");
    }
    
    @Override
    public void action(final String[] args) {
        if (CmdLore.mc.playerController.isNotCreative()) {
            CmdLore.sc.chat.chat("Creative only!");
        }
        final ItemStack s = CmdLore.mc.thePlayer.getHeldItem();
        s.setStackDisplayLore(String.join(" ", (CharSequence[])args).replace('&', '§').split(";"));
        SkillWrapper.sendPacket((Packet)new C10PacketCreativeInventoryAction(36 + CmdLore.mc.thePlayer.inventory.currentItem, s));
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        return new ArrayList<String>();
    }
}
