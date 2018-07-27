package com.rickweek.items;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CREEPSItemBattery extends Item {
    public CREEPSItemBattery(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        this.maxStackSize = 16;
        this.setCreativeTab(MCW.items);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        // world.playSound(entityplayer, "morecreeps:spark", 1.0F, 1.0F);
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_BATTERY, SoundCategory.PLAYERS, 1.0F, 1.0F);
        entityplayer.attackEntityFrom(DamageSource.inFire, 1.0F);
        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
}