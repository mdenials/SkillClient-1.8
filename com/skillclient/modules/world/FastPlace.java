package com.skillclient.modules.world;

import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class FastPlace extends Module
{
    ValueNumber click;
    
    public FastPlace() {
        super("FastPlace", Register.Category.WORLD, "Places Blocks faster");
        this.click = new ValueNumber("Speed", (Module)this, 4.0, 0.0, 0.0, 1);
    }
    
    public int getClick() {
        if (((Tower)Register.getModule((Class)Tower.class)).isActive() && (boolean)((Tower)Register.getModule((Class)Tower.class)).fast.getValue()) {
            return 0;
        }
        return this.isActive() ? this.click.getInt() : 4;
    }
}
