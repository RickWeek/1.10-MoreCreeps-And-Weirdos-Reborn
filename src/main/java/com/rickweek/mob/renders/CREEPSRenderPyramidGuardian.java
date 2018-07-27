package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityPyramidGuardian;
import com.rickweek.models.CREEPSModelPyramidGuardian;

public class CREEPSRenderPyramidGuardian extends RenderLiving
{
    protected CREEPSModelPyramidGuardian modelBipedMain;
    public boolean scaled;
    private ModelBase scaleAmount;

    public CREEPSRenderPyramidGuardian(RenderManager renderManager, CREEPSModelPyramidGuardian creepsmodelpyramidguardian, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelpyramidguardian, f);
        modelBipedMain = creepsmodelpyramidguardian;
        scaleAmount = creepsmodelpyramidguardian;
    }

    /**
     * sets the scale for the slime based on getSlimeSize in EntitySlime
     */
    protected void scaleSlime(CREEPSEntityPyramidGuardian creepsentitypyramidguardian, float f)
    {
        GL11.glScalef(0.55F, 0.55F, 0.75F);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        scaleSlime((CREEPSEntityPyramidGuardian)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityPyramidGuardian entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityPyramidGuardian) entity);
	}
}