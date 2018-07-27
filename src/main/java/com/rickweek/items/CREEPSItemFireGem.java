package com.rickweek.items;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import com.rickweek.entities.CREEPSEntityPreacher;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

public class CREEPSItemFireGem extends Item
{
    public static Random random = new Random();

    public CREEPSItemFireGem(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
        this.setCreativeTab(MCW.items);
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        // world.playSoundAtEntity(entityplayer, "morecreeps:firegem", 1.0F, 1.0F);
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_FIREGEM, SoundCategory.PLAYERS, 1.0F, 1.0F);
        itemstack.damageItem(1, entityplayer);
        // entityplayer.swingItem();
        List list = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(entityplayer.posX, entityplayer.posY, entityplayer.posZ, entityplayer.posX + 1.0D, entityplayer.posY + 1.0D, entityplayer.posZ + 1.0D).expand(10D, 10D, 10D));

        for (int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity)list.get(i);

            if (!(entity instanceof EntityLivingBase))
            {
                continue;
            }

            EntityLivingBase entityliving = (EntityLivingBase)entity;

            // TODO Add this entities
            if (/* (entityliving instanceof CREEPSEntityHotdog) || (entityliving instanceof CREEPSEntityHunchback) || */ (entityliving instanceof EntityPlayer) /* || (entityliving instanceof CREEPSEntityGuineaPig) */ || (entityliving instanceof CREEPSEntityPreacher))
            {
                continue;
            }

            if(world.isRemote)
            {
                for (int j = 0; j < 10; j++)
                {
                    double d = random.nextGaussian() * 0.02D;
                    double d1 = random.nextGaussian() * 0.02D;
                    double d2 = random.nextGaussian() * 0.02D;
                    world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entityliving.posX + (double)(random.nextFloat() * 1.5F), entityliving.posY + 0.5D + (double)(random.nextFloat() * 2.5F), entityliving.posZ + (double)(random.nextFloat() * 1.5F), d, d1, d2);
                }
            }

            entityliving.attackEntityFrom(DamageSource.inFire, 2F);
            entityliving.motionY += 0.5D;
            entityliving.setFire(15);
        }

        return new ActionResult(EnumActionResult.PASS, itemstack);
    }

    /**
     * Returns the maximum damage an item can take.
     */
    public int getMaxDamage()
    {
        return 64;
    }
}