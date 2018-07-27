package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.rickweek.entities.CREEPSEntityTrophy;
import com.rickweek.models.CREEPSModelTrophy;

public class CREEPSRenderTrophy extends RenderLiving
{
    protected CREEPSModelTrophy modelBipedMain;

    public CREEPSRenderTrophy(RenderManager renderManager, CREEPSModelTrophy creepsmodeltrophy, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodeltrophy, f);
        modelBipedMain = creepsmodeltrophy;
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityTrophy entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityTrophy) entity);
	}
}