package com.rickweek.items;

import java.util.Random;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CREEPSItemEarthGem extends Item
{
    public static Random random = new Random();

    public CREEPSItemEarthGem(String unlocalizedName, String registryName) {
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
        // world.playSoundAtEntity(entityplayer, "morecreeps:earthgem", 1.0F, 1.0F);
        world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_EARTHGEM, SoundCategory.PLAYERS, 1.0F, 1.0F);
        itemstack.damageItem(1, entityplayer);
        // entityplayer.swingItem();

        for (int k = -2; k < 4; k++)
        {
            for (int l = -3; l < 3; l++)
            {
                for (int i1 = -3; i1 < 3; i1++)
                {
                    Block i = world.getBlockState(new BlockPos((int)(entityplayer.posX + (double)l), (int)((entityplayer.posY - 2D) + (double)k), (int)(entityplayer.posZ + (double)i1))).getBlock();
                    Block j = world.getBlockState(new BlockPos((int)(entityplayer.posX + (double)l), (int)((entityplayer.posY - 1.0D) + (double)k), (int)(entityplayer.posZ + (double)i1))).getBlock();

                    if ((i == Blocks.DIRT || i == Blocks.GRASS) && random.nextInt(5) == 0 && j == Blocks.AIR)
                    {
                    	MCW.proxy.dirt(world, entityplayer, random, l, i1, k);

                        world.setBlockState(new BlockPos((int)(entityplayer.posX + (double)l), (int)((entityplayer.posY - 2D) + (double)k), (int)(entityplayer.posZ + (double)i1)), Blocks.FARMLAND.getDefaultState());
                        world.setBlockState(new BlockPos((int)(entityplayer.posX + (double)l), (int)((entityplayer.posY - 1.0D) + (double)k), (int)(entityplayer.posZ + (double)i1)), Block.getStateById(59));
                    }

                    /*int k1 = world.getBlockMetadata((int)(entityplayer.posX + (double)l), (int)((entityplayer.posY - 1.0D) + (double)k), (int)(entityplayer.posZ + (double)i1));
                    if (k1 < 7 && (i == 59 || i == 60))
                    {
                        world.setBlockMetadataWithNotify((int)(entityplayer.posX + (double)l), (int)((entityplayer.posY - 1.0D) + (double)k), (int)(entityplayer.posZ + (double)i1), k1 + 1);
                    }*/
                }
            }
        }

        return new ActionResult(EnumActionResult.PASS, itemstack);
    }

    /**
     * Returns the maximum damage an item can take.
     */
    public int getMaxDamage()
    {
        return 32;
    }
}