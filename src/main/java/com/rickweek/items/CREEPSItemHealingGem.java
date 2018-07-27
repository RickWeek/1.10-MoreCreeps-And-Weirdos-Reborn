package com.rickweek.items;

import java.util.Random;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSItemHealingGem extends Item
{
    public static Random rand = new Random();
    private int healAmount;

    public CREEPSItemHealingGem(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        healAmount = 5;
        maxStackSize = 1;
        this.setCreativeTab(MCW.items);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        if (entityplayer.getHealth() < 20)
        {
            world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_HEALINGGEM, SoundCategory.PLAYERS, 1.0F, 1.0F);
            itemstack.damageItem(1, entityplayer);
            // entityplayer.swingItem();

            for (int i = 0; i < 20; i++)
            {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                double d3 = -MathHelper.sin((entityplayer.rotationYaw * (float)Math.PI) / 180F);
                double d4 = MathHelper.cos((entityplayer.rotationYaw * (float)Math.PI) / 180F);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (entityplayer.posX + rand.nextGaussian() * 0.5D) - rand.nextGaussian() * 0.5D, ((entityplayer.posY - 0.5D) + rand.nextGaussian() * 0.5D) - rand.nextGaussian() * 0.5D, (entityplayer.posZ + rand.nextGaussian() * 0.5D) - rand.nextGaussian() * 0.5D, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.HEART, (entityplayer.posX + rand.nextGaussian() * 0.5D) - rand.nextGaussian() * 0.5D, ((entityplayer.posY - 0.5D) + rand.nextGaussian() * 0.5D) - rand.nextGaussian() * 0.5D, (entityplayer.posZ + rand.nextGaussian() * 0.5D) - rand.nextGaussian() * 0.5D, d, d1, d2);
            }

            entityplayer.heal(healAmount);
        }

        return new ActionResult(EnumActionResult.PASS, itemstack);
    }

    /**
     * Returns the maximum damage an item can take.
     */
    public int getMaxDamage()
    {
        return 16;
    }
}