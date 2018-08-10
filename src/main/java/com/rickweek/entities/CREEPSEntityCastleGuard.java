package com.rickweek.entities;

import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSEntityCastleGuard extends EntityMob
{
    private int angerLevel;
    private int randomSoundDelay;
    public String basetexture;
    public int attackdamage;
    public boolean isSwinging;
    public boolean swingArm;
    public int swingTick;
    public String texture;
    public boolean attacked;
    public float hammerswing;
    public float modelsize;
    static final String guardTextures[] =
    {
        "mcw:textures/entity/castleguard1.png", "mcw:textures/entity/castleguard2.png", "mcw:textures/entity/castleguard3.png", "mcw:textures/entity/castleguard4.png", "mcw:textures/entity/castleguard5.png"
    };

    public CREEPSEntityCastleGuard(World world) {
        super(world);
        angerLevel = 0;
        randomSoundDelay = 0;
        basetexture = guardTextures[rand.nextInt(guardTextures.length)];
        texture = basetexture;
        attacked = false;
        hammerswing = 0.0F;
        modelsize = 1.0F;
        attackdamage = 1;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 0.5D));
        this.tasks.addTask(5, new CREEPSEntityCastleGuard.AIAttackTarget());
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

    }
    
    public void applyEntityAttributes() {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000005D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
    }
    
    public boolean getSwinging()
    {
        return hammerswing != 0.0F;
    }
    
    public float getHammerSwing()
    {
        return hammerswing;
    }
    
    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        if (hammerswing < 0.0F)
        {
            hammerswing += 0.45F;
        }
        else
        {
            hammerswing = 0.0F;
        }

        super.onUpdate();
    }


    public int getMaxSpawnedInChunk() {
        return 2;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("Anger", (short)angerLevel);
        nbttagcompound.setBoolean("Attacked", attacked);
        nbttagcompound.setString("BaseTexture", basetexture);
        nbttagcompound.setFloat("ModelSize", modelsize);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        angerLevel = nbttagcompound.getShort("Anger");
        nbttagcompound.setBoolean("Attacked", attacked);
        nbttagcompound.setString("BaseTexture", basetexture);
        modelsize = nbttagcompound.getFloat("ModelSize");
        texture = basetexture;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        Entity entity = damagesource.getEntity();

        if (entity instanceof EntityPlayer)
        {
            attacked = true;
        }

        return super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity entity, float f)
    {
        if (onGround)
        {
        	
            double d = entity.posX - posX;
            double d2 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt_double(d * d + d2 * d2);
            motionX = (d / (double)f1) * 0.20000000000000001D * (0.58000001192092898D + motionX * 0.20000000298023224D);
            motionZ = (d2 / (double)f1) * 0.20000000000000001D * (0.52200000119209289D + motionZ * 0.20000000298023224D);
            motionY = 0.19500000596246447D;
            fallDistance = -25F;

        }

        if ((double)f > 3D && rand.nextInt(10) == 0)
        {
            double d1 = -MathHelper.sin((rotationYaw * (float)Math.PI) / 180F);
            double d3 = MathHelper.cos((rotationYaw * (float)Math.PI) / 180F);
            motionX += d1 * 0.10999999940395355D;
            motionZ += d3 * 0.10999999940395355D;
            motionY += 0.023000000044703484D;
        } 

        if ((double)f < 2.2999999999999998D - (1.0D - (double)modelsize) && entity.getEntityBoundingBox().maxY > entity.getEntityBoundingBox().minY && entity.getEntityBoundingBox().minY < entity.getEntityBoundingBox().maxY && !(entity instanceof CREEPSEntityCastleGuard))
        {
            if (hammerswing == 0.0F)
            {
                hammerswing = -2.6F;
            }

            entity.attackEntityFrom(DamageSource.causeMobDamage(this), attackdamage);
        }
    }
    
    public class AIAttackTarget extends EntityAIBase {

    	public CREEPSEntityCastleGuard castleguard = CREEPSEntityCastleGuard.this;
    	public AIAttackTarget() {}
    	
		@Override
		public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.castleguard.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.castleguard.getAttackTarget();
            double d0 = this.castleguard.getDistanceSqToEntity(entitylivingbase);

            if (d0 < 4.0D) {
            	this.castleguard.attackEntityAsMob(entitylivingbase);
                this.castleguard.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            else if (d0 < 256.0D) {
            	castleguard.attackEntity(entitylivingbase, (float)d0);
                this.castleguard.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            }
            else {
                this.castleguard.getNavigator().clearPathEntity();
                this.castleguard.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 0.5D);
            }
        }
    }

    private void becomeAngryAt(Entity entity) {
        this.attackEntity(entity, 1);
        angerLevel = 400 + rand.nextInt(400);
        randomSoundDelay = rand.nextInt(40);
    }

    // Ritorna il danno riprodotto dal mob quando e vivo
    protected SoundEvent getLivingSound()
    {
        if (attacked && rand.nextInt(5) == 0)
        {
        	return MCSoundEvents.ENTITY_CASTLEGUARD_MAD;
        }

        if (rand.nextInt(12) == 0)
        {
        	return MCSoundEvents.ENTITY_CASTLEGUARD;
        }
        else
        {
            return null;
        }
    }

    // Ritorna il danno riprodotto dal mob quando viene colpito
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_CASTLEGUARD_HURT;
    }

    // Ritorna il danno riprodotto dal mob alla sua morte
    protected SoundEvent getDeathSound()
    {
    	return MCSoundEvents.ENTITY_CASTLEGUARD_DEATH;
    }

    // Alla morte dell'entita
    public void onDeath(DamageSource damagesource) {
        super.onDeath(damagesource);
        if(!worldObj.isRemote)
            if (rand.nextInt(3) == 0) 
                dropItem(MCItems.Donut, rand.nextInt(2) + 1);
            
    }
}