package com.skillclient.modules.misc;

import com.skillclient.misc.Module;
import com.skillclient.misc.ValueBoolean;

class ClickGui$1 extends ValueBoolean {
    public String getDisplayname() {
        return this.getValue() ? "List" : "Click";
    }
}
