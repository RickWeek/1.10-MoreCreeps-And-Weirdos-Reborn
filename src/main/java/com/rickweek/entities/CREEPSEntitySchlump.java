package com.rickweek.entities;

import java.util.List;

import javax.annotation.Nullable;

import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSEntitySchlump extends EntityAnimal
{
	Block doorgetter[] = {
			Blocks.ACACIA_DOOR, Blocks.BIRCH_DOOR, Blocks.DARK_OAK_DOOR, Blocks.JUNGLE_DOOR, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR
	};
	World world;
	EntityPlayer entityplayer;
    protected double attackRange;
    private int waittime;
    public float modelsize;
    public boolean saved;
    public int age;
    public int agetimer;
    public int payouttimer;
    public boolean placed;
    public int deathtimer;
    public String texture;
    public double moveSpeed;
    public double health;

    public CREEPSEntitySchlump(World world)
    {
        super(world);
        texture = "mcw:textures/entity/schlump.png";
        moveSpeed = 0.0F;
        health = rand.nextInt(10) + 10;
        saved = false;
        waittime = rand.nextInt(1500) + 500;
        modelsize = 0.4F;
        setSize(width * modelsize, height * modelsize);
        age = 0;
        agetimer = 0;
        placed = false;
        deathtimer = -1;
    }
    
    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(rand.nextInt(10) + 10);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
    }

    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal)
    {
        return new CREEPSEntitySchlump(worldObj);
    }

    

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (inWater)
        {
            setDead();
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        ignoreFrustumCheck = true;

        if (agetimer++ > 50)
        {
            if (age < 22000)
            {
                age++;
            }

            if (age > 20000)
            {
                setDead();
            }

            if (age > 6000)
            {
                /* TODO
                confetti();
                worldObj.playSoundAtEntity(entityplayer, "morecreeps:achievement", 1.0F, 1.0F);
                entityplayer.addStat(MoreCreepsAndWeirdos.achieveschlump, 1);
                */
            }

            if (modelsize < 3.5F)
            {
                modelsize += 0.001F;
            }

            agetimer = 0;
            int i = (age / 100) * 2;

            if (i > 150)
            {
                i = 150;
            }

            if (age > 200 && rand.nextInt(200 - i) == 0)
            {
                giveReward();
            }
        }

        if (!placed)
        {
            placed = true;

            if (!checkHouse())
            {
                deathtimer = 200;
            }
        }
        else if (deathtimer-- == 0)
        {
            setDead();
        }

        super.onUpdate();
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return health < 1;
    }

    public boolean checkHouse()
    {
        boolean flag = false;
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(16D, 16D, 16D));
        int i = 0;

        do
        {
            if (i >= list.size())
            {
                break;
            }

            Entity entity = (Entity)list.get(i);

            if (entity instanceof CREEPSEntitySchlump)
            {
                flag = true;
                break;
            }

            i++;
        }
        while (true);

        if (flag)
        {
        	MCW.proxy.addChatMessage("Too close to another Schlump. SCHLUMP OVERLOAD!");
            // worldObj.playSoundAtEntity(this, "morecreeps:schlump-overload", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.SCHLUMP_OVERLOAD, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            return false;
        }

        i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor_double(posZ);

        if (worldObj.canBlockSeeSky(new BlockPos(i, j, k)))
        {
        	MCW.proxy.addChatMessage("Your Schlump needs to be indoors or it will die!");
            // worldObj.playSoundAtEntity(this, "morecreeps:schlump-indoors", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.SCHLUMP_INDOORS, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            return false;
        }

        if (worldObj.getBlockLightOpacity(new BlockPos(i, j, k)) > 11)
        {
        	MCW.proxy.addChatMessage("It is too bright in here for your little Schlump!");
            // worldObj.playSoundAtEntity(this, "morecreeps:schlump-bright", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.SCHLUMP_BRIGHT, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            return false;
        }

        int l = 0;

        for (int i1 = -2; i1 < 2; i1++)
        {
            for (int k1 = -2; k1 < 2; k1++)
            {
                for (int i2 = 0; i2 < 5; i2++)
                {
                    if (worldObj.getBlockState(new BlockPos((int)posX + i1, (int)posY + i2, (int)posZ + k1)).getBlock() == Blocks.AIR)
                    {
                        l++;
                    }
                }
            }
        }

        if (l < 60)
        {
        	MCW.proxy.addChatMessage("Your Schlump doesn't have enough room to grow!");
            // worldObj.playSoundAtEntity(this, "morecreeps:schlump-room", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.SCHLUMP_ROOM, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            return false;
        }

        int j1 = 0;

        for (int l1 = -5; l1 < 5; l1++)
        {
            for (int j2 = -5; j2 < 5; j2++)
            {
                for (int k2 = -5; k2 < 5; k2++)
                {
                    Block l2 = worldObj.getBlockState(new BlockPos((int)posX + l1, (int)posY + k2, (int)posZ + j2)).getBlock();

                    
                    
                    if (l2 == this.doorgetter[rand.nextInt(doorgetter.length)])
                    {
                        j1 += 10;
                    }

                    if (l2 == Blocks.IRON_DOOR)
                    {
                        j1 += 20;
                    }

                    if (l2 == Blocks.GLASS)
                    {
                        j1 += 5;
                    }

                    if (l2 == Blocks.CHEST)
                    {
                        j1 += 15;
                    }

                    if (l2 == Blocks.BED)
                    {
                        j1 += 20;
                    }

                    if (l2 == Blocks.BOOKSHELF)
                    {
                        j1 += 15;
                    }
                    
                    if (l2 == Blocks.BRICK_BLOCK)
                    {
                        j1 += 3;
                    }

                    if (l2 == Blocks.PLANKS)
                    {
                        j1 += 3;
                    }

                    if (l2 == Blocks.WOOL)
                    {
                        j1 += 2;
                    }

                    if (l2 == Blocks.CAKE)
                    {
                        j1 += 10;
                    }

                    if (l2 == Blocks.FURNACE)
                    {
                        j1 += 15;
                    }

                    if (l2 == Blocks.LIT_FURNACE)
                    {
                        j1 += 10;
                    }

                    if (l2 == Blocks.RED_FLOWER)
                    {
                        j1 += 5;
                    }

                    if (l2 == Blocks.RED_FLOWER)
                    {
                        j1 += 5;
                    }

                    if (l2 == Blocks.CRAFTING_TABLE)
                    {
                        j1 += 10;
                    }
                }
            }
        }

        if (j1 > 275)
        {
            if (age < 10)
            {
            	MCW.proxy.addChatMessage("This location is great! Your Schlump will love it here! ");
                // worldObj.playSoundAtEntity(this, "morecreeps:schlump-ok", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.SCHLUMP_OK, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            }

            return true;
        }
        else
        {
        	MCW.proxy.addChatMessage("This is not a good location for your Schlump. It will die here! ");
            // worldObj.playSoundAtEntity(this, "morecreeps:schlump-sucks", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.SCHLUMP_SUCKS, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            return false;
        }
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean processInteract(EntityPlayer entityplayer, EnumHand hand, @Nullable ItemStack stack)
    {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == MCItems.EmpyJar)
        {
            if (modelsize > 0.5F)
            {
            	MCW.proxy.addChatMessage("That Schlump is too big to fit in a jar! ");
                // worldObj.playSoundAtEntity(this, "morecreeps:schlump-big", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.SCHLUMP_BIG, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                return true;
            }

            setDead();
            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(MCItems.FullJar));
        }

        return super.processInteract(entityplayer, hand, stack);
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        if (i < 1)
        {
            i = 1;
        }

        hurtTime = maxHurtTime = 10;
        smoke();

        if (health <= 0)
        {
            // worldObj.playSoundAtEntity(this, getDeathSound(), getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), getDeathSound(), SoundCategory.NEUTRAL,getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            onDeath(damagesource);
        }
        else
        {
            // worldObj.playSoundAtEntity(this, getHurtSound(), getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), getHurtSound(), SoundCategory.NEUTRAL,getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
        }

        super.attackEntityFrom(damagesource, i);
        return true;
    }

    public boolean checkItems()
    {
        int i = 0;
        Object obj = null;
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(6D, 6D, 6D));

        for (int j = 0; j < list.size(); j++)
        {
            Entity entity = (Entity)list.get(j);

            if (entity instanceof EntityItem)
            {
                i++;
            }
        }

        return i > 25;
    }

    public void giveReward()
    {
        if (!checkHouse())
        {
        	MCW.proxy.addChatMessage("This is not a good location for your Schlump. It will die here!");
            // worldObj.playSoundAtEntity(this, "morecreeps:schlump-sucks", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.SCHLUMP_SUCKS, SoundCategory.NEUTRAL,getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            deathtimer = 200;
            return;
        }

        if (checkItems())
        {
            return;
        }

        // worldObj.playSoundAtEntity(this, "morecreeps:schlump-reward", 1.0F, 1.0F);
        worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.SCHLUMP_REWARD, SoundCategory.NEUTRAL,getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
        // TODO smallconfetti();
        int i = rand.nextInt(age / 100) + 1;

        if (i > 42)
        {
            i = 42;
        }

        

        if (entityplayer != null)
        {
            EntityItem entityitem = null;

            switch (i)
            {
                case 1:
                    entityitem = entityDropItem(new ItemStack(MCItems.Lolly, rand.nextInt(2) + 1, 0), 1.0F);
                    break;

                case 2:
                    entityitem = entityDropItem(new ItemStack(Items.WHEAT, 1, 0), 1.0F);
                    break;

                case 3:
                    entityitem = entityDropItem(new ItemStack(Items.WHEAT, 1, 0), 1.0F);
                    break;

                case 4:
                    entityitem = entityDropItem(new ItemStack(Items.WHEAT, 1, 0), 1.0F);
                    break;

                case 5:
                    entityitem = entityDropItem(new ItemStack(Items.WHEAT, 1, 0), 1.0F);
                    break;

                case 6:
                    entityitem = entityDropItem(new ItemStack(MCItems.Bandaid, 1, 0), 1.0F);
                    break;

                case 7:
                    entityitem = entityDropItem(new ItemStack(MCItems.Bandaid, 1, 0), 1.0F);
                    break;

                case 8:
                    entityitem = entityDropItem(new ItemStack(MCItems.Bandaid, 1, 0), 1.0F);
                    break;

                case 9:
                    entityitem = entityDropItem(new ItemStack(MCItems.Bandaid, 1, 0), 1.0F);
                    break;

                case 10:
                    entityitem = entityDropItem(new ItemStack(MCItems.Bandaid, 1, 0), 1.0F);
                    break;

                case 11:
                    entityitem = entityDropItem(new ItemStack(Items.BREAD, 1, 0), 1.0F);
                    break;

                case 12:
                    entityitem = entityDropItem(new ItemStack(Items.BREAD, 1, 0), 1.0F);
                    break;

                case 13:
                    entityitem = entityDropItem(new ItemStack(Items.BREAD, 1, 0), 1.0F);
                    break;

                case 14:
                    entityitem = entityDropItem(new ItemStack(Items.BREAD, 1, 0), 1.0F);
                    break;

                case 15:
                    entityitem = entityDropItem(new ItemStack(MCItems.Money, rand.nextInt(4) + 1, 0), 1.0F);
                    break;

                case 16:
                    entityitem = entityDropItem(new ItemStack(MCItems.Money, rand.nextInt(4) + 1, 0), 1.0F);
                    break;

                case 17:
                    entityitem = entityDropItem(new ItemStack(MCItems.Money, rand.nextInt(4) + 1, 0), 1.0F);
                    break;

                case 18:
                    entityitem = entityDropItem(new ItemStack(MCItems.Lolly, rand.nextInt(2) + 1, 0), 1.0F);
                    break;

                case 19:
                    entityitem = entityDropItem(new ItemStack(Items.APPLE, 1, 0), 1.0F);
                    break;

                case 20:
                    entityitem = entityDropItem(new ItemStack(Items.APPLE, 1, 0), 1.0F);
                    break;

                case 21:
                    entityitem = entityDropItem(new ItemStack(Items.PORKCHOP, 1, 0), 1.0F);
                    break;

                case 22:
                    entityitem = entityDropItem(new ItemStack(Items.COAL, 1, 0), 1.0F);
                    break;

                case 23:
                    entityitem = entityDropItem(new ItemStack(Items.COAL, 1, 0), 1.0F);
                    break;

                case 24:
                    entityitem = entityDropItem(new ItemStack(Items.MELON_SEEDS, 1, 0), 1.0F);
                    break;

                case 25:
                    entityitem = entityDropItem(new ItemStack(Items.PORKCHOP, 1, 0), 1.0F);
                    break;

                case 26:
                    entityitem = entityDropItem(new ItemStack(Items.PORKCHOP, 1, 0), 1.0F);
                    break;

                case 27:
                    entityitem = entityDropItem(new ItemStack(Items.IRON_INGOT, 1, 0), 1.0F);
                    break;

                case 28:
                    entityitem = entityDropItem(new ItemStack(Items.FISH, 1, 0), 1.0F);
                    break;

                case 29:
                    entityitem = entityDropItem(new ItemStack(MCItems.EvilEgg, rand.nextInt(5) + 1, 0), 1.0F);
                    break;

                case 30:
                    entityitem = entityDropItem(new ItemStack(Items.COOKED_FISH, 1, 0), 1.0F);
                    break;

                case 31:
                    // TODO Change to this entityitem = entityDropItem(new ItemStack(MCItems.gun, 1, 0), 1.0F);
                    entityitem = entityDropItem(new ItemStack(MCItems.Donut, 1, 0), 1.0F);
                    break;

                case 32:
                	// TODO Change to this entityitem = entityDropItem(new ItemStack(MCItems.extinguisher, rand.nextInt(2) + 1, 0), 1.0F);
                    entityitem = entityDropItem(new ItemStack(MCItems.EarthGem, 1, 0), 1.0F);
                    break;

                case 33:
                	// TODO Change to this entityitem = entityDropItem(new ItemStack(MCItems.rocket, 1, 0), 1.0F);
                	entityitem = entityDropItem(new ItemStack(MCItems.FireGem, 1, 0), 1.0F);
                    break;

                case 34:
                	// TODO Change to this entityitem = entityDropItem(new ItemStack(MCItems.atompacket, rand.nextInt(7) + 1, 0), 1.0F);
                	entityitem = entityDropItem(new ItemStack(MCItems.Lolly, 1, 0), 1.0F);
                    break;

                case 35:
                	// TODO Change to this entityitem = entityDropItem(new ItemStack(MCItems.armygem, 1, 0), 1.0F);
                	entityitem = entityDropItem(new ItemStack(MCItems.CaveClub, 1, 0), 1.0F);
                    break;

                case 36:
                    entityitem = entityDropItem(new ItemStack(MCItems.Money, rand.nextInt(24) + 1, 0), 1.0F);
                    break;

                case 37:
                	// TODO Change to this entityitem = entityDropItem(new ItemStack(MCItems.armygem, 1, 0), 1.0F);
                	entityitem = entityDropItem(new ItemStack(MCItems.Popsicle, 1, 0), 1.0F);
                    break;

                case 38:
                	// TODO Change to this entityitem = entityDropItem(new ItemStack(MCItems.horseheadgem, 1, 0), 1.0F);
                	entityitem = entityDropItem(new ItemStack(MCItems.HealingGem, 1, 0), 1.0F);
                    break;

                case 39:
                    entityitem = entityDropItem(new ItemStack(Items.GOLD_INGOT, 1, 0), 1.0F);
                    break;

                case 40:
                    entityitem = entityDropItem(new ItemStack(Items.DIAMOND, 1, 0), 1.0F);
                    break;

                case 41:
                	// TODO Change to this entityitem = entityDropItem(new ItemStack(MCItems.raygun, 1, 0), 1.0F);
                	entityitem = entityDropItem(new ItemStack(MCItems.GooDonut, 1, 0), 1.0F);
                    break;

                case 42:
                    entityitem = entityDropItem(new ItemStack(MCItems.Money, rand.nextInt(49) + 1, 0), 1.0F);
                    break;

                default:
                    entityitem = entityDropItem(new ItemStack(MCItems.Money, rand.nextInt(3) + 1, 0), 1.0F);
                    break;
            }

            double d = -MathHelper.sin((rotationYaw * (float)Math.PI) / 180F);
            double d1 = MathHelper.cos((rotationYaw * (float)Math.PI) / 180F);
            entityitem.posX = ((EntityPlayer)(entityplayer)).posX + d * 0.5D;
            entityitem.posZ = ((EntityPlayer)(entityplayer)).posZ + d1 * 0.5D;
            entityitem.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.15F;
            entityitem.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.15F;
        }
    }

    /**
     * Checks if this entity is inside of an opaque block
     */
    public boolean isEntityInsideOpaqueBlock()
    {
        return health <= 0;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setFloat("ModelSize", modelsize);
        nbttagcompound.setInteger("Age", age);
        nbttagcompound.setInteger("DeathTimer", deathtimer);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        modelsize = nbttagcompound.getFloat("ModelSize");
        age = nbttagcompound.getInteger("Age");
        deathtimer = nbttagcompound.getInteger("DeathTimer");
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor_double(posZ);
        int l = worldObj.getBlockLightOpacity(new BlockPos(i, j, k));
        int i1 = Block.getIdFromBlock((worldObj.getBlockState(new BlockPos(i, j - 1, k)).getBlock()));
        return true;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    private void smoke()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
            }
        }
    }

    public void playAmbientSound()
    {
        SoundEvent s = getAmbientSound();

        if (s != null)
        {
            // worldObj.playSoundAtEntity(this, s, getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F + (3F - modelsize));
            worldObj.playSound((EntityPlayer) null, getPosition(), s, SoundCategory.NEUTRAL,getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        if (rand.nextInt(5) == 0)
        {
            return MCSoundEvents.SCHLUMP;
        }
        else
        {
            return null;
        }
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.SCHLUMP_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.SCHLUMP_DEATH;
    }

    /* TODO
    public void confetti()
    {
        double d = -MathHelper.sin((((EntityPlayer)(entityplayer)).rotationYaw * (float)Math.PI) / 180F);
        double d1 = MathHelper.cos((((EntityPlayer)(entityplayer)).rotationYaw * (float)Math.PI) / 180F);
        CREEPSEntityTrophy creepsentitytrophy = new CREEPSEntityTrophy(world);
        creepsentitytrophy.setLocationAndAngles(((EntityPlayer)(entityplayer)).posX + d * 3D, ((EntityPlayer)(entityplayer)).posY - 2D, ((EntityPlayer)(entityplayer)).posZ + d1 * 3D, ((EntityPlayer)(entityplayer)).rotationYaw, 0.0F);
        world.spawnEntityInWorld(creepsentitytrophy);
    } */

    /* 
    public void smallconfetti()
    {
        for (int i = 1; i < 20; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                CREEPSFxConfetti creepsfxconfetti = new CREEPSFxConfetti(worldObj, posX + (double)(worldObj.rand.nextFloat() * 4F - worldObj.rand.nextFloat() * 4F), posY + (double)rand.nextInt(4) + 6D, posZ + (double)(worldObj.rand.nextFloat() * 4F - worldObj.rand.nextFloat() * 4F), Item.getItemFromBlock(Block.getBlockById(worldObj.rand.nextInt(99))));
                creepsfxconfetti.renderDistanceWeight = 20D;
                //Alternative not available?
                //creepsfxconfetti.particleMaxAge = rand.nextInt(40) + 30;
                Minecraft.getMinecraft().effectRenderer.addEffect(creepsfxconfetti);
            }
        }
    } */

    /* TODO 
     * Will get destroyed next tick.
     
    public void setDead()
    {
        smoke();
        worldObj.playSoundAtEntity(this, getDeathSound(), getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
        super.setDead();
    } */

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
        giveReward();
        super.onDeath(damagesource);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}
}