package com.rickweek.entities;

import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
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

    public CREEPSEntityCastleGuard(World world)
    {
        super(world);
        angerLevel = 0;
        randomSoundDelay = 0;
        basetexture = guardTextures[rand.nextInt(guardTextures.length)];
        texture = basetexture;
        attacked = false;
        hammerswing = 0.0F;
        modelsize = 1.0F;
        attackdamage = 1;
    }
    
    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000005D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
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

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
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

            //attackTime = 20;
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), attackdamage);
        }
    }

    private void becomeAngryAt(Entity entity)
    {
        this.attackEntity(entity, 1);
        angerLevel = 400 + rand.nextInt(400);
        randomSoundDelay = rand.nextInt(40);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
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

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_CASTLEGUARD_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
    	return MCSoundEvents.ENTITY_CASTLEGUARD_DEATH;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
        super.onDeath(damagesource);

        if (rand.nextInt(3) == 0)
        {
            dropItem(MCItems.Donut, rand.nextInt(2) + 1);
        }
    }
}