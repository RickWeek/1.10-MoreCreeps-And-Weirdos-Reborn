package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityG;
import com.rickweek.models.CREEPSModelG;

public class CREEPSRenderG extends RenderLiving
{
    protected CREEPSModelG modelBipedMain;

    public CREEPSRenderG(RenderManager renderManager, CREEPSModelG creepsmodelg, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelg, f);
        modelBipedMain = creepsmodelg;
    }

    protected void fattenup(CREEPSEntityG creepsentityg, float f)
    {
        GL11.glScalef(creepsentityg.modelsize, creepsentityg.modelsize, creepsentityg.modelsize);
    }
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        fattenup((CREEPSEntityG)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityG entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityG) entity);
	}
}