package com.skillclient.modules.player;

import com.skillclient.modules.movement.Fly;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class NoSlowdown extends Module
{
    public ValueNumber cobweb;
    public ValueNumber sneak;
    ValueNumber item;
    
    public NoSlowdown() {
        super("NoSlowdown", Register.Category.MOVEMENT, "Disables Speed-Reduction");
        this.cobweb = new ValueNumber("Cobweb", (Module)this, 100.0, 0.0, 100.0, 1);
        this.sneak = new ValueNumber("Sneak", (Module)this, 100.0, 30.0, 100.0, 1);
        this.item = new ValueNumber("Item", (Module)this, 100.0, 0.2, 100.0, 1);
    }
    
    public boolean isActive() {
        return super.isToggled();
    }
    
    public static float getItemSpeed() {
        if (((Fly)Register.getModule((Class)Fly.class)).isActive()) {
            return 1.0f;
        }
        if (((NoSlowdown)Register.getModule((Class)NoSlowdown.class)).isActive()) {
            return (float)((double)((NoSlowdown)Register.getModule((Class)NoSlowdown.class)).item.getValue() / 100.0);
        }
        return 0.2f;
    }
}
