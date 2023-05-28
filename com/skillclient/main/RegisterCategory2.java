package com.skillclient.main;

import java.util.Iterator;
import com.skillclient.misc.Module;
import java.util.ArrayList;

enum Register$Category$2
{
    @Override
    public ArrayList<Module> getModules() {
        final ArrayList<Module> list = new ArrayList<Module>();
        for (final Module m : Register.sc.moduleList) {
            if (m.isToggled()) {
                list.add(m);
            }
        }
        return list;
    }
}


