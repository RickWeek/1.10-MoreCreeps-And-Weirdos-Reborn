package com.rickweek.main.proxies;

import java.util.Random;

import com.rickweek.entities.CREEPSEntityAtom;
import com.rickweek.entities.CREEPSEntityBubbleScum;
import com.rickweek.entities.CREEPSEntityTrophy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CommonProxy {
	
	public void render(){}
	
	public void registerRenders() {
		
	}
	
	public void shrinkSmoke(World world, Entity entity){}

	public void robotTedSmoke(World worldObj, double posX, double posY,
			double posZ, int floattimer, double modelspeed) {		
	}

	public void addChatMessage(String s) {		
	}
	
	public void renderModelItem(){}
	public void foam(World world, EntityPlayer player){}
	public void foam2(World world, CREEPSEntityAtom atom){}
	
	public void dirt(World world, EntityPlayer entityplayer, Random random,
			int l, int i1, int k) {
		// TODO Auto-generated method stub
		
	}
	
	public void confettiA(EntityLivingBase player, World world)
	{
		if(!world.isRemote)
		{
	        double d = -MathHelper.sin((player.rotationYaw * (float)Math.PI) / 180F);
	        double d1 = MathHelper.cos((player.rotationYaw * (float)Math.PI) / 180F);
	        CREEPSEntityTrophy creepsentitytrophy = new CREEPSEntityTrophy(world);
	        creepsentitytrophy.setLocationAndAngles(player.posX + d * 3D, player.posY - 2D, player.posZ + d1 * 3D, player.rotationYaw, 0.0F);
	        world.spawnEntityInWorld(creepsentitytrophy);
		}
	}
	public void confettiB(World world, CREEPSEntityTrophy trophy){} // for the confetti particles
	public void pee(World world, double posX, double posY, double posZ, float rotationYaw, float modelsize){}
	
	public void smoke(World world, EntityPlayer player, Random random) {
		// TODO Auto-generated method stub
		
	}

	public void barf(World world, EntityPlayer entityplayer) {		
	}

	public void bubble(World worldObj, CREEPSEntityBubbleScum creepsEntityBubbleScum) {
		// TODO Auto-generated method stub
		
	}

}
