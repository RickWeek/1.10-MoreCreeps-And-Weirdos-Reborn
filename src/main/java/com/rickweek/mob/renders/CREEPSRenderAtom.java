package com.rickweek.mob.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.rickweek.entities.CREEPSEntityAtom;
import com.rickweek.main.Reference;
import com.rickweek.mob.renders.layers.LayerAtom;
import com.rickweek.models.CREEPSModelAtom;

public class CREEPSRenderAtom extends RenderLiving
{
    private ModelBase scaleAmount;
    protected CREEPSModelAtom modelBipedMain;

    public CREEPSRenderAtom(RenderManager renderManager, CREEPSModelAtom creepsmodelatom, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelatom, f);
        modelBipedMain = creepsmodelatom;
        scaleAmount = creepsmodelatom;
        // this.addLayer(new LayerAtom(this));
    }

    /**
     * sets the scale for the slime based on getSlimeSize in EntitySlime
     */
    protected void scaleSlime(CREEPSEntityAtom creepsentityatom, float f)
    {
        GL11.glScalef(creepsentityatom.atomsize * 0.3F, creepsentityatom.atomsize * 0.3F, creepsentityatom.atomsize * 0.3F);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
        scaleSlime((CREEPSEntityAtom)entityliving, f);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_ATOM);
	}
}