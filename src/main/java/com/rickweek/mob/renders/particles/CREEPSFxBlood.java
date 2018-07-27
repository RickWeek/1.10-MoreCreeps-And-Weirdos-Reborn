package com.rickweek.mob.renders.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class CREEPSFxBlood extends Particle
{
    public CREEPSFxBlood(World world, double d, double d1, double d2, Item item, float f)
    {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
        this.particleTexture = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(item);
        setSize(0.7F, 0.7F);
        particleRed = 1.0F;
        particleBlue = 1.0F;
        particleGreen = 1.0F;
        particleGravity = 2.0F;
        particleScale *= f;
        particleMaxAge = 90;
    }

    public int getFXLayer()
    {
        return 1;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    public void ConsumeParticles(VertexBuffer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_)
    {
        float f6 = (float)this.particleTextureIndexX / 16.0F;
        float f7 = f6 + 0.01560938F;
        float f8 = (float)this.particleTextureIndexY / 16.0F;
        float f9 = f8 + 0.01560938F;
        float f10 = 0.1F * this.particleScale;
        super.renderParticle(p_180434_1_, p_180434_2_, p_180434_3_, p_180434_4_, p_180434_5_, p_180434_6_, p_180434_7_, p_180434_8_);
    
        if (this.particleTexture != null)
        {
            f6 = this.particleTexture.getMinU();
            f7 = this.particleTexture.getMaxU();
            f8 = this.particleTexture.getMinV();
            f9 = this.particleTexture.getMaxV();
        }

        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)p_180434_3_ - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)p_180434_3_ - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)p_180434_3_ - interpPosZ);
        p_180434_1_.color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
        p_180434_1_.putColorRGBA((int)(f11 - p_180434_4_ * f10 - p_180434_7_ * f10), (int)(f12 - p_180434_5_ * f10), (int)(f13 - p_180434_6_ * f10 - p_180434_8_ * f10), (int)f7, (int)f9);
        p_180434_1_.putColorRGBA((int)(f11 - p_180434_4_ * f10 + p_180434_7_ * f10), (int)(f12 + p_180434_5_ * f10), (int)(f13 - p_180434_6_ * f10 + p_180434_8_ * f10), (int)f7, (int)f8);
        p_180434_1_.putColorRGBA((int)(f11 + p_180434_4_ * f10 + p_180434_7_ * f10), (int)(f12 + p_180434_5_ * f10), (int)(f13 + p_180434_6_ * f10 + p_180434_8_ * f10), (int)f6, (int)f8);
        p_180434_1_.putColorRGBA((int)(f11 + p_180434_4_ * f10 - p_180434_7_ * f10), (int)(f12 - p_180434_5_ * f10), (int)(f13 + p_180434_6_ * f10 - p_180434_8_ * f10), (int)f6, (int)f9);
    }
}