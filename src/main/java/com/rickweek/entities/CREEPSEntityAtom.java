package com.rickweek.entities;

import java.util.List;
import java.util.Random;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSEntityAtom extends EntityFlying
{
    private Random random;
    public static Random rand = new Random();
    protected double attackRange;
    private int angerLevel;
    public boolean jumping;
    public float robotsize;
    public int floattimer;
    public int floatdir;
    public float atomsize;
    public int lifespan;

    public CREEPSEntityAtom(World world)
    {
        super(world);
        setRenderDistanceWeight(10D);
        angerLevel = 0;
        attackRange = 16D;
        jumping = false;
        floattimer = 0;
        floatdir = 1;
        atomsize = 1.0F;
        lifespan = rand.nextInt(300) + 200;
        setSize(0.5F, 0.5F);
    }
    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.261D);
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
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
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    /*public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(getBoundingBox().minY);
        int k = MathHelper.floor_double(posZ);
        int l = worldObj.getFullBlockLightValue(i, j, k);
        int i1 = worldObj.getBlockId(i, j - 1, k);
        return i1 != Block.cobblestone.blockID && i1 != Block.wood.blockID && i1 != Block.stairDouble.blockID && i1 != Block.stairSingle.blockID && worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && worldObj.checkIfAABBIsClear(boundingBox) && worldObj.canBlockSeeTheSky(i, j, k) && rand.nextInt(10) == 0 && l > 8;
    }*/

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        int i = (int)posX;
        int j = (int)posY;
        int k = (int)posZ;

        for (int l = 0; (float)l < atomsize; l++)
        {
            double d = (float)i + worldObj.rand.nextFloat();
            double d1 = (float)j + worldObj.rand.nextFloat();
            double d2 = (float)k + worldObj.rand.nextFloat();
            double d3 = d - posX;
            double d4 = d1 - posY;
            double d5 = d2 - posZ;
            double d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            double d7 = 0.5D / (d6 / (double)atomsize + 0.10000000000000001D);
            d7 *= worldObj.rand.nextFloat() * worldObj.rand.nextFloat() + 0.3F;
            d3 *= d7;
            d4 *= d7;
            d5 *= d7;
            worldObj.spawnParticle(EnumParticleTypes.PORTAL, (d + posX * 1.0D) / 2D, (d1 + posY * 1.0D) / 2D + (double)(int)(atomsize / 4F), (d2 + posZ * 1.0D) / 2D, d3, d4, d5);
        }

        /* 
        if (rand.nextInt(6) == 0)
        {
        	if(worldObj.isRemote)
        	{
        		MCW.proxy.foam2(worldObj, this);
        	}
        } */

        if (lifespan-- == 0)
        {
        	if(!worldObj.isRemote)
            worldObj.createExplosion(this, posX, posY + (double)atomsize, posZ, 1.0F, true);
            setDead();
        }

        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(atomsize * 2.0F, atomsize * 2.0F, atomsize * 2.0F));

        for (int j1 = 0; j1 < list.size(); j1++)
        {
            Entity entity = (Entity)list.get(j1);
            float f = getDistanceToEntity(entity);

            if ((entity instanceof CREEPSEntityAtom) && f < 4F)
            {
                // worldObj.playSoundAtEntity(this, "morecreeps:atomsuck", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ATOM_SUCK, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                lifespan += ((CREEPSEntityAtom)entity).lifespan;
                entity.setDead();
                atomsize += ((CREEPSEntityAtom)entity).atomsize - 0.5F;
                setSize(atomsize * 0.6F, atomsize * 0.6F);
                continue;
            }

            if (!(entity instanceof EntityCreature) && !(entity instanceof EntityItem))
            {
                continue;
            }

            if (entity instanceof EntityLivingBase)
            {
            	double targetMoveSpeed = ((EntityLivingBase)entity).getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
            	targetMoveSpeed *= 0.8D;
            }

            float f1 = (0.15F / f) * atomsize;

            if (entity instanceof CREEPSEntityAtom)
            {
                f1++;
            }

            if (entity.posX > posX)
            {
                entity.motionX -= f1;
            }

            if (entity.posX < posX)
            {
                entity.motionX += f1;
            }

            if (entity.posY > posY)
            {
                entity.motionY -= f1;
            }

            if (entity.posY < posY)
            {
                entity.motionY += f1;
            }

            if (entity.posZ > posZ)
            {
                entity.motionZ -= f1;
            }

            if (entity.posZ < posZ)
            {
                entity.motionZ += f1;
            }

            if (rand.nextInt(50) == 0)
            {
                // worldObj.playSoundAtEntity(this, "morecreeps:atomblow", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ATOM_BLOW, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                int k1 = (int)(atomsize / 3F) + 1;
                entity.motionX += rand.nextInt(k1);
                entity.motionY += rand.nextInt(k1);
                entity.motionZ += rand.nextInt(k1);
            }
        }
        super.onLivingUpdate();
        if (rand.nextInt(70) == 0)
        {
            motionX += rand.nextFloat() * 1.0F - 0.5F;
        }

        if (rand.nextInt(50) == 0)
        {
            motionY += rand.nextFloat() * 2.0F - 0.5F;
        }

        if (rand.nextInt(70) == 0)
        {
            motionZ += rand.nextFloat() * 1.0F - 0.5F;
        }

        if (rand.nextInt(10) == 0)
        {
            motionY = -(atomsize * 0.015F);
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
        return MCSoundEvents.ATOM;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ATOM_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ATOM_DEAD;
    }

    public void onDeath(){
    	
    	//Don't know what it was before, but assumed it was to get the dropped items
        Item[] dropItems = (new Item[]
                {
                    Item.getItemFromBlock(Blocks.COBBLESTONE), Item.getItemFromBlock(Blocks.GRAVEL), Item.getItemFromBlock(Blocks.COBBLESTONE), Item.getItemFromBlock(Blocks.GRAVEL), Item.getItemFromBlock(Blocks.IRON_ORE), Item.getItemFromBlock(Blocks.MOSSY_COBBLESTONE)
                });
        
        //Selects random item by getting a random number, itemchooser, and applying it to item array.
        Random rand = new Random();
        int itemchooser = rand.nextInt(6) + 1;
        
        this.dropItem(dropItems[itemchooser], (int) this.getYOffset());
        
    }
}