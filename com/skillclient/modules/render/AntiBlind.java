ackage com.skillclient.modules.render;

import com.skillclient.events.api.EventTarget;
import net.minecraft.potion.Potion;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class AntiBlind extends Module
{
    public AntiBlind() {
        super("AntiBlind", Register.Category.RENDER, "Removes Some Potion-Effects");
    }
    
    public boolean isActive() {
        return this.isToggled();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (!((AntiBlind)Register.getModule((Class)AntiBlind.class)).isActive()) {
            return;
        }
        if (AntiBlind.mc.thePlayer.isPotionActive(Potion.confusion)) {
            AntiBlind.mc.thePlayer.removePotionEffect(Potion.confusion.id);
        }
        if (AntiBlind.mc.thePlayer.isPotionActive(Potion.blindness)) {
            AntiBlind.mc.thePlayer.removePotionEffect(Potion.blindness.id);
        }
        AntiBlind.mc.thePlayer.timeInPortal = 0.0f;
    }
}

