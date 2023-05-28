package com.skillclient.modules.combat;

import com.skillclient.events.api.EventTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.utils.TimerUtil;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class TriggerBot extends Module
{
    ValueNumber attackDelay;
    TimerUtil timer;
    
    public TriggerBot() {
        super("TriggerBot", Register.Category.COMBAT, "Hits Entitys you are looking at.");
        this.attackDelay = new ValueNumber("AttackSpeed", (Module)this, 10.0, 0.0, 1.0, 1);
        this.timer = new TimerUtil();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        ((TargetUtil)Register.getModule((Class)TargetUtil.class)).waitForDamage = 10;
        if (TriggerBot.mc.thePlayer.isDead || TriggerBot.mc.objectMouseOver.entityHit == null || (TriggerBot.mc.objectMouseOver.entityHit instanceof EntityLivingBase && !((TargetUtil)Register.getModule((Class)TargetUtil.class)).matches((EntityLivingBase)TriggerBot.mc.objectMouseOver.entityHit))) {
            return;
        }
        if (this.timer.isDelayComplete((long)(100.0 * (double)this.attackDelay.getValue()))) {
            this.timer.setLastMS();
            TriggerBot.mc.thePlayer.swingItem();
            TriggerBot.mc.playerController.attackEntity((EntityPlayer)TriggerBot.mc.thePlayer, TriggerBot.mc.objectMouseOver.entityHit);
            if (TriggerBot.mc.objectMouseOver.entityHit instanceof EntityLivingBase) {
                ((EntityLivingBase)TriggerBot.mc.objectMouseOver.entityHit).hurtTime = 10;
            }
        }
    }
}

