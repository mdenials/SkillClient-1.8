package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class CmdRename extends Cmd
{
    public CmdRename() {
        super("rename", "Rename your held item in gm1");
    }
    
    @Override
    public void action(final String[] args) {
        if (CmdRename.mc.playerController.isNotCreative()) {
            CmdRename.sc.chat.chat("Creative only!");
        }
        final ItemStack s = CmdRename.mc.thePlayer.getHeldItem();
        s.setStackDisplayName(String.join(" ", (CharSequence[])args).replace('&', '§'));
        SkillWrapper.sendPacket((Packet)new C10PacketCreativeInventoryAction(36 + CmdRename.mc.thePlayer.inventory.currentItem, s));
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        return new ArrayList<String>();
    }
}
