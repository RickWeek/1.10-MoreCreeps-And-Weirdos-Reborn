package com.rickweek.items.achievement;

import net.minecraft.item.Item;

public class ACHIEVEMENTHotDog extends Item
{

    public ACHIEVEMENTHotDog(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
    }
}