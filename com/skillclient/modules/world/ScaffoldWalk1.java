package com.skillclient.modules.world;

import com.skillclient.misc.Module;
import com.skillclient.misc.ValueNumber;

class ScaffoldWalk$1 extends ValueNumber {
    public String getDisplayname() {
        return ((double)this.getValue() == 0.0) ? (String.valueOf(this.name) + ": off") : super.getDisplayname();
    }
}
