package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityCastleGuard;
import com.rickweek.models.CREEPSModelCastleGuard;

public class CREEPSRenderCastleGuard extends RenderLiving
{
    protected CREEPSModelCastleGuard modelcastleguardmain;

    public CREEPSRenderCastleGuard(RenderManager renderManager, CREEPSModelCastleGuard creepsmodelcastleguard, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelcastleguard, f);
        modelcastleguardmain = creepsmodelcastleguard;
    }

    protected void fattenup(CREEPSEntityCastleGuard creepsentitycastleguard, float f)
    {
        GL11.glScalef(creepsentitycastleguard.modelsize, creepsentitycastleguard.modelsize, creepsentitycastleguard.modelsize);
    }
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        CREEPSEntityCastleGuard creepsentitycastleguard = (CREEPSEntityCastleGuard)entityliving;
        modelcastleguardmain.hammerswing = creepsentitycastleguard.hammerswing;
        fattenup((CREEPSEntityCastleGuard)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityCastleGuard entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityCastleGuard) entity);
	}
}