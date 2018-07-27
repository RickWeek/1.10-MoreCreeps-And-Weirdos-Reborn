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

public class CREEPSItemArmyGem extends Item
{
    public static Random random = new Random();

    public CREEPSItemArmyGem(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
        setMaxDamage(16);
        this.setCreativeTab(MCW.items);
    }


    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
        // entityplayer.swingItem();
    	entityplayer.swingArm(hand);
        // world.playSoundAtEntity(entityplayer, "morecreeps:armygem", 1.0F, 1.0F);
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_ARMYGEM, SoundCategory.PLAYERS, 1.0F, 1.0F);
        double d = -MathHelper.sin((entityplayer.rotationYaw * (float)Math.PI) / 180F);
        double d1 = MathHelper.cos((entityplayer.rotationYaw * (float)Math.PI) / 180F);
        /* 
        CREEPSEntityArmyGuy creepsentityarmyguy = new CREEPSEntityArmyGuy(world);
        creepsentityarmyguy.setLocationAndAngles(entityplayer.posX + d * 1.0D, entityplayer.posY + 1.0D, entityplayer.posZ + d1 * 1.0D, entityplayer.rotationYaw, 0.0F);
        if(!world.isRemote)
        {
            creepsentityarmyguy.loyal = true;
            System.out.println("[ITEM] ArmyGem is Loyal: " + creepsentityarmyguy.loyal);
            world.spawnEntityInWorld(creepsentityarmyguy);
        } 
        
        itemstack.damageItem(2, entityplayer);
        */
        return new ActionResult(EnumActionResult.PASS, itemstack);
    } 
}