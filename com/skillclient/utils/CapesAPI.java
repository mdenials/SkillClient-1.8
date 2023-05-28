package com.skillclient.utils;

import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import java.io.File;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.Minecraft;
import java.util.HashMap;
import net.minecraft.util.ResourceLocation;
import java.util.UUID;
import java.util.Map;

public class CapesAPI
{
    private static Map<UUID, ResourceLocation> capes;
    
    static {
        CapesAPI.capes = new HashMap<UUID, ResourceLocation>();
    }
    
    public static void loadCape(final UUID uuid) {
        if (hasCape(uuid)) {
            return;
        }
        final String url = "http://capesapi.com/api/v1/" + uuid.toString() + "/getCape";
        final ResourceLocation resourceLocation = new ResourceLocation("capeapi/capes/" + uuid.toString() + ".png");
        final TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
        final IImageBuffer iImageBuffer = (IImageBuffer)new CapesAPI.CapesAPI$1(uuid, resourceLocation);
        final ThreadDownloadImageData threadDownloadImageData = new ThreadDownloadImageData((File)null, url, (ResourceLocation)null, iImageBuffer);
        textureManager.loadTexture(resourceLocation, (ITextureObject)threadDownloadImageData);
    }
    
    public static void deleteCape(final UUID uuid) {
        CapesAPI.capes.remove(uuid);
    }
    
    public static ResourceLocation getCape(final UUID uuid) {
        return CapesAPI.capes.containsKey(uuid) ? CapesAPI.capes.get(uuid) : null;
    }
    
    public static boolean hasCape(final UUID uuid) {
        return CapesAPI.capes.containsKey(uuid);
    }
}
