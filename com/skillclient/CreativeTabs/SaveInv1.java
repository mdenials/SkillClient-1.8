package com.skillclient.CreativeTabs;

import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.client.gui.inventory.GuiChest;

class SaveInv$1 extends GuiChest {
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode == 1 || keyCode == SaveInv$1.mc.gameSettings.keyBindInventory.getKeyCode()) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiInventory((EntityPlayer)Minecraft.getMinecraft().thePlayer));
        }
        else {
            super.keyTyped(typedChar, keyCode);
        }
    }
}
