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

import com.rickweek.entities.CREEPSEntityPrisoner;
import com.rickweek.models.CREEPSModelMummy;

public class CREEPSRenderPrisoner extends RenderLiving
{

    public CREEPSRenderPrisoner(RenderManager renderManagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
	    super(renderManagerIn, modelbaseIn, shadowsizeIn);
	  }

	protected void fattenup(CREEPSEntityPrisoner creepsentityprisoner, float f)
    {
        GL11.glScalef(0.75F, 1.0F, 0.9F);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        CREEPSEntityPrisoner creepsentityprisoner = (CREEPSEntityPrisoner)entityliving;
        fattenup((CREEPSEntityPrisoner)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityPrisoner entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityPrisoner) entity);
	}
}