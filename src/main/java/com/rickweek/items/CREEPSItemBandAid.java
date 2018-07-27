package com.rickweek.items;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

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

public class CREEPSItemBandAid extends Item {
	
	public CREEPSItemBandAid(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.maxStackSize = 24;
		this.setCreativeTab(MCW.items);
	}
	
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        
        if(entityplayer.getHealth() < 20.0F) {
        	if(entityplayer instanceof EntityLivingBase)
        		entityplayer.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1));
        	world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_BANDAID, SoundCategory.PLAYERS, 1.0F, 1.0F);
        	itemstack.stackSize--;
        }
        return new ActionResult(EnumActionResult.PASS, itemstack);
    }

}
