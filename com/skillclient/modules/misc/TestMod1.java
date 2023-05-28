package com.skillclient.modules.misc;

import net.minecraft.init.Items;
import com.skillclient.utils.Rotation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import com.skillclient.modules.combat.TargetUtil;

class TestMod$1 implements TargetUtil.EntityComparator<EntityItem> {
    @Override
    public float value(final EntityItem e) {
        return (float)((float)TestMod.access$0().thePlayer.getDistanceSqToEntity((Entity)e) * (TestMod.access$0().thePlayer.canEntityBeSeen((Entity)e) ? 1 : 3) * new Rotation((Entity)e).radius());
    }
    
    @Override
    public boolean matches(final EntityItem e) {
        return e.getEntityItem().getItem().equals(Items.gold_ingot);
    }
}
