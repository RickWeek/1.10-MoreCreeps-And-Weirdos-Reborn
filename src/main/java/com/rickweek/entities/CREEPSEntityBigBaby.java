package com.rickweek.entities;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.rickweek.entities.ai.EntityBigBabyAI;
import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;
import com.rickweek.main.Reference;

public class CREEPSEntityBigBaby extends EntityMob
{
    public int skinDirection;
    public int skin;
    public int skinTimer;
    public float modelsize;
    public float hammerswing;
    public ResourceLocation texture;
    public int attackTime;

    public CREEPSEntityBigBaby(World world) {
        super(world);
        texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_BIGBABY0);
        setSize(width * 3.25F, height * 3.55F);
        skinDirection = 1;
        skinTimer = 0;
        skin = 0;
        modelsize = 6.5F;
        hammerswing = 0.0F;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityBigBabyAI(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
    }
    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000517232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3D);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (hammerswing < 0.0F)
        {
            hammerswing += 0.1000055F;
        }
        else
        {
            hammerswing = 0.0F;
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (skinTimer++ > 60)
        {
            skinTimer = 0;
            skin += skinDirection;

            if (skin == 4 || skin == 0)
            {
                skinDirection *= -1;
            }

            if (this.getAttackTarget() != null)
            {
                skin = 0;
            }

            if (skin < 0 || skin > 4)
            {
                skin = 0;
            }

            texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + (new StringBuilder()).append(Reference.TEXTURE_BIGBABY).append(String.valueOf(skin)).append(".png").toString());
        }

        super.onLivingUpdate();
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    public void attackEntity(Entity entity, float f)
    {
        if (onGround)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
            motionX = (d / (double)f1) * 0.20000000000000001D * (0.850000011920929D + motionX * 0.20000000298023224D);
            motionZ = (d1 / (double)f1) * 0.20000000000000001D * (0.80000001192092896D + motionZ * 0.20000000298023224D);
            motionY = 0.14400000596246448D;
            fallDistance = -25F;

            if ((double)f < 6D && (double)f > 3D && rand.nextInt(5) == 0)
            {
                double d2 = -MathHelper.sin((rotationYaw * (float)Math.PI) / 180F);
                double d3 = MathHelper.cos((rotationYaw * (float)Math.PI) / 180F);
                motionX += d2 * 0.25D;
                motionZ += d3 * 0.25D;
                motionY += 0.15000000596046448D;
            }
        }

        if ((double)f < (double)modelsize * 0.69999999999999996D + 1.5D && entity.getEntityBoundingBox().maxY > getEntityBoundingBox().minY && entity.getEntityBoundingBox().minY < getEntityBoundingBox().maxY && rand.nextInt(10) == 0)
        {
            if (hammerswing == 0.0F)
            {
                hammerswing = -1.1F;
            }

            attackTime = 20;
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3);
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        Entity entity = damagesource.getEntity();
        texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_BIGBABYBOP);
        skinTimer = 45;

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
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean processInteract(EntityPlayer entityplayer, EnumHand hand, @Nullable ItemStack stack)
    {
        // ItemStack itemstack = entityplayer.inventory.getCurrentItem();

        if (stack != null && stack.getItem() == MCItems.EmpyJar)
        {
            if (modelsize < 1.0F)
            {
                setDead();
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(MCItems.FullJar));
                MCW.proxy.addChatMessage("Now turn that Baby into a Schlump on the floor");
                worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BIGBABY_TAKEHOME, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            }
            else
            {
            	MCW.proxy.addChatMessage("That baby is too large");
                worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BIGBABY_SHRINK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            }
            return true;
        } else 
        	return super.processInteract(entityplayer, hand, stack);
    }

    public float getEyeHeight()
    {
        return height * 0.15F;
    }


    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
    
    public void playAmbientSound()
    {
        SoundEvent s = getAmbientSound();

        if (s != null)
        {
            // worldObj.playSoundAtEntity(this, s, getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F + (6.5F - modelsize) * 2.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), s, SoundCategory.NEUTRAL, getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F + (6.5F - modelsize) * 2.0F);
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        return MCSoundEvents.ENTITY_BIGBABY;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_BIGBABY_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_BIGBABY_HURT;
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
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
    	if(!worldObj.isRemote)
    	{
    		if(rand.nextInt(10) == 1)
    			dropItem(Items.PORKCHOP, 1);
    		else
    			dropItem(MCItems.BabyFood, rand.nextInt(2) + 1);
    	}
        super.onDeath(damagesource);
    }
}