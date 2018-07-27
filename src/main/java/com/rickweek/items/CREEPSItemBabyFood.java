package com.rickweek.items;

import com.rickweek.main.MCW;

import net.minecraft.item.Item;

public class CREEPSItemBabyFood extends Item {
    public CREEPSItemBabyFood(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        this.maxStackSize = 16;
        this.setCreativeTab(MCW.items);
    }
}