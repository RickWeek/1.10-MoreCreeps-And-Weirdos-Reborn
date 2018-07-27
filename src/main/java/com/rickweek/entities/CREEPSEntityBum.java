package com.rickweek.entities;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;
import com.rickweek.main.Reference;

public class CREEPSEntityBum extends EntityMob
{
	ResourceLocation resource;
    public boolean rideable;
    protected double attackRange;
    private int angerLevel;
    private int value;
    private boolean bumgave;
    public int timetopee;
    public float bumrotation;
    public float modelsize;
    public ResourceLocation texture;

    public CREEPSEntityBum(World world)
    {
        super(world);
        //The texture reference
        texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_BUM);
        angerLevel = 0;
        attackRange = 16D;
        bumgave = false;
        timetopee = rand.nextInt(900) + 500;
        bumrotation = 999F;
        modelsize = 1.0F;
    }
    
    public void onUpdate()
    {
        super.onUpdate();
    }
    
    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000517232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5D);
    }

    public String pingText()
    {
        return (new StringBuilder()).append("angerLevel ").append(angerLevel).toString();
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        double moveSpeed = this.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
        if (timetopee-- < 0 && !bumgave)
        {
            isJumping = false;

            if (bumrotation == 999F)
            {
                bumrotation = rotationYaw;
            }

            rotationYaw = bumrotation;
            moveSpeed = 0.0F;

            if (!onGround)
            {
                motionY -= 0.5D;
            }
            
            /* TODO
            if(worldObj.isRemote)
            {
            	MCW.proxy.pee(worldObj, posX, posY, posZ, rotationYaw, modelsize);
            } */

            if (timetopee < -200)
            {
                timetopee = rand.nextInt(600) + 600;
                bumrotation = 999F;
                int j = MathHelper.floor_double(posX);
                int k = MathHelper.floor_double(posY);
                int l = MathHelper.floor_double(posZ);

                for (int i1 = -1; i1 < 2; i1++)
                {
                    for (int j1 = -1; j1 < 2; j1++)
                    {
                        if (rand.nextInt(3) != 0)
                        {
                            continue;
                        }

                        Block k1 = worldObj.getBlockState(new BlockPos(j + j1, k - 1, l - i1)).getBlock();
                        Block l1 = worldObj.getBlockState(new BlockPos(j + j1, k, l - i1)).getBlock();

                        if (rand.nextInt(2) == 0)
                        {
                            if ((k1 == Blocks.GRASS || k1 == Blocks.DIRT) && l1 == Blocks.AIR)
                            {
                                worldObj.setBlockState(new BlockPos(j + j1, k, l - i1), Blocks.YELLOW_FLOWER.getDefaultState());
                            }

                            continue;
                        }

                        if ((k1 == Blocks.GRASS || k1 == Blocks.DIRT) && l1 == Blocks.AIR)
                        {
                        	worldObj.setBlockState(new BlockPos(j + j1, k, l - i1), Blocks.RED_FLOWER.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        Entity entity = damagesource.getEntity();

        if (entity instanceof EntityPlayer)
        {
        	setRevengeTarget((EntityLivingBase) entity);
            becomeAngryAt(entity);
        }

        timetopee = rand.nextInt(900) + 500;
        bumrotation = 999F;
        super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
        return true;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setShort("Anger", (short)angerLevel);
        nbttagcompound.setBoolean("BumGave", bumgave);
        nbttagcompound.setInteger("TimeToPee", timetopee);
        nbttagcompound.setFloat("BumRotation", bumrotation);
        nbttagcompound.setFloat("ModelSize", modelsize);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        angerLevel = nbttagcompound.getShort("Anger");
        bumgave = nbttagcompound.getBoolean("BumGave");
        timetopee = nbttagcompound.getInteger("TimeToPee");
        bumrotation = nbttagcompound.getFloat("BumRotation");
        modelsize = nbttagcompound.getFloat("ModelSize");

        if (bumgave)
        {
            texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES +
            		Reference.TEXTURE_BUM_DRESSED);
        }
    }
    
    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }


    /**TEMPORARILY REMOVED TO FIND AN ALTERNATIVE TO THESE FUNCTIONS**/
    /*protected Entity findPlayerToAttack()
    {
        if (angerLevel == 0)
        {
            return null;EntityPigZombie
        }
        else
        {
            return super.findPlayerToAttack();
        }
    }
    
    
    public boolean canAttackEntity(Entity entity, int i)
    {
        if (entity instanceof EntityPlayer)
        {
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getBoundingBox().expand(32D, 32D, 32D));
            for (int j = 0; j < list.size(); j++)
            {
                Entity entity1 = (Entity)list.get(j);
                if (entity1 instanceof CREEPSEntityBum)
                {
                    CREEPSEntityBum creepsentitybum = (CREEPSEntityBum)entity1;
                    creepsentitybum.becomeAngryAt(entity);
                }
            }
            becomeAngryAt(entity);
        }
        return super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }
    
    private void becomeAngryAt(Entity entity)
    {
        entityToAttack = entity;
        angerLevel = 400 + rand.nextInt(400);
    }*/

    /**Simple try if it work or not**/
    private void becomeAngryAt(Entity p_70835_1_)
    {
        this.angerLevel = 400 + this.rand.nextInt(400);

        if (p_70835_1_ instanceof EntityLivingBase)
        {
            this.setRevengeTarget((EntityLivingBase)p_70835_1_);
        }
    }
    
    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean processInteract(EntityPlayer entityplayer, EnumHand hand, @Nullable ItemStack stack)
    {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();

        if (!bumgave && angerLevel == 0)
        {
            if (itemstack != null && (itemstack.getItem() == Items.DIAMOND || itemstack.getItem() == Items.GOLD_INGOT || itemstack.getItem() == Items.IRON_INGOT))
            {
                if (itemstack.getItem() == Items.IRON_INGOT)
                {
                    value = rand.nextInt(2) + 1;
                }
                else if (itemstack.getItem() == Items.GOLD_INGOT)
                {
                    value = rand.nextInt(5) + 1;
                }
                else if (itemstack.getItem() == Items.DIAMOND)
                {
                    value = rand.nextInt(10) + 1;
                }

                if (itemstack.stackSize - 1 == 0)
                {
                    entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                }
                else
                {
                    itemstack.stackSize--;
                }

                for (int i = 0; i < 4; i++)
                {
                    for (int i1 = 0; i1 < 10; i1++)
                    {
                        double d1 = rand.nextGaussian() * 0.02D;
                        double d3 = rand.nextGaussian() * 0.02D;
                        double d6 = rand.nextGaussian() * 0.02D;
                        worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height) + (double)i, (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d1, d3, d6);
                    }
                }

                texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES +
                		Reference.TEXTURE_BUM_DRESSED);
                angerLevel = 0;
                //findPlayerToAttack();

                if (rand.nextInt(5) == 0)
                {
                    // worldObj.playSoundAtEntity(this, "morecreeps:bumsucker", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BUM_SUCKER, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    bumgave = true;
                }
                else
                {
                    // worldObj.playSoundAtEntity(this, "morecreeps:bumthankyou", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BUM_THANKYOU, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    bumgave = true;

                    for (int j = 0; j < 10; j++)
                    {
                        double d = rand.nextGaussian() * 0.02D;
                        double d2 = rand.nextGaussian() * 0.02D;
                        double d5 = rand.nextGaussian() * 0.02D;
                        worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d2, d5);
                    }

                    for (int k = 0; k < value; k++)
                    {
                        dropItem(Item.getItemById(rand.nextInt(95)), 1);
                        dropItem(Items.IRON_SHOVEL, 1);
                    }

                    return true;
                }
            }
            else if (itemstack != null)
            {
                if (timetopee > 0)
                {
                    // worldObj.playSoundAtEntity(this, "morecreeps:bumdontwant", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BUM_DONTWANT, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    
                }
                else if (itemstack != null && (itemstack.getItem() == Item.getItemFromBlock(Blocks.YELLOW_FLOWER) || itemstack.getItem() == Item.getItemFromBlock(Blocks.RED_FLOWER)))
                {
                	/* TODO
                	if(!((EntityPlayerMP)entityplayer).getStatFile().hasAchievementUnlocked(MoreCreepsAndWeirdos.achievebumflower))
                	{
                    	worldObj.playSoundAtEntity(entityplayer, "morecreeps:achievement", 1.0F, 1.0F);
                    	entityplayer.addStat(MoreCreepsAndWeirdos.achievebumflower, 1);
                    	confetti(entityplayer);
                	} */

                    // worldObj.playSoundAtEntity(this, "morecreeps:bumthanks", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BUM_THANKS, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    timetopee = rand.nextInt(1900) + 1500;

                    if (itemstack.stackSize - 1 == 0)
                    {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                    }
                    else
                    {
                        itemstack.stackSize--;
                    }
                }
                else if (itemstack != null && itemstack.getItem() == Items.BUCKET)
                {
                	/* TODO
                    if (!((EntityPlayerMP)entityplayer).getStatFile().hasAchievementUnlocked(MoreCreepsAndWeirdos.achievebumpot) && ((EntityPlayerMP)entityplayer).getStatFile().hasAchievementUnlocked(MoreCreepsAndWeirdos.achievebumflower))
                    {
                        worldObj.playSoundAtEntity(entityplayer, "morecreeps:achievement", 1.0F, 1.0F);
                        entityplayer.addStat(MoreCreepsAndWeirdos.achievebumpot, 1);
                        confetti(entityplayer);
                    } 
                    entityplayer.addStat(MoreCreepsAndWeirdos.achievebumpot, 1);
                    */
                    // worldObj.playSoundAtEntity(this, "morecreeps:bumthanks", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BUM_THANKS, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    timetopee = rand.nextInt(1900) + 1500;

                    if (itemstack.stackSize - 1 == 0)
                    {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                    }
                    else
                    {
                        itemstack.stackSize--;
                    }
                }
                else if (itemstack != null && itemstack.getItem() == Items.LAVA_BUCKET)
                {
                	/* 
                    if (!((EntityPlayerMP)entityplayer).getStatFile().hasAchievementUnlocked(MoreCreepsAndWeirdos.achievebumlava) && ((EntityPlayerMP)entityplayer).getStatFile().hasAchievementUnlocked(MoreCreepsAndWeirdos.achievebumpot))
                    {
                        worldObj.playSoundAtEntity(entityplayer, "morecreeps:achievement", 1.0F, 1.0F);
                        entityplayer.addStat(MoreCreepsAndWeirdos.achievebumlava, 1);
                        confetti(entityplayer);
                    }

                    entityplayer.addStat(MoreCreepsAndWeirdos.achievebumpot, 1);
                    */
                    // worldObj.playSoundAtEntity(this, "morecreeps:bumthanks", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BUM_THANKS, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    timetopee = rand.nextInt(1900) + 1500;

                    if (itemstack.stackSize - 1 == 0)
                    {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                    }
                    else
                    {
                        itemstack.stackSize--;
                    }

                    int l = (int)posX;
                    int j1 = (int)posY;
                    int k1 = (int)posZ;

                    if (rand.nextInt(4) == 0)
                    {
                        for (int l1 = 0; l1 < rand.nextInt(3) + 1; l1++)
                        {
                            Blocks.OBSIDIAN.dropBlockAsItem(worldObj, new BlockPos(l, j1, k1), worldObj.getBlockState(new BlockPos(l, j1, k1)), 0);
                        }
                    }

                    for (int i2 = 0; i2 < 15; i2++)
                    {
                        double d4 = (float)l + worldObj.rand.nextFloat();
                        double d7 = (float)j1 + worldObj.rand.nextFloat();
                        double d8 = (float)k1 + worldObj.rand.nextFloat();
                        double d9 = d4 - posX;
                        double d10 = d7 - posY;
                        double d11 = d8 - posZ;
                        double d12 = MathHelper.sqrt_double(d9 * d9 + d10 * d10 + d11 * d11);
                        d9 /= d12;
                        d10 /= d12;
                        d11 /= d12;
                        double d13 = 0.5D / (d12 / 10D + 0.10000000000000001D);
                        d13 *= worldObj.rand.nextFloat() * worldObj.rand.nextFloat() + 0.3F;
                        d9 *= d13;
                        d10 *= d13;
                        d11 *= d13;
                        worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (d4 + posX * 1.0D) / 2D, (d7 + posY * 1.0D) / 2D + 2D, (d8 + posZ * 1.0D) / 2D, d9, d10, d11);
                        worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d4, d7, d8, d9, d10, d11);
                    }

                    if (rand.nextInt(4) == 0)
                    {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(Items.BUCKET));
                    }
                }
                else if (!bumgave)
                {
                    // worldObj.playSoundAtEntity(this, "morecreeps:bumpee", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                    worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BUM_PEE, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                }
            }
        }
        else
        {
            // worldObj.playSoundAtEntity(this, "morecreeps:bumleavemealone", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_BUM_LEAVEMEALONE, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
        }

        return super.processInteract(entityplayer, hand, stack);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getAmbientSound()
    {
        if (timetopee > 0 || bumgave )
        {
            return MCSoundEvents.ENTITY_BUM;
        }
        else
        {
            return MCSoundEvents.ENTITY_BUM_LIVINGPEE;
        }
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        return MCSoundEvents.ENTITY_BUM_HURT;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        return MCSoundEvents.ENTITY_BUM_DEATH;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource damagesource)
    {
        super.onDeath(damagesource);
        if(!worldObj.isRemote)
        {
            dropItem(Items.COOKED_PORKCHOP, 1);
        }
    }
}