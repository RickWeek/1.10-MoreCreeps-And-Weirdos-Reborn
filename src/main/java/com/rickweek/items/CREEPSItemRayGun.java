package com.rickweek.items;

import java.util.Random;

import com.rickweek.entities.CREEPSEntityRay;
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

public class CREEPSItemRayGun extends Item
{
    public static Random rand = new Random();

    public CREEPSItemRayGun(String unlocalizedName, String registryName)
    {
        super();
        this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
        setMaxDamage(64);
        this.setCreativeTab(MCW.items);
    }
    
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
    	if (!world.isRemote)
        {
            CREEPSEntityRay creepsentityray = new CREEPSEntityRay(world, entityplayer);
            itemstack.damageItem(2, entityplayer);
            world.spawnEntityInWorld(creepsentityray);
        }

        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_RAYGUN, SoundCategory.PLAYERS, 1.0F, 1.0F);
        	
        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
}