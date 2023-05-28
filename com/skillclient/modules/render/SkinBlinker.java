package com.skillclient.modules.render;

import com.skillclient.events.api.EventTarget;
import java.util.Set;
import net.minecraft.entity.player.EnumPlayerModelParts;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.utils.TimerUtil;
import com.skillclient.misc.Module;

public class SkinBlinker extends Module
{
    private TimerUtil time;
    
    public SkinBlinker() {
        super("SkinBlinker", Register.Category.RENDER, "Toggels PlayerLayers");
        this.time = new TimerUtil();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (!this.time.isDelayComplete(100L)) {
            return;
        }
        final Set activeParts = SkinBlinker.mc.gameSettings.getModelParts();
        EnumPlayerModelParts[] values;
        for (int length = (values = EnumPlayerModelParts.values()).length, i = 0; i < length; ++i) {
            final EnumPlayerModelParts part = values[i];
            SkinBlinker.mc.gameSettings.setModelPartEnabled(part, !activeParts.contains(part));
        }
        this.time.setLastMS();
    }
    
    public void onDisable() {
        EnumPlayerModelParts[] values;
        for (int length = (values = EnumPlayerModelParts.values()).length, i = 0; i < length; ++i) {
            final EnumPlayerModelParts part = values[i];
            SkinBlinker.mc.gameSettings.setModelPartEnabled(part, true);
        }
        super.onDisable();
    }
}
