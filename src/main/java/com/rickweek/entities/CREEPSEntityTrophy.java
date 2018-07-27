package com.rickweek.entities;

import com.rickweek.init.MCItems;
import com.rickweek.main.MCW;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class CREEPSEntityTrophy extends EntityCreature
{
    public int partytime;
    public int trophylifespan;
    public float spin;
    public String texture;
    
    public CREEPSEntityTrophy(World world)
    {
        super(world);
        texture = "mcw:textures/entity/trophy.png";
        partytime = rand.nextInt(30) + 40;
        trophylifespan = 80;
        setSize(1.0F, 2.5F);
    }
    
    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0D);
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
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (partytime-- > 1)
        {
            MCW.proxy.confettiB(worldObj, this);
        }

        if (trophylifespan-- < 0)
        {
            setDead();
        }

        super.onUpdate();
    }

    /**
     * Checks if this entity is inside of an opaque block
     */
    public boolean isEntityInsideOpaqueBlock()
    {
        return false;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return true;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return null;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return null;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
        for (int i = 0; i < rand.nextInt(25) + 10; i++)
        {
            dropItem(MCItems.Money, 1);
        }

        super.setDead();
    }
}