package com.skillclient.utils;

import java.awt.image.BufferedImage;
import net.minecraft.util.ResourceLocation;
import java.util.UUID;
import net.minecraft.client.renderer.IImageBuffer;

class CapesAPI$1 implements IImageBuffer {
    private final /* synthetic */ UUID val$uuid;
    private final /* synthetic */ ResourceLocation val$resourceLocation;
    
    public BufferedImage parseUserSkin(final BufferedImage image) {
        return image;
    }
    
    public void skinAvailable() {
        CapesAPI.access$0().put(this.val$uuid, this.val$resourceLocation);
    }
}
