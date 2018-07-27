package com.rickweek.main.proxies;

import com.rickweek.entities.CREEPSEntityAtom;
import com.rickweek.entities.CREEPSEntityTrophy;
import com.rickweek.init.MCItems;
import com.rickweek.main.MCW;
import com.rickweek.main.utils.TickClientHandlerEvent;
import com.rickweek.mob.renders.particles.CREEPSFxAtoms;
import com.rickweek.mob.renders.particles.CREEPSFxBlood;
import com.rickweek.mob.renders.particles.CREEPSFxBubbles;
import com.rickweek.mob.renders.particles.CREEPSFxConfetti;
import com.rickweek.mob.renders.particles.CREEPSFxPee;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {
	
	public void render() {
		MinecraftForge.EVENT_BUS.register(new TickClientHandlerEvent());
		// FMLCommonHandler.instance().bus().register(new TickClientHandlerEvent());
	}
	
	@Override
	public void registerRenders() {
		MCItems.registerRenders();
	}
	
	public void renderModelItem()
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MCW.partBubble, 0, new ModelResourceLocation("mcw:bubble", "inventory"));
		// Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MCItems.partWhite, 0, new ModelResourceLocation("morecreeps:partWhite", "inventory"));
		// Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MCItems.partRed, 0, new ModelResourceLocation("morecreeps:partRed", "inventory"));
		// Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MCItems.partBlack, 0, new ModelResourceLocation("morecreeps:partBlack", "inventory"));
		// Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MCItems.partYellow, 0, new ModelResourceLocation("morecreeps:partYellow", "inventory"));
		// Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MCItems.partBlue, 0, new ModelResourceLocation("morecreeps:partBlue", "inventory"));
		// Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MCItems.partShrink, 0, new ModelResourceLocation("morecreeps:partShrink", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(MCW.partBarf, 0, new ModelResourceLocation("mcw:partBarf", "inventory"));
	}
	
	/*
	public void shrinkSmoke(World world, Entity entity)
	{
        for (int k = 0; k < 8; k++)
        {
            CREEPSFxSmoke creepsfxsmoke = new CREEPSFxSmoke(world, entity.posX, entity.posY, entity.posZ, MoreCreepsAndWeirdos.partShrink, 0.25F, 0);
            creepsfxsmoke.renderDistanceWeight = 30D;
            Minecraft.getMinecraft().effectRenderer.addEffect(creepsfxsmoke);
        }
	} */
	
	public void foam2(World world, CREEPSEntityAtom atom)
	{
        for (int i1 = 0; (float)i1 < atom.atomsize; i1++)
        {
            CREEPSFxAtoms creepsfxatoms = new CREEPSFxAtoms(world, atom.posX, atom.posY + (double)(int)(atom.atomsize / 3F), atom.posZ, Item.getItemById(atom.rand.nextInt(99) + 1), 0.3F);
            Minecraft.getMinecraft().effectRenderer.addEffect(creepsfxatoms);
        }
	}
	
	public void pee(World world, double posX, double posY, double posZ, float rotationYaw, float modelsize)
	{
        double d = -MathHelper.sin((rotationYaw * (float)Math.PI) / 180F);
        double d1 = MathHelper.cos((rotationYaw * (float)Math.PI) / 180F);

        for (int i = 0; i < 25; i++)
        {
            CREEPSFxPee creepsfxpee = new CREEPSFxPee(world, posX + d * 0.20000000000000001D, (posY + 0.75D) - (double)((1.0F - modelsize) * 0.8F), posZ + d1 * 0.20000000000000001D, Item.getItemFromBlock(Blocks.COBBLESTONE));
            creepsfxpee.interpPosX += d * 0.23999999463558197D;
            creepsfxpee.interpPosZ += d1 * 0.23999999463558197D;
            Minecraft.getMinecraft().effectRenderer.addEffect(creepsfxpee);
        }
	}
	
	public void confettiB(World world, CREEPSEntityTrophy trophy)
	{
		if(world.isRemote)
		{
	        for (int i = 1; i < 10; i++)
	        {
	            for (int j = 0; j < 10; j++)
	            {
	                CREEPSFxConfetti creepsfxconfetti = new CREEPSFxConfetti(world, trophy.posX + (double)(world.rand.nextFloat() * 8F - world.rand.nextFloat() * 8F), trophy.posY + (double)world.rand.nextInt(4) + 4D, trophy.posZ + (double)(world.rand.nextFloat() * 8F - world.rand.nextFloat() * 8F), Items.DIAMOND_CHESTPLATE);
	                Minecraft.getMinecraft().effectRenderer.addEffect(creepsfxconfetti);
	            }
	        }
		}
	}
	
	public void bubble(World world, EntityLivingBase entity)
	{
        double d = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
        double d1 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);
        CREEPSFxBubbles creepsfxbubbles = new CREEPSFxBubbles(world, entity.posX + d * 0.5D, entity.posY + 0.75D, entity.posZ + d1 * 0.5D, MCW.partBubble, 0.7F);
        Minecraft.getMinecraft().effectRenderer.addEffect(creepsfxbubbles);
	}
	
	public void barf(World world, EntityPlayer player)
	{
        double d = -MathHelper.sin((player.rotationYaw * (float)Math.PI) / 180F);
        double d1 = MathHelper.cos((player.rotationYaw * (float)Math.PI) / 180F);
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 50; j++)
            {
                CREEPSFxBlood creepsfxblood = new CREEPSFxBlood(world, player.posX, player.posY + 0.60000000298023224D, player.posZ, MCW.partBarf, 0.85F);
                creepsfxblood.interpPosX += d * 0.25D;
                creepsfxblood.interpPosZ += d1 * 0.25D;
                Minecraft.getMinecraft().effectRenderer.addEffect(creepsfxblood);
            }
        }
	} 
	
	public void addChatMessage(String s) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new TextComponentString(s));
		// Minecraft.getMinecraft().thePlayer.sendChatMessage(s);
		return;
	  }
}
