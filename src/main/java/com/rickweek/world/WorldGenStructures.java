package com.rickweek.world;

import java.util.Random;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenStructures implements IWorldGenerator {

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		generateSurface(world, world.rand, chunkX, chunkZ);
	}
    public void generateSurface(World world, Random random, int i, int j)
    {

            if (random.nextInt(30) == 0)
            {
                int k = i + random.nextInt(16) + 16;
                byte byte0 = 65;
                int j1 = j + random.nextInt(16) + 16;
                new CREEPSWorldGenPyramid().generate(world, random, k, byte0, j1);

                if ((new CREEPSWorldGenPyramid()).generate(world, random, k, byte0, j1))
                {
                	// MCW.proxy.playSound((EntityPlayer) null, EntityPlayer.getPosition(), MCSoundEvents.EVENT_PYRAMIDDISCOVER, SoundCategory.AMBIENT, 0.95F, 1.0F);
                }
            }
    }
}