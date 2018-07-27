package com.rickweek.entities;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.Reference;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.SkeletonType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeSnow;

public class CREEPSEntityInvisibleMan extends EntityMob
{
    private static final ItemStack defaultHeldItem;
    private static final Item dropItems[];
    protected double attackRange;
    private int angerLevel;
    private int randomSoundDelay;
    public float modelsize;
    public ResourceLocation texture;
    private UUID angerTargetUUID;

    public CREEPSEntityInvisibleMan(World world)
    {
        super(world);
        texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_INVISIBLEMAN);
        angerLevel = 0;
        attackRange = 16D;
        modelsize = 1.0F;
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STICK));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new AIAttackEntity());
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 0.5D));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
    }
    
    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000517232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
    }
    
    public void onUpdate() {
        super.onUpdate();
        if (motionY > 0.0D)
        {
            motionY -= 0.00033000000985339284D;
        }
    }
    
    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity entity, float f) {
    	if (onGround) {
	        double d = entity.posX - posX;
	        double d1 = entity.posZ - posZ;
	        float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
	        motionX = (d / (double)f1) * 0.2D * 0.800000011920929D + motionX * 0.20000000298023224D;
	        motionZ = (d1 / (double)f1) * 0.2D * 0.800000011920929D + motionZ * 0.20000000298023224D;
	        motionY = 0.20000000596246448D;
    	}
    }
    
    public void setRevengeTarget(@Nullable EntityLivingBase livingBase)
    {
        super.setRevengeTarget(livingBase);

        if (livingBase != null)
        {
            this.angerTargetUUID = livingBase.getUniqueID();
        }
    }

    public class AIAttackEntity extends EntityAIBase {

    	public CREEPSEntityInvisibleMan InviM = CREEPSEntityInvisibleMan.this;
    	public int attackTime;
    	public AIAttackEntity() {}
    	
		@Override
		public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.InviM.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive() && angerLevel > 0;
		}
        public void updateTask()
        {
        	--attackTime;
            EntityLivingBase entitylivingbase = this.InviM.getAttackTarget();
            double d0 = this.InviM.getDistanceSqToEntity(entitylivingbase);

            if (d0 < 4.0D)
            {
                if (this.attackTime <= 0)
                {
                    this.attackTime = 10;
                    entitylivingbase.motionX = motionX * 3D;
                    entitylivingbase.motionY = rand.nextFloat() * 2.533F;
                    entitylivingbase.motionZ = motionZ * 3D;
                    this.InviM.attackEntityAsMob(entitylivingbase);// or entitylivingbase.attackEntityFrom blablabla...
                }
                
                this.InviM.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            else if (d0 < 256.0D)
            {
                // ATTACK ENTITY JUST CALLED HERE :D
            	InviM.attackEntity(entitylivingbase, (float)d0);
                this.InviM.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            }
            else
            {
                this.InviM.getNavigator().clearPathEntity();
                this.InviM.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 0.5D);
            }
        }
    }
    
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("Anger", (short)angerLevel);
        nbttagcompound.setFloat("ModelSize", modelsize);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        angerLevel = nbttagcompound.getShort("Anger");
        modelsize = nbttagcompound.getFloat("ModelSize");
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

                    if (entity1 instanceof CREEPSEntityInvisibleMan)
                    {
                    	CREEPSEntityInvisibleMan creepsentityinvisibleman = (CREEPSEntityInvisibleMan)entity1;
                    	creepsentityinvisibleman.becomeAngryAt(entity);
                    }
                }

                becomeAngryAt(entity);
            }
    	}

        return super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    private void becomeAngryAt(Entity entity) {
        this.setAttackTarget((EntityLivingBase)entity);
        angerLevel = 40 + rand.nextInt(40);
        texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_INVISIBLEMAN_MAD);
    }
    
    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected boolean findPlayerToAttack()
    {
        if (angerLevel == 0)
        {
            return false;
        }
        else
        {
            texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_INVISIBLEMAN_MAD);
            return true;
        }
    }
    
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STICK));
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        super.onInitialSpawn(difficulty, livingdata);
        return livingdata;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    public boolean canAttackEntity22(Entity entity, float i)
    {
        if (entity instanceof EntityPlayer)
        {
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(25D, 25D, 25D));

            for (int j = 0; j < list.size(); j++)
            {
                Entity entity1 = (Entity)list.get(j);

                if (entity1 instanceof CREEPSEntityInvisibleMan)
                {
                    CREEPSEntityInvisibleMan creepsentityinvisibleman = (CREEPSEntityInvisibleMan)entity1;
                    creepsentityinvisibleman.becomeAngryAt(entity);
                }
            }

            becomeAngryAt(entity);
        }

        return super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        if (angerLevel == 0)
        {
            return MCSoundEvents.ENTITY_INVISIBLEMAN;
        }
        else
        {
            return MCSoundEvents.ENTITY_INVISIBLEMAN_ANGRY;
        }
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_INVISIBLEMAN_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_INVISIBLEMAN_DEATH;
    }

    /**
     * Returns the item that this EntityLiving is holding, if any.
     */
    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected Item getDropItem()
    {
        return dropItems[rand.nextInt(dropItems.length)];
    }

    static
    {
        defaultHeldItem = new ItemStack(Items.STICK, 1);
        dropItems = (new Item[]
                {
                    Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.APPLE, Items.BREAD, Items.BREAD, Items.CAKE, Items.STICK, Items.CAKE,
                    Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.STICK,
                    Items.STICK, Items.STICK, Items.STICK, Items.STICK, Items.GOLD_INGOT, Items.STICK, Items.STICK, Items.STICK, Items.APPLE, Items.APPLE,
                    Items.STICK
                });
    }
   
    public boolean isAngry()
    {
        return this.angerLevel > 0;
    }
    
    static class AIHurtByAggressor extends EntityAIHurtByTarget
    {
        public AIHurtByAggressor(CREEPSEntityInvisibleMan p_i45828_1_)
        {
            super(p_i45828_1_, true, new Class[0]);
        }

        protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn)
        {
            super.setEntityAttackTarget(creatureIn, entityLivingBaseIn);

            if (creatureIn instanceof CREEPSEntityInvisibleMan)
            {
                ((CREEPSEntityInvisibleMan)creatureIn).becomeAngryAt(entityLivingBaseIn);
            }
        }
    }

    static class AITargetAggressor extends EntityAINearestAttackableTarget<EntityPlayer>
    {
        public AITargetAggressor(CREEPSEntityInvisibleMan p_i45829_1_)
        {
            super(p_i45829_1_, EntityPlayer.class, true);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return ((CREEPSEntityInvisibleMan)this.taskOwner).isAngry() && super.shouldExecute();
        }
    }
}