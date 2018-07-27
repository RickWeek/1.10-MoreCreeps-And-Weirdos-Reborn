package com.rickweek.items;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CREEPSItemPopsicle extends Item
{
    private int healAmount;

    public CREEPSItemPopsicle(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        // healAmount = 4;
        maxStackSize = 16;
        this.setCreativeTab(MCW.items);
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.EAT;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        // entityplayer.swingItem();
        // world.playSoundAtEntity(entityplayer, "morecreeps:lick", 1.0F, 1.0F);
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_LICK, SoundCategory.PLAYERS, 1.0F, 1.0F);
        itemstack.stackSize--;
        // entityplayer.heal(healAmount);
        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
}