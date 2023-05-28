package com.skillclient.chat;

import java.util.Iterator;
import net.minecraft.client.network.NetworkPlayerInfo;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import java.util.Arrays;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

public class CmdBook extends Cmd
{
    public CmdBook() {
        super("book", "Set tags for your held book in gm1");
    }
    
    @Override
    public void action(final String[] args) {
        if (CmdBook.mc.playerController.isNotCreative()) {
            CmdBook.sc.chat.chat("Creative only!");
        }
        ItemStack stack = CmdBook.mc.thePlayer.getHeldItem();
        if (stack.getItem() != Items.written_book) {
            stack = new ItemStack(Items.written_book);
            try {
                stack.setTagCompound(JsonToNBT.getTagFromJson("{pages:[0:\"{\\\"text\\\":\\\"Hello world!\\\"}\"],author:\"MCmodding4K\",title:\"test\"}"));
            }
            catch (NBTException e) {
                e.printStackTrace();
            }
        }
        final NBTTagCompound tags = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        final String last_args = (args.length >= 1) ? String.join(" ", (CharSequence[])Arrays.copyOfRange(args, 1, args.length)) : "";
        if (args.length >= 1 && args[0].equalsIgnoreCase("author")) {
            tags.setString("author", last_args);
            CmdBook.sc.chat.chat("Set Author to \"" + last_args + "\"");
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("title")) {
            tags.setString("title", last_args);
            CmdBook.sc.chat.chat("Set Title to \"" + last_args + "\"");
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("generation")) {
            if (last_args.isEmpty()) {
                for (int i = 0; i < 4; ++i) {
                    CmdBook.sc.chat.chat("Click for generation: " + StatCollector.translateToLocal("book.generation." + i), ".book generation " + i);
                }
            }
            else {
                try {
                    tags.setInteger("generation", Integer.parseInt(last_args));
                    CmdBook.sc.chat.chat("Set Title to \"" + StatCollector.translateToLocal("book.generation." + tags.getInteger("generation")) + "\"");
                }
                catch (NumberFormatException e3) {
                    CmdBook.sc.chat.chat("Has to be integer: " + last_args);
                }
            }
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("copy")) {
            try {
                final String copy = tags.getTagList("pages", 8).getStringTagAt(Integer.parseInt(last_args)).toString();
                GuiScreen.setClipboardString(copy);
                CmdBook.sc.chat.chat("Copied: " + copy);
            }
            catch (NumberFormatException e3) {
                CmdBook.sc.chat.chat("Has to be integer: " + last_args);
            }
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("set")) {
            try {
                tags.getTagList("pages", 8).set(Integer.parseInt(last_args), (NBTBase)new NBTTagString(GuiScreen.getClipboardString()));
            }
            catch (NumberFormatException e3) {
                CmdBook.sc.chat.chat("Has to be integer: " + last_args);
            }
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("add")) {
            try {
                tags.getTagList("pages", 8).appendTag((NBTBase)new NBTTagString(GuiScreen.getClipboardString()));
            }
            catch (NumberFormatException e3) {
                CmdBook.sc.chat.chat("Has to be integer: " + last_args);
            }
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("type")) {
            if (stack.getItem() != Items.written_book) {
                CmdBook.sc.chat.chat("Only usefull for written books");
            }
            else if (!last_args.equals("confirm")) {
                CmdBook.sc.chat.chat("§cData might get lost!");
                CmdBook.sc.chat.chat("type .book type confirm", ".book type confirm");
            }
            else {
                stack = new ItemStack(Items.writable_book, stack.stackSize, stack.getMetadata());
                final NBTTagList old = tags.getTagList("pages", 8);
                final NBTTagList new_ = new NBTTagList();
                for (int j = 0; j < old.tagCount(); ++j) {
                    try {
                        final NBTTagCompound tag = JsonToNBT.getTagFromJson(old.getStringTagAt(j));
                        System.out.println(String.valueOf(tag.toString()) + " " + tag.getTag("\"text\""));
                        new_.appendTag((NBTBase)new NBTTagString(tag.getString("\"text\"").replace("\\n", "\n")));
                    }
                    catch (NBTException e2) {
                        CmdBook.sc.chat.chat("Invalid Tag Compund: " + e2.getMessage());
                    }
                }
                tags.setTag("pages", (NBTBase)new_);
                tags.removeTag("author");
                tags.removeTag("title");
            }
        }
        else if (args.length >= 1 && args[0].equalsIgnoreCase("command")) {
            final NBTTagCompound click = new NBTTagCompound();
            click.setString("\"action\"", "run_command");
            click.setString("\"value\"", last_args);
            final NBTTagList nbttaglist = tags.getTagList("pages", 8);
            for (int j = 0; j < nbttaglist.tagCount(); ++j) {
                try {
                    final NBTTagCompound tag = JsonToNBT.getTagFromJson(nbttaglist.getStringTagAt(j));
                    tag.setTag("\"clickEvent\"", (NBTBase)click);
                    System.out.println(tag);
                    nbttaglist.set(j, (NBTBase)new NBTTagString(tag.toString()));
                }
                catch (NBTException e2) {
                    CmdBook.sc.chat.chat("Invalid Tag Compund: " + e2.getMessage());
                }
            }
        }
        else {
            CmdBook.sc.chat.chat(".book author", ".book author WiZARD");
            CmdBook.sc.chat.chat(".book title", ".book title 1337 h4ck3r");
            CmdBook.sc.chat.chat(".book generation", ".book generation");
            CmdBook.sc.chat.chat(".book copy", ".book copy 0");
            CmdBook.sc.chat.chat(".book set", ".book set 0");
            CmdBook.sc.chat.chat(".book add", ".book add");
            CmdBook.sc.chat.chat(".book command", ".book command /say hi");
            CmdBook.sc.chat.chat(".book type", ".book type");
        }
        stack.setTagCompound(tags);
        SkillWrapper.sendPacket((Packet)new C10PacketCreativeInventoryAction(36 + CmdBook.mc.thePlayer.inventory.currentItem, stack));
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        final List<String> cmds = new ArrayList<String>();
        if (args.length == 1) {
            String[] array;
            for (int length = (array = new String[] { "author", "title", "generation", "copy", "set", "add", "command", "type" }).length, j = 0; j < length; ++j) {
                final String s = array[j];
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    cmds.add(s);
                }
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("author")) {
                for (final NetworkPlayerInfo p : SkillWrapper.getPlayerInfoMap()) {
                    if (p.getGameProfile().getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                        cmds.add(p.getGameProfile().getName());
                    }
                }
            }
            if (args[0].equalsIgnoreCase("generation")) {
                for (int i = 0; i < 4; ++i) {
                    cmds.add(new StringBuilder().append(i).toString());
                }
            }
            if (args[0].equalsIgnoreCase("type")) {
                cmds.add("confirm");
            }
        }
        return cmds;
    }
}
