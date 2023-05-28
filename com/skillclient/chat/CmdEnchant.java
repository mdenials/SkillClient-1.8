package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.enchantment.Enchantment;

public class CmdEnchant extends Cmd
{
    public CmdEnchant() {
        super("enchant", "Set enchantments for your held item in gm1");
    }
    
    @Override
    public void action(final String[] args) {
        if (CmdEnchant.mc.playerController.isNotCreative()) {
            CmdEnchant.sc.chat.chat("Creative only!");
        }
        final ItemStack s = CmdEnchant.mc.thePlayer.getHeldItem();
        Enchantment enchantment = null;
        if (args.length >= 1) {
            try {
                enchantment = Enchantment.getEnchantmentById(Integer.parseInt(args[0], 0));
            }
            catch (NumberFormatException var12) {
                enchantment = Enchantment.getEnchantmentByLocation(args[0]);
            }
        }
        final boolean all = args.length >= 1 && args[0].equalsIgnoreCase("all");
        int level = 32767;
        if (args.length == 2) {
            try {
                level = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e) {
                CmdEnchant.sc.chat.chat(e.getMessage());
            }
        }
        if (enchantment == null && !all) {
            CmdEnchant.sc.chat.chat("Invalid enchantment!");
            for (final ResourceLocation e2 : Enchantment.func_181077_c()) {
                CmdEnchant.sc.chat.chat(".enchant " + e2.getResourcePath(), ".enchant " + e2.getResourcePath() + " 1337");
            }
            CmdEnchant.sc.chat.chat(".enchant all", ".enchant all 1337");
        }
        else {
            final NBTTagCompound tags = s.hasTagCompound() ? s.getTagCompound() : new NBTTagCompound();
            if (!tags.hasKey("ench", 9)) {
                tags.setTag("ench", (NBTBase)new NBTTagList());
            }
            final NBTTagList nbttaglist = tags.getTagList("ench", 10);
            if (all) {
                Enchantment[] enchantmentsList;
                for (int length = (enchantmentsList = Enchantment.enchantmentsList).length, i = 0; i < length; ++i) {
                    final Enchantment e3 = enchantmentsList[i];
                    if (e3 != null) {
                        final NBTTagCompound nbttagcompound = new NBTTagCompound();
                        nbttagcompound.setInteger("id", e3.effectId);
                        nbttagcompound.setInteger("lvl", level);
                        nbttaglist.appendTag((NBTBase)nbttagcompound);
                    }
                }
            }
            else {
                final NBTTagCompound nbttagcompound2 = new NBTTagCompound();
                nbttagcompound2.setInteger("id", enchantment.effectId);
                nbttagcompound2.setInteger("lvl", level);
                nbttaglist.appendTag((NBTBase)nbttagcompound2);
            }
            s.setTagCompound(tags);
        }
        SkillWrapper.sendPacket((Packet)new C10PacketCreativeInventoryAction(36 + CmdEnchant.mc.thePlayer.inventory.currentItem, s));
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        final List<String> cmds = new ArrayList<String>();
        for (final ResourceLocation e : Enchantment.func_181077_c()) {
            if (!cmds.contains(e.getResourcePath()) && e.getResourcePath().toLowerCase().startsWith(args[0].toLowerCase())) {
                cmds.add(e.getResourcePath());
            }
        }
        return cmds;
    }
}


