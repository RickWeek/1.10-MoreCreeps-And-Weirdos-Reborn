package com.rickweek.items;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;
import com.rickweek.main.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CREEPSItemChickenWing extends ItemFood {

	private PotionEffect[] effects;
	
	public CREEPSItemChickenWing(String unlocalizedName, int amount, boolean isWolfFood) {
		super(amount, isWolfFood);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
	}
	
	public CREEPSItemChickenWing(String unlocalizedName, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
	}
	
	
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
        	worldIn.playSound((EntityPlayer) null, player.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
    		worldIn.playSound((EntityPlayer) null, player.getPosition(), MCSoundEvents.ITEM_CHICKENEAT, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }

}