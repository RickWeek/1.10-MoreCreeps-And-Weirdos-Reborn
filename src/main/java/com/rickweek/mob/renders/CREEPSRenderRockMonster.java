package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityRockMonster;
import com.rickweek.models.CREEPSModelRockMonster;

public class CREEPSRenderRockMonster extends RenderLiving
{
    protected CREEPSModelRockMonster modelBipedMain;

    public CREEPSRenderRockMonster(RenderManager renderManager, CREEPSModelRockMonster creepsmodelrockmonster, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelrockmonster, f);
        modelBipedMain = creepsmodelrockmonster;
    }

    /**
     * sets the scale for the slime based on getSlimeSize in EntitySlime
     */
    protected void scaleSlime(CREEPSEntityRockMonster creepsentityrockmonster, float f)
    {
        GL11.glScalef(creepsentityrockmonster.modelsize, creepsentityrockmonster.modelsize, creepsentityrockmonster.modelsize);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        scaleSlime((CREEPSEntityRockMonster)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityRockMonster entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityRockMonster) entity);
	}
}