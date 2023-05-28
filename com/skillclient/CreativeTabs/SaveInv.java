package com.skillclient.CreativeTabs;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.IInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.client.player.inventory.ContainerLocalMenu;

public class SaveInv extends ContainerLocalMenu
{
    static SaveInv instance;
    
    static {
        SaveInv.instance = new SaveInv();
    }
    
    public SaveInv() {
        super("", (IChatComponent)new ChatComponentText("SaveInv"), 54);
    }
    
    public static void openSaveInv() {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new SaveInv.SaveInv$1((IInventory)Minecraft.getMinecraft().thePlayer.inventory, (IInventory)SaveInv.instance));
    }
}

