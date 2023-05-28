package com.skillclient.modules.render;

import com.skillclient.misc.Module;
import com.skillclient.misc.ValueBoolean;

class CaveFinder$2 extends ValueBoolean {
    public void setValue(final Boolean value) {
        super.setValue((Object)value);
        if (this.isActive()) {
            CaveFinder$2.mc.renderGlobal.loadRenderers();
        }
    }
}
