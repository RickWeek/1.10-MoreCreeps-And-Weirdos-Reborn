package com.rickweek.entities;

import java.util.Random;

import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;

import net.minecraft.block.Block;
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
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSEntityRobotTodd extends EntityMob
{
    public static Random rand = new Random();
    protected double attackRange;
    private int angerLevel;
    public boolean jumping;
    public float robotsize;
    public int texswitch;
    public int texnumber;
    public float modelspeed;
    public String texture;

    public CREEPSEntityRobotTodd(World world)
    {
        super(world);
        texnumber = 0;
        texture = "mcw:textures/entity/robottodd1.png";
        angerLevel = 0;
        attackRange = 16D;
        jumping = false;
        robotsize = 2.5F;
        //yOffset *= 1.5F;
        modelspeed = 0.4F;
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIBreakDoor(this));
        
        tasks.addTask(2, new AIAttackEntity()); 
        
        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.061D));
        tasks.addTask(5, new EntityAIWander(this, 0.25D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
        tasks.addTask(6, new EntityAIWatchClosest(this, CREEPSEntityRobotTed.class, 8F));
        tasks.addTask(7, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, CREEPSEntityRobotTed.class, true));
    }

    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setFloat("RobotSize", robotsize);
        nbttagcompound.setFloat("ModelSpeed", modelspeed);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        robotsize = nbttagcompound.getFloat("RobotSize");
        modelspeed = nbttagcompound.getFloat("ModelSpeed");
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(modelspeed);
        super.onLivingUpdate();

        if (texswitch++ > 40)
        {
            if (texnumber++ > 1)
            {
                texnumber = 0;
            }

            if (texnumber == 0)
            {
                texture = "mcw:textures/entity/robottodd1.png";
            }

            if (texnumber == 1)
            {
                texture = "mcw:textures/entity/robottodd2.png";
            }
        }
    }

    
    protected void attackEntity(Entity entity, float f)
    {
        if (onGround)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
            motionX = (d / (double)f1) * 0.5D * 0.40000000192092894D + motionX * 0.20000000098023224D;
            motionZ = (d1 / (double)f1) * 0.5D * 0.30000000192092896D + motionZ * 0.20000000098023224D;
            motionY = 0.35000000196046449D;
            jumping = true;
        }
    }

    //to make attackEntity works in 1.8
    public class AIAttackEntity extends EntityAIBase {

    	public CREEPSEntityRobotTodd robot = CREEPSEntityRobotTodd.this;
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
    
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getLivingSound()
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
        return MCSoundEvents.ENTITY_ROBOTTODD_DEATH;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
    	if(!worldObj.isRemote)
    	{
    		dropItem(MCItems.Battery, rand.nextInt(2) + 1);
    	}
        super.onDeath(damagesource);
    }
}