package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityEvilScientist;
import com.rickweek.models.CREEPSModelEvilScientist;

public class CREEPSRenderEvilScientist extends RenderLiving
{
    protected CREEPSModelEvilScientist modelBipedMain;

    public CREEPSRenderEvilScientist(RenderManager renderManager, CREEPSModelEvilScientist creepsmodelevilscientist, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelevilscientist, f);
        modelBipedMain = creepsmodelevilscientist;
    }

    protected void fattenup(CREEPSEntityEvilScientist creepsentityevilscientist, float f)
    {
        GL11.glScalef(creepsentityevilscientist.modelsize, creepsentityevilscientist.modelsize, creepsentityevilscientist.modelsize);
    }
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        fattenup((CREEPSEntityEvilScientist)entityliving, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityEvilScientist entity)
    {
		return (entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityEvilScientist) entity);
	}
}
