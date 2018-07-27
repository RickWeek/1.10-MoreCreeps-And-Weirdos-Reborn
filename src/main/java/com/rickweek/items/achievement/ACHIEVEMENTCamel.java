package com.rickweek.items.achievement;

import net.minecraft.item.Item;

public class ACHIEVEMENTCamel extends Item
{

    public ACHIEVEMENTCamel(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
    }
}