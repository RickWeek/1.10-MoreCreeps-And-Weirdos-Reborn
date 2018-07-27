package com.rickweek.init;

import com.rickweek.items.Blorpcola;
import com.rickweek.items.CREEPSItemArmSword;
import com.rickweek.items.CREEPSItemArmyGem;
import com.rickweek.items.CREEPSItemAtom;
import com.rickweek.items.CREEPSItemBabyFood;
import com.rickweek.items.CREEPSItemBabyJarEmpty;
import com.rickweek.items.CREEPSItemBabyJarFull;
import com.rickweek.items.CREEPSItemBandAid;
import com.rickweek.items.CREEPSItemBattery;
import com.rickweek.items.CREEPSItemBomb;
import com.rickweek.items.CREEPSItemBoomBox;
import com.rickweek.items.CREEPSItemBullLeather;
import com.rickweek.items.CREEPSItemBullSteak;
import com.rickweek.items.CREEPSItemBullet;
import com.rickweek.items.CREEPSItemCandy;
import com.rickweek.items.CREEPSItemCavemanClub;
import com.rickweek.items.CREEPSItemChickenWing;
import com.rickweek.items.CREEPSItemCupcake;
import com.rickweek.items.CREEPSItemDiodes;
import com.rickweek.items.CREEPSItemDonut;
import com.rickweek.items.CREEPSItemEarthGem;
import com.rickweek.items.CREEPSItemEvilEgg;
import com.rickweek.items.CREEPSItemExtinguisher;
import com.rickweek.items.CREEPSItemFireGem;
import com.rickweek.items.CREEPSItemFrisbee;
import com.rickweek.items.CREEPSItemGemSword;
import com.rickweek.items.CREEPSItemGooDonut;
import com.rickweek.items.CREEPSItemGrowRay;
import com.rickweek.items.CREEPSItemGrowRayRay;
import com.rickweek.items.CREEPSItemGuineaPigRadio;
import com.rickweek.items.CREEPSItemGun;
import com.rickweek.items.CREEPSItemHealingGem;
import com.rickweek.items.CREEPSItemHorseHeadGem;
import com.rickweek.items.CREEPSItemLifeGem;
import com.rickweek.items.CREEPSItemLimbs;
import com.rickweek.items.CREEPSItemLolly;
import com.rickweek.items.CREEPSItemMiningGem;
import com.rickweek.items.CREEPSItemMobilePhone;
import com.rickweek.items.CREEPSItemMoney;
import com.rickweek.items.CREEPSItemMoopsWorm;
import com.rickweek.items.CREEPSItemPopsicle;
import com.rickweek.items.CREEPSItemRam16K;
import com.rickweek.items.CREEPSItemRayGun;
import com.rickweek.items.CREEPSItemRayRay;
import com.rickweek.items.CREEPSItemRocket;
import com.rickweek.items.CREEPSItemShrinkRay;
import com.rickweek.items.CREEPSItemSkyGem;
import com.rickweek.items.CREEPSItemTombstone;
import com.rickweek.items.CREEPSItemZebraHide;
import com.rickweek.items.achievement.ACHIEVEMENTBubble;
import com.rickweek.items.achievement.ACHIEVEMENTCamel;
import com.rickweek.items.achievement.ACHIEVEMENTFloob;
import com.rickweek.items.achievement.ACHIEVEMENTHell;
import com.rickweek.items.achievement.ACHIEVEMENTHotDog;
import com.rickweek.items.achievement.ACHIEVEMENTHotDog2;
import com.rickweek.items.achievement.ACHIEVEMENTNonSwimmer;
import com.rickweek.items.achievement.ACHIEVEMENTPig;
import com.rickweek.items.achievement.ACHIEVEMENTPyramid;
import com.rickweek.main.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MCItems {
	
	// ITEMS
	
	// Gems
	public static Item HealingGem, FireGem, EarthGem, MiningGem, SkyGem, LifeGem;
	
	// Guns
	public static Item RayGun, RayRay, Gun, Bullet;
	
	// Food
	public static Item BullSteak;
	public static Item HardCandy;
	public static Item ChickenWing;
	public static Item Cupcake;
	
	// Misc
	public static Item Blorpcola, Bandaid, EmpyJar, FullJar, Ram16K, Battery, EvilEgg,Money, Lolly, GooDonut, Donut, Popsicle, CaveClub
		,AtomPacket, ZebraHide, MobilePhone, ArmSword, ArmorZebra, ArmyGem, Extinguisher, Frisbee, GrowRayRay, GrowRay, GemSword,
		GGPigRadio, Limbs, Tombstone, MoopsWorm, HorseHeadGem, ShrinkRay, BabyFood, Rocket, Bomb, BoomBox, BullLeather, Diodes;
	
	// Achievement
	public static Item aBubble, aCamel, aFloob, aHell, aHotDog, aHotDog2, aPig, aNonSwimmer, aPyramid;
	
	public static void init() {
		// Init Items
		
		Diodes = new CREEPSItemDiodes("Diodes", "Diodes");
		BullLeather = new CREEPSItemBullLeather("BullLeather", "BullLeather");
		BoomBox = new CREEPSItemBoomBox("BoomBox", "BoomBox");
		Bomb = new CREEPSItemBomb("Bomb", "Bomb");
		Rocket = new CREEPSItemRocket("Rocket", "Rocket");
		BabyFood = new CREEPSItemBabyFood("BabyFood", "BabyFood");
		ShrinkRay = new CREEPSItemShrinkRay("ShrinkRay", "ShrinkRay");
		HorseHeadGem = new CREEPSItemHorseHeadGem("HorseHeadGem", "HorseHeadGem");
		MoopsWorm = new CREEPSItemMoopsWorm("MoopsWorm", "MoopsWorm");
		Tombstone = new CREEPSItemTombstone("Tombstone", "Tombstone");
		Limbs = new CREEPSItemLimbs("Limbs", "Limbs");
		GGPigRadio = new CREEPSItemGuineaPigRadio("GGPigRadio", "GGPigRadio");
		GemSword = new CREEPSItemGemSword("GemSword", "GemSword");
		GrowRay = new CREEPSItemGrowRay("GrowRay", "GrowRay");
		GrowRayRay = new CREEPSItemGrowRayRay("GrowRayRay", "GrowRayRay");
		Frisbee = new CREEPSItemFrisbee("Frisbee", "Frisbee");
		Blorpcola = new Blorpcola("Blorpcola", "Blorpcola");
		Bandaid = new CREEPSItemBandAid("Bandaid", "Bandaid");
		EmpyJar = new CREEPSItemBabyJarEmpty("EmpyJar", "EmpyJar");
		FullJar = new CREEPSItemBabyJarFull("FullJar", "FullJar");
		Ram16K = new CREEPSItemRam16K("Ram16K", "Ram16K");
		Battery = new CREEPSItemBattery("Battery", "Battery");
		EvilEgg = new CREEPSItemEvilEgg("EvilEgg", "EvilEgg");
		Money = new CREEPSItemMoney("Money", "Money");
		Lolly = new CREEPSItemLolly("Lolly", "Lolly");
		GooDonut = new CREEPSItemGooDonut("GooDonut", "GooDonut");
		Donut = new CREEPSItemDonut("Donut", "Donut");
		Popsicle = new CREEPSItemPopsicle("Popsicle", "Popsicle");
		CaveClub = new CREEPSItemCavemanClub("CavemanClub", "CavemanClub");
		HealingGem = new CREEPSItemHealingGem("HealingGem", "HealingGem");
		FireGem = new CREEPSItemFireGem("FireGem", "FireGem");
		EarthGem = new CREEPSItemEarthGem("EarthGem", "EarthGem");
		MiningGem = new CREEPSItemMiningGem("MiningGem", "MiningGem");
		SkyGem = new CREEPSItemSkyGem("SkyGem", "SkyGem");
		LifeGem = new CREEPSItemLifeGem("LifeGem", "LifeGem");
		AtomPacket = new CREEPSItemAtom("AtomPacket", "AtomPacket");
		ZebraHide = new CREEPSItemZebraHide("ZebraHide", "ZebraHide");
		MobilePhone = new CREEPSItemMobilePhone("MobilePhone", "MobilePhone");
		RayGun = new CREEPSItemRayGun("RayGun", "RayGun");
		RayRay = new CREEPSItemRayRay("RayRay", "RayRay");
		Gun = new CREEPSItemGun("Gun", "Gun");
		Bullet = new CREEPSItemBullet("Bullet", "Bullet");
		ArmSword = new CREEPSItemArmSword("ArmSword", "ArmSword");
		ArmyGem = new CREEPSItemArmyGem("ArmyGem", "ArmyGem");
		Extinguisher = new CREEPSItemExtinguisher("Extinguisher", "Extinguisher");
		
		// Init Foods Items
		
		BullSteak = new CREEPSItemBullSteak("BullSteak", 8, 5, true);
		HardCandy = new CREEPSItemCandy("HardCandy", 1, 1, false);
		ChickenWing = new CREEPSItemChickenWing("ChickenWing", 4, 4, true);
		Cupcake = new CREEPSItemCupcake("Cupcake", 2, 2, false);
		
		// Init A-Items
		aBubble = new ACHIEVEMENTBubble("aBubble", "aBubble");
		aCamel = new ACHIEVEMENTCamel("aCamel", "aCamel");
		aFloob = new ACHIEVEMENTFloob("aFloob", "aFloob");
		aHell = new ACHIEVEMENTHell("aHell", "aHell");
		aHotDog = new ACHIEVEMENTHotDog("aHotDog", "aHotDog");
		aHotDog2 = new ACHIEVEMENTHotDog2("aHotDog2", "aHotDog2");
		aPig = new ACHIEVEMENTNonSwimmer("aPig", "aPig");
		aNonSwimmer = new ACHIEVEMENTPig("aNonSwimmer", "aNonSwimmer");
		aPyramid = new ACHIEVEMENTPyramid("aPyramid", "aPyramid");
		
	}
	
	public static void register() {
		// Register Items
		
		// Food
		registerItem(BullSteak);
		registerItem(HardCandy);
		registerItem(ChickenWing);
		registerItem(Cupcake);
		
		// Misc
		registerItem(Diodes);
		registerItem(BullLeather);
		registerItem(BoomBox);
		registerItem(Bomb);
		registerItem(Rocket);
		registerItem(GrowRay);
		registerItem(GrowRayRay);
		registerItem(Blorpcola);
		registerItem(Bandaid);
		registerItem(EmpyJar);
		registerItem(FullJar);
		registerItem(Ram16K);
		registerItem(Battery);
		registerItem(EvilEgg);
		registerItem(Money);
		registerItem(Lolly);
		registerItem(GooDonut);
		registerItem(Donut);
		registerItem(Popsicle);
		registerItem(CaveClub);
		registerItem(HealingGem);
		registerItem(FireGem);
		registerItem(EarthGem);
		registerItem(MiningGem);
		registerItem(SkyGem);
		registerItem(LifeGem);
		registerItem(AtomPacket);
		registerItem(ZebraHide);
		registerItem(MobilePhone);
		registerItem(RayGun);
		registerItem(RayRay);
		registerItem(Gun);
		registerItem(Bullet);
		registerItem(ArmSword);
		registerItem(ArmyGem);
		registerItem(Extinguisher);
		registerItem(Frisbee);
		registerItem(GemSword);
		registerItem(GGPigRadio);
		registerItem(Limbs);
		registerItem(Tombstone);
		registerItem(MoopsWorm);
		registerItem(HorseHeadGem);
		registerItem(ShrinkRay);
		registerItem(BabyFood);
		
		// Register A-Items
		registerItem(aBubble);
		registerItem(aCamel);
		registerItem(aFloob);
		registerItem(aHell);
		registerItem(aHotDog);
		registerItem(aHotDog2);
		registerItem(aNonSwimmer);
		registerItem(aPig);
		registerItem(aPyramid);
	}
	
	public static void registerRenders() {
		// Render 
		
		// Food
		registerRender(BullSteak);
		registerRender(HardCandy);
		registerRender(ChickenWing);
		registerRender(Cupcake);
		
		// Misc
		registerRender(Diodes);
		registerRender(BullLeather);
		registerRender(BoomBox);
		registerRender(Bomb);
		registerRender(Rocket);
		registerRender(Blorpcola);
		registerRender(GrowRay);
		registerRender(GrowRayRay);
		registerRender(Bandaid);
		registerRender(EmpyJar);
		registerRender(FullJar);
		registerRender(Ram16K);
		registerRender(Battery);
		registerRender(EvilEgg);
		registerRender(Money);
		registerRender(Lolly);
		registerRender(GooDonut);
		registerRender(Donut);
		registerRender(Popsicle);
		registerRender(CaveClub);
		registerRender(HealingGem);
		registerRender(FireGem);
		registerRender(EarthGem);
		registerRender(MiningGem);
		registerRender(SkyGem);
		registerRender(LifeGem);
		registerRender(AtomPacket);
		registerRender(ZebraHide);
		registerRender(MobilePhone);
		registerRender(RayGun);
		registerRender(RayRay);
		registerRender(Gun);
		registerRender(Bullet);
		registerRender(ArmSword);
		registerRender(ArmyGem);
		registerRender(Extinguisher);
		registerRender(Frisbee);
		registerRender(GemSword);
		registerRender(GGPigRadio);
		registerRender(Limbs);
		registerRender(Tombstone);
		registerRender(MoopsWorm);
		registerRender(HorseHeadGem);
		registerRender(ShrinkRay);
		registerRender(BabyFood);
		
		// Render A-Items
		registerRender(aBubble);
		registerRender(aCamel);
		registerRender(aFloob);
		registerRender(aHell);
		registerRender(aHotDog);
		registerRender(aHotDog2);
		registerRender(aNonSwimmer);
		registerRender(aPig);
		registerRender(aPyramid);
	}
	
	public static void registerItem(Item item) {
		GameRegistry.register(item);
		
	}
	
	public static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
	}

}
