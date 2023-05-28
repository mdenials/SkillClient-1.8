package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.tileentity.TileEntityChest;
import java.util.Iterator;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

public class CmdInventory extends Cmd
{
    public CmdInventory() {
        super("inv", "Opens inventory of your held item or another Player");
    }
    
    @Override
    public void action(final String[] args) {
        if (args.length == 1) {
            for (final Entity e : CmdInventory.mc.theWorld.loadedEntityList) {
                if (e instanceof EntityPlayer && e.getName().equalsIgnoreCase(args[0])) {
                    final InventoryBasic inv = new InventoryBasic(e.getName(), true, 9);
                    final ItemStack skull = new ItemStack(Items.skull, 1, 3);
                    skull.setTagInfo("SkullOwner", (NBTBase)new NBTTagString(e.getName()));
                    skull.setStackDisplayName(e.getName());
                    CmdInventory.mc.displayGuiScreen((GuiScreen)new CmdInventory.CmdInventory$1(this, (IInventory)CmdInventory.mc.thePlayer.inventory, (IInventory)inv, inv, skull, e));
                }
            }
        }
        else {
            final ItemStack s = CmdInventory.mc.thePlayer.getHeldItem();
            if (s.hasTagCompound() && s.getTagCompound().hasKey("BlockEntityTag")) {
                final TileEntityChest tile = (TileEntityChest)new CmdInventory.CmdInventory$2(this);
                tile.readFromNBT(s.getTagCompound().getCompoundTag("BlockEntityTag"));
                tile.setCustomName(s.getDisplayName());
                CmdInventory.mc.displayGuiScreen((GuiScreen)new CmdInventory.CmdInventory$3(this, (IInventory)CmdInventory.mc.thePlayer.inventory, (IInventory)tile));
            }
        }
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        final List<String> cmds = new ArrayList<String>();
        if (args.length == 1) {
            for (final Entity e : CmdInventory.mc.theWorld.loadedEntityList) {
                if (e instanceof EntityPlayer && e.getName().toLowerCase().startsWith(args[0].toLowerCase())) {
                    cmds.add(e.getName());
                }
            }
        }
        return cmds;
    }
}

