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

import com.rickweek.entities.CREEPSEntityHunchbackSkeleton;
import com.rickweek.models.CREEPSModelMummy;

public class CREEPSRenderHunchbackSkeleton extends RenderLiving
{
    protected ModelBiped modelBipedMain;

    public CREEPSRenderHunchbackSkeleton(RenderManager renderManagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
	    super(renderManagerIn, modelbaseIn, shadowsizeIn);
	  }

    protected void fattenup(CREEPSEntityHunchbackSkeleton creepsentityhunchbackskeleton, float f)
    {
        GL11.glScalef(creepsentityhunchbackskeleton.modelsize, creepsentityhunchbackskeleton.modelsize, creepsentityhunchbackskeleton.modelsize);
    }

    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        fattenup((CREEPSEntityHunchbackSkeleton)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityHunchbackSkeleton entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityHunchbackSkeleton) entity);
	}
}
