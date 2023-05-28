package com.skillclient.modules.render;

import com.skillclient.events.api.EventTarget;
import net.minecraft.util.BlockPos;
import com.skillclient.utils.RenderUtils;
import com.skillclient.events.EventRender;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueText;
import com.skillclient.misc.Module;

public class Compass extends Module
{
    ValueText home;
    
    public Compass() {
        super("Compass", Register.Category.RENDER, "Virtual Compass");
        this.home = new ValueText("Home", (Module)this, "x y z");
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        final BlockPos b = Compass.mc.thePlayer.getBedLocation();
        if (b != null) {
            RenderUtils.tracerLine(b, RenderUtils.friend);
            RenderUtils.framelessBlockESP(b.getX() - 1, b.getY() - 1, b.getZ() - 1, 2, 2, 2, 0.2f, 0.2f, 1.0f);
        }
        if (this.home.getValue() != null && this.home.getValue().matches("[0-9]{1,} [0-9]{1,} [0-9]{1,}")) {
            final String[] xyz = this.home.getValue().split(" ");
            final int x = Integer.parseInt(xyz[0]);
            final int y = Integer.parseInt(xyz[1]);
            final int z = Integer.parseInt(xyz[2]);
            RenderUtils.tracerLine(new BlockPos(x, y, z), RenderUtils.team);
        }
    }
}

