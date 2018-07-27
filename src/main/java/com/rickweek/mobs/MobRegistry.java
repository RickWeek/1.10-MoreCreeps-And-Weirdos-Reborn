package com.rickweek.mobs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.rickweek.entities.CREEPSEntityAtom;
import com.rickweek.entities.CREEPSEntityBigBaby;
import com.rickweek.entities.CREEPSEntityBlorp;
import com.rickweek.entities.CREEPSEntityBubbleScum;
import com.rickweek.entities.CREEPSEntityBum;
import com.rickweek.entities.CREEPSEntityCamelJockey;
import com.rickweek.entities.CREEPSEntityCastleCritter;
import com.rickweek.entities.CREEPSEntityCastleGuard;
import com.rickweek.entities.CREEPSEntityCaveman;
import com.rickweek.entities.CREEPSEntityDigBug;
import com.rickweek.entities.CREEPSEntityEvilChicken;
import com.rickweek.entities.CREEPSEntityEvilCreature;
import com.rickweek.entities.CREEPSEntityEvilLight;
import com.rickweek.entities.CREEPSEntityEvilPig;
import com.rickweek.entities.CREEPSEntityEvilScientist;
import com.rickweek.entities.CREEPSEntityEvilSnowman;
import com.rickweek.entities.CREEPSEntityG;
import com.rickweek.entities.CREEPSEntityHunchback;
import com.rickweek.entities.CREEPSEntityHunchbackSkeleton;
import com.rickweek.entities.CREEPSEntityInvisibleMan;
import com.rickweek.entities.CREEPSEntityLawyerFromHell;
import com.rickweek.entities.CREEPSEntityLolliman;
import com.rickweek.entities.CREEPSEntityMummy;
import com.rickweek.entities.CREEPSEntityPreacher;
import com.rickweek.entities.CREEPSEntityPrisoner;
import com.rickweek.entities.CREEPSEntityPyramidGuardian;
import com.rickweek.entities.CREEPSEntityRatMan;
import com.rickweek.entities.CREEPSEntityRobotTed;
import com.rickweek.entities.CREEPSEntityRobotTodd;
import com.rickweek.entities.CREEPSEntityRockMonster;
import com.rickweek.entities.CREEPSEntitySchlump;
import com.rickweek.entities.CREEPSEntitySneakySal;
import com.rickweek.entities.CREEPSEntityThief;
import com.rickweek.entities.CREEPSEntityTrophy;
import com.rickweek.main.MCW;
import com.rickweek.mob.renders.CREEPSRenderAtom;
import com.rickweek.mob.renders.CREEPSRenderBabyMummy;
import com.rickweek.mob.renders.CREEPSRenderBigBaby;
import com.rickweek.mob.renders.CREEPSRenderBlorp;
import com.rickweek.mob.renders.CREEPSRenderBubbleScum;
import com.rickweek.mob.renders.CREEPSRenderBum;
import com.rickweek.mob.renders.CREEPSRenderCamelJockey;
import com.rickweek.mob.renders.CREEPSRenderCastleCritter;
import com.rickweek.mob.renders.CREEPSRenderCastleGuard;
import com.rickweek.mob.renders.CREEPSRenderCaveman;
import com.rickweek.mob.renders.CREEPSRenderDigBug;
import com.rickweek.mob.renders.CREEPSRenderEvilChicken;
import com.rickweek.mob.renders.CREEPSRenderEvilCreature;
import com.rickweek.mob.renders.CREEPSRenderEvilLight;
import com.rickweek.mob.renders.CREEPSRenderEvilPig;
import com.rickweek.mob.renders.CREEPSRenderEvilScientist;
import com.rickweek.mob.renders.CREEPSRenderEvilSnowman;
import com.rickweek.mob.renders.CREEPSRenderG;
import com.rickweek.mob.renders.CREEPSRenderHunchback;
import com.rickweek.mob.renders.CREEPSRenderHunchbackSkeleton;
import com.rickweek.mob.renders.CREEPSRenderInvisibleMan;
import com.rickweek.mob.renders.CREEPSRenderLawyerFromHell;
import com.rickweek.mob.renders.CREEPSRenderLolliman;
import com.rickweek.mob.renders.CREEPSRenderPreacher;
import com.rickweek.mob.renders.CREEPSRenderPrisoner;
import com.rickweek.mob.renders.CREEPSRenderPyramidGuardian;
import com.rickweek.mob.renders.CREEPSRenderRatMan;
import com.rickweek.mob.renders.CREEPSRenderRobotTed;
import com.rickweek.mob.renders.CREEPSRenderRobotTodd;
import com.rickweek.mob.renders.CREEPSRenderRockMonster;
import com.rickweek.mob.renders.CREEPSRenderSchlump;
import com.rickweek.mob.renders.CREEPSRenderSneakySal;
import com.rickweek.mob.renders.CREEPSRenderThief;
import com.rickweek.mob.renders.CREEPSRenderTrophy;
import com.rickweek.models.CREEPSModelAtom;
import com.rickweek.models.CREEPSModelBigBaby;
import com.rickweek.models.CREEPSModelBlorp;
import com.rickweek.models.CREEPSModelBubbleScum;
import com.rickweek.models.CREEPSModelBum;
import com.rickweek.models.CREEPSModelCamelJockey;
import com.rickweek.models.CREEPSModelCastleCritter;
import com.rickweek.models.CREEPSModelCastleGuard;
import com.rickweek.models.CREEPSModelCaveman;
import com.rickweek.models.CREEPSModelDigBug;
import com.rickweek.models.CREEPSModelEvilChicken;
import com.rickweek.models.CREEPSModelEvilCreature;
import com.rickweek.models.CREEPSModelEvilLight;
import com.rickweek.models.CREEPSModelEvilPig;
import com.rickweek.models.CREEPSModelEvilScientist;
import com.rickweek.models.CREEPSModelEvilSnowman;
import com.rickweek.models.CREEPSModelG;
import com.rickweek.models.CREEPSModelHunchback;
import com.rickweek.models.CREEPSModelLawyerFromHell;
import com.rickweek.models.CREEPSModelLolliman;
import com.rickweek.models.CREEPSModelMummy;
import com.rickweek.models.CREEPSModelPreacher;
import com.rickweek.models.CREEPSModelPyramidGuardian;
import com.rickweek.models.CREEPSModelRatMan;
import com.rickweek.models.CREEPSModelRobotTed;
import com.rickweek.models.CREEPSModelRobotTodd;
import com.rickweek.models.CREEPSModelRockMonster;
import com.rickweek.models.CREEPSModelSchlump;
import com.rickweek.models.CREEPSModelSneakySal;
import com.rickweek.models.CREEPSModelTrophy;

public class MobRegistry {

	public MobRegistry() {}
	  
	  public static void register() {
	    registerRender();
	    registerEntity();
	  }
	  
	  // The registerEntityRenderingHandler have to change ASAP because this method is deprecated
	  public static void registerRender() {
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityMummy.class, new CREEPSRenderBabyMummy(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelMummy(0.5F), 0.5F));
		  // TODO RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityBabyMummy.class, new CREEPSRenderBabyMummy(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelMummy(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityLolliman.class, new CREEPSRenderLolliman(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelLolliman(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityBlorp.class, new CREEPSRenderBlorp(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelBlorp(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityBigBaby.class, new CREEPSRenderBigBaby(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelBigBaby(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityRockMonster.class, new CREEPSRenderRockMonster(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelRockMonster(0.5F), 0.5F));
		  // RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityInvisibleMan.class, new CREEPSRenderInvisibleMan(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelMummy(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityInvisibleMan.class, new CREEPSRenderInvisibleMan(Minecraft.getMinecraft().getRenderManager(), new ModelBiped(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityRobotTed.class, new CREEPSRenderRobotTed(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelRobotTed(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityRobotTodd.class, new CREEPSRenderRobotTodd(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelRobotTodd(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityEvilLight.class, new CREEPSRenderEvilLight(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelEvilLight(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityEvilSnowman.class, new CREEPSRenderEvilSnowman(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelEvilSnowman(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityEvilPig.class, new CREEPSRenderEvilPig(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelEvilPig(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityEvilCreature.class, new CREEPSRenderEvilCreature(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelEvilCreature(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityEvilChicken.class, new CREEPSRenderEvilChicken(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelEvilChicken(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityEvilScientist.class, new CREEPSRenderEvilScientist(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelEvilScientist(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityPreacher.class, new CREEPSRenderPreacher(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelPreacher(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityPrisoner.class, new CREEPSRenderPrisoner(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelMummy(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityLawyerFromHell.class, new CREEPSRenderLawyerFromHell(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelLawyerFromHell(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityRatMan.class, new CREEPSRenderRatMan(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelRatMan(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityBum.class, new CREEPSRenderBum(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelBum(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityCastleGuard.class, new CREEPSRenderCastleGuard(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelCastleGuard(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityThief.class, new CREEPSRenderThief(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelMummy(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityCaveman.class, new CREEPSRenderCaveman(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelCaveman(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityDigBug.class, new CREEPSRenderDigBug(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelDigBug(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityG.class, new CREEPSRenderG(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelG(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityPyramidGuardian.class, new CREEPSRenderPyramidGuardian(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelPyramidGuardian(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityCamelJockey.class, new CREEPSRenderCamelJockey(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelCamelJockey(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityCastleCritter.class, new CREEPSRenderCastleCritter(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelCastleCritter(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityHunchbackSkeleton.class, new CREEPSRenderHunchbackSkeleton(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelMummy(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityHunchback.class, new CREEPSRenderHunchback(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelHunchback(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntitySchlump.class, new CREEPSRenderSchlump(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelSchlump(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityTrophy.class, new CREEPSRenderTrophy(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelTrophy(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityAtom.class, new CREEPSRenderAtom(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelAtom(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntitySneakySal.class, new CREEPSRenderSneakySal(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelSneakySal(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityBubbleScum.class, new CREEPSRenderBubbleScum(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelBubbleScum(0.5F), 0.5F));
		  
	  }
	  
	  public static void registerEntity() {
		    EntityRegistry.registerModEntity(CREEPSEntityMummy.class, "mummy", 300, MCW.instance, 64, 1, true, 16250871, 2106401);
		    EntityRegistry.addSpawn(CREEPSEntityMummy.class, 11, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.DRY));
		    
		    EntityRegistry.registerModEntity(CREEPSEntityLolliman.class, "lolliman", 301, MCW.instance, 64, 1, true, 16250871, 2106401);
		    EntityRegistry.addSpawn(CREEPSEntityLolliman.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
		    
		    EntityRegistry.registerModEntity(CREEPSEntityBlorp.class, "blorp", 302, MCW.instance, 64, 1, true, 16250871, 2106401);
		    EntityRegistry.addSpawn(CREEPSEntityBlorp.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
		    
		    EntityRegistry.registerModEntity(CREEPSEntityBigBaby.class, "bigbaby", 303, MCW.instance, 64, 1, true, 16250871, 2106401);
		    EntityRegistry.addSpawn(CREEPSEntityBigBaby.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
		  
		    EntityRegistry.registerModEntity(CREEPSEntityRockMonster.class, "rockmonster", 304, MCW.instance, 64, 1, true, 16250871, 2106401);
		    EntityRegistry.addSpawn(CREEPSEntityRockMonster.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
		    
		    EntityRegistry.registerModEntity(CREEPSEntityInvisibleMan.class, "invisibleman", 305, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityInvisibleMan.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityRobotTed.class, "robotted", 306, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityRobotTed.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityRobotTodd.class, "robottodd", 307, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityRobotTodd.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityEvilLight.class, "evillight", 308, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityEvilLight.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityEvilSnowman.class, "evilsnowman", 309, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityEvilSnowman.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityEvilPig.class, "evilpig", 310, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityEvilPig.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityEvilCreature.class, "evilcreature", 311, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityEvilCreature.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityEvilChicken.class, "evilchicken", 312, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityEvilChicken.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityEvilScientist.class, "evilscientist", 313, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityEvilScientist.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityPreacher.class, "preacher", 314, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityPreacher.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityPrisoner.class, "prisoner", 315, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityPrisoner.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityLawyerFromHell.class, "lawyerfromhell", 316, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityLawyerFromHell.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityRatMan.class, "ratman", 317, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityRatMan.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityBum.class, "bum", 318, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityBum.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityCastleGuard.class, "castleguard", 319, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityCastleGuard.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityThief.class, "thief", 320, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityThief.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityCaveman.class, "caveman", 321, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityCaveman.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityDigBug.class, "digbug", 322, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityDigBug.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityG.class, "g", 323, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityG.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityPyramidGuardian.class, "pyramidguardian", 324, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityPyramidGuardian.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityCamelJockey.class, "cameljockey", 325, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityCamelJockey.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityCastleCritter.class, "castlecritter", 326, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityCastleCritter.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityHunchbackSkeleton.class, "hunchbackskeleton", 327, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityHunchbackSkeleton.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityHunchback.class, "hunchback", 328, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityHunchback.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntitySchlump.class, "schlump", 329, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntitySchlump.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityTrophy.class, "trophy", 330, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityTrophy.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntityAtom.class, "atom", 331, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityAtom.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
			
			EntityRegistry.registerModEntity(CREEPSEntitySneakySal.class, "sneakysal", 332, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntitySneakySal.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);

			EntityRegistry.registerModEntity(CREEPSEntityBubbleScum.class, "bubblescum", 333, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityBubbleScum.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
	  }
	
}
