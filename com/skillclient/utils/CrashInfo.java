package com.skillclient.utils;

import java.util.Iterator;
import net.minecraft.client.Minecraft;
import com.skillclient.misc.Value;
import com.skillclient.misc.Module;
import com.skillclient.main.SkillClient;

public class CrashInfo
{
    public static void addInfo(final StringBuilder stringbuilder) {
        try {
            final SkillClient sc = SkillClient.getClient();
            final StringBuilder sb = new StringBuilder("SkillClient Version");
            sc.getClass();
            final StringBuilder append = sb.append("Minecraft 1.8.8 | SkillClient b8.1").append("VERSION_NUM:");
            sc.getClass();
            stringbuilder.append(append.append(9).append("\n").toString());
            for (final Module module : sc.moduleList) {
                if (module.isActive()) {
                    stringbuilder.append(String.valueOf(module.name) + ": " + module.isToggled() + "\n");
                    for (final Value v : module.valueList) {
                        stringbuilder.append("  " + v.getName() + ":" + v.getValue() + "\n");
                    }
                }
            }
            if (Minecraft.getMinecraft().getCurrentServerData() != null) {
                stringbuilder.append("server:" + Minecraft.getMinecraft().getCurrentServerData().serverIP + "\n");
            }
            stringbuilder.append("\n\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
