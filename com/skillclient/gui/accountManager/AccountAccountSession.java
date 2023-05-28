package com.skillclient.gui.accountManager;

import net.minecraft.util.Session;

public static class AccountSession extends Account
{
    public AccountSession(final AccountManager.AccountType type) {
        super(type);
    }
    
    public AccountSession(final Session session) {
        super(AccountManager.AccountType.SESSION);
        this.username = session.getUsername();
        this.uuid = session.getPlayerID();
        this.token = session.getToken();
        this.line1 = String.valueOf(this.type.toString()) + ": " + this.username;
        this.line2 = this.getSession().getPlayerID().replace("-", "");
        AccountManager.orig = this;
    }
    
    @Override
    public String save() {
        return super.save();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountSession)) {
            return false;
        }
        final AccountSession other = (AccountSession)o;
        return other.canEqual(this) && super.equals(o);
    }
    
    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof AccountSession;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + super.hashCode();
        return result;
    }
}
