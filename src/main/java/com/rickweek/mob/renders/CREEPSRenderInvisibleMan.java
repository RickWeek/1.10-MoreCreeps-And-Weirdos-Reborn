package com.rickweek.mob.renders;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityInvisibleMan;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class CREEPSRenderInvisibleMan extends RenderBiped
{
	public CREEPSRenderInvisibleMan(RenderManager renderManagerIn, ModelBiped modelbaseIn, float shadowsizeIn) {
	    super(renderManagerIn, modelbaseIn, shadowsizeIn);
	  }

    protected void fattenup(CREEPSEntityInvisibleMan creepsentityinvisibleman, float f)
    {
        GL11.glScalef(creepsentityinvisibleman.modelsize, creepsentityinvisibleman.modelsize, creepsentityinvisibleman.modelsize);
    }

    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        fattenup((CREEPSEntityInvisibleMan)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityInvisibleMan entity)
    {
		return entity.texture;
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityInvisibleMan) entity);
	}
}