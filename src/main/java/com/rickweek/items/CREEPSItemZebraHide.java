package com.rickweek.items;

import com.rickweek.main.MCW;

import net.minecraft.item.Item;

public class CREEPSItemZebraHide extends Item {
	
	public CREEPSItemZebraHide(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.maxStackSize = 64;
		this.setCreativeTab(MCW.items);
	}
}