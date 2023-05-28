package com.skillclient.modules.misc;

import com.skillclient.main.SkillClient;
import com.skillclient.events.api.EventTarget;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import com.skillclient.events.EventUpdate;
import com.skillclient.main.Register;
import java.util.Collection;
import java.util.Arrays;
import java.io.File;
import java.util.ArrayList;
import com.skillclient.misc.Module;

public class SkinStealer extends Module
{
    public static ArrayList<File> skins;
    private static boolean downloading;
    
    static {
        SkinStealer.skins = new ArrayList<File>(Arrays.asList(SkinStealer.sc.filemanager.skinDir.listFiles()));
        SkinStealer.downloading = false;
    }
    
    public SkinStealer() {
        super("SkinStealer", Register.Category.MISC, "Steals Skins");
    }
    
    public static void stealSkin(final String name) {
        if (!SkinStealer.skins.contains(new File(SkinStealer.sc.filemanager.skinDir, String.valueOf(name) + ".png"))) {
            SkinStealer.downloading = true;
            new Thread((Runnable)new SkinStealer.SkinStealer$1(name)).start();
        }
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        if (SkinStealer.mc.theWorld == null) {
            return;
        }
        if (SkinStealer.downloading) {
            return;
        }
        for (final Entity entity : SkinStealer.mc.theWorld.loadedEntityList) {
            stealSkin(entity.getName());
        }
    }
}

