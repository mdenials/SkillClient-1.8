package com.skillclient.gui;

import java.awt.Color;
import java.util.Collection;
import com.skillclient.main.Register;
import com.skillclient.modules.misc.HUD;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;

public class GuiOverlay
{
    public static final Minecraft mc;
    public static final int TOTAL_DISPLAY_TIME = 10000;
    public static ArrayList<GuiOverlay.InfoElement> elements;
    
    static {
        mc = Minecraft.getMinecraft();
        GuiOverlay.elements = new ArrayList<GuiOverlay.InfoElement>();
    }
    
    public static void render(final float partialTicks, final int width, final int height) {
        if (!Register.isActive((Class)HUD.class)) {
            return;
        }
        final ArrayList<GuiOverlay.InfoElement> remove = new ArrayList<GuiOverlay.InfoElement>();
        for (int i = 0; i < GuiOverlay.elements.size(); ++i) {
            final GuiOverlay.InfoElement e = GuiOverlay.elements.get(i);
            e.render(partialTicks, width, height, i);
            if (e.timer.isDelayComplete(10000L)) {
                remove.add(e);
            }
        }
        GuiOverlay.elements.removeAll(remove);
    }
    
    public static void notify(final String info, final Color color) {
        GuiOverlay.elements.add(new GuiOverlay.InfoElement(info, color));
    }
    
    public static void notify(final String info) {
        notify(info, Color.GRAY);
    }
}

