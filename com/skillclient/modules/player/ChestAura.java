package com.skillclient.modules.player;

import com.skillclient.wrapper.SkillWrapper;
import com.skillclient.events.EventUpdate;
import com.skillclient.events.api.EventTarget;
import java.util.Iterator;
import com.skillclient.utils.Rotation;
import com.skillclient.modules.render.ChestESP;
import net.minecraft.tileentity.TileEntityChest;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import net.minecraft.tileentity.TileEntity;
import java.util.ArrayList;
import com.skillclient.events.EventRotation;
import com.skillclient.main.Register;
import net.minecraft.util.BlockPos;
import com.skillclient.utils.TimerUtil;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class ChestAura extends Module
{
    ValueNumber range;
    TimerUtil timer;
    BlockPos pos;
    
    public ChestAura() {
        super("ChestAura", Register.Category.PLAYER, "Automatically opens Chests");
        this.range = new ValueNumber("range", (Module)this, 6.0, 3.0, 5.0, 1);
        this.timer = new TimerUtil();
    }
    
    @EventTarget(4)
    public void onUpdate(final EventRotation event) {
        if (!event.isPacket() || (!ChestAura.mc.thePlayer.onGround && !ChestAura.mc.thePlayer.isOnLadder())) {
            return;
        }
        this.pos = null;
        final ArrayList<TileEntity> chests = new ArrayList<TileEntity>(ChestAura.mc.theWorld.loadedTileEntityList);
        Collections.sort(chests, (t1, t2) -> (int)(ChestAura.mc.thePlayer.getDistanceSq(t1.getPos()) - ChestAura.mc.thePlayer.getDistanceSq(t2.getPos())));
        for (final TileEntity tileEntity : chests) {
            if (!(tileEntity instanceof TileEntityChest)) {
                continue;
            }
            if (ChestAura.mc.thePlayer.getDistanceSq(tileEntity.getPos()) > (double)this.range.getValue() * (double)this.range.getValue()) {
                return;
            }
            if (ChestAura.mc.theWorld.getBlockState(tileEntity.getPos().add(0, 1, 0)).getBlock().isVisuallyOpaque()) {
                continue;
            }
            if (((TileEntityChest)tileEntity).numPlayersUsing != 0.0 && !ChestESP.openedList.contains(tileEntity.getPos())) {
                ChestESP.openedList.add(tileEntity.getPos());
            }
            if (ChestESP.openedList.contains(tileEntity.getPos())) {
                continue;
            }
            final Rotation rotation = new Rotation(tileEntity.getPos());
            event.setPitch(rotation.getPitch());
            event.setYaw(rotation.getYaw());
            event.stop();
            this.pos = tileEntity.getPos();
        }
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (this.timer.isDelayComplete(500L) && ChestAura.mc.currentScreen == null && !ChestAura.mc.thePlayer.isUsingItem() && this.pos != null) {
            System.out.println(ChestAura.mc.objectMouseOver.sideHit);
            SkillWrapper.rightClickBlock(this.pos, ChestAura.mc.objectMouseOver.sideHit, ChestAura.mc.objectMouseOver.hitVec);
            ChestAura.mc.thePlayer.swingItem();
            this.timer.setLastMS();
            this.pos = null;
        }
    }
}

