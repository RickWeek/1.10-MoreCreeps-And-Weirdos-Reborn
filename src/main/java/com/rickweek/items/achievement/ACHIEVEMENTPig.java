package com.rickweek.items.achievement;

import net.minecraft.item.Item;

public class ACHIEVEMENTPig extends Item
{

    public ACHIEVEMENTPig(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
    }
}