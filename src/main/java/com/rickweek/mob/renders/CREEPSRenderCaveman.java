package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityCaveman;
import com.rickweek.models.CREEPSModelCaveman;
import com.rickweek.models.CREEPSModelMummy;

public class CREEPSRenderCaveman extends RenderLiving
{
	CREEPSEntityCaveman ecaveman;
    protected CREEPSModelCaveman modelcavemanmain;

    public CREEPSRenderCaveman(RenderManager renderManager,CREEPSModelCaveman creepsmodelcaveman, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelcaveman, f);
        modelcavemanmain = creepsmodelcaveman;
    }


	protected void fattenup(CREEPSEntityCaveman creepsentitycaveman, float f)
    {
        GL11.glScalef(creepsentitycaveman.modelsize + creepsentitycaveman.fat, creepsentitycaveman.modelsize, creepsentitycaveman.modelsize);
    }
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        CREEPSEntityCaveman creepsentitycaveman = (CREEPSEntityCaveman)entityliving;
        modelcavemanmain.hammerswing = creepsentitycaveman.hammerswing;
        modelcavemanmain.frozen = creepsentitycaveman.frozen;
        modelcavemanmain.cavegirl = creepsentitycaveman.cavegirl;
        modelcavemanmain.evil = creepsentitycaveman.evil;
        fattenup((CREEPSEntityCaveman)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityCaveman entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityCaveman) entity);
	}
}