package com.rickweek.creativetab;

import com.rickweek.init.MCItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CREEPSCreativeTab extends CreativeTabs {

	/**
	 * Just says the unlocalized name of our creative tab
	 */
	public CREEPSCreativeTab() {
		super("mcwtab");
	}

	@Override
	public Item getTabIconItem() {
		return MCItems.FullJar;
	}


}