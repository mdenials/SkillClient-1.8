package com.skillclient.gui;

import java.util.Iterator;
import java.io.IOException;
import com.skillclient.gui.accountManager.GuiAccountManager;
import net.minecraft.client.resources.I18n;
import com.skillclient.gui.utils.GuiButton;
import com.skillclient.utils.TimerUtil;
import java.util.List;
import net.minecraft.util.IChatComponent;
import net.minecraft.client.gui.GuiScreen;

public class GuiDisconnected extends GuiScreen
{
    private String reason;
    private IChatComponent message;
    private List<String> multilineMessage;
    private final GuiScreen parentScreen;
    private int textHeight;
    static boolean autoreconnect;
    TimerUtil timer;
    GuiButton buttonautoreconnect;
    
    static {
        GuiDisconnected.autoreconnect = true;
    }
    
    public GuiDisconnected(final GuiScreen screen, final String reasonLocalizationKey, final IChatComponent chatComp) {
        this.timer = new TimerUtil();
        this.parentScreen = screen;
        this.reason = I18n.format(reasonLocalizationKey, new Object[0]);
        this.message = chatComp;
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.id == 0) {
            GuiDisconnected.mc.displayGuiScreen(this.parentScreen);
        }
        else if (button.id == 1) {
            this.reconnect();
        }
        else if (button.id == 2) {
            GuiDisconnected.autoreconnect = !GuiDisconnected.autoreconnect;
        }
        else if (button.id == 3) {
            GuiDisconnected.mc.displayGuiScreen((GuiScreen)new GuiAccountManager(this.parentScreen));
        }
        else if (button.id == 4 && this.parentScreen instanceof GuiMultiplayer && ((GuiMultiplayer)this.parentScreen).serverListSelector.func_148193_k() > 0) {
            ((GuiMultiplayer)this.parentScreen).savedServerList.servers.remove(((GuiMultiplayer)this.parentScreen).serverListSelector.func_148193_k());
            GuiDisconnected.mc.displayGuiScreen(this.parentScreen);
        }
    }
    
    void reconnect() {
        try {
            if (this.parentScreen instanceof GuiMultiplayer) {
                if (((GuiMultiplayer)this.parentScreen).selectedServer != null) {
                    ((GuiMultiplayer)this.parentScreen).connectToServer(((GuiMultiplayer)this.parentScreen).selectedServer);
                }
                else {
                    ((GuiMultiplayer)this.parentScreen).connectToSelected();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.reason, this.width / 2, this.height / 2 - this.textHeight / 2 - this.fontRendererObj.FONT_HEIGHT * 2, 11184810);
        int i = this.height / 2 - this.textHeight / 2;
        if (this.multilineMessage != null) {
            for (final String s : this.multilineMessage) {
                this.drawCenteredString(this.fontRendererObj, s, this.width / 2, i, 16777215);
                i += this.fontRendererObj.FONT_HEIGHT;
            }
        }
        this.buttonautoreconnect.displayString = "AutoReconnect" + (GuiDisconnected.autoreconnect ? (" " + (5 - this.timer.pastTime() / 1000)) : "");
        if (GuiDisconnected.autoreconnect && this.timer.isDelayComplete(5000L)) {
            this.reconnect();
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void initGui() {
        this.buttonList.clear();
        this.multilineMessage = (List<String>)this.fontRendererObj.listFormattedStringToWidth(this.message.getFormattedText(), this.width - 50);
        this.textHeight = this.multilineMessage.size() * this.fontRendererObj.FONT_HEIGHT;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 + this.textHeight / 2 + this.fontRendererObj.FONT_HEIGHT, I18n.format("gui.toMenu", new Object[0])));
        this.timer.setLastMS();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 2 + this.textHeight / 2 + this.fontRendererObj.FONT_HEIGHT + 24, "Reconnect"));
        this.buttonList.add(this.buttonautoreconnect = new GuiButton(2, this.width / 2 - 100, this.height / 2 + this.textHeight / 2 + this.fontRendererObj.FONT_HEIGHT + 48, "AutoReconnect"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 2 + this.textHeight / 2 + this.fontRendererObj.FONT_HEIGHT + 73, "Delete Server"));
        this.buttonList.add(new GuiButton(3, this.width - 110, this.height - 30, 100, 20, "Alt-Manager"));
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
    }
}
