package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.client.gui.GuiScreen;
import java.util.Arrays;

public class CmdNBT extends Cmd
{
    public CmdNBT() {
        super("nbt", "Modify NBT-Tags of held item in gm1");
    }
    
    @Override
    public void action(final String[] args) {
        if (CmdNBT.mc.playerController.isNotCreative()) {
            CmdNBT.sc.chat.chat("Creative only!");
        }
        final ItemStack stack = CmdNBT.mc.thePlayer.getHeldItem();
        final String last_args = (args.length >= 1) ? String.join(" ", (CharSequence[])Arrays.copyOfRange(args, 1, args.length)).replace("&", "§") : "";
        if (args.length >= 1 && args[0].equalsIgnoreCase("show")) {
            CmdNBT.sc.chat.chat(stack.hasTagCompound() ? stack.getTagCompound().toString().replace("§", "&") : "No NBT-Tag!");
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("copy")) {
            GuiScreen.setClipboardString(stack.hasTagCompound() ? stack.getTagCompound().toString() : "{}");
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("set")) {
            try {
                final NBTTagCompound tags = JsonToNBT.getTagFromJson(last_args);
                stack.setTagCompound(tags);
            }
            catch (NBTException e) {
                CmdNBT.sc.chat.chat("Invalid Tag Compund: " + e.getMessage());
            }
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("add")) {
            try {
                final NBTTagCompound tags = JsonToNBT.getTagFromJson(last_args);
                if (stack.hasTagCompound()) {
                    stack.getTagCompound().merge(tags);
                }
                else {
                    stack.setTagCompound(tags);
                }
            }
            catch (NBTException e) {
                CmdNBT.sc.chat.chat("Invalid Tag Compund: " + e.getMessage());
            }
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("remove")) {
            if (stack.hasTagCompound()) {
                final String[] remove_array = last_args.split(" ");
                NBTTagCompound tags2 = stack.getTagCompound();
                for (int i = 0; i < remove_array.length - 1; ++i) {
                    tags2 = tags2.getCompoundTag(remove_array[i]);
                }
                tags2.removeTag(remove_array[remove_array.length - 1]);
            }
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("clear")) {
            stack.setTagCompound((NBTTagCompound)null);
        }
        else {
            CmdNBT.sc.chat.chat(".nbt show", ".nbt show");
            CmdNBT.sc.chat.chat(".nbt copy", ".nbt copy");
            CmdNBT.sc.chat.chat(".nbt set {display:{Name:\"hi\"}}", ".nbt set {display:{Name:\"hi\"}}");
            CmdNBT.sc.chat.chat(".nbt add {display:{Name:\"hi\"}}", ".nbt add {display:{Name:\"hi\"}}");
            CmdNBT.sc.chat.chat(".nbt remove display Lore", ".nbt remove display Lore");
            CmdNBT.sc.chat.chat(".nbt clear", ".nbt clear");
        }
        SkillWrapper.sendPacket((Packet)new C10PacketCreativeInventoryAction(36 + CmdNBT.mc.thePlayer.inventory.currentItem, stack));
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        final List<String> cmds = new ArrayList<String>();
        if (args.length == 1) {
            String[] array;
            for (int length = (array = new String[] { "show", "copy", "set", "add", "remove", "clear" }).length, i = 0; i < length; ++i) {
                final String s = array[i];
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    cmds.add(s);
                }
            }
        }
        return cmds;
    }
}
