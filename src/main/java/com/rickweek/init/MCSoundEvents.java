package com.rickweek.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MCSoundEvents
{
	
	// Items' Sound
	public static SoundEvent ITEM_BLORPCOLA, ITEM_CHEW, ITEM_BANDAID, ITEM_EMPTYJAR, ITEM_SKYGEMUP, ITEM_SKYGEMDOWN, ITEM_SHRINKRAY,
  		ITEM_RAYGUN, ITEM_LOLLY, ITEM_MININGGEM, ITEM_MININGGEM_BAD, ITEM_EATPASTA, ITEM_GEMSWORD, ITEM_FIREGEM, ITEM_EARTHGEM,
  		ITEM_HEALINGGEM, ITEM_FIREEXT, ITEM_BATTERY, ITEM_HARDCANDY, ITEM_LICK, ITEM_CHICKENEAT, ITEM_GUM, ITEM_BULLET, ITEM_ARMYGEM,
  		ITEM_CAVEDRUMS, ITEM_STEREO, ITEM_SPARK, ITEM_MOBILE, ITEM_EXTINGUISHER, ITEM_GGPIGRADIO, ITEM_HORSEHEADGEM, ITEM_DIODES, 
  		ITEM_BOOMBOX;
	
	
  
	public static SoundEvent Welcome;
	public static SoundEvent Congratulation;
	
	
	public static SoundEvent ATOM, ATOM_BLOW, ATOM_DEAD, ATOM_HURT, ATOM_SUCK;
	public static SoundEvent SHRINKKILL;
	public static SoundEvent BARF;
	public static SoundEvent GROWRAY;
	public static SoundEvent EVILEGGCLUCK, EVILEGGBIRTH;
	public static SoundEvent ENTITY_MUMMY, ENTITY_MUMMY_HURT, ENTITY_MUMMY_DEATH;
	public static SoundEvent ENTITY_BABY_MUMMY, ENTITY_BABY_MUMMY_HURT, ENTITY_BABY_MUMMY_DEATH;
	public static SoundEvent ENTITY_LOLLIMAN, ENTITY_LOLLIMAN_HURT, ENTITY_LOLLIMAN_DEATH, ENTITY_LOLLIMAN_TAKEOFF, ENTITY_LOLLIMAN_EXPLODE;
	public static SoundEvent ENTITY_KID, ENTITY_KID_HURT, ENTITY_KID_DEATH;
	public static SoundEvent ENTITY_CAMELJOCKEY, ENTITY_CAMELJOCKEY_HURT, ENTITY_CAMELJOCKEY_DEATH, ENTITY_CAMELJOCKEY_GET;
	public static SoundEvent ENTITY_BIGBABY, ENTITY_BIGBABY_HURT, ENTITY_BIGBABY_TAKEHOME, ENTITY_BIGBABY_SHRINK;
	public static SoundEvent ENTITY_FLOOBS, ENTITY_FLOOBS_HURT, ENTITY_FLOOBS_DEATH;
	public static SoundEvent ENTITY_NONSWIMMER, ENTITY_NONSWIMMER_HURT, ENTITY_NONSWIMMER_DEATH;
	public static SoundEvent ENTITY_EVILCREATURE, ENTITY_EVILCREATURE_JUMP, ENTITY_EVILCREATURE_HURT, ENTITY_EVILCREATURE_DEATH;
	public static SoundEvent ENTITY_EVILLIGHT;
	public static SoundEvent ENTITY_SNOWMAN, ENTITY_SNOWMAN_JUMP, ENTITY_SNOWMAN_HURT, ENTITY_SNOWMAN_DEATH;
	public static SoundEvent ENTITY_EXPERIMENT_EXPLOSION, ENTITY_EVIL_LAUGH, ENTITY_EVIL_HURT;
	public static SoundEvent ENTITY_BUBBLESCUM, ENTITY_BUBBLESCUM_HURT, ENTITY_BUBBLESCUM_DEATH, ENTITY_BUBBLESCUM_PICKUP, ENTITY_BUBBLESCUM_PUTDOWN;
	public static SoundEvent ENTITY_THIEF, ENTITY_THIEF_HURT, ENTITY_THIEF_DEATH, ENTITY_THIEF_STEAL, ENTITY_THIEF_FINDPLAYER;
	public static SoundEvent ENTITY_LAWYER, ENTITY_LAWYER_UNDEAD, ENTITY_LAWYER_HURT, ENTITY_LAWYER_UNDEAD_HURT, ENTITY_LAWYER_DEATH,ENTITY_LAWYER_UNDEAD_DEATH, ENTITY_LAWYER_SUCK, ENTITY_LAWYER_MONEYHIT, ENTITY_LAWYER_TAKE, ENTITY_LAWYER_BUM, ENTITY_LAWYER_BUSTED;
	public static SoundEvent ENTITY_PRISONER, ENTITY_PRISONER_HURT, ENTITY_PRISONER_DEATH, ENTITY_PRISONER_REWARD, ENTITY_PRISONER_SORRY;
	public static SoundEvent ENTITY_BLORP, ENTITY_BLORP_HURT, ENTITY_BLORP_DEATH, ENTITY_BLORP_BOUNCE, ENTITY_BLORP_EAT, ENTITY_BLORP_GROW;
	public static SoundEvent ENTITY_BLACKSOUL, ENTITY_BLACKSOUL_HURT, ENTITY_BLACKSOUL_DEATH;
	public static SoundEvent ENTITY_PYRAMIDGUARDIAN, ENTITY_PYRAMIDGUARDIAN_HURT, ENTITY_PYRAMIDGUARDIAN_DEATH, ENTITY_PYRAMIDGUARDIAN_CURSE;
	public static SoundEvent EVENT_PYRAMIDDISCOVER;
	public static SoundEvent ENTITY_CAMEL, ENTITY_CAMEL_HURT, ENTITY_CAMEL_DEATH;
	public static SoundEvent ENTITY_CASTLEKING, ENTITY_CASTLEKING_HURT, ENTITY_CASTLEKING_DEATH;
	public static SoundEvent ENTITY_CASTLEGUARD, ENTITY_CASTLEGUARD_MAD, ENTITY_CASTLEGUARD_HURT, ENTITY_CASTLEGUARD_DEATH;
	public static SoundEvent ENTITY_ROBOTTODD, ENTITY_ROBOTTODD_HURT, ENTITY_ROBOTTODD_DEATH;
	public static SoundEvent ENTITY_RATMAN, ENTITY_RATMAN_HURT, ENTITY_RATMAN_SCRATCH;
	public static SoundEvent ENTITY_DIGBUG, ENTITY_DIGBUG_HURT, ENTITY_DIGBUG_EAT, ENTITY_DIGBUG_DEATH, ENTITY_DIGBUG_CALL, ENTITY_DIGBUG_DIG, ENTITY_DIGBUG_FULL;
	public static SoundEvent ENTITY_SNEAKSAL_HURT, ENTITY_SNEAKSAL_DEATH, ENTITY_SNEAKSAL_GREETING, ENTITY_SNEAKSAL_RATS, ENTITY_SNEAKSAL_SALE, ENTITY_SNEAKSAL_NOMONEY, ENTITY_SNEAKSAL_salnobodyshrinks;
	public static SoundEvent ENTITY_PREACHER, ENTITY_PREACHER_BURN, ENTITY_PREACHER_HURT, ENTITY_PREACHER_DEATH, ENTITY_PREACHER_RAISE;
	public static SoundEvent ENTITY_ARMY, ENTITY_ARMY_ARM, ENTITY_ARMY_BOTHLEGS, ENTITY_ARMY_DEATH, ENTITY_ARMY_HEAD, ENTITY_ARMY_HELMET, ENTITY_ARMY_HURT, ENTITY_ARMY_LEG;
	public static SoundEvent ENTITY_CAVEMAN_BUILD, ENTITY_CAVEMAN_DEATH, ENTITY_CAVEMAN_EVIL, ENTITY_CAVEMAN_FREE, ENTITY_CAVEMAN_FROZEN, ENTITY_CAVEMAN_HURT, ENTITY_CAVEMAN_ICE, ENTITY_CAVEMAN_FINISHED;
	public static SoundEvent ENTITY_CAVEWOMAN_DEAD, ENTITY_CAVEWOMAN_FREE, ENTITY_CAVEWOMAN_FROZEN, ENTITY_CAVEWOMAN_HURT;
	public static SoundEvent ENTITY_CASTLECRITTER, ENTITY_CASTLECRITTER_HURT, ENTITY_CASTLECRITTER_DEATH;
	public static SoundEvent ENTITY_INVISIBLEMAN, ENTITY_INVISIBLEMAN_HURT, ENTITY_INVISIBLEMAN_DEATH, ENTITY_INVISIBLEMAN_ANGRY, ENTITY_INVISIBLEMAN_FORGETIT;
	public static SoundEvent ENTITY_BUM, ENTITY_BUM_HURT, ENTITY_BUM_DEATH, ENTITY_BUM_DONTWANT, ENTITY_BUM_LEAVEMEALONE, ENTITY_BUM_LIVINGPEE,ENTITY_BUM_PEE, ENTITY_BUM_PEEDONE, ENTITY_BUM_SUCKER, ENTITY_BUM_THANKS, ENTITY_BUM_THANKYOU;
	public static SoundEvent ENTITY_HUNCHBACK_QUIET, ENTITY_HUNCHBACK_HURT, ENTITY_HUNCHBACK_DEATH, ENTITY_HUNCHBACK_CAKE, ENTITY_HUNCHBACK_THANKYOU, ENTITY_HUNCHBACK_ARMY, ENTITY_HUNCHBACK_THANKS;
	public static SoundEvent ENTITY_GGPIGARMOR;
	public static SoundEvent ENTITY_MANDOG, ENTITY_MANDOG_HURT, ENTITY_MANDOG_DEATH, ENTITY_MANDOG_TAMED, ENTITY_SUPERDOG_APPLE, ENTITY_SUPERDOG_NAME;
	public static SoundEvent ENTITY_ROCKMONSTER, ENTITY_ROCKMONSTER_HURT, ENTITY_ROCKMONSTER_DEATH;
	public static SoundEvent SCHLUMP_OVERLOAD, SCHLUMP_INDOORS, SCHLUMP_BRIGHT, SCHLUMP_ROOM, SCHLUMP_OK, SCHLUMP_SUCKS, SCHLUMP_BIG, SCHLUMP_REWARD, SCHLUMP, SCHLUMP_HURT, SCHLUMP_DEATH;
	public static SoundEvent TROPHYSMASH;
	public static SoundEvent ENTITY_TEDINSULT, ENTITY_TEDINSULT_DEATH;
	public static SoundEvent ENTITY_G, ENTITY_G_HURT, ENTITY_G_DEATH;
	public static SoundEvent ENTITY_DESERTLIZARD, ENTITY_DESERTLIZARD_HURT, ENTITY_DESERTLIZARD_DEATH, ENTITY_DESERTLIZARD_FIREBALL;
	public static SoundEvent ENTITY_HIPPO, ENTITY_HIPPO_HURT, ENTITY_HIPPO_DEATH;
	public static SoundEvent ENTITY_DISCOMOLE, ENTITY_DISCOMOLE_HURT, ENTITY_DISCOMOLE_DEATH;
	public static SoundEvent ENTITY_GGREGG, ENTITY_GREGG_HURT, ENTITY_GGREGG_DEATH;
	public static SoundEvent ENTITY_GOOGOAT, ENTITY_GOOGOAT_STRETCH, ENTITY_GOOGOAT_HURT, ENTITY_GOOGOAT_DEATH;
	public static SoundEvent ENTITY_HORSEHEAD, ENTITY_HORSEHEAD_BLASTOFF;
	public static SoundEvent ENTITY_GGPIG_MOUNT, ENTITY_GGPIG_UNMOUNT;
  
  public MCSoundEvents() {}
  
  public static void registerSounds()
  {
	  	ITEM_BOOMBOX = registerSound("boombox.players");
	    ITEM_HORSEHEADGEM = registerSound("horseheadgem.players");
	    ITEM_GGPIGRADIO = registerSound("ggpigradio.players");
	    ITEM_BLORPCOLA = registerSound("blorpcola.players");
	    ITEM_CHEW = registerSound("chew.players");
	    ITEM_BANDAID = registerSound("bandaid.players");
	    ITEM_EMPTYJAR = registerSound("emptyjar.players");
	    ITEM_SKYGEMUP = registerSound("skygemup.players");
	    ITEM_SKYGEMDOWN = registerSound("skygemdown.players");
	    ITEM_SHRINKRAY = registerSound("shrinkray.players");
	    ITEM_RAYGUN = registerSound("raygun.players");
	    ITEM_LOLLY = registerSound("lolly.players");
	    ITEM_MININGGEM = registerSound("mininggem.players");
	    ITEM_MININGGEM_BAD = registerSound("mininggembad.players");
	    BARF = registerSound("barf.players");
	    ITEM_EATPASTA = registerSound("eatpasta.players");
	    EVENT_PYRAMIDDISCOVER = registerSound("pyramiddiscover");
	    ITEM_GEMSWORD = registerSound("gemsword.players");
	    ITEM_FIREGEM = registerSound("firegem.players");
	    ITEM_EARTHGEM = registerSound("earthgem.players");
	    ITEM_HEALINGGEM = registerSound("healinggem.players");
	    ITEM_FIREEXT = registerSound("fireext.players");
	    ITEM_BATTERY = registerSound("battery.players");
	    ITEM_HARDCANDY = registerSound("hardcandy.players");
	    ITEM_LICK = registerSound("lick.players");
	    ITEM_CHICKENEAT = registerSound("chickeneat.players");
	    ITEM_GUM = registerSound("gum.players");
	    ITEM_BULLET = registerSound("bullet.players");
	    ITEM_ARMYGEM = registerSound("armygem.players");
	    ITEM_CAVEDRUMS = registerSound("cavedrums.players");
	    ITEM_STEREO = registerSound("stereo.players");
	    ATOM = registerSound("atom.players");
	    ATOM_BLOW = registerSound("atomblow.players");
	    ATOM_DEAD = registerSound("atomdead.players");
	    ATOM_HURT = registerSound("atomhurt.players");
	    ATOM_SUCK = registerSound("atomsuck.players");
	    SHRINKKILL = registerSound("shrinkkill.players");
	    TROPHYSMASH = registerSound("trophysmash.players");
	    GROWRAY = registerSound("growray.players");
	    EVILEGGCLUCK = registerSound("evileggcluck.players");
	    EVILEGGBIRTH = registerSound("evileggbirth.players");
	    ITEM_DIODES = registerSound("diodes.players");
	    ITEM_MOBILE = registerSound("mobilephone.players");
	    ITEM_EXTINGUISHER = registerSound("extinguisher.players");
	    
    SCHLUMP_OVERLOAD = registerSound("schlump.overload");
    SCHLUMP_INDOORS = registerSound("schlump.indoors");
    SCHLUMP_BRIGHT = registerSound("schlump.bright");
    SCHLUMP_ROOM = registerSound("schlump.room");
    SCHLUMP_OK = registerSound("schlump.ok");
    SCHLUMP_SUCKS = registerSound("schlump.sucks");
    SCHLUMP_BIG = registerSound("schlump.big");
    SCHLUMP_REWARD = registerSound("schlump.reward");
    SCHLUMP = registerSound("schlump");
    SCHLUMP_HURT = registerSound("schlump.hurt");
    SCHLUMP_DEATH = registerSound("schlump.death");
    
    ENTITY_GGPIG_UNMOUNT = registerSound("ggpig.unmount");
    ENTITY_GGPIG_MOUNT = registerSound("ggpig.mount");
    
    ENTITY_HORSEHEAD = registerSound("horsehead");
    ENTITY_HORSEHEAD_BLASTOFF = registerSound("horsehead.blastoff");
    
    ENTITY_GOOGOAT = registerSound("googoat");
    ENTITY_GOOGOAT_STRETCH = registerSound("googoat.stretch");
    ENTITY_GOOGOAT_HURT = registerSound("googoat.hurt");
    ENTITY_GOOGOAT_DEATH = registerSound("googoat.death");
    
    ENTITY_GGREGG = registerSound("gregg");
    ENTITY_GREGG_HURT = registerSound("gregg.hurt");
    ENTITY_GGREGG_DEATH = registerSound("gregg.death");
    
    ENTITY_HIPPO = registerSound("hippo");
    ENTITY_HIPPO_HURT = registerSound("hippo.hurt");
    ENTITY_HIPPO_DEATH = registerSound("hippo.death");
    
    ENTITY_DISCOMOLE = registerSound("discomole");
    ENTITY_DISCOMOLE_HURT = registerSound("discomole.hurt");
    ENTITY_DISCOMOLE_DEATH = registerSound("discomole.death");
    
    ENTITY_G = registerSound("g");
    ENTITY_G_HURT = registerSound("g.hurt");
    ENTITY_G_DEATH = registerSound("g.death");
    
    ENTITY_DESERTLIZARD = registerSound("desertlizard");
    ENTITY_DESERTLIZARD_HURT = registerSound("desertlizard.hurt");
    ENTITY_DESERTLIZARD_DEATH = registerSound("desertlizard.death");
    ENTITY_DESERTLIZARD_FIREBALL = registerSound("desertlizard.fireball");
    
    ENTITY_MUMMY = registerSound("mummy");
    ENTITY_MUMMY_HURT = registerSound("mummy.hurth");
    ENTITY_MUMMY_DEATH = registerSound("mummy.death");
    
    ENTITY_BABY_MUMMY = registerSound("babymummy");
    ENTITY_BABY_MUMMY_HURT = registerSound("babymummy.hurt");
    ENTITY_BABY_MUMMY_DEATH = registerSound("babymummy.death");
    
    ENTITY_LOLLIMAN = registerSound("lolliman");
    ENTITY_LOLLIMAN_HURT = registerSound("lolliman.hurt");
    ENTITY_LOLLIMAN_DEATH = registerSound("lolliman.death");
    ENTITY_LOLLIMAN_TAKEOFF = registerSound("lolliman.takeoff");
    ENTITY_LOLLIMAN_EXPLODE = registerSound("lolliman.explode");
    
    ENTITY_KID = registerSound("kid");
    ENTITY_KID_HURT = registerSound("kid.hurt");
    ENTITY_KID_DEATH = registerSound("kid.death");
    
    ENTITY_CAMELJOCKEY = registerSound("cameljockey");
    ENTITY_CAMELJOCKEY_HURT = registerSound("cameljockey.hurt");
    ENTITY_CAMELJOCKEY_DEATH = registerSound("cameljockey.death");
    ENTITY_CAMELJOCKEY_GET = registerSound("cameljockey.get");
    
    ENTITY_BIGBABY = registerSound("bigbaby");
    ENTITY_BIGBABY_HURT = registerSound("bigbaby.hurt");
    ENTITY_BIGBABY_TAKEHOME = registerSound("bigbaby.takehome");
    ENTITY_BIGBABY_SHRINK = registerSound("bigbaby.shrink");
    
    ENTITY_FLOOBS = registerSound("floob");
    ENTITY_FLOOBS_HURT = registerSound("floob.hurt");
    ENTITY_FLOOBS_DEATH = registerSound("floob.death");
    
    ENTITY_NONSWIMMER = registerSound("nonswimmer");
    ENTITY_NONSWIMMER_HURT = registerSound("nonswimmer.hurt");
    ENTITY_NONSWIMMER_DEATH = registerSound("nonswimmer.death");
    
    ENTITY_EVILCREATURE = registerSound("evilcreature");
    ENTITY_EVILCREATURE_JUMP = registerSound("evilcreature.jump");
    ENTITY_EVILCREATURE_HURT = registerSound("evilcreature.hurt");
    ENTITY_EVILCREATURE_DEATH = registerSound("evilcreature.death");
    
    ENTITY_EVILLIGHT = registerSound("evillight");
    
    ENTITY_SNOWMAN = registerSound("snowman");
    ENTITY_SNOWMAN_JUMP = registerSound("snowman.jump");
    ENTITY_SNOWMAN_HURT = registerSound("snowman.hurt");
    ENTITY_SNOWMAN_DEATH = registerSound("snowman.death");
    
    ENTITY_EXPERIMENT_EXPLOSION = registerSound("evilexplosion");
    
    ENTITY_EVIL_LAUGH = registerSound("evil.laugh");
    ENTITY_EVIL_HURT = registerSound("evil.hurt");
    
    Welcome = registerSound("welcome");
    Congratulation = registerSound("congratulation");
    
    ENTITY_BUBBLESCUM = registerSound("bubblescum");
    ENTITY_BUBBLESCUM_HURT = registerSound("bubblescum.hurt");
    ENTITY_BUBBLESCUM_DEATH = registerSound("bubblescum.death");
    ENTITY_BUBBLESCUM_PICKUP = registerSound("bubblescum.pickup");
    ENTITY_BUBBLESCUM_PUTDOWN = registerSound("bubblescum.putdown");
    
    ENTITY_THIEF = registerSound("thief");
    ENTITY_THIEF_HURT = registerSound("thief.hurt");
    ENTITY_THIEF_DEATH = registerSound("thief.death");
    ENTITY_THIEF_STEAL = registerSound("thief.steal");
    ENTITY_THIEF_FINDPLAYER = registerSound("thief.find");
    
    ENTITY_LAWYER = registerSound("lawyer");
    ENTITY_LAWYER_UNDEAD = registerSound("lawyer.undead");
    ENTITY_LAWYER_HURT = registerSound("lawyer.hurt");
    ENTITY_LAWYER_UNDEAD_HURT = registerSound("lawyer.undead.hurt");
    ENTITY_LAWYER_DEATH = registerSound("lawyer.death");
    ENTITY_LAWYER_UNDEAD_DEATH = registerSound("lawyer.undead.death");
    ENTITY_LAWYER_SUCK = registerSound("lawyer.suck");
    ENTITY_LAWYER_TAKE = registerSound("lawyer.take");
    ENTITY_LAWYER_MONEYHIT = registerSound("lawyer.moneyhit");
    ENTITY_LAWYER_BUM = registerSound("lawyer.bum");
    ENTITY_LAWYER_BUSTED  = registerSound("lawyer.busted");
    
    ENTITY_PRISONER = registerSound("prisoner");
    ENTITY_PRISONER_HURT = registerSound("prisoner.hurt");
    ENTITY_PRISONER_DEATH = registerSound("prisoner.death");
    ENTITY_PRISONER_REWARD = registerSound("prisoner.reward");
    ENTITY_PRISONER_SORRY = registerSound("prisoner.sorry");
    
    ENTITY_BLORP = registerSound("blorp");
    ENTITY_BLORP_HURT = registerSound("blorp.hurt");
    ENTITY_BLORP_DEATH = registerSound("blorp.death");
    ENTITY_BLORP_BOUNCE = registerSound("blorp.bounce");
    ENTITY_BLORP_EAT = registerSound("blorp.eat");
    ENTITY_BLORP_GROW = registerSound("blorp.grow");
    
    ENTITY_BLACKSOUL = registerSound("blacksoul");
    ENTITY_BLACKSOUL_HURT = registerSound("blacksoul.hurt");
    ENTITY_BLACKSOUL_DEATH = registerSound("blacksoul.death");
    
    ENTITY_PYRAMIDGUARDIAN = registerSound("pyramid");
    ENTITY_PYRAMIDGUARDIAN_HURT = registerSound("pyramid.hurt");
    ENTITY_PYRAMIDGUARDIAN_DEATH = registerSound("pyramid.death");
    ENTITY_PYRAMIDGUARDIAN_CURSE = registerSound("pyramid.curse");
    
    ENTITY_CAMEL = registerSound("camel");
    ENTITY_CAMEL_HURT = registerSound("camel.hurt");
    ENTITY_CAMEL_DEATH = registerSound("camel.death");
    
    ENTITY_CASTLEKING = registerSound("castleking");
    ENTITY_CASTLEKING_HURT = registerSound("castleking.hurt");
    ENTITY_CASTLEKING_DEATH = registerSound("castleking.death");
    
    ENTITY_CASTLEGUARD = registerSound("castleguard");
    ENTITY_CASTLEGUARD_MAD = registerSound("castleguard.mad");
    ENTITY_CASTLEGUARD_HURT = registerSound("castleguard.hurt");
    ENTITY_CASTLEGUARD_DEATH = registerSound("castleguard.death");
    
    ENTITY_ROBOTTODD = registerSound("robottodd");
    ENTITY_ROBOTTODD_HURT = registerSound("robottodd.hurt");
    ENTITY_ROBOTTODD_DEATH = registerSound("robottodd.death");
    
    ENTITY_RATMAN = registerSound("ratman");
    ENTITY_RATMAN_SCRATCH = registerSound("ratman.scratch");
    ENTITY_RATMAN_HURT = registerSound("ratman.hurt");
    
    ENTITY_DIGBUG = registerSound("digbug");
    ENTITY_DIGBUG_EAT = registerSound("digbug.eat");
    ENTITY_DIGBUG_HURT = registerSound("digbug.hurt");
    ENTITY_DIGBUG_DEATH = registerSound("digbug.death");
    ENTITY_DIGBUG_CALL = registerSound("digbug.call");
    ENTITY_DIGBUG_DIG = registerSound("digbug.dig");
    ENTITY_DIGBUG_FULL = registerSound("digbug.full");
    
    ENTITY_SNEAKSAL_HURT = registerSound("sneakysal.hurt");
    ENTITY_SNEAKSAL_DEATH = registerSound("sneakysal.death");
    ENTITY_SNEAKSAL_GREETING = registerSound("sneakysal.greeting");
    ENTITY_SNEAKSAL_RATS = registerSound("sneakysal.rats");
    ENTITY_SNEAKSAL_SALE = registerSound("sneakysal.sale");
    ENTITY_SNEAKSAL_NOMONEY = registerSound("sneakysal.nomoney");
    ENTITY_SNEAKSAL_salnobodyshrinks = registerSound("sneakysal.salnobodyshrinks");
    
    ENTITY_PREACHER = registerSound("preacher");
    ENTITY_PREACHER_HURT = registerSound("preacher.hurt");
    ENTITY_PREACHER_DEATH = registerSound("preacher.death");
    ENTITY_PREACHER_BURN = registerSound("preacher.burn");
    ENTITY_PREACHER_RAISE = registerSound("preacher.raise");
    
    ENTITY_ARMY = registerSound("army");
    ENTITY_ARMY_ARM = registerSound("army.arm");
    ENTITY_ARMY_BOTHLEGS = registerSound("army.bothlegs");
    ENTITY_ARMY_DEATH = registerSound("army.death");
    ENTITY_ARMY_HEAD = registerSound("army.head");
    ENTITY_ARMY_HELMET = registerSound("army.helmet");
    ENTITY_ARMY_HURT = registerSound("army.hurt");
    ENTITY_ARMY_LEG = registerSound("army.leg");
    
    ENTITY_CAVEMAN_BUILD = registerSound("caveman.build");
    ENTITY_CAVEMAN_DEATH = registerSound("caveman.death");
    ENTITY_CAVEMAN_EVIL = registerSound("caveman.evil");
    ENTITY_CAVEMAN_FREE = registerSound("caveman.free");
    ENTITY_CAVEMAN_FROZEN = registerSound("caveman.frozen");
    ENTITY_CAVEMAN_HURT = registerSound("caveman.hurt");
    ENTITY_CAVEMAN_ICE = registerSound("caveman.ice");
    ENTITY_CAVEMAN_FINISHED = registerSound("caveman.finished");
    ENTITY_CAVEWOMAN_DEAD = registerSound("cavewoman.dead");
    ENTITY_CAVEWOMAN_FREE = registerSound("cavewoman.free");
    ENTITY_CAVEWOMAN_FROZEN = registerSound("cavewoman.frozen");
    ENTITY_CAVEWOMAN_HURT = registerSound("cavewoman.hurt");
    
    ENTITY_CASTLECRITTER = registerSound("castlecritter");
    ENTITY_CASTLECRITTER_HURT = registerSound("castlecritter.hurt");
    ENTITY_CASTLECRITTER_DEATH = registerSound("castlecritter.death");
    
    ENTITY_INVISIBLEMAN = registerSound("invisibleman");
    ENTITY_INVISIBLEMAN_HURT = registerSound("invisibleman.hurt");
    ENTITY_INVISIBLEMAN_DEATH = registerSound("invisibleman.death");
    ENTITY_INVISIBLEMAN_FORGETIT = registerSound("invisibleman.forgetit");
    ENTITY_INVISIBLEMAN_ANGRY = registerSound("invisibleman.angry");
    
    ENTITY_BUM = registerSound("bum");
    ENTITY_BUM_HURT = registerSound("bum.hurt");
    ENTITY_BUM_DEATH = registerSound("bum.death");
    ENTITY_BUM_DONTWANT = registerSound("bum.dontwant");
    ENTITY_BUM_LEAVEMEALONE = registerSound("bum.leavemealone");
    ENTITY_BUM_LIVINGPEE = registerSound("bum.livingpee");
    ENTITY_BUM_PEE = registerSound("bum.pee");
    ENTITY_BUM_PEEDONE = registerSound("bum.peedone");
    ENTITY_BUM_SUCKER = registerSound("bum.sucker");
    ENTITY_BUM_THANKS = registerSound("bum.thanks");
    ENTITY_BUM_THANKYOU = registerSound("bum.thankyou");
    
    ENTITY_HUNCHBACK_QUIET = registerSound("hunchback.quiet");
    ENTITY_HUNCHBACK_HURT = registerSound("hunchback.hurt");
    ENTITY_HUNCHBACK_DEATH = registerSound("hunchback.death");
    ENTITY_HUNCHBACK_CAKE = registerSound("hunchback.cake");
    ENTITY_HUNCHBACK_THANKYOU = registerSound("hunchback.thankyou");
    ENTITY_HUNCHBACK_ARMY = registerSound("hunchback.army");
    ENTITY_HUNCHBACK_THANKS = registerSound("hunchback.thanks");
    
    ENTITY_GGPIGARMOR = registerSound("ggpigarmor");
    
    
    ENTITY_MANDOG = registerSound("mandog");
    ENTITY_MANDOG_HURT = registerSound("mandog.hurt");
    ENTITY_MANDOG_DEATH = registerSound("mandog.death");
    ENTITY_MANDOG_TAMED = registerSound("mandog.tamed");
    ENTITY_SUPERDOG_APPLE = registerSound("superdog.apple");
    ENTITY_SUPERDOG_NAME = registerSound("superdog.name");
    
    ENTITY_ROCKMONSTER = registerSound("rockmonster");
    ENTITY_ROCKMONSTER_HURT = registerSound("rockmonster.hurt");
    ENTITY_ROCKMONSTER_DEATH = registerSound("rockmonster.death");
    
    ENTITY_TEDINSULT = registerSound("robotted");
    ENTITY_TEDINSULT_DEATH = registerSound("robotted.death");
  }
  
  public static SoundEvent registerSound(String soundName)
  {
    ResourceLocation soundID = new ResourceLocation("mcw", soundName);
    return (SoundEvent)GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
  }
}