package com.rickweek.mob.renders.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class CREEPSFxBubbles extends Particle
{
    public int getFXLayer()
    {
        return 2;
    }

    public CREEPSFxBubbles(World world, double d, double d1, double d2, Item item, float f)
    {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
        this.particleTexture = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(item);
        setSize(0.5F, 0.5F);
        particleRed = 1.0F;
        particleBlue = 1.0F;
        particleGreen = 1.0F;
        particleGravity = -0.15F;
        particleScale *= f;
    }
}