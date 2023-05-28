package com.skillclient.modules.util;

import com.skillclient.modules.world.ScaffoldWalk;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class NoRotation extends Module
{
    public NoRotation() {
        super("NoRotation", Register.Category.UTIL, "Removes Player-Rotation-Packets. Can be always usefull");
    }
    
    public boolean isActive() {
        return super.isActive() || ((ScaffoldWalk)Register.getModule((Class)ScaffoldWalk.class)).isActive();
    }
}
