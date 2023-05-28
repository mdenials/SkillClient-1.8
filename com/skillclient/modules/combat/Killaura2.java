package com.skillclient.modules.combat;

import com.skillclient.misc.Module;
import com.skillclient.misc.ValueNumber;

class KillAura$2 extends ValueNumber {
    public String getDisplayname() {
        return ((double)this.getValue() == 0.0) ? (String.valueOf(this.name) + ": off") : super.getDisplayname();
    }
}
