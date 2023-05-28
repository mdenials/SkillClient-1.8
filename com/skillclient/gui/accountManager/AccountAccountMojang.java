package com.skillclient.gui.accountManager;

import com.mojang.authlib.exceptions.AuthenticationException;
import com.skillclient.gui.GuiOverlay;
import java.awt.Color;
import com.skillclient.utils.FakePlayer.EntityFakePlayer;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.util.UUID;
import java.net.Proxy;

public static class AccountMojang extends Account
{
    public String email;
    public String password;
    
    public AccountMojang(final AccountManager.AccountType type) {
        super(type);
    }
    
    public AccountMojang(final String email, final String password) throws AuthenticationException {
        super(AccountManager.AccountType.MOJANG);
        this.email = email;
        this.password = password;
        final YggdrasilUserAuthentication loginService = new YggdrasilUserAuthentication(new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString()), Agent.MINECRAFT);
        loginService.setUsername(email);
        loginService.setPassword(password);
        loginService.logIn();
        this.username = loginService.getSelectedProfile().getName();
        try {
            this.player = new EntityFakePlayer(this.username);
        }
        catch (Exception ex) {}
        this.uuid = loginService.getSelectedProfile().getId().toString();
        this.token = loginService.getAuthenticatedToken();
        this.line1 = String.valueOf(this.type.toString()) + ": " + this.username;
        this.line2 = String.valueOf(email) + ":" + password;
        GuiOverlay.notify("Added Account " + this.username, Color.ORANGE);
    }
    
    @Override
    public void relog() {
        try {
            final AccountMojang accountMojang = new AccountMojang(this.email, this.password);
        }
        catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String save() {
        return String.valueOf(super.save()) + ";" + this.email + ";" + this.password;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountMojang)) {
            return false;
        }
        final AccountMojang other = (AccountMojang)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final Object this$email = this.email;
        final Object other$email = other.email;
        Label_0075: {
            if (this$email == null) {
                if (other$email == null) {
                    break Label_0075;
                }
            }
            else if (this$email.equals(other$email)) {
                break Label_0075;
            }
            return false;
        }
        final Object this$password = this.password;
        final Object other$password = other.password;
        if (this$password == null) {
            if (other$password == null) {
                return true;
            }
        }
        else if (this$password.equals(other$password)) {
            return true;
        }
        return false;
    }
    
    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof AccountMojang;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + super.hashCode();
        final Object $email = this.email;
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        final Object $password = this.password;
        result = result * 59 + (($password == null) ? 43 : $password.hashCode());
        return result;
    }
}

