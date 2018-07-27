package com.rickweek.mob.renders;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import com.rickweek.entities.CREEPSEntityEvilLight;
import com.rickweek.main.Reference;
import com.rickweek.mob.renders.layers.LayerEvilLightGlow;
import com.rickweek.models.CREEPSModelEvilLight;

public class CREEPSRenderEvilLight extends RenderLiving
{
    public CREEPSRenderEvilLight(RenderManager renderManager, CREEPSModelEvilLight creepsmodelevillight, float f)
    {
        super(Minecraft.getMinecraft().getRenderManager(), creepsmodelevillight, f);
        shadowSize = 0.0F;
        this.addLayer(new LayerEvilLightGlow(this));
    }
    protected int glow(CREEPSEntityEvilLight creepsentityevillight, int i, float f)
    {
        if (i != 0)
        {
            return -1;
        }
        if (i != 0)
        {
            return -1;
        }
        else
        {
            // bindTexture("mcw:textures/entity/evillightglow.png");
            bindTexture(new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_EVILLIGHTGLOW));
            float f1 = (1.0F - creepsentityevillight.getBrightness(1.0F)) * 0.5F;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
            return 1;
        }
    }
    protected int shouldRenderPass(EntityLiving entityliving, int i, float f)
    {
        return glow((CREEPSEntityEvilLight)entityliving, i, f);
    }

    protected ResourceLocation getEntityTexture(CREEPSEntityEvilLight entity)
    {
		return new ResourceLocation(entity.texture);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {

		return getEntityTexture((CREEPSEntityEvilLight) entity);
	}
}