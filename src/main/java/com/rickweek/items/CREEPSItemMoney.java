package com.rickweek.items;

import java.util.Random;

import com.rickweek.entities.CREEPSEntityMoney;
import com.rickweek.init.MCItems;
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

public class CREEPSItemMoney extends Item
{
    public static Random rand = new Random();

    public CREEPSItemMoney(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 50;
        this.setCreativeTab(MCW.items);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        checkAchievements(world, entityplayer);
        itemstack.stackSize--;
        // world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new CREEPSEntityMoney(world, entityplayer));
        }

        return new ActionResult(EnumActionResult.PASS, itemstack);
    }

    public void checkAchievements(World world, EntityPlayer entityplayer)
    {
        Object obj = null;
        ItemStack aitemstack[] = entityplayer.inventory.mainInventory;
        int i = 0;

        for (int j = 0; j < aitemstack.length; j++)
        {
            ItemStack itemstack = aitemstack[j];

            if (itemstack != null && itemstack.getItem() == MCItems.Money)
            {
                i += itemstack.stackSize;
            }
        }

        boolean flag = false;

        // TODO
        /* 
        if (i > 99)
        {
            flag = true;
            MoreCreepsAndWeirdos.proxy.confettiA(entityplayer, world);
            entityplayer.addStat(MoreCreepsAndWeirdos.achieve100bucks, 1);
        }

        if (i > 499)
        {
            flag = true;
            MoreCreepsAndWeirdos.proxy.confettiA(entityplayer, world);
            entityplayer.addStat(MoreCreepsAndWeirdos.achieve500bucks, 1);
        }

        if (i > 999)
        {
            flag = true;
            MoreCreepsAndWeirdos.proxy.confettiA(entityplayer, world);
            entityplayer.addStat(MoreCreepsAndWeirdos.achieve1000bucks, 1);
        }

        if (flag)
        {
            world.playSoundAtEntity(entityplayer, "morecreeps:achievement", 1.0F, 1.0F);
        }
        */
    }
}