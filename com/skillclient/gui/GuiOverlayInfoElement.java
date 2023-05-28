package com.skillclient.gui;

import com.skillclient.utils.TimerUtil;
import java.awt.Color;
import net.minecraft.client.gui.Gui;

private static class InfoElement extends Gui
{
    Color color;
    String info;
    TimerUtil timer;
    TimerUtil timer2;
    int offsetY;
    int text_width;
    
    public InfoElement(final String info, final Color color) {
        this.timer = new TimerUtil();
        this.timer2 = new TimerUtil();
        this.offsetY = 0;
        this.color = color;
        this.info = info;
        this.timer.setLastMS();
        this.text_width = InfoElement.mc.fontRendererObj.getStringWidth(info);
    }
    
    public void render(final float partialTicks, final int width, final int height, final int pos) {
        int offsetX = 0;
        if (!this.timer.isDelayComplete(this.text_width * 10)) {
            offsetX = this.text_width - this.timer.pastTime() / 10;
        }
        else if (this.timer.isDelayComplete(10000 - this.text_width * 10)) {
            offsetX = this.text_width - (10000 - this.timer.pastTime()) / 10;
        }
        this.offsetY = Math.max(pos * 30, this.offsetY - (int)(this.timer2.pastTime() / 3.0f));
        this.timer2.setLastMS();
        drawRect(width - this.text_width - 25 + offsetX, height - 30 - this.offsetY, width - 5 + offsetX, height - 10 - this.offsetY, this.color.brighter().getRGB());
        drawRect(width - 15 + offsetX, height - 30 - this.offsetY, width - 5 + offsetX, height - 10 - this.offsetY, this.color.darker().getRGB());
        this.drawString(InfoElement.mc.fontRendererObj, this.info, width - this.text_width - 20 + offsetX, height - 24 - this.offsetY, -1);
    }
}
