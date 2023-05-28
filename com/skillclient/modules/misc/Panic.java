package com.skillclient.modules.misc;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class Panic extends Module
{
    public Panic() {
        super("Panic", Register.Category.MISC, "Turns off all Mods");
        this.modes = 1;
    }
    
    public void onEnable() {
        for (final Module module : Panic.sc.moduleList) {
            if (module.isToggled()) {
                try {
                    module.onDisable();
                }
                catch (ConcurrentModificationException ex) {}
            }
        }
    }
}
