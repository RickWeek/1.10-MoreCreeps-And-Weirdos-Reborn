package com.rickweek.entities;

import com.rickweek.init.MCSoundEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class CREEPSEntityRatMan extends EntityMob
{
    protected double attackrange;
    protected int attack;
    public boolean jumper;
    public float modelsize;
    public float modelspeed;
    public float stepHeight;
    public String texture;

    public CREEPSEntityRatMan(World world)
    {
        super(world);
        texture = "mcw:textures/entity/ratman.png";
        attack = 1;
        attackrange = 16D;
        jumper = false;
        modelsize = 0.75F;
        modelspeed = 0.61F;
        stepHeight = 1.0F;
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIBreakDoor(this));
        tasks.addTask(2, new EntityAIAttackMelee(this, 0.25D, false));
        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.25D));
        tasks.addTask(4, new EntityAIMoveThroughVillage(this, 0.25D, false));
        tasks.addTask(5, new EntityAIWander(this, 0.25D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        tasks.addTask(6, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(7D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.025D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
    }

    


    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return 0.5D;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
    	
    	if (modelspeed < 0.05F)
        {
            modelspeed = 0.05F;
        }
    	
        super.onLivingUpdate();

        if (handleWaterMovement())
        {
            motionY = 0.15999999642372131D;
        }
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    //the old "getBlockPathHeight" or something called like this
    public float func_180484_a(BlockPos bp)
    {
        if (worldObj.getBlockState(bp.down()).getBlock() == Blocks.SAND || worldObj.getBlockState(bp.down()).getBlock() == Blocks.GRAVEL)
        {
            return 10F;
        }
        else
        {
            return -(float)bp.getY();
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setFloat("ModelSpeed", modelspeed);
        nbttagcompound.setFloat("ModelSize", modelsize);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        modelspeed = nbttagcompound.getFloat("ModelSpeed");
        modelsize = nbttagcompound.getFloat("ModelSize");
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        if (rand.nextInt(2) == 0)
        {
            return MCSoundEvents.ENTITY_RATMAN;
        }
        else
        {
            return MCSoundEvents.ENTITY_RATMAN_SCRATCH;
        }
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_RATMAN_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_RATMAN_HURT;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
    	if(!worldObj.isRemote)
    	{
            if (rand.nextInt(10) == 0)
            {
                dropItem(Items.PORKCHOP, rand.nextInt(3) + 1);
            }

            if (rand.nextInt(10) == 0)
            {
                dropItem(Items.WHEAT_SEEDS, rand.nextInt(3) + 1);
            }
    	}

        super.onDeath(damagesource);
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }
}