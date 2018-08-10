package com.rickweek.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.rickweek.entities.ai.EntityBlorpAI;
import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;

public class CREEPSEntityBlorp extends EntityMob {
    public double attackrange;
    public boolean bone;
    protected int attack;
    public float blorpsize;
    public int eaten;
    public boolean hungry;
    public int hungrytime;
    public int blorplevel;
    public boolean angry;
    public String texture;
    public int attackTime;

    public CREEPSEntityBlorp(World world) {
        super(world);
        bone = false;
        texture = "mcw:textures/entity/blorp.png";
        setSize(width * 1.5F, height * 2.5F);
        attack = 2;
        attackrange = 16D;
        blorpsize = 1.0F;
        hungry = false;
        hungrytime = rand.nextInt(20) + 20;
        blorplevel = 1;
        angry = false;
        this.tasks.addTask(0, new EntityBlorpAI(this));
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
    }

    public void applyEntityAttributes() {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000517232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    }


    public void onLivingUpdate() {
    	if (blorpsize > 2.0F) {
            ignoreFrustumCheck = true;
        }

        super.onLivingUpdate();

        if (getAttackTarget() != null) {
            hungry = false;
            hungrytime = 100;
        }

        if (hungry) {
            int ai[] = findTree(this, Double.valueOf(2D));

            if (ai[1] != 0) {
                int i = ai[0];
                int j = ai[1];
                int k = ai[2];
                worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BLORP_EAT, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                worldObj.setBlockToAir(new BlockPos(i, j, k));
                hungrytime = hungrytime + rand.nextInt(100) + 25;

                if (hungrytime > 1000) {
                    hungry = false;

                    if (blorpsize < 6F) {
                        blorpsize = blorpsize + 0.3F;
                    }


                    blorplevel++;
                    float health = this.getHealth();
                    health = 10 * blorplevel + 25;
                    setSize(width * blorpsize, 2.0F + height * blorpsize);
                    worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BLORP_GROW, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                }

                faceTreeTop(i, j, k, 10F);
                int l = 0;
                int i1 = 0;

                if (posX < (double)i)
                {
                    l = i - MathHelper.floor_double(posX);
                    motionX += 0.050000000000000003D;
                }
                else
                {
                    l = MathHelper.floor_double(posX) - i;
                    motionX -= 0.050000000000000003D;
                }

                if (posZ < (double)k)
                {
                    i1 = k - MathHelper.floor_double(posZ);
                    motionZ += 0.050000000000000003D;
                }
                else
                {
                    i1 = MathHelper.floor_double(posZ) - k;
                    motionZ -= 0.050000000000000003D;
                }

                double d = l + i1;
            }
        }
        else
        {
            hungrytime--;

            if (hungrytime < 1)
            {
                hungry = true;
                hungrytime = 1;
            }
        }
    }

    //previously called : getBlockpathWeight
    public float func_180484_a(BlockPos bp)
    {
        if (worldObj.getBlockState(bp.down()).getBlock() == Blocks.LEAVES || worldObj.getBlockState(bp.down()).getBlock() == Blocks.LOG)
        {
            return 10F;
        }
        else
        {
            return -(float)bp.getY();
        }
    }
    
    public void faceTreeTop(int i, int j, int k, float f)
    {
        double d = (double)i - posX;
        double d1 = (double)k - posZ;
        double d2 = (double)j - posY;
        double d3 = MathHelper.sqrt_double(d * d + d1 * d1);
        float f1 = (float)((Math.atan2(d1, d) * 180D) / Math.PI);
        float f2 = (float)((Math.atan2(d2, d3) * 180D) / Math.PI);
        rotationYaw = (float)(Math.atan2(motionX, motionZ) / Math.PI);
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            Entity entity = source.getEntity();

            if (entity instanceof EntityPlayer)
            {
                this.becomeAngryAt(entity);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    /**
     * Causes this PigZombie to become angry at the supplied Entity (which will be a player).
     */
    private void becomeAngryAt(Entity p_70835_1_)
    {
        if (p_70835_1_ instanceof EntityLivingBase)
        {
            this.setRevengeTarget((EntityLivingBase)p_70835_1_);
        }
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    public void attackEntity(Entity entity, float f)
    {
        if (onGround)
        {
            // worldObj.playSoundAtEntity(this, "morecreeps:blorpbounce", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, entity.getPosition(), MCSoundEvents.ENTITY_BLORP_BOUNCE, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            double d = entity.posX - posX;
            double d2 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt_double(d * d + d2 * d2);
            motionX = (d / (double)f1) * 0.20000000000000001D * 0.80000001192092896D + motionX * 0.20000000298023224D;
            motionZ = (d2 / (double)f1) * 0.20000000000000001D * 0.80000001192092896D + motionZ * 0.20000000298023224D;
            motionY = 0.70000000596246448D + (double)blorpsize * 0.050000004559D;
            fallDistance = -(25F + blorpsize * 5F);
        }
        else
        {
            double d1 = 2.5D + ((double)blorpsize - 1.5D) * 0.80000000000000004D;

            if (d1 > 3.5D)
            {
                d1 = 3.5D;
            }

            if ((double)f < d1 && entity.getEntityBoundingBox().maxY > getEntityBoundingBox().minY && entity.getEntityBoundingBox().minY < getEntityBoundingBox().maxY)
            {
                attackTime = 20;

                if (entity instanceof CREEPSEntityBlorp)
                {
                    entity.attackEntityFrom(DamageSource.causeMobDamage(this), attack + blorplevel);
                }
                else
                {
                    entity.attackEntityFrom(DamageSource.causeMobDamage(this), attack);
                }
            }
        }
    }

    public int[] findTree(Entity entity, Double double1)
    {
        AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(double1.doubleValue(), double1.doubleValue(), double1.doubleValue());
        int i = MathHelper.floor_double(axisalignedbb.minX);
        int j = MathHelper.floor_double(axisalignedbb.maxX + 1.0D);
        int k = MathHelper.floor_double(axisalignedbb.minY);
        int l = MathHelper.floor_double(axisalignedbb.maxY + 1.0D);
        int i1 = MathHelper.floor_double(axisalignedbb.minZ);
        int j1 = MathHelper.floor_double(axisalignedbb.maxZ + 1.0D);

        for (int k1 = i; k1 < j; k1++)
        {
            for (int l1 = k; l1 < l; l1++)
            {
                for (int i2 = i1; i2 < j1; i2++)
                {
                    Block j2 = worldObj.getBlockState(new BlockPos(k1, l1, i2)).getBlock();

                    if (j2 != Blocks.AIR && j2 == Blocks.LEAVES)
                    {
                        return (new int[]
                                {
                                    k1, l1, i2
                                });
                    }

                    if (j2 != Blocks.AIR && blorplevel > 3 && j2 == Blocks.LOG)
                    {
                        return (new int[]
                                {
                                    k1, l1, i2
                                });
                    }
                }
            }
        }

        return (new int[]
                {
                    -1, 0, 0
                });
    }

    /**
     * Checks if this entity is inside of an opaque block
     */
    public boolean isEntityInsideOpaqueBlock()
    {
        return false;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Hungry", hungry);
        nbttagcompound.setInteger("BlorpLevel", blorplevel);
        nbttagcompound.setFloat("BlorpSize", blorpsize);
        nbttagcompound.setBoolean("Angry", angry);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        hungry = nbttagcompound.getBoolean("Hungry");
        blorplevel = nbttagcompound.getInteger("BlorpLevel");
        blorpsize = nbttagcompound.getFloat("BlorpSize");
        angry = nbttagcompound.getBoolean("Angry");
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        return MCSoundEvents.ENTITY_BLORP;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_BLORP_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_BLORP_DEATH;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
    	if(!worldObj.isRemote)
    	{
            dropItem(MCItems.Blorpcola, blorplevel);
    	}
        super.onDeath(damagesource);
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 3;
    }
}