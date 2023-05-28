package com.skillclient.main;

import java.util.Iterator;
import com.skillclient.misc.Module;
import java.util.ArrayList;

enum Register$Category$3
{
    @Override
    public ArrayList<Module> getModules() {
        final ArrayList<Module> output = new ArrayList<Module>();
        for (final Module modul : Register.sc.moduleList) {
            if (modul.getKeyCode() != 0) {
                output.add(modul);
            }
        }
        return output;
    }
}
