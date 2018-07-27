package com.rickweek.items;

import java.util.Random;

import com.rickweek.entities.CREEPSEntityEvilEgg;
import com.rickweek.entities.CREEPSEntityMoney;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CREEPSItemEvilEgg extends Item
{
    public static Random random = new Random();

    public CREEPSItemEvilEgg(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 44;
        this.setCreativeTab(MCW.items);
    }
    
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        itemstack.stackSize--;
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new CREEPSEntityEvilEgg(world, entityplayer));
        }

        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
}