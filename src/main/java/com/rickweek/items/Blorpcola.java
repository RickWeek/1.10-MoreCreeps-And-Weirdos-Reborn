package com.rickweek.items;

import com.rickweek.creativetab.CREEPSCreativeTab;
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

public class Blorpcola extends Item {
	
	public Blorpcola(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.maxStackSize = 24;
		this.setCreativeTab(MCW.items);
	}
	
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        
        if(entityplayer.getHealth() < 20.0F) {
        	if(entityplayer instanceof EntityLivingBase)
        		entityplayer.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1));
        	world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_BLORPCOLA, SoundCategory.PLAYERS, 1.0F, 1.0F);
        	itemstack.stackSize--;
        }

        // TODO
        /* if (MoreCreepsAndWeirdos.colacount++ >= 10)
        {
            world.playSoundAtEntity(entityplayer, "morecreeps:achievement", 1.0F, 1.0F);
            entityplayer.addStat(MoreCreepsAndWeirdos.achievechugcola, 1);
            MoreCree psAndWeirdos.proxy.confettiA(entityplayer, world);
        } */
        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
}
