package com.rickweek.entities;

import com.rickweek.init.MCSoundEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class CREEPSEntityEvilSnowman extends EntityMob
{
    public float snowsize;
    public String texture;

    public CREEPSEntityEvilSnowman(World world)
    {
        super(world);
        texture = "mcw:textures/entity/evilsnowman.png";
        setSize(0.7F, 1.5F);
        snowsize = 1.0F;
        isImmuneToFire = true;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new CREEPSEntityEvilSnowman.AIAttackTarget());
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.5D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000517232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
    }
    
    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!onGround && !isJumping)
        {
            motionY -= 0.0020000000949949026D;
        }

        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(getEntityBoundingBox().minY);
        int k = MathHelper.floor_double(posZ);
        Block l = worldObj.getBlockState(new BlockPos(i, j - 1, k)).getBlock();
        Block i1 = worldObj.getBlockState(new BlockPos(i, j, k)).getBlock();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(snowsize + 1);

        if (/*l > 77 && l < 81 || */i1 == Blocks.SNOW)
        {
            snowsize += 0.001F;
        }
        else
        {
            snowsize -= 0.002F;
        }

        if (inWater)
        {
            snowsize -= 0.02F;
        }

        if (snowsize > 6F)
        {
            snowsize = 6F;
        }

        setSize(snowsize * 0.45F, snowsize * 2.0F);

        if ((double)snowsize < 0.050000000000000003D)
        {
            setDead();
        }
    }

    public float getShadowSize()
    {
        return snowsize * 0.4F;
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity entity, float f)
    {
        if (onGround)
        {
            for (int i = 0; i < 8; i++)
            {
                // worldObj.playSoundAtEntity(this, "morecreeps:snowmanbounce", 1.0F, 2.0F - snowsize);
                worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_SNOWMAN_JUMP, SoundCategory.NEUTRAL, 1.0F, 2.0F - snowsize);
                worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
            
            
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1);
            motionX = (d / (double)f1) * 0.5D * 0.30000000192092896D + motionX * 0.20000000098023224D;
            motionZ = (d1 / (double)f1) * 0.5D * 0.25000000192092897D + motionZ * 0.20000000098023224D;
            motionY = 0.35000000196046449D;

            if (rand.nextInt(20) == 0)
            {
                
                double d2 = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
                double d5 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);
                motionX -= d2 * 0.40000000596046448D;
                motionZ -= d5 * 0.40000000596046448D;
            }

            if (rand.nextInt(20) == 0)
            {
                double d3 = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
                double d6 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);
                motionX -= d3 * 1.0D;
                motionY += 0.16599999368190765D;
            }

            if (rand.nextInt(40) == 0)
            {
                double d4 = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
                double d7 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);
                motionX -= d4 * 0.30000001192092896D;
                motionZ -= d7 * 0.30000001192092896D;
                motionY += 0.76599997282028198D;
            }
        }
    }

    public class AIAttackTarget extends EntityAIBase {

    	public CREEPSEntityEvilSnowman evilsnowman = CREEPSEntityEvilSnowman.this;
    	public int attackTime;
    	public AIAttackTarget() {}
    	
		@Override
		public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.evilsnowman.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}
        public void updateTask()
        {
        	--attackTime;
            EntityLivingBase entitylivingbase = this.evilsnowman.getAttackTarget();
            double d0 = this.evilsnowman.getDistanceSqToEntity(entitylivingbase);

            if (d0 < 4.0D)
            {
                if (this.attackTime <= 0)
                {
                    this.attackTime = 20;
                    this.evilsnowman.attackEntityAsMob(entitylivingbase);
                }
                
                this.evilsnowman.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }
            else if (d0 < 256.0D)
            {
                // ATTACK ENTITY GOES HERE
                evilsnowman.attackEntity(entitylivingbase, (float)d0);
                this.evilsnowman.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            }
            else
            {
                this.evilsnowman.getNavigator().clearPathEntity();
                this.evilsnowman.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 0.5D);
            }
        }
    }
    
    /**
     * knocks back this entity
     */
    public void knockBack(Entity entity, int i, double d, double d1)
    {
        for (int j = 0; j < 8 + (int)(snowsize * 20F); j++)
        {
            worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, posX, posY + (double)snowsize, posZ, 0.0D, 0.0D, 0.0D);
        }

        i *= i;
        motionY += 0.33300000429153442D;
        d *= 8.1999998092651367D;
        d1 *= 8.3000001907348633D;
        super.knockBack(entity, i, d, d1);
    }

   

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    public void fall(float distance, float damageMultiplier)
    {
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
    public boolean getCanSpawnHere()
    {
        return true;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
        EntityPlayer entityplayer = (EntityPlayer) damagesource.getEntity();
        boolean flag = false;

        /* TODO
        if (!((EntityPlayerMP)entityplayer).getStatFile().hasAchievementUnlocked(MoreCreepsAndWeirdos.achievesnowtiny) && snowsize < 0.1F)
        {
            worldObj.playSoundAtEntity(entityplayer, "morecreeps:achievement", 1.0F, 1.0F);
            entityplayer.addStat(MoreCreepsAndWeirdos.achievesnowtiny, 1);
            confetti();
            flag = true;
        }

        if (!((EntityPlayerMP)entityplayer).getStatFile().hasAchievementUnlocked(MoreCreepsAndWeirdos.achievesnowtall) && snowsize > 5F)
        {
            worldObj.playSoundAtEntity(entityplayer, "morecreeps:achievement", 1.0F, 1.0F);
            entityplayer.addStat(MoreCreepsAndWeirdos.achievesnowtall, 1);
            confetti();
            flag = true;
        }

        if (!((EntityPlayerMP)entityplayer).getStatFile().hasAchievementUnlocked(MoreCreepsAndWeirdos.achievesnow) && !flag)
        {
            worldObj.playSoundAtEntity(entityplayer, "morecreeps:achievement", 1.0F, 1.0F);
            entityplayer.addStat(MoreCreepsAndWeirdos.achievesnow, 1);
            confetti();
        } */

        if(!worldObj.isRemote)
        {
            if (rand.nextInt(10) == 0)
            {
                dropItem(Item.getItemFromBlock(Blocks.ICE), rand.nextInt(3) + 1);
                dropItem(Item.getItemFromBlock(Blocks.SNOW), rand.nextInt(10) + 1);
            }
            else
            {
                dropItem(Item.getItemFromBlock(Blocks.SNOW), rand.nextInt(5) + 2);
            }
        }

        super.onDeath(damagesource);
    }

    // TODO
    /*
    public void confetti()
    {
    	MoreCreepsAndWeirdos.proxy.confettiA(this, worldObj);
    } */

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        return MCSoundEvents.ENTITY_SNOWMAN;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_SNOWMAN_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_SNOWMAN_DEATH;
    }
}