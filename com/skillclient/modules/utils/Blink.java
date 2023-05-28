package com.skillclient.modules.util;

import com.skillclient.main.Register;
import com.skillclient.utils.TimerUtil;
import com.skillclient.misc.Module;

public class Blink extends Module
{
    TimerUtil timer;
    
    public Blink() {
        super("Blink", Register.Category.UTIL, "Fake Laggs");
        this.timer = new TimerUtil();
    }
    
    public String getDisplayName() {
        return String.valueOf(super.getDisplayName()) + (this.isToggled() ? ("[" + this.timer.pastTime() / 1000 + "s]") : "");
    }
    
    public void onEnable() {
        super.onEnable();
        this.timer.setLastMS();
    }
    
    public void onDisable() {
        super.onDisable();
        this.timer.setLastMS();
    }
    
    public boolean isActive() {
        return super.isActive() && Blink.mc.theWorld != null;
    }
}

