package com.skillclient.modules.world;

import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class WorldInteraction extends Module
{
    ValueBoolean mode;
    
    public WorldInteraction() {
        super("WorldInteraction", Register.Category.WORLD, "Makes water solid or Grass Unsolid");
        this.mode = (ValueBoolean)new WorldInteraction.WorldInteraction$1(this, "mode", (Module)this, true);
    }
    
    public static boolean v1() {
        return ((WorldInteraction)Register.getModule((Class)WorldInteraction.class)).isActive() && (boolean)((WorldInteraction)Register.getModule((Class)WorldInteraction.class)).mode.getValue();
    }
    
    public static boolean v2() {
        return ((WorldInteraction)Register.getModule((Class)WorldInteraction.class)).isActive() && !(boolean)((WorldInteraction)Register.getModule((Class)WorldInteraction.class)).mode.getValue();
    }
}

