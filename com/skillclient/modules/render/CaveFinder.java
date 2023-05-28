package com.skillclient.modules.render;

import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class CaveFinder extends Module
{
    public ValueNumber alpha;
    public ValueBoolean allBlocks;
    
    public CaveFinder() {
        super("Cavefinder", Register.Category.RENDER, "Amazing shit");
        this.alpha = (ValueNumber)new CaveFinder.CaveFinder$1(this, "Alpha", (Module)this, 255.0, 0.0, 100.0, 1);
        this.allBlocks = (ValueBoolean)new CaveFinder.CaveFinder$2(this, "allBlocks", (Module)this, false);
    }
    
    public void onDisable() {
        CaveFinder.mc.renderGlobal.loadRenderers();
        super.onDisable();
    }
    
    public void onEnable() {
        CaveFinder.mc.renderGlobal.loadRenderers();
        super.onEnable();
    }
}
