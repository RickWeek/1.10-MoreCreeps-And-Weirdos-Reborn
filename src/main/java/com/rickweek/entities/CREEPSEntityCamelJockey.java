package com.rickweek.entities;

import com.rickweek.init.MCSoundEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSEntityCamelJockey extends EntityMob
{
    protected double attackrange;
    public boolean bone;
    protected int attack;
    public String texture;
    public float goatsize;

    /** Entity motion Y */
    public double motionY;
    public int eaten;
    public boolean hungry;
    public int hungrytime;
    public int goatlevel;
    public double waterX;
    public double waterY;
    public double waterZ;
    public boolean findwater;
    public int spittimer;
    public float modelsize;
    public double health;
    public double speed;
    public double strength;

    public CREEPSEntityCamelJockey(World world)
    {
        super(world);
        bone = false;
        texture = "mcw:textures/entity/jockey.png";
        setSize(width * 0.6F, height * 0.6F);
        attackrange = 16D;
        hungry = false;
        findwater = false;
        hungrytime = rand.nextInt(100) + 10;
        goatlevel = 1;
        spittimer = 50;
        modelsize = 0.6F;
    }

    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000005D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
    }

    /* TODO
    /**
     * Called to update the entity's position/logic.
     
    public void onUpdate()
    {
        super.onUpdate();

        if (ridingEntity == null)
        {
            for (int i = 0; i < worldObj.loadedEntityList.size(); i++)
            {
                Entity entity = (Entity)worldObj.loadedEntityList.get(i);

                if (((entity instanceof CREEPSEntityCamel) || entity.riddenByEntity == null) && (entity instanceof CREEPSEntityCamel) && entity.riddenByEntity == null && !((CREEPSEntityCamel)entity).tamed)
                {
                    double d = entity.getDistance(posX, posY, posZ);
                    CREEPSEntityCamel creepsentitycamel = (CREEPSEntityCamel)entity;

                    if (d < 4D && entity.riddenByEntity == null)
                    {
                        mountEntity(entity);
                    }

                    creepsentitycamel.interest = 0;
                    creepsentitycamel.tamed = false;
                    creepsentitycamel.name = "";

                    if (d < 16D && creepsentitycamel.canEntityBeSeen(this))
                    {
                        this.attackEntity(creepsentitycamel, 0);
                    }
                }
            }
        }
        else
        {
            rotationYaw = ridingEntity.rotationYaw;
        }
    }

    
     * Returns the Y Offset of this entity.
     
    public double getYOffset()
    {
        if (ridingEntity instanceof CREEPSEntityCamel)
        {
            return (double)(this.getYOffset() + 1.5F);
        }
        else
        {
            return (double)this.getYOffset();
        }
    }

    public void updateRiderPosition()
    {
        riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ);
    }

    */
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
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        Entity entity = damagesource.getEntity();
        hungry = false;

        if (super.attackEntityFrom(DamageSource.causeMobDamage(this), i))
        {
            if (entity != this && worldObj.getDifficulty().getDifficultyId() > 0)
            {
                this.attackEntityAsMob(entity);
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
    	/* TODO
        if (onGround)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
            motionX = (d / (double)f1) * 0.20000000000000001D * (0.850000011920929D + motionX * 0.20000000298023224D);
            motionZ = (d1 / (double)f1) * 0.20000000000000001D * (0.80000001192092896D + motionZ * 0.20000000298023224D);
            motionY = 0.10000000596246449D;
            fallDistance = -25F;
        } */

        if ((double)f < 2D && entity.getEntityBoundingBox().maxY > this.getEntityBoundingBox().minY && entity.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY /* && !(entity instanceof CREEPSEntityCamel)*/)
        {
            //attackTime = 20;
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), attack);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Hungry", hungry);
        nbttagcompound.setFloat("ModelSize", modelsize);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        hungry = nbttagcompound.getBoolean("Hungry");
        modelsize = nbttagcompound.getFloat("ModelSize");
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        if (isRiding() != false)
        {
            return MCSoundEvents.ENTITY_CAMELJOCKEY_GET;
        }
        else
        {
            return MCSoundEvents.ENTITY_CAMELJOCKEY;
        }
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_CAMELJOCKEY_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_CAMELJOCKEY_DEATH;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
        if (rand.nextInt(10) == 0)
        {
            dropItem(Items.PORKCHOP, rand.nextInt(3) + 1);
        }

        if (rand.nextInt(10) == 0)
        {
            dropItem(Items.REEDS, rand.nextInt(3) + 1);
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