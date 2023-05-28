package com.skillclient.gui.accountManager;

import java.io.IOException;
import com.skillclient.utils.ClientSettings;
import com.skillclient.gui.utils.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiAccountManager extends GuiScreen
{
    private GuiScreen prevGui;
    private GuiAccountManager.GuiList list;
    private String displayText;
    
    public GuiAccountManager(final GuiScreen gui) {
        this.displayText = "";
        this.prevGui = gui;
    }
    
    public void initGui() {
        (this.list = new GuiAccountManager.GuiList(this, (GuiScreen)this)).registerScrollButtons(7, 8);
        this.list.elementClicked(-1, false, 0, 0);
        final int j = 5;
        this.buttonList.add(new GuiButton(1, 10, this.height - 25, 120, 20, "Add " + AccountManager.AccountType.CRACKED.getName()));
        this.buttonList.add(new GuiButton(2, 10, this.height - 50, 120, 20, "Add " + AccountManager.AccountType.MOJANG.getName()));
        this.buttonList.add(new GuiButton(3, 140, this.height - 25, 120, 20, "Add " + AccountManager.AccountType.MCLeaks.getName()));
        this.buttonList.add(new GuiButton(4, 140, this.height - 50, 120, 20, "Relog"));
        this.buttonList.add(new GuiButton(5, this.width - 110, this.height - 50, 90, 20, "Remove"));
        this.buttonList.add(new GuiButton(6, this.width - 110, this.height - 25, 90, 20, "Return"));
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.list.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(GuiAccountManager.mc.fontRendererObj, "Accounts", this.width / 2, 15, 16777215);
        this.drawCenteredString(GuiAccountManager.mc.fontRendererObj, "§c" + this.displayText, this.width / 2, 27, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        Account current = null;
        if (this.list.getSelectedSlot() != -1 && AccountManager.accounts.size() > this.list.getSelectedSlot()) {
            current = AccountManager.accounts.get(this.list.getSelectedSlot());
        }
        switch (button.id) {
            case 1: {
                GuiAccountManager.mc.displayGuiScreen((GuiScreen)new GuiAddAccount((GuiScreen)this, AccountManager.AccountType.CRACKED));
                break;
            }
            case 2: {
                GuiAccountManager.mc.displayGuiScreen((GuiScreen)new GuiAddAccount((GuiScreen)this, AccountManager.AccountType.MOJANG));
                break;
            }
            case 3: {
                GuiAccountManager.mc.displayGuiScreen((GuiScreen)new GuiAddAccount((GuiScreen)this, AccountManager.AccountType.MCLeaks));
                break;
            }
            case 4: {
                if (current == null) {
                    this.displayText = "No Account selected";
                    break;
                }
                current.relog();
                break;
            }
            case 5: {
                if (current == null) {
                    this.displayText = "No Account selected";
                    break;
                }
                if (current.type.equals(AccountManager.AccountType.SESSION)) {
                    this.displayText = "Account is locked";
                    break;
                }
                if (current.equals(AccountManager.account)) {
                    this.displayText = "Account is currently used";
                    break;
                }
                AccountManager.accounts.remove(current);
                ClientSettings.saveOptions();
                this.displayText = "Removed Account";
                break;
            }
            case 6: {
                GuiAccountManager.mc.displayGuiScreen(this.prevGui);
                break;
            }
        }
        AccountManager.save();
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode == 1) {
            GuiAccountManager.mc.displayGuiScreen(this.prevGui);
            if (GuiAccountManager.mc.currentScreen == null) {
                GuiAccountManager.mc.setIngameFocus();
            }
        }
    }
    
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }
}
