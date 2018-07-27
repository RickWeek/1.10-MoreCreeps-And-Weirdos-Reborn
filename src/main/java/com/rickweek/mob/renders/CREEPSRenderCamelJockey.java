package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityCamelJockey;
import com.rickweek.models.CREEPSModelCamelJockey;

public class CREEPSRenderCamelJockey extends RenderLiving
{
    protected CREEPSModelCamelJockey modelBipedMain;

    public CREEPSRenderCamelJockey(RenderManager renderManager, CREEPSModelCamelJockey creepsmodelcameljockey, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelcameljockey, f);
        modelBipedMain = creepsmodelcameljockey;
    }

    protected void fattenup(CREEPSEntityCamelJockey creepsentitycameljockey, float f)
    {
        GL11.glScalef(creepsentitycameljockey.modelsize, creepsentitycameljockey.modelsize, creepsentitycameljockey.modelsize);
    }
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        fattenup((CREEPSEntityCamelJockey)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityCamelJockey entity) {
		
		return new ResourceLocation(entity.texture);
	}
    
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		return getEntityTexture((CREEPSEntityCamelJockey) entity);
	}
}