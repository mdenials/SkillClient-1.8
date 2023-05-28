package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import com.skillclient.misc.Module;

public class CmdBind extends Cmd
{
    public CmdBind() {
        super("bind", "Set a KeyBind. You can also use the Gui xD");
    }
    
    @Override
    public void action(final String[] args) {
        if (args.length == 2) {
            for (final Module modul : CmdBind.sc.moduleList) {
                if (args[0].toLowerCase().equals(modul.name.replaceAll("[^a-zA-Z0-9]", "").toLowerCase())) {
                    try {
                        modul.setKeyCode(Keyboard.getKeyIndex(args[1].toUpperCase()));
                    }
                    catch (Exception e) {
                        CmdBind.sc.chat.chat("Failed to bind " + args[0] + ". " + e.getMessage());
                    }
                    CmdBind.sc.chat.chat("Module " + args[0] + " binded to [" + Keyboard.getKeyName(modul.getKeyCode()) + "]");
                    return;
                }
            }
            CmdBind.sc.chat.chat("Module " + args[0] + " not found.");
        }
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        if (args.length == 1) {
            final List<String> cmds = new ArrayList<String>();
            for (final Module modul : CmdBind.sc.moduleList) {
                if (!cmds.contains(modul.name) && modul.name.toLowerCase().startsWith(args[0].toLowerCase())) {
                    cmds.add(modul.name);
                }
            }
            return cmds;
        }
        if (args.length == 2) {
            final List<String> cmds = new ArrayList<String>();
            for (int i = 0; i < 256; ++i) {
                final String s = Keyboard.getKeyName(i);
                if (s != null && !cmds.contains(s) && s.toLowerCase().startsWith(args[1].toLowerCase())) {
                    cmds.add(s);
                }
            }
            return cmds;
        }
        return new ArrayList<String>();
    }
}

