package com.skillclient.utils.FakePlayer;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class RenderEntityFakePlayer extends RenderPlayer
{
    public RenderEntityFakePlayer(final RenderManager renderManager) {
        super(renderManager);
    }
    
    protected boolean canRenderName(final AbstractClientPlayer entity) {
        return false;
    }
    
    public void setModelVisibilities(final AbstractClientPlayer clientPlayer) {
        super.setModelVisibilities(clientPlayer);
        final ModelPlayer modelplayer = this.getMainModel();
        modelplayer.bipedBody.showModel = true;
        modelplayer.bipedBodyWear.showModel = true;
        modelplayer.bipedHead.showModel = true;
        modelplayer.bipedHeadwear.showModel = true;
        modelplayer.bipedLeftArm.showModel = true;
        modelplayer.bipedLeftArmwear.showModel = true;
        modelplayer.bipedLeftLeg.showModel = true;
        modelplayer.bipedLeftLegwear.showModel = true;
        modelplayer.bipedRightLeg.showModel = true;
        modelplayer.bipedRightLegwear.showModel = true;
    }
}

