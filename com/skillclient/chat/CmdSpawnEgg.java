package com.skillclient.chat;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import com.skillclient.wrapper.SkillWrapper;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;

public class CmdSpawnEgg extends Cmd
{
    public CmdSpawnEgg() {
        super("spawnegg", "Set spawn/motion for Spawneggs/Armor Stands");
    }
    
    @Override
    public void action(final String[] args) {
        if ((args.length != 4 && args.length != 1) || (!args[0].equalsIgnoreCase("spawn") && !args[0].equalsIgnoreCase("motion"))) {
            CmdSpawnEgg.sc.chat.chat(".spawnegg <position/motion> [x y z]");
            return;
        }
        if (CmdSpawnEgg.mc.playerController.isNotCreative()) {
            CmdSpawnEgg.sc.chat.chat("Creative only!");
        }
        final ItemStack s = CmdSpawnEgg.mc.thePlayer.getHeldItem();
        double[] values_array = null;
        Label_0178: {
            if (args.length == 1) {
                try {
                    values_array = new double[] { CmdSpawnEgg.mc.thePlayer.posX, CmdSpawnEgg.mc.thePlayer.posY, CmdSpawnEgg.mc.thePlayer.posZ };
                    break Label_0178;
                }
                catch (NumberFormatException e) {
                    e.printStackTrace();
                    return;
                }
            }
            try {
                values_array = new double[] { Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]) };
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }
        }
        final NBTTagCompound base = s.hasTagCompound() ? s.getTagCompound() : new NBTTagCompound();
        final NBTTagCompound entityTag = base.hasKey("EntityTag") ? base.getCompoundTag("EntityTag") : new NBTTagCompound();
        final NBTTagList pos = new NBTTagList();
        pos.appendTag((NBTBase)new NBTTagDouble(values_array[0]));
        pos.appendTag((NBTBase)new NBTTagDouble(values_array[1]));
        pos.appendTag((NBTBase)new NBTTagDouble(values_array[2]));
        entityTag.setTag(args[0].equalsIgnoreCase("position") ? "Pos" : "Motion", (NBTBase)pos);
        base.setTag("EntityTag", (NBTBase)entityTag);
        s.setTagCompound(base);
        SkillWrapper.sendPacket((Packet)new C10PacketCreativeInventoryAction(36 + CmdSpawnEgg.mc.thePlayer.inventory.currentItem, s));
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        if (args.length > 4) {
            return new ArrayList<String>();
        }
        if (args.length == 1) {
            return Arrays.asList("position", "motion");
        }
        final int[] list = { CmdSpawnEgg.mc.objectMouseOver.getBlockPos().getX(), CmdSpawnEgg.mc.objectMouseOver.getBlockPos().getY(), CmdSpawnEgg.mc.objectMouseOver.getBlockPos().getZ() };
        System.out.println(args.length);
        return Arrays.asList(new StringBuilder().append(list[args.length - 2]).toString());
    }
}

