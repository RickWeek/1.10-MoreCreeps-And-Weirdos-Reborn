package com.rickweek.entities;

import java.util.List;

import com.rickweek.init.MCSoundEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSEntityRockMonster extends EntityMob
{
    private static final Item dropItems[];
    protected double attackRange;
    private int angerLevel;
    public float modelsize;
    public String texture;

    public CREEPSEntityRockMonster(World world)
    {
        super(world);
        texture = "mcw:textures/entity/rockmonster.png";
        angerLevel = 0;
        attackRange = 16D;
        setSize(width * 3.25F, height * 3.25F);
        height = 4F;
        width = 3F;
        modelsize = 3F;
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(2, new AIAttackEntity()); 
        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.35D));
        tasks.addTask(5, new EntityAIWander(this, 0.35D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 16F));
        tasks.addTask(7, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
    }
    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5D);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(this.getAttackTarget() != null ? 0.23000000617232513D : 0.23000000417232513D);
        super.onUpdate();

        if (motionY > 0.0D)
        {
            motionY -= 0.00033000000985339284D;
        }
        if (angerLevel > 0)
        {
            angerLevel--;
        }
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity entity, float f)
    {
        double d = entity.posX - posX;
        double d1 = entity.posZ - posZ;
        float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
        motionX = (d / (double)f1) * 0.5D * 0.30000000192092896D + motionX * 0.38000000098023223D;
        motionZ = (d1 / (double)f1) * 0.5D * 0.17000000192092896D + motionZ * 0.38000000098023223D;
    }
    
    public class AIAttackEntity extends EntityAIBase {

    	public CREEPSEntityRockMonster rockM = CREEPSEntityRockMonster.this;
    	public int attackTime;
    	public AIAttackEntity() {}
    	
		@Override
		public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.rockM.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive() && angerLevel > 0;
		}
        public void updateTask()
        {
        	--attackTime;
            EntityLivingBase entitylivingbase = this.rockM.getAttackTarget();
            double d0 = this.rockM.getDistanceSqToEntity(entitylivingbase);

            if (d0 < 4.0D)
            {
                if (this.attackTime <= 0)
                {
                    this.attackTime = 10;
                    entitylivingbase.motionX = motionX * 3D;
                    entitylivingbase.motionY = rand.nextFloat() * 2.533F;
                    entitylivingbase.motionZ = motionZ * 3D;
                    this.rockM.attackEntityAsMob(entitylivingbase);// or entitylivingbase.attackEntityFrom blablabla...
                }
                
                this.rockM.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            else if (d0 < 256.0D)
            {
                // ATTACK ENTITY JUST CALLED HERE :D
            	rockM.attackEntity(entitylivingbase, (float)d0);
                this.rockM.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            }
            else
            {
                this.rockM.getNavigator().clearPathEntity();
                this.rockM.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 0.5D);
            }
        }
    }
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setFloat("ModelSize", modelsize);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        modelsize = nbttagcompound.getFloat("ModelSize");
    }
    
    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
    	Entity entity = damagesource.getEntity();
    	if(entity != null)
    	{
            if (entity instanceof EntityPlayer)
            {
                List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(32D, 32D, 32D));

                for (int j = 0; j < list.size(); j++)
                {
                    Entity entity1 = (Entity)list.get(j);

                    if (entity1 instanceof CREEPSEntityRockMonster)
                    {
                        CREEPSEntityRockMonster creepsentityrockmonster = (CREEPSEntityRockMonster)entity1;
                        creepsentityrockmonster.becomeAngryAt(entity);
                    }
                }

                becomeAngryAt(entity);
            }
    	}

        return super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    private void becomeAngryAt(Entity entity)
    {
        this.setAttackTarget((EntityLivingBase) entity);
        angerLevel = 400 + rand.nextInt(400);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        return MCSoundEvents.ENTITY_ROCKMONSTER;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_ROCKMONSTER_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_ROCKMONSTER_DEATH;
    }

    /**
     * Plays living's sound at its position
     */
    public void playLivingSound()
    {
        SoundEvent s = getAmbientSound();

        if (s != null)
        {
            worldObj.playSound((EntityPlayer) null, getPosition(), s, SoundCategory.NEUTRAL, getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F + (3F - modelsize));
        }
    }

    // TODO
    /* 
    public void onDeath(DamageSource damagesource)
    {
        Entity entity = damagesource.getEntity();

        if (entity != null && (entity instanceof EntityPlayer) && !((EntityPlayerMP)entity).getStatFile().hasAchievementUnlocked(MoreCreepsAndWeirdos.achieverockmonster))
        {
            worldObj.playSoundAtEntity(entity, "morecreeps:achievement", 1.0F, 1.0F);
            ((EntityPlayer) entity).addStat(MoreCreepsAndWeirdos.achieverockmonster, 1);
        }

        super.onDeath(damagesource);
    } */

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    public Item getDropItem()
    {
    	return dropItems[rand.nextInt(dropItems.length)];
    }

    static
    {
        dropItems = (new Item[]
                {
                    Item.getItemFromBlock(Blocks.COBBLESTONE), Item.getItemFromBlock(Blocks.GRAVEL), Item.getItemFromBlock(Blocks.COBBLESTONE), Item.getItemFromBlock(Blocks.GRAVEL), Item.getItemFromBlock(Blocks.IRON_ORE), Item.getItemFromBlock(Blocks.MOSSY_COBBLESTONE)
                });
    }
}