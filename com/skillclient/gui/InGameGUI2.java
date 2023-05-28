package com.skillclient.gui;

import net.minecraft.client.Minecraft;
import com.skillclient.misc.Module;
import java.util.Comparator;

class InGameGUI$2 implements Comparator<Module> {
    @Override
    public int compare(final Module o1, final Module o2) {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth(o2.getDisplayName()) - Minecraft.getMinecraft().fontRendererObj.getStringWidth(o1.getDisplayName());
    }
}
