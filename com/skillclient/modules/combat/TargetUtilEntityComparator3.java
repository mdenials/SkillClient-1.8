package com.skillclient.modules.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

class TargetUtil$EntityComparator$3 implements EntityComparator<EntityLivingBase> {
    @Override
    public String getDisplayname() {
        return "Health & Armor";
    }
    
    @Override
    public float value(final EntityLivingBase e) {
        return e.getHealth() * e.getTotalArmorValue();
    }
}
