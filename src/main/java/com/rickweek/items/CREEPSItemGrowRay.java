package com.rickweek.items;

import java.util.Random;

import com.rickweek.entities.CREEPSEntityGrow;
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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CREEPSItemGrowRay extends Item
{
    public static Random rand = new Random();

    public CREEPSItemGrowRay(String unlocalizedName, String registryName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
        setMaxDamage(64);
        this.setCreativeTab(MCW.items);
    }
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.GROWRAY, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        entityplayer.addChatMessage(new TextComponentString("\2476This Item is still WIP. Stay Tuned."));
        // Da sistemare
        
        	/*
            double d = -MathHelper.sin((entityplayer.rotationYaw * (float)Math.PI) / 180F);
            double d1 = MathHelper.cos((entityplayer.rotationYaw * (float)Math.PI) / 180F);
            double d2 = 0.0D;
            double d3 = 0.0D;
            double d4 = 0.012999999999999999D;
            double d5 = 4D;
            CREEPSEntityGrow creepsentitygrow = new CREEPSEntityGrow(world, entityplayer, 0.0F);

            itemstack.damageItem(1, entityplayer);
            world.spawnEntityInWorld(creepsentitygrow);
            */
        

        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
}