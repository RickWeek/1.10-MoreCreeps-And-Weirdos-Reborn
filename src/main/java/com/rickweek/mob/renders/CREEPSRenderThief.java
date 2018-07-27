package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityThief;

public class CREEPSRenderThief extends RenderLiving
{
    protected ModelBiped modelBipedMain;

    public CREEPSRenderThief(RenderManager renderManagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
	    super(renderManagerIn, modelbaseIn, shadowsizeIn);
	  }

    protected void fattenup(CREEPSEntityThief creepsentitythief, float f)
    {
        GL11.glScalef(creepsentitythief.modelsize, creepsentitythief.modelsize, creepsentitythief.modelsize);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        fattenup((CREEPSEntityThief)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityThief entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityThief) entity);
	}
}