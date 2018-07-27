package com.rickweek.items;

import java.util.List;
import java.util.Random;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class CREEPSItemGuineaPigRadio extends Item
{
    public boolean pickup;
    public static Random rand = new Random();

    public CREEPSItemGuineaPigRadio(String unlocalizedName, String registryName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
        pickup = false;
        this.setCreativeTab(MCW.items);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
    	/* TODO
        if (entityplayer.isSneaking())
        {
            return itemstack;
        }

        if (entityplayer.getRidingEntity() == null)
        {
            if (pickup)
            {
                pickup = false;
                // world.playSoundAtEntity(entityplayer, "morecreeps:ggpigunmount", 1.0F, 1.0F);
                world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ENTITY_GGPIG_UNMOUNT, SoundCategory.PLAYERS, 1.0F, 1.0F);

                for (int i = 0; i < 21; i++)
                {
                    Object obj = entityplayer;
                    int k;

                    for (k = 0; ((Entity)obj).isBeingRidden() != false && k < 20; k++)
                    {
                        obj = ((Entity)obj).isBeingRidden();
                    }

                    if (k < 20)
                    {
                        ((Entity)obj).fallDistance = -25F;
                        ((Entity)obj).dismountRidingEntity();
                    }
                }
            }
            else
            {
                pickup = true;
                // world.playSoundAtEntity(entityplayer, "morecreeps:ggpigradio", 1.0F, 1.0F);
                world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_GGPIGRADIO, SoundCategory.PLAYERS, 1.0F, 1.0F);
                List list = world.getEntitiesWithinAABB(fr.elias.morecreeps.common.entity.CREEPSEntityGuineaPig.class, new AxisAlignedBB(entityplayer.posX, entityplayer.posY, entityplayer.posZ, entityplayer.posX + 1.0D, entityplayer.posY + 1.0D, entityplayer.posZ + 1.0D).expand(150D, 150D, 150D));

                for (int j = 0; j < list.size(); j++)
                {
                    Entity entity = (Entity)list.get(j);

                    if ((entity instanceof CREEPSEntityGuineaPig) && ((CREEPSEntityGuineaPig)entity).wanderstate == 0 && ((CREEPSEntityGuineaPig)entity).tamed)
                    {
                        Object obj1 = entityplayer;

                        if (entity.getRidingEntity() != obj1 && entity.getRidingEntity() == null)
                        {
                            int l;

                            for (l = 0; ((Entity)obj1).isBeingRidden() != false && l < 20; l++)
                            {
                                obj1 = ((Entity)obj1).isBeingRidden();
                            }

                            if (l < 20)
                            {
                                entity.rotationYaw = ((Entity)obj1).rotationYaw;
                                entity.startRiding((Entity)obj1);
                                // world.playSoundAtEntity(entityplayer, "morecreeps:ggpigmount", 1.0F, 1.0F);
                                world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ENTITY_GGPIG_MOUNT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                            }
                        }
                    }
                }
            }
        }
        else
        {
            MCW.proxy.addChatMessage("Get off that creature before using the Guinea Pig Radio");
        } 
        */

        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
}