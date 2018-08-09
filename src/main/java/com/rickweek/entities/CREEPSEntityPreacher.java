package com.rickweek.entities;

import com.rickweek.init.MCSoundEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CREEPSEntityPreacher extends EntityMob
{
    public boolean rideable;
    protected double attackRange;
    private int angerLevel;
    private int value;
    private boolean ritual;
    private Entity targetedEntity;
    private Entity victimEntity;
    public int raise;
    public boolean getvictim;
    private float victimspeed;
    private int waittime;
    private int raiselevel;
    public int revenge;
    
    public String texture;

    public CREEPSEntityPreacher(World world) {
        super(world);
        texture = "mcw:textures/entity/preacher0.png";
        angerLevel = 0;
        attackRange = 16D;
        ritual = false;
        getvictim = false;
        raise = 0;
        victimspeed = 0.0F;
        waittime = rand.nextInt(500) + 500;
        raiselevel = 0;
        revenge = 0;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntitySheep.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPig.class, true));
    }
    
    public void applyEntityAttributes() {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(55D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000517232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5D);
    }

    public void onUpdate() {
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(this.getAttackTarget() != null ? 0.23000000617232513D : 0.23000000517232513D);

        if (rand.nextInt(4) == 0) {
            texture = (new StringBuilder()).append("mcw:textures/entity/preacher").append(String.valueOf(rand.nextInt(3))).append(".png").toString();
        } 
        
        if (isBurning()) {
            if (rand.nextInt(25) == 0)
            	 worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_PREACHER_BURN, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);            
        }
        
        super.onUpdate();
        
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (inWater || isEntityInsideOpaqueBlock())
        {
            int i = MathHelper.floor_double(posX);
            int l = MathHelper.floor_double(getEntityBoundingBox().minY);
            int j1 = MathHelper.floor_double(posZ);
            Block l1 = worldObj.getBlockState(new BlockPos(i, l + 2, j1)).getBlock();

            if (l1 != Blocks.AIR)
            {
                worldObj.setBlockToAir(new BlockPos(i, l + 2, j1));
                motionY += 0.5D;
            }
        }

        if (getvictim)
        {
            motionX = 0.0D;
            motionY = 0.0D;
            motionZ = 0.0D;

            if (raise++ > raiselevel)
            {
                getvictim = false;
                ritual = false;
                victimEntity.motionY = -0.80000001192092896D;
                raise = 0;
                waittime = rand.nextInt(500) + 500;
            }
            else
            {
                int j = MathHelper.floor_double(victimEntity.posX);
                int i1 = MathHelper.floor_double(victimEntity.getEntityBoundingBox().minY);
                int k1 = MathHelper.floor_double(victimEntity.posZ);
                Block i2 = worldObj.getBlockState(new BlockPos(j, i1 + 2, k1)).getBlock();

                if (i2 != Blocks.AIR && (victimEntity instanceof EntityPlayer))
                {
                    worldObj.setBlockToAir(new BlockPos(j, i1 + 2, k1));
                }

                victimEntity.motionY = 0.20000000298023224D;
                waittime = 1000;
                smokevictim(victimEntity);
                smoke();

                if (rand.nextInt(10) == 0)
                {
                    victimEntity.motionX = rand.nextFloat() * 0.85F - 0.5F;
                }
                else if (rand.nextInt(10) == 0)
                {
                    victimEntity.motionZ = rand.nextFloat() * 0.8F - 0.5F;
                }
            }
        }

        if (ritual && !getvictim)
        {
        	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
            targetedEntity = null;

            for (int k = 0; k < worldObj.loadedEntityList.size(); k++)
            {
                targetedEntity = (Entity)worldObj.loadedEntityList.get(k);

                if ((targetedEntity instanceof EntitySheep) || (targetedEntity instanceof EntityPig))
                {
                    getvictim = true;
                    victimEntity = targetedEntity;
                    victimEntity.motionX = 0.0D;
                    victimEntity.motionY = 0.0D;
                    victimEntity.motionZ = 0.0D;
                    raiselevel = rand.nextInt(50) + 50;
                    // worldObj.playSoundAtEntity(this, "morecreeps:preacherraise", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F);
                    worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_PREACHER_RAISE, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                }
            }

            if (targetedEntity == null)
            {
                ritual = false;
                getvictim = false;
            	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
            }
        }
        else if (rand.nextInt(2) == 0 && waittime-- < 1)
        {
            ritual = true;
            waittime = 1000;
            getvictim = false;
        }

        super.onLivingUpdate();
    }


    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("Anger", (short)angerLevel);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        angerLevel = nbttagcompound.getShort("Anger");
    }

    // Massimo numero di mob che possono spawnare in un chunk
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    // Richiamato quando il mob viene colpito
    public boolean attackEntityFrom(DamageSource damagesource, float amount) {
        Entity entity = damagesource.getEntity();
        if (entity != null) {
            if (amount < 1) 
            		amount = 1;
            
            if ((entity instanceof CREEPSEntityMoney) || (entity instanceof CREEPSEntityBullet)) {
            	amount = 2;
            }
            
            raise = 1;
            waittime = 0;
            smoke();
            getvictim = true;
            victimEntity = ((Entity)(entity));
            victimEntity.motionX = 0.0D;
            victimEntity.motionY = 0.0D;
            victimEntity.motionZ = 0.0D;
            raiselevel = rand.nextInt(50) + 50;
            worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_PREACHER_RAISE, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F);
        }

        return super.attackEntityFrom(DamageSource.causeMobDamage(this), amount);
    }


    public void knockBack(Entity entity, float i, double d, double d1) {
        motionX *= 1.5D;
        motionZ *= 1.5D;
        motionY += 0.5D;
    }

    /* Cerca il giocatore piu vicino nel range di 16 blocchi, nel caso ritornasse NULL il mob sarebbe pacifico
     *  (es. ragni di giorno, pigzombie se non attaccati)
     */ 
    protected Entity findPlayerToAttack() {
        return null;
    }

    private void smoke() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2, new int[0]);
            }
        }
    }

    private void smokevictim(Entity entity) {
        for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 5; j++) {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (entity.posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, (entity.posY + (double)(rand.nextFloat() * height) + (double)i) - 2D, (entity.posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2, new int[0]);
            }
        }
    }

    // Ritorna il danno riprodotto dal mob quando e vivo
    protected SoundEvent getAmbientSound() {
        return MCSoundEvents.ENTITY_PREACHER;
    }

    // Ritorna il danno riprodotto dal mob quando viene colpito
    protected SoundEvent getHurtSound() {
        return MCSoundEvents.ENTITY_PREACHER_HURT;
    }

    // Ritorna il danno riprodotto dal mob alla sua morte
    protected SoundEvent getDeathSound() {
        return MCSoundEvents.ENTITY_PREACHER_DEATH;
    }


    // Alla morte dell'entita
    public void onDeath(DamageSource damagesource) {
    	super.onDeath(damagesource);

    	if(!worldObj.isRemote) {
        	if (rand.nextInt(50) == 0)
        		dropItem(Items.DIAMOND, rand.nextInt(2) + 1);

        	if (rand.nextInt(50) == 0)
        		entityDropItem(new ItemStack(Items.DYE, 1, 4), 1.0F);
        	
        	if (rand.nextInt(50) == 0)
        		entityDropItem(new ItemStack(Items.DYE, 1, 3), 1.0F);
        
        	if (rand.nextInt(50) == 0)
        		entityDropItem(new ItemStack(Items.DYE, 1, 1), 1.0F);
        	
        	if (rand.nextInt(2) == 0)
        		dropItem(Items.GOLD_INGOT, rand.nextInt(5) + 2);
        	
        	else {
        		dropItem(Items.BOOK, 1);
        		dropItem(Items.APPLE, 1);
        	}
    	}
    }
}