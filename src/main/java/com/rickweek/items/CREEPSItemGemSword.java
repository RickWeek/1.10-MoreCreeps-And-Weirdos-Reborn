package com.rickweek.items;

import java.util.Random;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CREEPSItemGemSword extends ItemSword
{
    public static Random random = new Random();

    public CREEPSItemGemSword(String unlocalizedName, String registryName) {
        super(ToolMaterial.DIAMOND);
        this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        setMaxDamage(256);
        this.setCreativeTab(MCW.items);
    }

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity entity)
    {
        return 25;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        return itemstack;
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base, (Quality+1)*2 if correct blocktype, 1.5F if
     * sword
     */
    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        return 55F;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1)
    {
        itemstack.damageItem(1, entityliving1);
        return true;
    }

    public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving)
    {
        itemstack.damageItem(2, entityliving);
        return true;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block block)
    {
        return true;
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
    {
    	EntityPlayer player = (EntityPlayer)entity;
        super.onUpdate(itemstack, world, entity, i, flag);

        if (flag)
        {
            if (player.isSwingInProgress)
            {
                // world.playSoundAtEntity(player, "morecreeps:gemsword", 0.6F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                world.playSound((EntityPlayer) null, player.getPosition(), MCSoundEvents.ITEM_GEMSWORD, SoundCategory.PLAYERS, 0.6F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            }

            MCW.proxy.smoke(world, player, random);
        }
    }
}