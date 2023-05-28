package com.skillclient.modules.movement;

import com.skillclient.modules.world.ScaffoldWalk;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class SafeWalk extends Module
{
    public SafeWalk() {
        super("SafeWalk", Register.Category.MOVEMENT, "Like Sneak but cool");
    }
    
    public boolean isActive() {
        return super.isActive() || ((ScaffoldWalk)Register.getModule((Class)ScaffoldWalk.class)).isActive();
    }
}

