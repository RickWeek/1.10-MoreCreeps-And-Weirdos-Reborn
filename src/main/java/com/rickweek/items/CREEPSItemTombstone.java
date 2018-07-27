package com.rickweek.items;

import java.util.Random;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CREEPSItemTombstone extends Item
{
    public int zPosition;
    public static Random random = new Random();

    public CREEPSItemTombstone(String unlocalizedName, String registryName) {
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
        entityplayer.swingArm(hand);

        if (itemstack.stackSize < 1)
        {
            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
            itemstack.stackSize = 0;
        }

        // world.playSoundAtEntity(entityplayer, "morecreeps:evileggcluck", 1.0F, 1.0F);
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.EVILEGGCLUCK, SoundCategory.PLAYERS, 1.0F, 1.0F);

        if (world.isRemote);

        return new ActionResult(EnumActionResult.PASS, itemstack);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setInteger("TileZ", zPosition);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        zPosition = nbttagcompound.getInteger("TileZ");
    }
}