package com.skillclient.utils;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import java.io.File;
import com.skillclient.misc.SCMC;

public class FileManager implements SCMC
{
    public final File skillDir;
    public final File skinDir;
    public final File config;
    public final File accounts;
    
    public FileManager() {
        this.skillDir = new File(Minecraft.getMinecraft().mcDataDir, "SkillClient");
        this.skinDir = new File(this.skillDir, "skins");
        final File skillDir = this.skillDir;
        final StringBuilder sb = new StringBuilder("config");
        FileManager.sc.getClass();
        this.config = new File(skillDir, sb.append("1.8").append(".cfg").toString());
        this.accounts = new File(this.skillDir, "accounts.txt");
        if (!this.skillDir.exists()) {
            this.skillDir.mkdir();
        }
        if (!this.skinDir.exists()) {
            this.skinDir.mkdir();
        }
        if (!this.config.exists()) {
            try {
                this.config.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!this.accounts.exists()) {
            try {
                this.accounts.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!new File(Minecraft.getMinecraft().mcDataDir, "server-resource-packs").exists()) {
            new File(Minecraft.getMinecraft().mcDataDir, "server-resource-packs").mkdir();
        }
    }
}

