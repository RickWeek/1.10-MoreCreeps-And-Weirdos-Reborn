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
import net.minecraft.world.World;

public class CREEPSItemExtinguisher extends Item
{
    public static Random rand = new Random();

    public CREEPSItemExtinguisher(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
        setMaxDamage(1024);
        this.setCreativeTab(MCW.items);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        // world.playSoundAtEntity(entityplayer, "morecreeps:extinguisher", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_EXTINGUISHER, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        /* TODO
        if (!world.isRemote)
        {
            CREEPSEntityFoam creepsentityfoam = new CREEPSEntityFoam(world, entityplayer, 0.0F);

            if (creepsentityfoam != null)
            {
                itemstack.damageItem(1, entityplayer);
                world.spawnEntityInWorld(creepsentityfoam);
            }
        }

        if(world.isRemote)
        {
        	MoreCreepsAndWeirdos.proxy.foam(world, entityplayer);
        } */
        
        return new ActionResult(EnumActionResult.PASS, itemstack);
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }
}