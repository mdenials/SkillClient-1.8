package com.skillclient.modules.render;

import com.skillclient.events.api.EventTarget;
import java.util.Iterator;
import com.skillclient.utils.RenderUtils;
import java.awt.Color;
import net.minecraft.tileentity.TileEntity;
import com.skillclient.events.EventRender;
import com.skillclient.main.Register;
import com.skillclient.misc.Module;

public class DataViewer extends Module
{
    public DataViewer() {
        super("DataViewer", Register.Category.RENDER, "Highlights TileEntitys");
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        for (final TileEntity tileentity : DataViewer.mc.theWorld.loadedTileEntityList) {
            RenderUtils.framelessBlockESP(tileentity.getPos(), Color.green);
        }
    }
    
    public String getDisplayName() {
        return String.valueOf(super.getDisplayName()) + ((DataViewer.mc.theWorld == null) ? "" : ("[" + DataViewer.mc.theWorld.loadedTileEntityList.size() + "]"));
    }
}
