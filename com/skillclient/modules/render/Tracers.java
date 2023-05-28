package com.skillclient.modules.render;

import com.skillclient.events.api.EventTarget;
import java.util.Iterator;
import com.skillclient.utils.RenderUtils;
import com.skillclient.modules.combat.TargetUtil;
import net.minecraft.entity.Entity;
import com.skillclient.events.EventRender;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Tracers extends Module
{
    ValueNumber distance;
    
    public Tracers() {
        super("Tracers", Register.Category.RENDER, "Draws a line to the next Entitys");
        this.distance = new ValueNumber("Distance", (Module)this, 200.0, 10.0, 100.0, 1);
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        for (final Entity entity : Tracers.mc.theWorld.loadedEntityList) {
            if (((TargetUtil)Register.getModule((Class)TargetUtil.class)).isValidType(entity)) {
                final double d1 = entity.getDistanceToEntity((Entity)Tracers.mc.thePlayer) / this.distance.getInt();
                RenderUtils.tracerLine(entity, 1.0 - d1, 0.5, 0.5, 0.5);
            }
        }
    }
}

