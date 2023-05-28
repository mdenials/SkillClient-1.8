package com.skillclient.modules.combat;

import net.minecraft.entity.EntityLivingBase;
import com.skillclient.misc.ValueArray;
import net.minecraft.entity.Entity;

public interface EntityComparator<E extends Entity> extends ValueArray.loadsave
{
    public static final EntityComparator<EntityLivingBase> health = new EntityComparator.TargetUtil$EntityComparator$1();
    public static final EntityComparator distance = new EntityComparator.TargetUtil$EntityComparator$2();
    public static final EntityComparator<EntityLivingBase> armor = new EntityComparator.TargetUtil$EntityComparator$3();
    public static final EntityComparator[] VALUES = { EntityComparator.health, EntityComparator.distance, EntityComparator.armor };
    
    default float value(final E e) {
        return 1.0f;
    }
    
    default boolean matches(final E e) {
        return true;
    }
    
    default String getDisplayname() {
        return "";
    }
}
