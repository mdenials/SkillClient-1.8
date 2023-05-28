package com.skillclient.misc;

import net.minecraft.client.Minecraft;
import com.skillclient.main.SkillClient;

public interface SCMC
{
    public static final SkillClient sc = SkillClient.getClient();
    public static final Minecraft mc = Minecraft.getMinecraft();
}
