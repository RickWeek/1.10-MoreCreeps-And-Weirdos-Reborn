package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityHunchback;
import com.rickweek.models.CREEPSModelHunchback;

public class CREEPSRenderHunchback extends RenderLiving
{
    protected CREEPSModelHunchback modelBipedMain;

    public CREEPSRenderHunchback(RenderManager renderManager, CREEPSModelHunchback creepsmodelhunchback, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelhunchback, f);
        modelBipedMain = creepsmodelhunchback;
    }

    protected void fattenup(CREEPSEntityHunchback creepsentityhunchback, float f)
    {
        GL11.glScalef(creepsentityhunchback.modelsize, creepsentityhunchback.modelsize, creepsentityhunchback.modelsize);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        fattenup((CREEPSEntityHunchback)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityHunchback entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityHunchback) entity);
	}

}