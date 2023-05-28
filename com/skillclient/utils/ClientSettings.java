package com.skillclient.utils;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Iterator;
import com.skillclient.misc.Value;
import com.skillclient.misc.Module;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.FileReader;
import com.skillclient.misc.SCMC;

public class ClientSettings implements SCMC
{
    public static void loadOptions() {
        try {
            loadOptions(new FileReader(ClientSettings.sc.filemanager.config));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadOptions(final Reader reader) {
        System.out.println("Loading settings...");
        try {
            final BufferedReader bufferedreader = new BufferedReader(reader);
            String s = "";
            while ((s = bufferedreader.readLine()) != null) {
                if (s.startsWith("#")) {
                    continue;
                }
                try {
                    final String[] args = s.split(" ");
                    if (args[0].equals("say")) {
                        ClientSettings.sc.chat.chat(args[1].replaceAll("_", " "));
                    }
                    for (final Module modul : ClientSettings.sc.moduleList) {
                        try {
                            if (args.length < 1 || !args[1].equals(modul.name.replaceAll("[^a-zA-Z0-9]", ""))) {
                                continue;
                            }
                            if (args[0].equals("bind")) {
                                modul.setKeyCode(Integer.parseInt(args[2]));
                            }
                            if (args[0].equals("toggle")) {
                                try {
                                    modul.setMode(0);
                                    modul.onDisable();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                                final int i = Integer.parseInt(args[2]);
                                if (i != 0) {
                                    modul.toggle();
                                }
                                modul.setMode(i);
                            }
                            if (!args[0].equals("value")) {
                                continue;
                            }
                            for (final Value value : modul.valueList) {
                                if (args[2].equals(value.getName().replaceAll("[^a-zA-Z0-9]", ""))) {
                                    value.loadValue(args[3].replaceAll("_", " "));
                                }
                            }
                        }
                        catch (Exception ex) {
                            System.err.println("Failed to load config for " + modul.name);
                            System.err.println(s);
                            ex.printStackTrace();
                        }
                    }
                }
                catch (Exception e2) {
                    System.err.println(s);
                    e2.printStackTrace();
                }
            }
            bufferedreader.close();
        }
        catch (Exception e3) {
            e3.printStackTrace();
        }
        System.out.println("Loading finished.");
        saveOptions();
    }
    
    public static void saveOptions(final File file, final String... text) {
        try {
            final PrintWriter printwriter = new PrintWriter(new FileWriter(file));
            for (final String s : text) {
                printwriter.println(s);
            }
            for (final Module modul : ClientSettings.sc.moduleList) {
                if (modul.getKeyBinding().getKeyCode() != 0) {
                    printwriter.println("bind " + modul.name.replaceAll("[^a-zA-Z0-9]", "") + " " + modul.getKeyBinding().getKeyCode());
                }
                if (modul.getMode() != 0) {
                    printwriter.println("toggle " + modul.name.replaceAll("[^a-zA-Z0-9]", "") + " " + modul.getMode());
                }
                for (final Value property : modul.valueList) {
                    if (!property.saveValue().isEmpty()) {
                        printwriter.println("value " + modul.name.replaceAll("[^a-zA-Z0-9]", "") + " " + property.getName().replaceAll("[^a-zA-Z0-9]", "") + " " + property.saveValue().replaceAll(" ", "_"));
                    }
                }
            }
            printwriter.println("# end");
            printwriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void saveOptions() {
        saveOptions(ClientSettings.sc.filemanager.config, new String[0]);
    }
}


