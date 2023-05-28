package com.skillclient.modules.misc;

import net.minecraft.client.Minecraft;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class NameProtect extends Module
{
    public NameProtect.ProtectionsMap protections;
    
    public NameProtect() {
        super("NameProtect", Register.Category.MISC, "Just a test");
        this.protections = new NameProtect.ProtectionsMap();
    }
    
    public void onEnable() {
        this.protections.reset();
        super.onEnable();
    }
}
