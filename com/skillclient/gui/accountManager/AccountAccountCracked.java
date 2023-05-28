package com.skillclient.gui.accountManager;

import com.skillclient.gui.GuiOverlay;
import java.awt.Color;
import com.skillclient.utils.FakePlayer.EntityFakePlayer;
import com.skillclient.utils.exceptions.InvalidPlayerException;
import java.util.Scanner;
import java.net.URL;
import java.util.UUID;

public static class AccountCracked extends Account
{
    public AccountCracked(final AccountManager.AccountType type) {
        super(type);
    }
    
    public AccountCracked(final String username, final boolean offlinePlayer) throws InvalidPlayerException {
        super(AccountManager.AccountType.CRACKED);
        if (offlinePlayer) {
            this.uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes()).toString().replace("-", "");
        }
        else {
            this.uuid = "";
            try {
                final URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + username);
                final Scanner scanner = new Scanner(url.openStream());
                final String line = scanner.nextLine();
                this.uuid = line.split("\"")[3];
                scanner.close();
            }
            catch (Exception e) {
                throw new InvalidPlayerException();
            }
        }
        this.username = username;
        try {
            this.player = new EntityFakePlayer(this.username);
        }
        catch (Exception ex) {}
        this.line1 = String.valueOf(this.type.toString()) + ": " + username;
        this.line2 = this.getSession().getPlayerID().replace("-", "");
        GuiOverlay.notify("Added Account " + username, Color.ORANGE);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountCracked)) {
            return false;
        }
        final AccountCracked other = (AccountCracked)o;
        return other.canEqual(this) && super.equals(o);
    }
    
    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof AccountCracked;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + super.hashCode();
        return result;
    }
}
