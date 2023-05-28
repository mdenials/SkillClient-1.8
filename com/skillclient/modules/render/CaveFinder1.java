package com.skillclient.modules.render;

import com.skillclient.misc.Module;
import com.skillclient.misc.ValueNumber;

class CaveFinder$1 extends ValueNumber {
    public void setValue(final Double value) {
        super.setValue((Object)value);
        if (this.isActive()) {
            CaveFinder$1.mc.renderGlobal.loadRenderers();
        }
    }
}
