package com.rickweek.entities;

import javax.annotation.Nullable;

import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CREEPSEntitySneakySal extends EntityMob
{
	World world;
	EntityPlayer entityplayer;
    public int salslots[];
    public static final Item[] salitems;
    public static final int salprices[] =
    {
        10, 200, 100, 20, 175, 150, 225, 50, 350, 100,
        150, 10, 200, 150, 250
    };
    public static final String saldescriptions[] =
    {
        "BLORP COLA", "ARMY GEM", "HORSE HEAD GEM", "BAND AID", "SHRINK RAY", "EXTINGUISHER", "GROW RAY", "FRISBEE", "LIFE GEM", "GUN",
        "RAYGUN", "POPSICLE", "EARTH GEM", "FIRE GEM", "SKY GEM"
    };
    public static final ItemStack itemstack[];
    private boolean foundplayer;
    protected Entity playerToAttack;

    /**
     * returns true if a creature has attacked recently only used for creepers and skeletons
     */
    protected boolean hasAttacked;
    private float distance;
    public boolean tamed;
    public int basehealth;
    public int tamedfood;
    public int attempts;
    public double dist;
    public double prevdist;
    public int facetime;
    public String basetexture;
    public int rockettime;
    public int rocketcount;
    public int galloptime;
    public String name;
    public int sale;
    public float saleprice = 1.0F - (rand.nextFloat() * 0.25F - rand.nextFloat() * 0.25F);
    public int dissedmax;
    public ItemStack defaultHeldItem;
    private Entity targetedEntity;
    public int bulletTime;
    public float modelsize;
    public boolean shooting;
    public int shootingdelay;
    public int itemused;
    public int itemnew;
    public String texture;
    public double moveSpeed;
    public double attackStrength;
    public double health;

    public CREEPSEntitySneakySal(World world)
    {
        super(world);
        salslots = new int[30];
        basetexture = "mcw:textures/entity/sneakysal.png";
        texture = basetexture;
        moveSpeed = 0.65F;
        attackStrength = 3;
        basehealth = rand.nextInt(50) + 50;
        health = basehealth;
        hasAttacked = false;
        foundplayer = false;
        setSize(1.5F, 4F);
        dissedmax = rand.nextInt(4) + 1;
        // defaultHeldItem = new ItemStack(MoreCreepsAndWeirdos.gun, 1);
        sale = rand.nextInt(2000) + 100;
        // TODO saleprice = 0.0F;
        shooting = false;
        shootingdelay = 20;
        modelsize = 1.7F;
        // ((PathNavigateGround)this.getNavigator()).func_179688_b(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.35D));
        tasks.addTask(5, new EntityAIWander(this, 0.35D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 16F));
        tasks.addTask(7, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
    }
    
    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(rand.nextInt(50) + 50);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.24000000417232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
    }

    

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setInteger("Sale", sale);
        nbttagcompound.setFloat("SalePrice", saleprice);
        nbttagcompound.setInteger("DissedMax", dissedmax);
        nbttagcompound.setFloat("ModelSize", modelsize);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        sale = nbttagcompound.getInteger("Sale");
        saleprice = nbttagcompound.getFloat("SalePrice");
        dissedmax = nbttagcompound.getInteger("DissedMax");
        modelsize = nbttagcompound.getFloat("ModelSize");
        // TODO saleprice = 0.0F;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (playerToAttack instanceof CREEPSEntitySneakySal)
        {
            playerToAttack = null;
        }

        super.onUpdate();
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean processInteract(EntityPlayer entityplayer, EnumHand hand, @Nullable ItemStack stack)
    {
        ItemStack itemstack1 = entityplayer.inventory.getCurrentItem();
        
        World worldIn = entityplayer.worldObj;
        
        if(worldIn.isRemote) {
	        entityplayer.addChatMessage(new TextComponentString("\2476The GUI of this mob is still WIP. Stay Tuned."));
	        /* TODO
	        if (dissedmax > 0)
	        {
	            if (saleprice == 0.0F || sale < 1)
	            {
	                restockSal();
	            }
	
	            if (dissedmax > 0 && !(playerToAttack instanceof EntityPlayer))
	            {
	                entityplayer.openGui(MCW.instance, 6, world, (int)this.posX, (int)this.posY, (int)this.posZ);
	                }
	        }
	        */
	        return true;
        } else return super.processInteract(entityplayer, hand, stack);
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        if (dissedmax < 1)
        {
            EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 16D);

            if (entityplayer != null && canEntityBeSeen(entityplayer))
            {
                return entityplayer;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity entity, float f)
    {
        if (dissedmax < 1)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
            motionX = (d / (double)f1) * 0.40000000000000002D * 0.20000000192092895D + motionX * 0.18000000098023225D;
            motionZ = (d1 / (double)f1) * 0.40000000000000002D * 0.14000000192092896D + motionZ * 0.18000000098023225D;

            if ((double)f < 2.7999999999999998D && entity.getEntityBoundingBox().maxY > this.getEntityBoundingBox().minY && entity.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY)
            {
                //attackTime = 10;
                entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) attackStrength);
            }

            super.attackEntityAsMob(entity);
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        Entity entity = damagesource.getEntity();

        if (entity instanceof EntityPlayer)
        {
            dissedmax = 0;
        }

        return super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (shootingdelay-- < 1)
        {
            shooting = false;
        }

        targetedEntity = worldObj.getClosestPlayerToEntity(this, 3D);

        if (targetedEntity != null && (targetedEntity instanceof EntityPlayer) && canEntityBeSeen(targetedEntity))
        {
            float f = rotationYaw;

            for (int i = 0; i < 360; i++)
            {
                rotationYaw = i;
            }

            if (rand.nextInt(4) == 0)
            {
                attackEntity(targetedEntity, 1.0F);
            }
        }

        if (bulletTime-- < 1 && dissedmax < 1)
        {
            bulletTime = rand.nextInt(50) + 25;
            double d = 64D;
            targetedEntity = worldObj.getClosestPlayerToEntity(this, 30D);

            if (targetedEntity != null && canEntityBeSeen(targetedEntity) && (targetedEntity instanceof EntityPlayer) && !isDead && !(targetedEntity instanceof CREEPSEntitySneakySal) && !(targetedEntity instanceof CREEPSEntityRatMan))
            {
                double d2 = targetedEntity.getDistanceSqToEntity(this);

                if (d2 < d * d && d2 > 3D)
                {
                    double d4 = targetedEntity.posX - posX;
                    double d5 = (targetedEntity.getEntityBoundingBox().minY + (double)(targetedEntity.height / 2.0F)) - (posY + (double)(height / 2.0F));
                    double d6 = targetedEntity.posZ - posZ;
                    renderYawOffset = rotationYaw = (-(float)Math.atan2(d4, d6) * 180F) / (float)Math.PI;
                    // worldObj.playSoundAtEntity(this, "morecreeps:bullet", 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
                    world.playSound((EntityPlayer) null, entityplayer.getPosition(), MCSoundEvents.ITEM_BULLET, SoundCategory.PLAYERS, 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
                    shooting = true;
                    shootingdelay = 10;
                    CREEPSEntityBullet creepsentitybullet = new CREEPSEntityBullet(worldObj, this, 0.0F);

                    if (creepsentitybullet != null)
                    {
                        worldObj.spawnEntityInWorld(creepsentitybullet);
                    }
                }
            }
        }

        sale--;

        /* TODO 
        if (rand.nextInt(10) == 0)
        {
            double d1 = -MathHelper.sin((rotationYaw * (float)Math.PI) / 180F);
            double d3 = MathHelper.cos((rotationYaw * (float)Math.PI) / 180F);
            CREEPSFxSmoke creepsfxsmoke = new CREEPSFxSmoke(worldObj, posX + d1 * 0.5D, posY + 2D, posZ + d3 * 0.5D, MoreCreepsAndWeirdos.partWhite, 0.5F, 0.5F);
            creepsfxsmoke.renderDistanceWeight = 15D;
            Minecraft.getMinecraft().effectRenderer.addEffect(creepsfxsmoke);
        } */

        if (dissedmax < 1 && playerToAttack == null)
        {
            findPlayerToAttack();
        }

        super.onLivingUpdate();
    }

    public void restockSal()
    {
        sale = rand.nextInt(2000) + 100;
        saleprice = 1.0F - (rand.nextFloat() * 0.25F - rand.nextFloat() * 0.25F);
        itemnew = rand.nextInt(salitems.length);
        itemused = 0;

        for (int i = 0; i < salitems.length; i++)
        {
            salslots[i] = i;
        }

        for (int j = 0; j < salitems.length; j++)
        {
            int k = rand.nextInt(salitems.length);
            int l = salslots[j];
            salslots[j] = salslots[k];
            salslots[k] = l;
        }
    }

    /**
     * Returns the item that this EntityLiving is holding, if any.
     */
    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }

    private void smoke()
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                double d = rand.nextGaussian() * 0.059999999999999998D;
                double d1 = rand.nextGaussian() * 0.059999999999999998D;
                double d2 = rand.nextGaussian() * 0.059999999999999998D;
                worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height) + (double)i, (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
            }
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        if (rand.nextInt(10) == 0)
        {
            return MCSoundEvents.ENTITY_SNEAKSAL_GREETING;
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
        return MCSoundEvents.ENTITY_SNEAKSAL_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_SNEAKSAL_DEATH;
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
        smoke();

        if (rand.nextInt(10) == 0)
        {
            dropItem(MCItems.Rocket, rand.nextInt(5) + 1);
        }

        super.onDeath(damagesource);
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

    static
    {
        salitems = (new Item[]
                {
                    MCItems.Blorpcola, MCItems.Bandaid, MCItems.LifeGem,
                    MCItems.RayGun, MCItems.Popsicle, MCItems.EarthGem, MCItems.FireGem, MCItems.SkyGem
                });
        itemstack = (new ItemStack[]
                {
                    new ItemStack(MCItems.Blorpcola), new ItemStack(MCItems.Bandaid), new ItemStack(MCItems.LifeGem),
                    new ItemStack(MCItems.RayGun), new ItemStack(MCItems.Popsicle), new ItemStack(MCItems.EarthGem), new ItemStack(MCItems.FireGem), new ItemStack(MCItems.SkyGem)
                });
    }
}