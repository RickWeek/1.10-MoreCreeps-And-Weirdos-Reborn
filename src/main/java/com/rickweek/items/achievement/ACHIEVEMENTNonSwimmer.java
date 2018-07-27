package com.rickweek.items.achievement;

import net.minecraft.item.Item;

public class ACHIEVEMENTNonSwimmer extends Item
{

    public ACHIEVEMENTNonSwimmer(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
    }
}