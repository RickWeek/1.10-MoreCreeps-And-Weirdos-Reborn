package com.rickweek.items;

import com.rickweek.main.MCW;

import net.minecraft.item.Item;

public class CREEPSItemLifeGem extends Item {
	
	public CREEPSItemLifeGem(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.maxStackSize = 1;
		this.setCreativeTab(MCW.items);
	}
}