package com.rickweek.items;

import java.util.Random;

import com.rickweek.entities.CREEPSEntityShrink;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSItemShrinkRay extends Item
{
    public static Random rand = new Random();

    public CREEPSItemShrinkRay(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
        setMaxDamage(128);
        this.setCreativeTab(MCW.items);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_SHRINKRAY, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote)
        {
            double d = -MathHelper.sin((entityplayer.rotationYaw * (float)Math.PI) / 180F);
            double d1 = MathHelper.cos((entityplayer.rotationYaw * (float)Math.PI) / 180F);
            double d2 = 0.0D;
            double d3 = 0.0D;
            double d4 = 0.012999999999999999D;
            double d5 = 4D;
            CREEPSEntityShrink creepsentityshrink = new CREEPSEntityShrink(world, entityplayer, 0.0F);

            if (creepsentityshrink != null)
            {
                itemstack.damageItem(1, entityplayer);
                world.spawnEntityInWorld(creepsentityshrink);
            }
        }

        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
}