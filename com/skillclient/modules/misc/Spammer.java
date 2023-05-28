package com.skillclient.modules.misc;

import com.skillclient.events.api.EventTarget;
import org.apache.commons.lang3.RandomStringUtils;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import com.skillclient.utils.TimerUtil;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.ValueText;
import com.skillclient.misc.Module;

public class Spammer extends Module
{
    ValueText texts;
    ValueNumber time;
    TimerUtil timer;
    
    public Spammer() {
        super("Spammer", Register.Category.MISC, "Spams Messages");
        this.texts = new ValueText("texts", (Module)this, "Hi n00bs; I am hacking xD");
        this.time = new ValueNumber("speed", " s", (Module)this, 5.0, 0.0, 2.0, 1);
        this.timer = new TimerUtil();
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (this.timer.isDelayComplete(((Double)this.time.getValue()).longValue() * 1000L) && !this.texts.getValue().isEmpty()) {
            this.timer.setLastMS();
            final String[] msgs = this.texts.getValue().split(";");
            Spammer.mc.thePlayer.sendChatMessage(String.valueOf(msgs[Spammer.random.nextInt(msgs.length)]) + " " + RandomStringUtils.randomAlphabetic(20).toLowerCase());
        }
    }
}
