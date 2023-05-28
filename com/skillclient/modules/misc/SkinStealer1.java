package com.skillclient.modules.misc;

import java.net.URLConnection;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.File;

class SkinStealer$1 implements Runnable {
    private final /* synthetic */ String val$name;
    
    @Override
    public void run() {
        try {
            final File skin = new File(SkinStealer.access$0().filemanager.skinDir, String.valueOf(this.val$name) + ".png");
            SkinStealer.skins.add(skin);
            final URL skinURL = new URL("http://skins.minecraft.net/MinecraftSkins/" + this.val$name + ".png");
            final URLConnection skinCon = skinURL.openConnection();
            final BufferedInputStream skinputStream = new BufferedInputStream(skinCon.getInputStream());
            final FileOutputStream outputStream = new FileOutputStream(skin);
            int i;
            while ((i = skinputStream.read()) != -1) {
                outputStream.write(i);
            }
            outputStream.close();
            skinputStream.close();
        }
        catch (Exception ex) {}
        SkinStealer.access$1(false);
    }
}
