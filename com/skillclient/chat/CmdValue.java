package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import com.skillclient.misc.Value;
import com.skillclient.misc.Module;

public class CmdValue extends Cmd
{
    Module m;
    
    public CmdValue(final Module m) {
        super(m.name, "Set a Value for " + m.name);
        this.m = m;
    }
    
    @Override
    public void action(final String[] args) {
        Value value = null;
        if (args.length >= 1) {
            for (final Value v : this.m.valueList) {
                if (args[0].toLowerCase().equals(v.getName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase())) {
                    value = v;
                }
            }
        }
        if (args.length == 1 && value != null) {
            CmdValue.sc.chat.chat("§6 Value \"" + value.getName() + "\" in \"" + this.m.name + "\" is \"" + value.getValue() + "\"");
        }
        else if (args.length == 2 && value != null) {
            value.loadValue(args[1]);
            CmdValue.sc.chat.chat("§6 Value \"" + value.getName() + "\" in \"" + this.m.name + "\" has been set to \"" + value.getValue() + "\"");
        }
        else {
            CmdValue.sc.chat.chat("§6 Values for " + this.m.name + ":");
            for (final Value v : this.m.valueList) {
                if (v.getValue() != null) {
                    CmdValue.sc.chat.chat(" ." + this.m.name + " " + v.getName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase() + " " + v.getValue(), "." + this.m.name + " " + v.getName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase() + " ");
                }
            }
        }
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        final List<String> cmds = new ArrayList<String>();
        if (args.length == 1) {
            for (final Value v : this.m.valueList) {
                if (v.getName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().startsWith(args[0].toLowerCase())) {
                    cmds.add(v.getName().replaceAll("[^a-zA-Z0-9]", ""));
                }
            }
        }
        return cmds;
    }
}
