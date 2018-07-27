package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityDigBug;
import com.rickweek.models.CREEPSModelDigBug;

public class CREEPSRenderDigBug extends RenderLiving
{
    public CREEPSRenderDigBug(RenderManager renderManager, CREEPSModelDigBug creepsmodeldigbug, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodeldigbug, f);
    }

    protected void fattenup(CREEPSEntityDigBug creepsentitydigbug, float f)
    {
        GL11.glScalef(creepsentitydigbug.modelsize, creepsentitydigbug.modelsize, creepsentitydigbug.modelsize);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        fattenup((CREEPSEntityDigBug)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityDigBug entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityDigBug) entity);
	}
}