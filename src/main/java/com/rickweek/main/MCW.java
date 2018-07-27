package com.rickweek.main;

import com.rickweek.creativetab.CREEPSCreativeTab;
import com.rickweek.entities.CREEPSEntityBullet;
import com.rickweek.entities.CREEPSEntityEvilEgg;
import com.rickweek.entities.CREEPSEntityFrisbee;
import com.rickweek.entities.CREEPSEntityGrow;
import com.rickweek.entities.CREEPSEntityMoney;
import com.rickweek.entities.CREEPSEntityRay;
import com.rickweek.entities.CREEPSEntityShrink;
import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.init.gui.handler.CREEPSGuiHandler;
import com.rickweek.main.proxies.CommonProxy;
import com.rickweek.main.utils.CREEPSRecipeHandler;
import com.rickweek.main.utils.CraftingHandlerEvent;
import com.rickweek.mobs.MobRegistry;
import com.rickweek.world.WorldGenStructures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class MCW {

	@Mod.Instance(Reference.MODID)
	public static MCW instance;
	
	@SidedProxy(serverSide = Reference.SERVER_PROXY_CLASS, clientSide = Reference.CLIENT_PROXY_CLASS)
	public static CommonProxy proxy;

	private int count;
    
    public int spittime = 500;
    
    public int currentJailX;
    public int currentJailY;
    public int currentJailZ;
    public boolean jailBuilt;
    
    public int currentfine;
    
    public int creepsTimer;
    
    public static int prisonercount = 0;
    public static int colacount = 0;
    public static int rocketcount = 0;
    public static int floobcount = 0;
    public static int goatcount = 0;
    public static int preachercount = 0;
    public static int cavemancount = 0;
    public static boolean cavemanbuilding = false;
    
    public static Item partBubble, partWhite, partRed, partBlack, partYellow, partBlue, partShrink, partBarf;
    
    public static final CreativeTabs items = new CREEPSCreativeTab();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// preInit register
		MCItems.init();
		MCItems.register();
		MCSoundEvents.registerSounds();
		GameRegistry.registerWorldGenerator(new WorldGenStructures(), 0);
		
		// Particles register
		partBarf = new Item().setUnlocalizedName("partBarf");
		GameRegistry.registerItem(partBarf, "partBarf");
		
		partBubble = new Item().setUnlocalizedName("partBubble");
		GameRegistry.registerItem(partBubble, "partBubble");
		
		
		MinecraftForge.EVENT_BUS.register(new CraftingHandlerEvent());
		
		// Proxy register
		proxy.render();
		proxy.registerRenders();
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		MobRegistry.register();
		// proxy.render();
		
		MinecraftForge.EVENT_BUS.register(new CraftingHandlerEvent());
		NetworkRegistry.INSTANCE.registerGuiHandler(MCW.instance, new CREEPSGuiHandler());
		
		// Register Recipes
		CREEPSRecipeHandler.Init(event);
		
		// Register Projectiles 
		EntityRegistry.registerModEntity(CREEPSEntityMoney.class, "MoneyEnt", 500, this, 40, 1, true);
		EntityRegistry.registerModEntity(CREEPSEntityRay.class, "RayEnt", 501, this, 40, 1, true);
		EntityRegistry.registerModEntity(CREEPSEntityBullet.class, "BulletEnt", 502, this, 40, 1, true);
		EntityRegistry.registerModEntity(CREEPSEntityFrisbee.class, "FrisbeeEnt", 503, this, 40, 1, true);
		EntityRegistry.registerModEntity(CREEPSEntityGrow.class, "GrowEnt", 504, this, 40, 1, true);
		EntityRegistry.registerModEntity(CREEPSEntityShrink.class, "ShrinkEnt", 505, this, 40, 1, true);
		EntityRegistry.registerModEntity(CREEPSEntityEvilEgg.class, "EvilEggEnt", 506, this, 40, 1, true);
		
		// Render Projectiles
		RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityMoney.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), MCItems.Money, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityRay.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), MCItems.RayRay, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityBullet.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), MCItems.Bullet, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityFrisbee.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), MCItems.Frisbee, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityGrow.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), MCItems.GrowRayRay, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityShrink.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), MCItems.GrowRayRay, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityEvilEgg.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), MCItems.EvilEgg, Minecraft.getMinecraft().getRenderItem()));
		
		// Proxy render
		proxy.renderModelItem();
		proxy.registerRenders();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
