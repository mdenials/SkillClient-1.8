package com.skillclient.gui;

import java.io.IOException;
import org.lwjgl.input.Mouse;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class GuiChangeLog extends GuiScreen
{
    public static ArrayList<String> changelog;
    int pos;
    
    static {
        GuiChangeLog.changelog = new ArrayList<String>();
    }
    
    public GuiChangeLog() {
        GuiChangeLog.changelog.add("Couldn't load Changelog!");
        this.pos = 0;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        for (int i = 0; i < GuiChangeLog.changelog.size(); ++i) {
            this.drawString(GuiChangeLog.mc.fontRendererObj, (String)GuiChangeLog.changelog.get(i), 20, 10 + this.pos / 20 + i * 10, -1);
        }
    }
    
    public void handleMouseInput() throws IOException {
        this.pos = Math.min(this.pos + Mouse.getDWheel(), 0);
    }
}


