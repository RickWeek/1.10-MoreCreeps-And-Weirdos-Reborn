package com.rickweek.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.rickweek.entities.CREEPSEntityAtom;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

public class CREEPSItemAtom extends Item
{
    public CREEPSItemAtom(String unlocalizedName, String registryName) {
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
        // entityplayer.swingItem();

        if (itemstack.stackSize < 1)
        {
            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
            itemstack.stackSize = 0;
        }

        // world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), SoundEvents.ENTITY_SKELETON_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F);

        if (!world.isRemote)
        {
            CREEPSEntityAtom creepsentityatom = new CREEPSEntityAtom(world);
            creepsentityatom.setLocationAndAngles(entityplayer.posX + 3D, entityplayer.posY, entityplayer.posZ + 3D, entityplayer.rotationYaw, 0.0F);
            double d = -MathHelper.sin((entityplayer.rotationYaw * (float)Math.PI) / 180F);
            double d1 = MathHelper.cos((entityplayer.rotationYaw * (float)Math.PI) / 180F);
            creepsentityatom.motionX = 1.7D * d * (double)MathHelper.cos((entityplayer.rotationPitch / 180F) * (float)Math.PI);
            creepsentityatom.motionY = -1.8D * (double)MathHelper.sin((entityplayer.rotationPitch / 180F) * (float)Math.PI);
            creepsentityatom.motionZ = 1.7D * d1 * (double)MathHelper.cos((entityplayer.rotationPitch / 180F) * (float)Math.PI);
            creepsentityatom.setPosition(entityplayer.posX + d * 0.80000000000000004D, entityplayer.posY, entityplayer.posZ + d1 * 0.80000000000000004D);
            creepsentityatom.prevPosX = creepsentityatom.posX;
            creepsentityatom.prevPosY = creepsentityatom.posY;
            creepsentityatom.prevPosZ = creepsentityatom.posZ;
            world.spawnEntityInWorld(creepsentityatom);
        }

        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
}