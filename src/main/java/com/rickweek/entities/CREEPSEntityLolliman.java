package com.rickweek.entities;

import java.util.List;

import com.rickweek.init.MCSoundEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSEntityLolliman extends EntityAnimal
{
    protected double attackrange;
    protected int attack;
    public int kidcheck;
    public boolean kidmounted;
    public int rockettime;
    public float modelsize;
    public int treats;
    public String texture;

    public CREEPSEntityLolliman(World world)
    {
        super(world);
        texture = "mcw:textures/entity/lolliman.png";
        setSize(0.9F, 3F);
        attack = 2;
        attackrange = 16D;
        kidcheck = 0;
        kidmounted = false;
        rockettime = 0;
        modelsize = 2.0F;
        treats = 0;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 0.5D));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
    }

    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    }

    /**
     * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal.
     */
    public EntityAnimal createChild(EntityAgeable entityanimal)
    {
        return new CREEPSEntityLolliman(worldObj);
    }

    /*// TODO
    protected void updateAITasks()
    {
        if (kidcheck++ > 25 && !kidmounted)
        {
            kidcheck = 0;
            List list = null;
            list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(2D, 2D, 2D));

            for (int k = 0; k < list.size(); k++)
            {
                Entity entity = (Entity)list.get(k);

                if (!((entity != null) & (entity instanceof CREEPSEntityKid)) || (entity.ridingEntity == null || !(entity.ridingEntity instanceof EntityPlayer)))
                {
                    continue;
                }

                worldObj.playSoundAtEntity(this, "morecreeps:lollimantakeoff", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                //smallconfetti();

                //Actually not stable
                EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 4D);
                if (!((EntityPlayerMP)entityplayer).getStatFile().hasAchievementUnlocked(MoreCreepsAndWeirdos.achievelolliman))
                {
                    worldObj.playSoundAtEntity(entityplayer, "morecreeps:achievement", 1.0F, 1.0F);
                    entityplayer.addStat(MoreCreepsAndWeirdos.achievelolliman, 1);
                    confetti();
                }

                entity.mountEntity(null);
                entity.mountEntity(this);
                this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
                motionY = 0.60000002384185791D;
                kidmounted = true;
                kidcheck = 255;
            }
        }

        if (kidcheck > 250 && kidmounted)
        {
            if(worldObj.isRemote)
            	MoreCreepsAndWeirdos.proxy.smoke3(worldObj, this, rand);

            motionY = 0.25D;

            if (rockettime++ > 5)
            {
                rockettime = 0;

                for (int j = 0; j < rand.nextInt(2) + 1; j++)
                {
                    Object obj = null;
                    int l = rand.nextInt(4);
                    treats++;

                    if (treats == 30)
                    {
                        worldObj.playSoundAtEntity(this, "morecreeps:lollimanexplode", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    }

                    if (rand.nextInt(3) == 0)
                    {
                        EntityItem entityitem;

                        switch (l)
                        {
                            case 1:
                                entityitem = entityDropItem(new ItemStack(Items.cookie, 1, 0), 1.0F);
                                break;

                            case 2:
                                entityitem = entityDropItem(new ItemStack(Items.cake, 1, 0), 1.0F);
                                break;

                            case 3:
                                entityitem = entityDropItem(new ItemStack(MoreCreepsAndWeirdos.lolly, 1, 0), 1.0F);
                                break;

                            default:
                                entityitem = entityDropItem(new ItemStack(MoreCreepsAndWeirdos.lolly, 1, 0), 1.0F);
                                break;
                        }

                        entityitem.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.2F;
                        entityitem.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.2F;
                    }

                    if (posY <= 100D && treats <= 55)
                    {
                        continue;
                    }

                    if (riddenByEntity != null)
                    {
                        riddenByEntity.setDead();
                    }

                    setDead();

                    if (treats > 50)
                    {
                        worldObj.createExplosion(this, posX, posY + 2D, posZ, 2.5F, true);
                    }
                }
            }
        }

        super.updateEntityActionState();
    } */

    /** TODO
     * Checks if this entity is inside of an opaque block
     
    public boolean isEntityInsideOpaqueBlock()
    {
        if (ridingEntity != null && kidmounted)
        {
            return false;
        }
        else
        {
            return super.isEntityInsideOpaqueBlock();
        }
    } */
    
    //this function doesn't exist anymore
    /*
    protected void updateFallState(double d, boolean flag)
    {
        if (!kidmounted)
        {
            super.updateFallState(d, flag);
        }
    }*/

    /*protected Entity findPlayerToAttack()
    {
        if (worldObj.getDifficulty() != EnumDifficulty.PEACEFUL)
        {
            float f = getBrightness(1.0F);
            if (f < 0.0F)
            {
                EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, attackrange);
                if (entityplayer != null)
                {
                    return entityplayer;
                }
            }
            if (rand.nextInt(10) == 0)
            {
                EntityLiving entityliving = func_21018_getClosestTarget(this, 15D);
                return entityliving;
            }
        }
        return null;
    }
    public EntityLiving func_21018_getClosestTarget(Entity entity, double d)
    {
        double d1 = -1D;
        EntityLiving entityliving = null;
        for (int i = 0; i < worldObj.loadedEntityList.size(); i++)
        {
            Entity entity1 = (Entity)worldObj.loadedEntityList.get(i);
            if (!(entity1 instanceof EntityLiving) || entity1 == entity || entity1 == entity.riddenByEntity || entity1 == entity.ridingEntity || (entity1 instanceof EntityPlayer) || (entity1 instanceof EntityMob) || (entity1 instanceof EntityAnimal))
            {
                continue;
            }
            double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
            if ((d < 0.0D || d2 < d * d) && (d1 == -1D || d2 < d1) && ((EntityLiving)entity1).canEntityBeSeen(entity))
            {
                d1 = d2;
                entityliving = (EntityLiving)entity1;
            }
        }
        return entityliving;
    }
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        Entity entity = damagesource.getEntity();
        entityToAttack = entity;
        return super.attackEntityFrom(damagesource, i);
    }
    protected void attackEntity(Entity entity, float f)
    {
        if (onGround)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
            motionX = (d / (double)f1) * 0.20000000000000001D * (0.850000011920929D + motionX * 0.20000000298023224D);
            motionZ = (d1 / (double)f1) * 0.20000000000000001D * (0.80000001192092896D + motionZ * 0.20000000298023224D);
            motionY = 0.10000000596246449D;
            fallDistance = -25F;
        }
        if ((double)f < 3.1000000000000001D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            entity.attackEntityFrom(DamageSource.causeMobDamage(this), attack);
        }
    }*/


    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setInteger("KidCheck", kidcheck);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
      
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        kidcheck = nbttagcompound.getInteger("KidCheck");
        kidmounted = false;

        if (riddenByEntity != null)
        {
            riddenByEntity.setDead();
        }
    } */

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        if (!kidmounted)
        {
            return MCSoundEvents.ENTITY_LOLLIMAN;
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
        return MCSoundEvents.ENTITY_LOLLIMAN_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_LOLLIMAN_DEATH;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
    	if(!worldObj.isRemote)
    	{
            if (rand.nextInt(10) == 0)
            {
                dropItem(Items.PORKCHOP, rand.nextInt(3) + 1);
            }

            if (rand.nextInt(10) == 0)
            {
                dropItem(Items.WHEAT_SEEDS, rand.nextInt(3) + 1);
            }
    	}

        super.onDeath(damagesource);
    }

    /*public void smallconfetti()
    {
        for (int i = 1; i < 20; i++)
        {
            for (int j = 0; j < 20; j++)
            {
                CREEPSFxConfetti creepsfxconfetti = new CREEPSFxConfetti(worldObj, posX + (double)(worldObj.rand.nextFloat() * 8F - worldObj.rand.nextFloat() * 8F), posY + (double)rand.nextInt(4) + 6D, posZ + (double)(worldObj.rand.nextFloat() * 8F - worldObj.rand.nextFloat() * 8F), worldObj.rand.nextInt(99));
                creepsfxconfetti.renderDistanceWeight = 30D;
                creepsfxconfetti.particleMaxAge = 55;
                ModLoader.getMinecraftInstance().effectRenderer.addEffect(creepsfxconfetti);
            }
        }
    }*/

    // TODO
    /* 
    public void confetti()
    {
    	MoreCreepsAndWeirdos.proxy.confettiA(this, worldObj);
    } */

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }
}