package com.skillclient.modules.auto;

import com.skillclient.events.api.EventTarget;
import net.minecraft.util.MovingObjectPosition;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class AutoMine extends Module
{
    static boolean mining;
    
    public AutoMine() {
        super("AutoMine", Register.Category.AUTO, "Automatically Mines Blocks");
    }
    
    public void onDisable() {
        AutoMine.mc.gameSettings.keyBindAttack.pressed = (AutoMine.mining = false);
        super.onDisable();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (AutoMine.mc.objectMouseOver.typeOfHit.equals((Object)MovingObjectPosition.MovingObjectType.BLOCK) && !AutoMine.mining) {
            AutoMine.mc.gameSettings.keyBindAttack.pressed = (AutoMine.mining = true);
        }
        else if (AutoMine.mining) {
            AutoMine.mc.gameSettings.keyBindAttack.pressed = (AutoMine.mining = false);
        }
    }
}


