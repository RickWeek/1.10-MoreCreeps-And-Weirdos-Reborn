package com.rickweek.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

public class CREEPSItemRam16K extends Item {
	
	public CREEPSItemRam16K(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.maxStackSize = 24;
		this.setCreativeTab(MCW.items);
	}
}