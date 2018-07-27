package com.rickweek.items;

import com.rickweek.main.MCW;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CREEPSItemGooDonut extends Item
{
    public CREEPSItemGooDonut(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 16;
        this.setCreativeTab(MCW.items);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        // entityplayer.swingItem();

        if (itemstack.stackSize < 1)
        {
            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
            itemstack.stackSize = 0;
        }

        // world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        // TODO
        /* 
        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new CREEPSEntityGooDonut(world, entityplayer));
        } */

        return itemstack;
    }
}	