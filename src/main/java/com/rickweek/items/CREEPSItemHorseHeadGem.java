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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSItemHorseHeadGem extends Item
{
    public static Random random = new Random();

    public CREEPSItemHorseHeadGem(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 16;
        this.setCreativeTab(MCW.items);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        itemstack.stackSize--;
        entityplayer.swingArm(hand);

        if (itemstack.stackSize < 1)
        {
            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
            itemstack.stackSize = 0;
        }

        // world.playSoundAtEntity(entityplayer, "morecreeps:horseheadgem", 1.0F, 1.0F);
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_HORSEHEADGEM, SoundCategory.PLAYERS, 1.0F, 1.0F);

        /* TODO
        if (!world.isRemote)
        {
            double d = -MathHelper.sin((entityplayer.rotationYaw * (float)Math.PI) / 180F);
            double d1 = MathHelper.cos((entityplayer.rotationYaw * (float)Math.PI) / 180F);
            CREEPSEntityHorseHead creepsentityhorsehead = new CREEPSEntityHorseHead(world);
            creepsentityhorsehead.setLocationAndAngles(entityplayer.posX + d * 1.0D, entityplayer.posY + 1.0D, entityplayer.posZ + d1 * 1.0D, entityplayer.rotationYaw, 0.0F);
            world.spawnEntityInWorld(creepsentityhorsehead);
        }
		*/
        
        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
}