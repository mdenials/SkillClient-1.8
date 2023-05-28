package com.skillclient.gui.accountManager;

import java.io.IOException;
import com.skillclient.utils.ClientSettings;
import com.skillclient.gui.utils.SkillGuiCheckBox;
import com.skillclient.gui.utils.GuiButton;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiScreen;

public class GuiAddAccount extends GuiScreen
{
    private GuiScreen prevGui;
    private GuiTextField textField1;
    private GuiTextField textField2;
    private AccountManager.AccountType type;
    private String displayString;
    static boolean offlineUUID;
    
    static {
        GuiAddAccount.offlineUUID = true;
    }
    
    public GuiAddAccount(final GuiScreen gui, final AccountManager.AccountType type) {
        this.displayString = "";
        this.prevGui = gui;
        this.type = type;
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 108, "Add"));
        (this.textField1 = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20)).setFocused(true);
        this.textField2 = new GuiTextField(3, this.fontRendererObj, this.width / 2 - 100, 85, 200, 20);
        if (this.type.equals(AccountManager.AccountType.MOJANG)) {
            this.buttonList.add(new GuiButton(2, this.width / 2 - 100, 110, "Paste"));
        }
        if (this.type.equals(AccountManager.AccountType.CRACKED)) {
            this.buttonList.add(new SkillGuiCheckBox(2, this.width / 2 - 100, 110, "offline-UUID", GuiAddAccount.offlineUUID));
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(GuiAddAccount.mc.fontRendererObj, "Add Account: " + this.type.getName(), this.width / 2, 24, 16777215);
        this.drawCenteredString(GuiAddAccount.mc.fontRendererObj, this.displayString, this.width / 2, 34, 16777215);
        this.textField1.drawTextBox();
        if (this.type.equals(AccountManager.AccountType.MOJANG)) {
            this.textField2.drawTextBox();
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.id == 2) {
            if (this.type.equals(AccountManager.AccountType.MOJANG)) {
                try {
                    final String clip = getClipboardString();
                    this.textField1.setText(clip.split(":")[0]);
                    this.textField2.setText(clip.split(":")[1]);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    this.displayString = e.getMessage();
                }
            }
            else if (this.type.equals(AccountManager.AccountType.CRACKED)) {
                GuiAddAccount.offlineUUID = !GuiAddAccount.offlineUUID;
            }
        }
        else {
            Label_0292: {
                try {
                    this.displayString = "Logging in...";
                    switch (this.type) {
                        case CRACKED: {
                            AccountManager.accounts.add(new Account.AccountCracked(this.textField1.getText(), GuiAddAccount.offlineUUID));
                            break;
                        }
                        case MOJANG: {
                            AccountManager.accounts.add(new Account.AccountMojang(this.textField1.getText(), this.textField2.getText()));
                            break;
                        }
                        case MCLeaks: {
                            AccountManager.accounts.add(new Account.AccountMCLeaks(this.textField1.getText()));
                            break;
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    this.displayString = e.getMessage();
                    break Label_0292;
                }
                finally {
                    GuiAddAccount.mc.displayGuiScreen(this.prevGui);
                }
                GuiAddAccount.mc.displayGuiScreen(this.prevGui);
            }
            AccountManager.save();
        }
        super.actionPerformed(button);
        ClientSettings.saveOptions();
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode == 1) {
            GuiAddAccount.mc.displayGuiScreen(this.prevGui);
            return;
        }
        if (this.textField1.isFocused()) {
            this.textField1.textboxKeyTyped(typedChar, keyCode);
        }
        if (this.textField2.isFocused()) {
            this.textField2.textboxKeyTyped(typedChar, keyCode);
        }
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.textField1.mouseClicked(mouseX, mouseY, mouseButton);
        this.textField2.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        super.onGuiClosed();
    }
}

