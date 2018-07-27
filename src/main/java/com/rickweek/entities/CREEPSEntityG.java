package com.rickweek.entities;

import com.rickweek.init.MCSoundEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSEntityG extends EntityMob
{
    public float modelsize;
    public String texture;

    public CREEPSEntityG(World world)
    {
        super(world);
        texture = "mcw:textures/entity/g.png";
        setSize(width * 2.0F, height * 2.5F);
        modelsize = 2.0F;
        // ((PathNavigateGround)this.getNavigator()).func_179688_b(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new CREEPSEntityG.AIAttackEntity());
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.5D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.245D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
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

    protected void attackEntity(Entity entity, float f)
    {
        if (onGround)
        {
            double d = entity.posX - posX;
            double d2 = entity.posZ - posZ;
            float f1 = MathHelper.sqrt_double(d * d + d2 * d2);
            motionX = (d / (double)f1) * 0.40000000000000002D * 0.50000000192092897D + motionX * 0.18000000098023225D;
            motionZ = (d2 / (double)f1) * 0.40000000000000002D * 0.37000000192092897D + motionZ * 0.18000000098023225D;
            motionY = 0.15000000019604645D;
        }

        if ((double)f < 6D)
        {
            double d1 = entity.posX - posX;
            double d3 = entity.posZ - posZ;
            float f2 = MathHelper.sqrt_double(d1 * d1 + d3 * d3);
            motionX = (d1 / (double)f2) * 0.40000000000000002D * 0.40000000192092894D + motionX * 0.18000000098023225D;
            motionZ = (d3 / (double)f2) * 0.40000000000000002D * 0.27000000192092893D + motionZ * 0.18000000098023225D;
            rotationPitch = 90F;
        }
    }
    
    class AIAttackEntity extends EntityAIBase
    {
		@Override
		public boolean shouldExecute() {
			return CREEPSEntityG.this.getAttackTarget() != null;
		}
		
		public void updateTask()
		{
			float f = CREEPSEntityG.this.getDistanceToEntity(getAttackTarget());
			if(f < 256F)
			{
				attackEntity(CREEPSEntityG.this.getAttackTarget(), f);
				CREEPSEntityG.this.getLookHelper().setLookPositionWithEntity(CREEPSEntityG.this.getAttackTarget(), 10.0F, 10.0F);
				CREEPSEntityG.this.getNavigator().clearPathEntity();
				CREEPSEntityG.this.getMoveHelper().setMoveTo(CREEPSEntityG.this.getAttackTarget().posX, CREEPSEntityG.this.getAttackTarget().posY, CREEPSEntityG.this.getAttackTarget().posZ, 0.5D);
			}
			if(f < 2F)
			{
				CREEPSEntityG.this.attackEntityAsMob(CREEPSEntityG.this.getAttackTarget());
			}
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
        return MCSoundEvents.ENTITY_G;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_G_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_G_DEATH;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
    	if(!worldObj.isRemote)
    	{
            if (rand.nextInt(200) == 98)
            {
                dropItem(Item.getItemFromBlock(Blocks.GOLD_BLOCK), 1);
            }
            else if (rand.nextInt(5) == 0)
            {
                dropItem(Items.GOLD_INGOT, rand.nextInt(2) + 1);
            }
            else if (rand.nextInt(150) > 145)
            {
                dropItem(Items.GOLDEN_SWORD, 1);
            }
            else if (rand.nextInt(100) > 98)
            {
                dropItem(Items.GOLDEN_PICKAXE, 1);
            }
            else if (rand.nextInt(100) > 98)
            {
                dropItem(Items.GOLDEN_SHOVEL, 1);
            }
            else if (rand.nextInt(100) > 98)
            {
                dropItem(Items.GOLDEN_AXE, 1);
            }
            else if (rand.nextInt(100) > 98)
            {
                dropItem(Items.GOLDEN_HELMET, 1);
            }
            else if (rand.nextInt(100) > 98)
            {
                dropItem(Items.GOLDEN_CHESTPLATE, 1);
            }
            else if (rand.nextInt(100) > 98)
            {
                dropItem(Items.GOLDEN_BOOTS, 1);
            }
            else if (rand.nextInt(100) > 80)
            {
                dropItem(Items.WHEAT, rand.nextInt(6) + 1);
            }
            else if (rand.nextInt(100) > 98)
            {
                dropItem(Item.getItemFromBlock(Blocks.GLASS), rand.nextInt(6) + 1);
            }
            /* TODO
            else if (rand.nextInt(100) > 98)
            {
                dropItem(MoreCreepsAndWeirdos.goodonut, rand.nextInt(3) + 1);
            } */
            else if (rand.nextInt(100) > 88)
            {
                dropItem(Item.getItemFromBlock(Blocks.GRASS), rand.nextInt(6) + 1);
            }
            else if (rand.nextInt(100) > 98)
            {
                dropItem(Item.getItemFromBlock(Blocks.GLOWSTONE), rand.nextInt(2) + 1);
            }
            else if (rand.nextInt(100) > 98)
            {
                dropItem(Items.GLOWSTONE_DUST, rand.nextInt(2) + 1);
            }
    	}
        super.onDeath(damagesource);
    }
}