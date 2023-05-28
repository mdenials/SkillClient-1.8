package com.skillclient.modules.player;

import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class BackSprint extends Module
{
    public BackSprint() {
        super("BackSprint", Register.Category.PLAYER, "Sprints also Backwards");
    }
    
    public boolean isActive() {
        return super.isActive() && BackSprint.mc.gameSettings.keyBindSprint.isKeyDown();
    }
}

