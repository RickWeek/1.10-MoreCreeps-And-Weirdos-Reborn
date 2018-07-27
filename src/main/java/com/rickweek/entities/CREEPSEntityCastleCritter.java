package com.rickweek.entities;

import com.rickweek.init.MCSoundEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class CREEPSEntityCastleCritter extends EntityMob
{
    protected double attackrange;
    protected int attack;
    public float modelsize;
    public String texture;
    public int attackTime;

    public CREEPSEntityCastleCritter(World world)
    {
        super(world);
        texture = "mcw:textures/entity/castlecritter.png";
        setSize(0.6F, 0.6F);
        attack = 1;
        attackrange = 16D;
        modelsize = 1.6F;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 0.5D));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        // Not need this this.targetTasks.addTask(0, new CREEPSEntityCastleCritter.AIAttackEntity());
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.24D);
    	
    }

    /**
     * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
     * Args: x, y, z
     */
    public float func_180484_a(BlockPos bp)
    {
        if (worldObj.getBlockState(bp.down()).getBlock() == Blocks.DOUBLE_STONE_SLAB || worldObj.getBlockState(bp.down()) == Blocks.STONE_SLAB)
        {
            return 10F;
        }
        else
        {
            return -(float)bp.getY();
        }
    }
    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        Entity entity = damagesource.getEntity();

        if (super.attackEntityFrom(DamageSource.causeMobDamage(this), i))
        {
            if (entity != this && worldObj.getDifficulty() != EnumDifficulty.PEACEFUL)
            {
                this.setRevengeTarget((EntityLivingBase) entity);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity entity, float f)
    {
        if (onGround)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
            motionX = (d / (double)f1) * 0.20000000000000001D * (0.80000001192092896D + motionX * 0.20000000298023224D);
            motionZ = (d1 / (double)f1) * 0.20000000000000001D * (0.75000001192092891D + motionZ * 0.20000000298023224D);
            motionY = 0.10000000596246449D;
            fallDistance = -25F;

            if (rand.nextInt(5) == 0)
            {
                double d2 = -MathHelper.sin((rotationYaw * (float)Math.PI) / 180F);
                double d3 = MathHelper.cos((rotationYaw * (float)Math.PI) / 180F);
                motionX += d2 * 0.64999997615814209D;
                motionZ += d3 * 0.64999997615814209D;
            }
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        if (rand.nextInt(5) == 0)
        {
            return MCSoundEvents.ENTITY_CASTLECRITTER;
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
        return MCSoundEvents.ENTITY_CASTLECRITTER_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_CASTLECRITTER_DEATH;
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
                dropItem(Items.BONE, rand.nextInt(3) + 1);
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