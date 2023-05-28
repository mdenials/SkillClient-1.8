package com.skillclient.modules.misc;

import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.entity.item.EntityItem;
import com.skillclient.modules.combat.TargetUtil;

class TestMod$2 implements TargetUtil.EntityComparator<EntityItem> {
    @Override
    public boolean matches(final EntityItem e) {
        return e.getEntityItem().getItem().equals(Items.gold_ingot);
    }
}
