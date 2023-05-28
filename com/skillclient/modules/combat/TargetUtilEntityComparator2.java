package com.skillclient.modules.combat;

import net.minecraft.entity.Entity;

class TargetUtil$EntityComparator$2 implements EntityComparator {
    @Override
    public String getDisplayname() {
        return "Distance";
    }
    
    @Override
    public float value(final Entity e) {
        return (float)TargetUtil.access$0().thePlayer.getDistanceSqToEntity(e);
    }
}
