package com.rickweek.entities;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

public class CREEPSEntityRobotTed extends EntityMob
{
    public static Random rand = new Random();
    protected double attackRange;
    private int angerLevel;
    public boolean jumping;
    public float robotsize;
    public int floattimer;
    public int floatdir;
    protected Entity playerToAttack;
    protected boolean hasAttacked;
    public double modelspeed;
    public double floatcycle;
    public double floatmaxcycle;

    public String texture;
    
    public CREEPSEntityRobotTed(World world)
    {
        super(world);
        texture = "mcw:textures/entity/robotted.png";
        angerLevel = 0;
        attackRange = 16D;
        jumping = false;
        robotsize = 2.5F;
        setSize(width * (robotsize * 0.8F), height * (robotsize * 0.8F));
        modelspeed = 0.61F;
        floattimer = 0;
        floatdir = 1;
        floatcycle = 0.0D;
        floatmaxcycle = 0.10499999672174454D;
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIBreakDoor(this));
        
        tasks.addTask(2, new AIAttackEntity()); 
        
        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.061D));
        tasks.addTask(5, new EntityAIWander(this, 0.25D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        tasks.addTask(6, new EntityAIWatchClosest(this, CREEPSEntityRobotTodd.class, 8F));
        tasks.addTask(7, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, CREEPSEntityRobotTodd.class, true));
    }

    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000517232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
    }

    public float getEyeHeight()
    {
        return 2.0F;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setFloat("RobotSize", robotsize);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        robotsize = nbttagcompound.getFloat("RobotSize");
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (modelspeed < 0.23000000517232513D)
        {
            modelspeed = 0.23000000517232513D;
        }

        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(modelspeed);
        isJumping = false;

        if(worldObj.isRemote)
        {
        	MCW.proxy.robotTedSmoke(worldObj, posX, posY, posZ, floattimer, modelspeed);
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (isEntityInsideOpaqueBlock())
        {
            posY += 2.5D;
            floatdir = 1;
            floatcycle = 0.0D;
        }

        if (floatdir > 0)
        {
            floatcycle += 0.017999999225139618D;

            if (floatcycle > floatmaxcycle)
            {
                floatdir = floatdir * -1;
                fallDistance += -1.5F;
            }
        }
        else
        {
            floatcycle -= 0.0094999996945261955D;

            if (floatcycle < -floatmaxcycle)
            {
                floatdir = floatdir * -1;
                fallDistance += -1.5F;
            }
        }

        super.onUpdate();
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity entity, float f)
    {
        if (entity instanceof EntityPlayer)
        {
            double d = getDistanceToEntity(entity);

            if (posY > entity.posY && d < 6D)
            {
                motionY = -0.02500000037252903D;
            }

            if (posY < entity.posY - 0.5D && d < 6D)
            {
                motionY = 0.043999999761581421D;
            }

            if (d < 3D)
            {
                double d2 = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
                double d4 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);
                motionX = -(d2 * 0.20000000298023224D);
                motionZ = -(d4 * 0.20000000298023224D);

                if (posY > entity.posY)
                {
                    motionY = -0.070000000298023224D;
                }
            }
        }

        fallDistance = -25F;
        double d1 = entity.posX - posX;
        double d3 = entity.posZ - posZ;
        float f1 = MathHelper.sqrt_double(d1 * d1 + d3 * d3);
        motionX = (d1 / (double)f1) * 0.5D * 0.35000000192092895D + motionX * 0.20000000098023224D;
        motionZ = (d3 / (double)f1) * 0.5D * 0.25000000192092897D + motionZ * 0.20000000098023224D;
        jumping = true;
    }

    //to make attackEntity works in 1.8
    public class AIAttackEntity extends EntityAIBase {

    	public CREEPSEntityRobotTed robot = CREEPSEntityRobotTed.this;
    	public int attackTime;
    	public AIAttackEntity() {}
    	
		@Override
		public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.robot.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}
        public void updateTask()
        {
        	--attackTime;
            EntityLivingBase entitylivingbase = this.robot.getAttackTarget();
            double d0 = this.robot.getDistanceSqToEntity(entitylivingbase);

            if (d0 < 4.0D)
            {
                if (this.attackTime <= 0)
                {
                    this.attackTime = 40;
                    this.robot.attackEntityAsMob(entitylivingbase);// or entitylivingbase.attackEntityFrom blablabla...
                }
                
                this.robot.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            else if (d0 < 256.0D)
            {
                // ATTACK ENTITY JUST CALLED HERE :D
            	robot.attackEntity(entitylivingbase, (float)d0);
                this.robot.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            }
            else
            {
                this.robot.getNavigator().clearPathEntity();
                this.robot.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 0.5D);
            }
        }
    }
    
    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        Entity entity = damagesource.getEntity();

        if (entity != null)
        {
            double d = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
            double d1 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);
            motionX = d * 1.25D;
            motionZ = d1 * 1.25D;

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
        else
        {
            return false;
        }
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        return MCSoundEvents.ENTITY_TEDINSULT;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_ROBOTTODD_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_TEDINSULT_DEATH;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
        if(!worldObj.isRemote)
        {
             for (int i = 0; i < 4; i++)
            {
                CREEPSEntityEvilLight creepsentityevillight = new CREEPSEntityEvilLight(worldObj);
                creepsentityevillight.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);

                if (damagesource != null)
                {
                    creepsentityevillight.motionX = -(rand.nextFloat() * 2.0F);
                    creepsentityevillight.motionZ = -(rand.nextFloat() * 2.0F);
                    creepsentityevillight.lifespan = 15;
                }
                    worldObj.spawnEntityInWorld(creepsentityevillight);
            } 

            dropItem(MCItems.Ram16K, rand.nextInt(2) + 1);
        }
        super.onDeath(damagesource);
    }
}