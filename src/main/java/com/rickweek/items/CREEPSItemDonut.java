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

public class CREEPSItemDonut extends Item {
	
	public CREEPSItemDonut(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.maxStackSize = 64;
		this.setCreativeTab(MCW.items);
	}
	
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        
        if(entityplayer.getHealth() < 20.0F) {
        	if(entityplayer instanceof EntityLivingBase)
        		entityplayer.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1));
        	world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_CHEW, SoundCategory.PLAYERS, 1.0F, 1.0F);
        	itemstack.stackSize--;
        }
        return new ActionResult(EnumActionResult.PASS, itemstack);
    }

}
