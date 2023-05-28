package com.skillclient.main;

import com.skillclient.modules.world.CivBreak;
import com.skillclient.modules.exploit.BeaconExploit;
import com.skillclient.modules.render.CaveFinder;
import com.skillclient.modules.combat.KillAura;
import com.skillclient.modules.world.ScaffoldWalk;
import com.skillclient.modules.exploit.Teleport;
import com.skillclient.misc.Module;
import java.util.ArrayList;

enum Register$Category$4
{
    @Override
    public ArrayList<Module> getModules() {
        final ArrayList<Module> output = new ArrayList<Module>();
        output.add(Register.getModule((Class<? extends Module>)Teleport.class));
        output.add(Register.getModule((Class<? extends Module>)ScaffoldWalk.class));
        output.add(Register.getModule((Class<? extends Module>)KillAura.class));
        output.add(Register.getModule((Class<? extends Module>)CaveFinder.class));
        output.add(Register.getModule((Class<? extends Module>)BeaconExploit.class));
        output.add(Register.getModule((Class<? extends Module>)CivBreak.class));
        return output;
    }
}
