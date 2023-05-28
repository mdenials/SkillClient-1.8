package com.skillclient.modules.auto;

import com.skillclient.events.api.EventTarget;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class AutoPlace extends Module
{
    public AutoPlace() {
        super("AutoPlace", Register.Category.AUTO, "Automatically places Blocks");
    }
    
    public void onDisable() {
        AutoPlace.mc.gameSettings.keyBindUseItem.pressed = false;
        super.onDisable();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        AutoPlace.mc.gameSettings.keyBindUseItem.pressed = true;
    }
}
