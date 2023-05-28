package com.skillclient.modules.combat;

import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class Range extends Module
{
    ValueNumber range;
    
    public Range() {
        super("Range", Register.Category.COMBAT, "Increases Range");
        this.range = new ValueNumber("Range", (Module)this, 6.0, 4.0, 5.0, 2);
    }
    
    public static float getRange() {
        return (float)(((Range)Register.getModule((Class)Range.class)).isActive() ? ((Range)Register.getModule((Class)Range.class)).range.getValue() : ((double)(Range.mc.playerController.getCurrentGameType().isCreative() ? 5.0f : 4.5f)));
    }
}
