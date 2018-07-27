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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CREEPSItemDiodes extends Item {
    public CREEPSItemDiodes(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        this.maxStackSize = 36;
        this.setCreativeTab(MCW.items);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {

        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_DIODES, SoundCategory.PLAYERS, 1.0F, 1.0F);

        for (int i = 0; i < world.rand.nextInt(15) + 5; i++)
        {
            world.spawnParticle(EnumParticleTypes.REDSTONE, (float)entityplayer.posX + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.412F, ((float)entityplayer.posY + entityplayer.getEyeHeight()) - 0.2F, (float)entityplayer.posZ + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.412F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.112F, 0.0002455D, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.111F);
        }
        
        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
}