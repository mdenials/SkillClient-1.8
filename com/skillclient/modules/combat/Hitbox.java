package com.skillclient.modules.combat;

import com.skillclient.main.Register;
import com.skillclient.misc.Module;
import com.skillclient.misc.ValueModule;
import com.skillclient.misc.ValueNumber;
import com.skillclient.modules.combat.TargetUtil;
import com.skillclient.utils.FakePlayer.EntityFakePlayer;
import net.minecraft.entity.Entity;

public class HitBox extends Module {
    ValueNumber size;

    public HitBox() {
        super("HitBox", Register.Category.COMBAT, "Expands the HitBox");
        new ValueModule("Targets", (Module)this, TargetUtil.class);
        this.size = new ValueNumber("Size", (Module)this, 20.0, 0.0, 10.0, 1);
    }

    public static float getAmount(Entity e) {
        if (e.equals((Object)HitBox.mc.thePlayer) || e instanceof EntityFakePlayer || !((HitBox)Register.getModule(HitBox.class)).isActive()) {
            return 0.0f;
        }
        return (float)((Double)((HitBox)Register.getModule(HitBox.class)).size.getValue() / 10.0);
    }
}