package com.rickweek.main.utils;

import com.rickweek.init.MCSoundEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class TickClientHandlerEvent {
	final Minecraft mc = Minecraft.getMinecraft();
    public static final String welcome[] =
    {
        "Now, go out there and have some fun!",
        "Don't let those stinky Floobs push you around!",
        "Give a diamond to a level 25 HotDog for a special reward!",
        "Urinating Bums can help with landscaping. Try one today!",
        "You're doing something right!", "Watch out for grumpy G's!",
        "Guinea Pigs make nice pets.",
        "Bring a lost Kid back to a Lolliman for a nice reward.",
        "Robot Ted thinks Robot Todd is a dirty chicken wing.",
        "Sneaky Sal changes his prices. Check back for bargains.",
        "Power your HotDog with redstone for a fire attack!",
        "You want money? Punch a Lawyer From Hell!",
        "Equip your HotDogs with Redstone for fire attacks!",
        "Guinea Pigs eat Wheat and Apples.",
        "A Floob Ship will spit out Floobs until it is destroyed.",
        "Drop a BubbleScum 100 blocks for the MERCILESS achievement!",
        "Throw a BubbleScum down a DigBug hole for a cookie fountain!",
        "Feed lots of cake to a Hunchback and he will stay loyal.",
        "The longer you ride a RocketPony, the more tame it will be.",
        "Visit Sneaky Sal for those hard to find items.",
        "Hitting a Caveman will turn him/her evil!"
    };
    
    public TickClientHandlerEvent() {}
    
	@SubscribeEvent
	public void moreCreepsWelcomeMessage(PlayerEvent.PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		World world = player.worldObj;
		if(!world.isRemote) {

			world.playSound((EntityPlayer) null, player.getPosition(), MCSoundEvents.Welcome, SoundCategory.PLAYERS, 1.0F, 1.0F);
	        String randomMessage = welcome[world.rand.nextInt(welcome.length)];
			player.addChatMessage(new TextComponentString("\2476More Creeps and Weirdos Reborn \247eVersion Beta 1.17\2476 loaded."));
			player.addChatMessage(new TextComponentString(randomMessage));
			
		}
	}
}