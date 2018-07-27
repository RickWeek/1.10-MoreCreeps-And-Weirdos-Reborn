package com.rickweek.items;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CREEPSItemBoomBox extends Item {
	
	
    public CREEPSItemBoomBox(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        this.maxStackSize = 1;
        this.setCreativeTab(MCW.items);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
 
    	entityplayer.swingArm(hand);
    	world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_BOOMBOX, SoundCategory.PLAYERS, 1.0F, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F + 1.0F);

        
        /* TODO interazione con il mob DiscoMole
        for (int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);

            if (entity instanceof CREEPSEntityDiscoMole)
            {
                EntityLiving entityliving = (EntityLiving)entity;
                entityliving.health -= rand.nextInt(5) + 1;
                entityliving.motionY += 0.014999999664723873D;
                entityliving.setFire(3);
            }
        }
        */
        return new ActionResult(EnumActionResult.PASS, itemstack);
    }

    
}