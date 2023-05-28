package com.skillclient.chat;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import net.minecraft.util.IChatComponent;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import java.awt.Desktop;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.Reader;
import com.skillclient.utils.ClientSettings;
import java.io.InputStreamReader;
import java.net.URL;

public class CmdSettings extends Cmd
{
    public CmdSettings() {
        super("settings", "Saves or loads settings");
    }
    
    @Override
    public void action(final String[] args) {
        try {
            if (args.length >= 1) {
                if (args[0].equals("load") && args.length == 2) {
                    CmdSettings.sc.chat.chat("Loading File from Github...");
                    try {
                        ClientSettings.loadOptions((Reader)new InputStreamReader(new URL("https://raw.githubusercontent.com/MCmodding4K/SkillClient/master/settings/" + args[1] + ".cfg").openStream()));
                        return;
                    }
                    catch (FileNotFoundException e3) {
                        CmdSettings.sc.chat.chat("Loading local-File...");
                        try {
                            ClientSettings.loadOptions((Reader)new FileReader(new File(CmdSettings.sc.filemanager.config, String.valueOf(args[1]) + ".cfg")));
                        }
                        catch (FileNotFoundException e4) {
                            CmdSettings.sc.chat.chat("Loading local-File...");
                        }
                        return;
                    }
                }
                if (args[0].equals("save") && args.length == 2) {
                    final File file = new File(CmdSettings.sc.filemanager.skillDir, String.valueOf(args[1]) + ".cfg");
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    final String timeStamp = new SimpleDateFormat("dd.MM.yyyy_HH:mm").format(Calendar.getInstance().getTime());
                    ClientSettings.saveOptions(file, new String[] { "# " + args[1].replace("_", " "), "say Loading_config_for_" + args[1], "say Last_Updated:_" + timeStamp, "#", "#" });
                    Desktop.getDesktop().open(file);
                    final ChatComponentText itextcomponent = new ChatComponentText("You can now publish your config for all other Players. Click here.");
                    itextcomponent.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/MCmodding4K/SkillClient/tree/master/settings"));
                    CmdSettings.sc.chat.chat((IChatComponent)itextcomponent);
                }
                else {
                    final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/MCmodding4K/SkillClient/master/settings/Settings-List.txt").openStream()));
                    String s = "";
                    while ((s = bufferedreader.readLine()) != null) {
                        CmdSettings.sc.chat.chat(" - " + s);
                    }
                }
            }
            else {
                CmdSettings.sc.chat.chat(".settings <load/save/list> [configname]");
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    @Override
    public List<String> tabComplete(final String[] args) {
        final List<String> cmds = new ArrayList<String>();
        if (args.length == 1) {
            String[] array;
            for (int length = (array = new String[] { "load", "save", "list" }).length, i = 0; i < length; ++i) {
                final String s = array[i];
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    cmds.add(s);
                }
            }
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("load")) {
            try {
                final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/MCmodding4K/SkillClient/master/settings/Settings-List.txt").openStream()));
                String s2 = "";
                while ((s2 = bufferedreader.readLine()) != null) {
                    if (s2.toLowerCase().startsWith(args[1].toLowerCase())) {
                        cmds.add(s2);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cmds;
    }
}

