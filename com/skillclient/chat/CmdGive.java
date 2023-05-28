package com.skillclient.chat;

import java.util.Collections;
import java.util.Arrays;
import java.util.Collection;
import net.minecraft.command.CommandBase;
import net.minecraft.item.Item;
import java.util.List;
import net.minecraft.item.ItemStack;
import java.util.Map;
import com.skillclient.utils.Download;
import java.util.HashMap;
import com.skillclient.main.SkillClient;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import com.skillclient.utils.ItemStackUtil;
import org.apache.commons.lang3.StringUtils;

public class CmdGive extends Cmd
{
    public CmdGive() {
        super("give", "like /give but without OP (Creative Only)");
    }
    
    @Override
    public void action(final String[] args) {
        if (CmdGive.mc.playerController.isNotCreative()) {
            CmdGive.sc.chat.chat("Creative only!");
        }
        final String arg = StringUtils.join((Object[])args, " ");
        final ItemStack s = ItemStackUtil.stringtostack(arg);
        SkillWrapper.sendPacket((Packet)new C10PacketCreativeInventoryAction(36 + CmdGive.mc.thePlayer.inventory.currentItem, s));
        CmdGive.mc.thePlayer.inventory.setInventorySlotContents(36 + CmdGive.mc.thePlayer.inventory.currentItem, s);
        CmdGive.sc.chat.chat("Added Item: " + s.getItem().getItemStackDisplayName(s) + " " + s.stackSize + " " + s.getMetadata() + (s.hasTagCompound() ? (" " + s.getTagCompound().toString()) : ""));
        HashMap<String, String> map;
        final String s2;
        SkillClient.executor.submit(() -> {
            try {
                map = new HashMap<String, String>();
                map.put("user", new StringBuilder().append(Download.user_id).toString());
                map.put("item", s2);
                Download.getHttpsConnection("https://server.skillclient.com/api2/stats/give_item.php", (Map)map).getResponseCode();
            }
            catch (Exception e) {
                if (CmdGive.sc.indev) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        if (args.length == 1) {
            return (List<String>)CommandBase.getListOfStringsMatchingLastWord(args, (Collection)Item.itemRegistry.getKeys());
        }
        if (args.length == 2) {
            return Arrays.asList("1");
        }
        if (args.length == 3) {
            return Arrays.asList("0");
        }
        if (args.length == 4) {
            return Arrays.asList("{}", "{display:{Name:\"Test\"}}", "{display:{Lore:[0:\"line1\",1:\"line2\"]}}", "{HideFlags:63}");
        }
        return Collections.emptyList();
    }
}
