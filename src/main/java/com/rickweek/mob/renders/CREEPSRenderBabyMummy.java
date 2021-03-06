package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityMummy;
import com.rickweek.models.CREEPSModelMummy;

public class CREEPSRenderBabyMummy extends RenderLiving<CREEPSEntityMummy> {
	
	private static final ResourceLocation MUMMY_TEXTURE = new ResourceLocation("mcw", "textures/entity/mummy.png");
	
	public CREEPSRenderBabyMummy(RenderManager renderManagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
	    super(renderManagerIn, modelbaseIn, shadowsizeIn);
	  }
    

	protected ResourceLocation getEntityTexture(CREEPSEntityMummy entity) {
		return MUMMY_TEXTURE;
	}
}
