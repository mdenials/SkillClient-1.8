package com.skillclient.modules.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

class KillAura$4 implements TargetUtil.EntityComparator<EntityLivingBase> {
    public boolean matches(final EntityLivingBase e) {
        return KillAura.access$0().thePlayer.getDistanceToEntity((Entity)e) < (double)KillAura.this.range.getValue() + (double)KillAura.this.range_extended.getValue();
    }
}
