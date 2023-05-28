package com.skillclient.modules.misc;

import com.skillclient.utils.Features;
import net.minecraft.client.network.NetworkPlayerInfo;
import java.util.HashMap;
import com.skillclient.main.Register;
import java.util.UUID;
import java.util.Map;
import com.skillclient.misc.ValueBoolean;
import com.skillclient.misc.Module;

public class HUD extends Module
{
    public ValueBoolean skin;
    public ValueBoolean arrowkeys;
    public ValueBoolean user_info;
    public Map<UUID, Integer> launches;
    
    public HUD() {
        super("HUD", Register.Category.MISC, "IngameGUI");
        this.skin = new ValueBoolean("Skin", (Module)this, true);
        this.arrowkeys = new ValueBoolean("Use Arrow-Keys to navigate", (Module)this, false);
        this.user_info = new ValueBoolean("User Info", (Module)this, false);
        this.launches = new HashMap<UUID, Integer>();
        this.invisible = true;
        this.setMode(1);
        this.onEnable();
    }
    
    public String getString(final NetworkPlayerInfo networkplayerinfo) {
        if (this.isActive() && (boolean)this.user_info.getValue()) {
            return "gm" + networkplayerinfo.getGameType().getID() + (networkplayerinfo.getGameProfile().isLegacy() ? " legacy" : "") + ((HUD.mc.theWorld.getPlayerEntityByUUID(networkplayerinfo.getGameProfile().getId()) != null) ? " r" : "") + ((this.launches.containsKey(networkplayerinfo.getGameProfile().getId()) && this.launches.get(networkplayerinfo.getGameProfile().getId()) != 0 && Features.isDonator) ? (" " + this.launches.get(networkplayerinfo.getGameProfile().getId())) : "");
        }
        return "";
    }
}

