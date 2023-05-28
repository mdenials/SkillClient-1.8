package com.skillclient.modules.render;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import com.skillclient.gui.accountManager.mcleaks.ChatColor;
import com.skillclient.events.api.EventTarget;
import java.util.Iterator;
import net.minecraft.entity.EntityLivingBase;
import com.skillclient.modules.combat.TargetUtil;
import net.minecraft.entity.Entity;
import com.skillclient.events.EventRender;
import com.skillclient.main.Register;
import com.skillclient.misc.ValueNumber;
import com.skillclient.misc.Module;

public class NameTags extends Module
{
    ValueNumber scale;
    
    public NameTags() {
        super("NameTags", Register.Category.RENDER, "Advanced Playerinfo.");
        this.scale = new ValueNumber("Scale", (Module)this, 500.0, 10.0, 200.0, 1);
    }
    
    @EventTarget
    public void onRender(final EventRender event) {
        for (final Entity entity : NameTags.mc.theWorld.loadedEntityList) {
            if (((TargetUtil)Register.getModule((Class)TargetUtil.class)).isValidType(entity)) {
                this.render((EntityLivingBase)entity, event.getPartialTicks());
            }
        }
    }
    
    public void render(final EntityLivingBase entity, final float partialTicks) {
        final String string = String.valueOf(entity.getDisplayName().getFormattedText()) + " " + ((entity.getHealth() > entity.getMaxHealth() / 3.0f) ? ((entity.getHealth() > entity.getMaxHealth() / 3.0f * 2.0f) ? ChatColor.GREEN : ChatColor.YELLOW) : ChatColor.DARK_RED) + (int)(entity.getHealth() * 2.0f) / 2.0f + "h";
        final int i = NameTags.mc.fontRendererObj.getStringWidth(string);
        GlStateManager.pushMatrix();
        this.rotate(entity, partialTicks);
        this.renderHealth(entity, i);
        NameTags.mc.fontRendererObj.drawString(string, -i / 2, 0, -1);
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    private void rotate(final EntityLivingBase entity, final float partialTicks) {
        final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - NameTags.mc.getRenderManager().renderPosX;
        final double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - NameTags.mc.getRenderManager().renderPosY + entity.height + 0.2 + (double)this.scale.getValue() / 200.0;
        final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - NameTags.mc.getRenderManager().renderPosZ;
        GlStateManager.translate(x, y, z);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-NameTags.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(NameTags.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-(double)this.scale.getValue() / 2000.0, -(double)this.scale.getValue() / 2000.0, (double)this.scale.getValue() / 2000.0);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    }
    
    private void renderHealth(final EntityLivingBase entity, final int i) {
        GlStateManager.disableTexture2D();
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer vertexbuffer = tessellator.getWorldRenderer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos((double)(-i / 2), 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        vertexbuffer.pos((double)(-i / 2), 10.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        vertexbuffer.pos((double)(i / 2), 10.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        vertexbuffer.pos((double)(i / 2), 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        tessellator.draw();
        final double health = entity.getHealth() / entity.getMaxHealth();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos((double)(-i / 2), 8.0, 0.0).color(1.0f, 0.0f, 0.0f, 0.75f).endVertex();
        vertexbuffer.pos((double)(-i / 2), 10.0, 0.0).color(1.0f, 0.0f, 0.0f, 0.75f).endVertex();
        vertexbuffer.pos(i * health - i / 2, 10.0, 0.0).color(1.0f, 0.0f, 0.0f, 0.75f).endVertex();
        vertexbuffer.pos(i * health - i / 2, 8.0, 0.0).color(1.0f, 0.0f, 0.0f, 0.75f).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
    }
}
