package com.skillclient.gui;

import net.minecraft.client.Minecraft;
import java.util.Comparator;

class InGameGUI$1 implements Comparator<String> {
    @Override
    public int compare(final String o1, final String o2) {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth(o2) - Minecraft.getMinecraft().fontRendererObj.getStringWidth(o1);
    }
}
