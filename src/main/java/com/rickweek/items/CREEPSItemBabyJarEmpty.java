package com.rickweek.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import com.rickweek.entities.CREEPSEntityBigBaby;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

public class CREEPSItemBabyJarEmpty extends Item
{
    private int healAmount;
    private boolean messagegiven;
    public int messagedelay = 0;

    public CREEPSItemBabyJarEmpty(String unlocalizedName, String registryName) {
        super();
        this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        healAmount = 10;
        maxStackSize = 1;
        messagegiven = false;
        this.setCreativeTab(MCW.items);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
    {
    	if(messagedelay < 0 ) {
	        if (!messagegiven) {
	        	messagedelay = 3;
	            boolean flag = false;
	            List list = world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.getEntityBoundingBox().expand(8D, 8D, 8D));
	
	            for (int i = 0; i < list.size(); i++) {
	                Entity entity = (Entity)list.get(i);
	
	                if (entity instanceof CREEPSEntityBigBaby) {
	                    flag = true;
	                }
	            }
	
	            if (!flag) {
	                world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_EMPTYJAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
	                messagegiven = true;
	            }
	        }
    	}
        return new ActionResult(EnumActionResult.PASS, itemstack);
    }
    
}