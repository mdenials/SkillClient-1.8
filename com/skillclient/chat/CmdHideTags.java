package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.nbt.NBTTagCompound;

public class CmdHideTags extends Cmd
{
    public CmdHideTags() {
        super("hideTags", "Set Hide tags for your held item in gm1");
    }
    
    @Override
    public void action(final String[] args) {
        if (CmdHideTags.mc.playerController.isNotCreative()) {
            CmdHideTags.sc.chat.chat("Creative only!");
        }
        final ItemStack stack = CmdHideTags.mc.thePlayer.getHeldItem();
        final NBTTagCompound tags = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        int b = tags.hasKey("HideFlags") ? tags.getByte("HideFlags") : 0;
        if (args.length == 1 && args[0].equalsIgnoreCase("Enchantments")) {
            b ^= 0x1;
            CmdHideTags.sc.chat.chat("Enchantments: " + (((b & 0x1) == 0x0) ? "shown" : "hidden"), ".hideTags Enchantments");
        }
        else if (args.length == 1 && args[0].equalsIgnoreCase("AttributeModifiers")) {
            b ^= 0x2;
            CmdHideTags.sc.chat.chat("AttributeModifiers: " + (((b & 0x2) == 0x0) ? "shown" : "hidden"), ".hideTags AttributeModifiers");
        }
        else if (args.length == 1 && args[0].equalsIgnoreCase("Unbreakable")) {
            b ^= 0x4;
            CmdHideTags.sc.chat.chat("Unbreakable: " + (((b & 0x4) == 0x0) ? "shown" : "hidden"), ".hideTags Unbreakable");
        }
        else if (args.length == 1 && args[0].equalsIgnoreCase("CanDestroy")) {
            b ^= 0x8;
            CmdHideTags.sc.chat.chat("CanDestroy: " + (((b & 0x8) == 0x0) ? "shown" : "hidden"), ".hideTags CanDestroy");
        }
        else if (args.length == 1 && args[0].equalsIgnoreCase("CanPlaceOn")) {
            b ^= 0x10;
            CmdHideTags.sc.chat.chat("CanPlaceOn: " + (((b & 0x10) == 0x0) ? "shown" : "hidden"), ".hideTags CanPlaceOn");
        }
        else if (args.length == 1 && args[0].equalsIgnoreCase("Others")) {
            b ^= 0x20;
            CmdHideTags.sc.chat.chat("Others: " + (((b & 0x20) == 0x0) ? "shown" : "hidden"), ".hideTags Others");
        }
        else {
            CmdHideTags.sc.chat.chat("Enchantments: " + (((b & 0x1) == 0x0) ? "shown" : "hidden"), ".hideTags Enchantments");
            CmdHideTags.sc.chat.chat("AttributeModifiers: " + (((b & 0x2) == 0x0) ? "shown" : "hidden"), ".hideTags AttributeModifiers");
            CmdHideTags.sc.chat.chat("Unbreakable: " + (((b & 0x4) == 0x0) ? "shown" : "hidden"), ".hideTags Unbreakable");
            CmdHideTags.sc.chat.chat("CanDestroy: " + (((b & 0x8) == 0x0) ? "shown" : "hidden"), ".hideTags CanDestroy");
            CmdHideTags.sc.chat.chat("CanPlaceOn: " + (((b & 0x10) == 0x0) ? "shown" : "hidden"), ".hideTags CanPlaceOn");
            CmdHideTags.sc.chat.chat("Others: " + (((b & 0x20) == 0x0) ? "shown" : "hidden"), ".hideTags Others");
        }
        tags.setByte("HideFlags", (byte)b);
        stack.setTagCompound(tags);
        SkillWrapper.sendPacket((Packet)new C10PacketCreativeInventoryAction(36 + CmdHideTags.mc.thePlayer.inventory.currentItem, stack));
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        final List<String> cmds = new ArrayList<String>();
        String[] array;
        for (int length = (array = new String[] { "Enchantments", "AttributeModifiers", "Unbreakable", "CanDestroy", "CanPlaceOn", "Others" }).length, i = 0; i < length; ++i) {
            final String s = array[i];
            if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                cmds.add(s);
            }
        }
        return cmds;
    }
}

