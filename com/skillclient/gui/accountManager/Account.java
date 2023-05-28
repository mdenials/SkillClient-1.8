package com.skillclient.gui.accountManager;

import net.minecraft.util.Session;
import com.skillclient.utils.FakePlayer.EntityFakePlayer;

public class Account
{
    public final AccountManager.AccountType type;
    public String username;
    public String uuid;
    public String token;
    public String line1;
    public String line2;
    protected EntityFakePlayer player;
    
    public EntityFakePlayer getPlayer() {
        if (this.player == null) {
            try {
                this.player = new EntityFakePlayer(this.username);
            }
            catch (Exception ex) {}
        }
        return this.player;
    }
    
    public Session getSession() {
        return new Session(this.username, this.uuid, this.token, "legacy");
    }
    
    public Account(final AccountManager.AccountType type) {
        this.token = "-";
        this.line1 = "";
        this.line2 = "";
        this.type = type;
    }
    
    public void relog() {
    }
    
    public String save() {
        return String.valueOf(this.type.toString()) + ";" + this.username + ";" + this.uuid + ";" + this.token + ";" + this.line1 + ";" + this.line2;
    }
    
    public static Account load(final String[] args) {
        final Account acc = base(args);
        acc.username = args[1];
        acc.uuid = args[2];
        acc.token = args[3];
        acc.line1 = args[4];
        acc.line2 = args[5];
        return acc;
    }
    
    public static Account base(final String[] s) {
        final AccountManager.AccountType type = AccountManager.AccountType.valueOf(s[0]);
        if (type.equals((Object)AccountManager.AccountType.CRACKED)) {
            return (Account)new Account.AccountCracked(AccountManager.AccountType.CRACKED);
        }
        if (type.equals((Object)AccountManager.AccountType.MCLeaks)) {
            final Account.AccountMCLeaks acc = new Account.AccountMCLeaks(AccountManager.AccountType.MCLeaks);
            acc.session_mcleaks = s[s.length - 1];
            acc.token_mcleaks = s[s.length - 2];
            return (Account)acc;
        }
        if (type.equals((Object)AccountManager.AccountType.MOJANG)) {
            final Account.AccountMojang acc2 = new Account.AccountMojang(type);
            acc2.email = s[s.length - 1];
            acc2.password = s[s.length - 2];
            return (Account)acc2;
        }
        return new Account(type);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        final Account other = (Account)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$type = this.type;
        final Object other$type = other.type;
        Label_0065: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0065;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$username = this.username;
        final Object other$username = other.username;
        Label_0102: {
            if (this$username == null) {
                if (other$username == null) {
                    break Label_0102;
                }
            }
            else if (this$username.equals(other$username)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$uuid = this.uuid;
        final Object other$uuid = other.uuid;
        Label_0139: {
            if (this$uuid == null) {
                if (other$uuid == null) {
                    break Label_0139;
                }
            }
            else if (this$uuid.equals(other$uuid)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$token = this.token;
        final Object other$token = other.token;
        Label_0176: {
            if (this$token == null) {
                if (other$token == null) {
                    break Label_0176;
                }
            }
            else if (this$token.equals(other$token)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$line1 = this.line1;
        final Object other$line1 = other.line1;
        Label_0213: {
            if (this$line1 == null) {
                if (other$line1 == null) {
                    break Label_0213;
                }
            }
            else if (this$line1.equals(other$line1)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$line2 = this.line2;
        final Object other$line2 = other.line2;
        Label_0250: {
            if (this$line2 == null) {
                if (other$line2 == null) {
                    break Label_0250;
                }
            }
            else if (this$line2.equals(other$line2)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$player = this.player;
        final Object other$player = other.player;
        if (this$player == null) {
            if (other$player == null) {
                return true;
            }
        }
        else if (this$player.equals(other$player)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Account;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $type = this.type;
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $username = this.username;
        result = result * 59 + (($username == null) ? 43 : $username.hashCode());
        final Object $uuid = this.uuid;
        result = result * 59 + (($uuid == null) ? 43 : $uuid.hashCode());
        final Object $token = this.token;
        result = result * 59 + (($token == null) ? 43 : $token.hashCode());
        final Object $line1 = this.line1;
        result = result * 59 + (($line1 == null) ? 43 : $line1.hashCode());
        final Object $line2 = this.line2;
        result = result * 59 + (($line2 == null) ? 43 : $line2.hashCode());
        final Object $player = this.player;
        result = result * 59 + (($player == null) ? 43 : $player.hashCode());
        return result;
    }
}

