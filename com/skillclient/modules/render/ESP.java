package com.skillclient.modules.render;

import com.skillclient.events.api.EventTarget;
import java.util.Iterator;
import com.skillclient.utils.RenderUtils;
import net.minecraft.entity.EntityLivingBase;
import com.skillclient.events.EventRender;
import net.minecraft.entity.Entity;
import com.skillclient.modules.combat.TargetUtil;
import com.skillclient.misc.ValueModule;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class ESP extends Module
{
    ValueBoolean outline;
    
    public ESP() {
        super("ESP", Register.Category.RENDER, "Highlights Entitys");
        new ValueModule("Targets", (Module)this, (Class)TargetUtil.class);
        this.outline = new ValueBoolean("Outline", (Module)this, true);
    }
    
    public boolean render(final Entity e) {
        return this.isActive() && (boolean)this.outline.getValue() && ((TargetUtil)Register.getModule((Class)TargetUtil.class)).isValidType(e);
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        for (final Entity entity : ESP.mc.theWorld.loadedEntityList) {
            if (((TargetUtil)Register.getModule((Class)TargetUtil.class)).isValidType(entity) && (!(boolean)this.outline.getValue() || !(entity instanceof EntityLivingBase))) {
                RenderUtils.entityESPBox(entity, 0.0f, 1.0f, 1.0f, 0.2f);
            }
        }
    }
    
    public void onEnable() {
        if (ESP.mc.gameSettings.ofFastRender) {
            ESP.sc.chat.chat("Please disable FastRender");
        }
        else {
            super.onEnable();
        }
    }
}

