package com.skillclient.gui.accountManager;

import com.skillclient.gui.accountManager.mcleaks.RedeemResponse;
import com.skillclient.gui.GuiOverlay;
import java.awt.Color;
import net.minecraft.client.gui.GuiScreen;
import com.skillclient.chat.Chat;
import com.skillclient.gui.accountManager.mcleaks.ModApi;
import com.skillclient.utils.FakePlayer.EntityFakePlayer;
import java.util.UUID;
import com.skillclient.utils.exceptions.InvalidTokenException;
import com.skillclient.gui.accountManager.mcleaks.ChatColor;

public static class AccountMCLeaks extends Account
{
    public String session_mcleaks;
    public String token_mcleaks;
    
    public AccountMCLeaks(final AccountManager.AccountType type) {
        super(type);
    }
    
    public AccountMCLeaks(final String token) throws InvalidTokenException {
        super(AccountManager.AccountType.MCLeaks);
        if (token.length() != 16) {
            throw new InvalidTokenException(ChatColor.RED + "The token has to be 16 characters long!");
        }
        this.token_mcleaks = token;
        this.username = "...";
        this.uuid = UUID.randomUUID().toString();
        try {
            this.player = new EntityFakePlayer("");
        }
        catch (Exception ex) {}
        ModApi.redeem(this.token_mcleaks, o -> {
            try {
                if (o instanceof String) {
                    this.line1 = (String)o;
                    throw new InvalidTokenException((String)o);
                }
                final RedeemResponse response = (RedeemResponse)o;
                this.session_mcleaks = response.getSession();
                this.username = response.getMcName();
                try {
                    this.player = new EntityFakePlayer(this.username);
                }
                catch (Exception ex) {}
                this.line1 = String.valueOf(this.type.toString()) + ": " + this.username;
                this.line2 = this.token_mcleaks;
                GuiOverlay.notify("Added Account " + this.username, Color.ORANGE);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public AccountMCLeaks(final String[] s) {
        super(AccountManager.AccountType.MCLeaks);
        this.username = s[0];
        try {
            this.player = new EntityFakePlayer(this.username);
        }
        catch (Exception ex) {}
        this.session_mcleaks = s[1];
        this.token_mcleaks = s[2];
        this.uuid = UUID.randomUUID().toString();
        this.line1 = String.valueOf(this.type.toString()) + ": " + this.username;
        this.line2 = this.token_mcleaks;
    }
    
    @Override
    public void relog() {
        Chat.openWebLink("https://mcleaks.net/renew");
        GuiScreen.setClipboardString("token");
        GuiOverlay.notify("Copied token to clipboard!", Color.BLUE);
    }
    
    @Override
    public String save() {
        return String.valueOf(super.save()) + ";" + this.session_mcleaks + ";" + this.token_mcleaks;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountMCLeaks)) {
            return false;
        }
        final AccountMCLeaks other = (AccountMCLeaks)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final Object this$session_mcleaks = this.session_mcleaks;
        final Object other$session_mcleaks = other.session_mcleaks;
        Label_0075: {
            if (this$session_mcleaks == null) {
                if (other$session_mcleaks == null) {
                    break Label_0075;
                }
            }
            else if (this$session_mcleaks.equals(other$session_mcleaks)) {
                break Label_0075;
            }
            return false;
        }
        final Object this$token_mcleaks = this.token_mcleaks;
        final Object other$token_mcleaks = other.token_mcleaks;
        if (this$token_mcleaks == null) {
            if (other$token_mcleaks == null) {
                return true;
            }
        }
        else if (this$token_mcleaks.equals(other$token_mcleaks)) {
            return true;
        }
        return false;
    }
    
    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof AccountMCLeaks;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + super.hashCode();
        final Object $session_mcleaks = this.session_mcleaks;
        result = result * 59 + (($session_mcleaks == null) ? 43 : $session_mcleaks.hashCode());
        final Object $token_mcleaks = this.token_mcleaks;
        result = result * 59 + (($token_mcleaks == null) ? 43 : $token_mcleaks.hashCode());
        return result;
    }
}

