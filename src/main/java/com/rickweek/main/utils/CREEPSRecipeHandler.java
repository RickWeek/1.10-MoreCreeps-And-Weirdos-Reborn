package com.rickweek.main.utils;

import com.rickweek.init.MCItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CREEPSRecipeHandler {
	
	public static void Init(FMLInitializationEvent event){
		
		GameRegistry.addRecipe(new ItemStack(MCItems.Frisbee, 4), new Object[]
                {
                    "###", "#X#", "###", '#', new ItemStack(Items.DYE, 1, 4), 'X', Items.CLAY_BALL
                });
        
		/*
		 * GuineaPig Crafting TODO
        GameRegistry.addRecipe(new ItemStack(MoreCreepsAndWeirdos.guineapigradio, 1), new Object[]
                {
                    "###", "#X#", "###", '#', Items.wheat, 'X', Items.iron_ingot
                });
         */

        GameRegistry.addRecipe(new ItemStack(MCItems.Rocket, 8), new Object[]
                {
                    "#", "X", "X", '#', Items.IRON_INGOT, 'X', Blocks.TORCH
                });
        
        GameRegistry.addRecipe(new ItemStack(MCItems.AtomPacket, 8), new Object[]
                {
                    "XXX", "X#X", "XXX", '#', MCItems.Battery, 'X', MCItems.Ram16K
                });
        /*
         * TODO Shrink Ray Recipe
         
        GameRegistry.addRecipe(new ItemStack(MCItems.ShrinkRay, 1), new Object[]
                {
                    " # ", "XXX", "ZZZ", '#', Items.DIAMOND, 'X', MoreCreepsAndWeirdos.ram16k, 'Z', MoreCreepsAndWeirdos.battery
                });
                
         */

		/*
		 * ArmSword Crafting TODO
        GameRegistry.addRecipe(new ItemStack(MoreCreepsAndWeirdos.armsword, 1), new Object[]
                {
                    " X ", " X ", " X ", 'X', MoreCreepsAndWeirdos.limbs
                });
        GameRegistry.addShapelessRecipe(new ItemStack(MoreCreepsAndWeirdos.armsword, 1), new Object[]
                {
                	MoreCreepsAndWeirdos.limbs, MoreCreepsAndWeirdos.limbs, MoreCreepsAndWeirdos.limbs, MoreCreepsAndWeirdos.limbs, MoreCreepsAndWeirdos.limbs, MoreCreepsAndWeirdos.limbs, MoreCreepsAndWeirdos.limbs, MoreCreepsAndWeirdos.limbs, MoreCreepsAndWeirdos.limbs
                });
        */
        GameRegistry.addRecipe(new ItemStack(MCItems.EmpyJar, 1), new Object[]
                {
                    "ZZZ", "XXX", "XXX", 'Z', Items.IRON_INGOT, 'X', Blocks.GLASS
                });
		/*
		 * MobilePhone Crafting TODO
        GameRegistry.addRecipe(new ItemStack(MoreCreepsAndWeirdos.mobilephone, 1), new Object[]
                {
                    "ZZZ", "ZYZ", "XXX", 'X', MoreCreepsAndWeirdos.ram16k, 'Z', Blocks.GLASS, 'Y', MoreCreepsAndWeirdos.battery
                });
        */
		/*
		 * Zembra Armor Crafting TODO
        GameRegistry.addRecipe(new ItemStack(MoreCreepsAndWeirdos.zebrabody, 1), new Object[]
                {
                    "* *", "***", "***", '*', MoreCreepsAndWeirdos.zebrahide
                });
        GameRegistry.addRecipe(new ItemStack(MoreCreepsAndWeirdos.zebralegs, 1), new Object[]
                {
                    "***", "* *", "* *", '*', MoreCreepsAndWeirdos.zebrahide
                });
        GameRegistry.addRecipe(new ItemStack(MoreCreepsAndWeirdos.zebrahelmet, 1), new Object[]
                {
                    "***", "* *", '*', MoreCreepsAndWeirdos.zebrahide
                });
        GameRegistry.addRecipe(new ItemStack(MoreCreepsAndWeirdos.zebraboots, 1), new Object[]
                {
                    "* *", "* *", '*', MoreCreepsAndWeirdos.zebrahide
                });
        */
	}

}