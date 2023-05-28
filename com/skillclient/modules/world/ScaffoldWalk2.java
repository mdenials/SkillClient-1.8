package com.skillclient.modules.world;

import com.skillclient.misc.Module;
import com.skillclient.misc.ValueNumber;

class ScaffoldWalk$2 extends ValueNumber {
    public String getDisplayname() {
        return ((double)this.getValue() == 0.0) ? (String.valueOf(this.name) + ": off") : super.getDisplayname();
    }
}

Select a decompiler
Procyon - fast decompiler for modern Java
CFR - very good and well-supported decompiler for modern Java
JDCore (very fast)
Jadx, fast and with Android support
Fernflower
JAD (very fast, but outdated)

