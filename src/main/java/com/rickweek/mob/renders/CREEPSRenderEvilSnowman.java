package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityEvilSnowman;
import com.rickweek.models.CREEPSModelEvilSnowman;

public class CREEPSRenderEvilSnowman extends RenderLiving
{
    public float snowsize;
    protected CREEPSModelEvilSnowman modelBipedMain;

    public CREEPSRenderEvilSnowman(RenderManager renderManager, CREEPSModelEvilSnowman creepsmodelevilsnowman, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelevilsnowman, f);
        modelBipedMain = creepsmodelevilsnowman;
    }

    protected void preRenderScale(CREEPSEntityEvilSnowman creepsentityevilsnowman, float f)
    {
        shadowSize = creepsentityevilsnowman.snowsize * 0.5F;
        GL11.glScalef(creepsentityevilsnowman.snowsize, creepsentityevilsnowman.snowsize, creepsentityevilsnowman.snowsize);
    }
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        preRenderScale((CREEPSEntityEvilSnowman)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityEvilSnowman entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityEvilSnowman) entity);
	}
}