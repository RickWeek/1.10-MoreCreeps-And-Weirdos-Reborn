package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.rickweek.entities.CREEPSEntityPreacher;
import com.rickweek.models.CREEPSModelPreacher;

public class CREEPSRenderPreacher extends RenderLiving
{
    protected CREEPSModelPreacher modelBipedMain;

    public CREEPSRenderPreacher(RenderManager renderManager, CREEPSModelPreacher creepsmodelpreacher, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelpreacher, f);
        modelBipedMain = creepsmodelpreacher;
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityPreacher entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityPreacher) entity);
	}

}