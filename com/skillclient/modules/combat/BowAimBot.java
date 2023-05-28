package com.skillclient.modules.combat;

import com.skillclient.events.api.EventTarget;
import com.skillclient.utils.exceptions.NoEntityFoundException;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBow;
import com.skillclient.events.EventRotation;
import com.skillclient.misc.ValueModule;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class BowAimBot extends Module
{
    public BowAimBot() {
        super("BowAimBot", Register.Category.COMBAT, "Improves your Bow-Skills");
        new ValueModule("Targets", (Module)this, (Class)TargetUtil.class);
    }
    
    @EventTarget
    public void onSend(final EventRotation event) {
        if (!((BowAimBot)Register.getModule((Class)BowAimBot.class)).isActive()) {
            return;
        }
        if (BowAimBot.mc.thePlayer.inventory.getCurrentItem() == null || !(BowAimBot.mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemBow) || !BowAimBot.mc.gameSettings.keyBindUseItem.pressed) {
            return;
        }
        Entity target = null;
        try {
            ((TargetUtil)Register.getModule((Class)TargetUtil.class)).waitForDamage = 0;
            target = ((TargetUtil)Register.getModule((Class)TargetUtil.class)).bestEntity((Class)Entity.class, new TargetUtil.EntityComparator[] { TargetUtil.EntityComparator.distance });
        }
        catch (NoEntityFoundException e) {
            return;
        }
        final int bowCharge = BowAimBot.mc.thePlayer.getItemInUseCount();
        float velocity = (float)(bowCharge / 20);
        velocity = (velocity * velocity + velocity * 2.0f) / 3.0f;
        if (((FastUse)Register.getModule((Class)FastUse.class)).isActive()) {
            velocity = 1.0f;
        }
        if (velocity > 1.0f) {
            velocity = 1.0f;
        }
        final double posX = target.posX + (target.posX - target.prevPosX) * 5.0 - BowAimBot.mc.thePlayer.posX;
        final double posY = target.posY + (target.posY - target.prevPosY) * 5.0 + target.getEyeHeight() - 0.15 - BowAimBot.mc.thePlayer.posY - BowAimBot.mc.thePlayer.getEyeHeight();
        final double posZ = target.posZ + (target.posZ - target.prevPosZ) * 5.0 - BowAimBot.mc.thePlayer.posZ;
        final float yaw = (float)(Math.atan2(posZ, posX) * 180.0 / 3.141592653589793) - 90.0f;
        final double y2 = Math.sqrt(posX * posX + posZ * posZ);
        final float g = 0.006f;
        final float tmp = (float)(velocity * velocity * velocity * velocity - g * (g * (y2 * y2) + 2.0 * posY * (velocity * velocity)));
        final float pitch = (float)(-Math.toDegrees(Math.atan((velocity * velocity - Math.sqrt(tmp)) / (g * y2))));
        event.setYaw(yaw);
        event.setPitch(pitch);
        event.stop();
    }
}
