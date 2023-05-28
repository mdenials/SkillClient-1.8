package com.skillclient.modules.world;

import com.skillclient.misc.Module;
import com.skillclient.misc.ValueBoolean;

class WorldInteraction$1 extends ValueBoolean {
    public String getDisplayname() {
        return this.getValue() ? "Solid Water" : "Ignore Blocks without BoundingBox";
    }
}
