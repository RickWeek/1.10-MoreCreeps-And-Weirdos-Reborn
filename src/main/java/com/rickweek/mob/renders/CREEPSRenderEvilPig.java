package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityEvilPig;
import com.rickweek.models.CREEPSModelEvilPig;

public class CREEPSRenderEvilPig extends RenderLiving
{
    protected CREEPSModelEvilPig modelBipedMain;

    public CREEPSRenderEvilPig(RenderManager renderManager, CREEPSModelEvilPig creepsmodelevilpig, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelevilpig, f);
        modelBipedMain = creepsmodelevilpig;
    }

    protected void fattenup(CREEPSEntityEvilPig creepsentityevilpig, float f)
    {
        GL11.glScalef(creepsentityevilpig.modelsize, creepsentityevilpig.modelsize, creepsentityevilpig.modelsize);
    }
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        fattenup((CREEPSEntityEvilPig)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityEvilPig entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityEvilPig) entity);
	}
}