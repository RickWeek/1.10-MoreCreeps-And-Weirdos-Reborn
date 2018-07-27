package com.rickweek.entities;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.MCW;
import com.rickweek.main.Reference;

public class CREEPSEntityLawyerFromHell extends EntityMob
{
    private boolean foundplayer;
    private boolean stolen;

    /**
     * returns true if a creature has attacked recently only used for creepers and skeletons
     */
    protected boolean hasAttacked;
    protected ItemStack stolengood;
    private double goX;
    private double goZ;
    private float distance;
    public int itemnumber;
    public int stolenamount;
    public boolean undead = false;
    public int jailX;
    public int jailY;
    public int jailZ;
    public int area;
    public int lawyerstate;
    public int lawyertimer;
    private static ItemStack defaultHeldItem;
    public float modelsize;
    public int maxObstruct;
    public ResourceLocation texture;

    public CREEPSEntityLawyerFromHell(World world)
    {
        super(world);
        texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_LAWYER);

        if (undead)
        {
        	texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_LAWYER_UNDEAD);
        }
        stolen = false;
        hasAttacked = false;
        foundplayer = false;
        lawyerstate = 0;
        lawyertimer = 0;

        if (!undead)
        {
            defaultHeldItem = null;
        }

        modelsize = 1.0F;
        maxObstruct = 20;
        
        this.tasks.addTask(0, new EntityAISwimming(this));
        //this.tasks.addTask(1, new CREEPSEntityLawyerFromHell.AIAttackEntity());
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.4D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 0.5D));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new CREEPSEntityLawyerFromHell.AIAttackEntity());
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    public void applyEntityAttributes()
    {
    	super.applyEntityAttributes();
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000447232513D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
    }
    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected boolean findPlayerToAttack()
    {
        if (lawyerstate == 0 && !undead)
        {
            return false;
        }

        if (MCW.instance.currentfine <= 0 && !undead)
        {
            lawyerstate = 0;
            //pathToEntity = null;
            return false;
        }

        if (lawyerstate > 0)
        {
            EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 16D);

            if (entityplayer != null && canEntityBeSeen(entityplayer))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        // texture = undead ? "mcw:textures/entity/lawyerfromhellundead.png" : "mcw:textures/entity/lawyerfromhell.png";
        if (undead == true) texture = new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_LAWYER_UNDEAD);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(undead ? 0.24D : 0.44D);

        if (undead && defaultHeldItem == null)
        {
            defaultHeldItem = new ItemStack(Items.BONE, 1);
        }

        super.onLivingUpdate();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (MCW.instance.currentfine > 0 && lawyerstate == 0 && !undead)
        {
            lawyerstate = 1;
        }

        if (MCW.instance.currentfine > 2500 && lawyerstate < 5 && !undead)
        {
            lawyerstate = 5;
        }

        if (undead)
        {
            lawyerstate = 1;
        }

        super.onUpdate();
    }

    /**
     * Checks if this entity is inside of an opaque block
     */
    public boolean isEntityInsideOpaqueBlock()
    {
        if (undead && isCollided)
        {
            return false;
        }
        else
        {
            return super.isEntityInsideOpaqueBlock();
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        Entity entity = damagesource.getEntity();

        if (!undead && (entity instanceof EntityPlayer) /* TODO || (entity instanceof CREEPSEntityGuineaPig) && ((CREEPSEntityGuineaPig)entity).tamed || (entity instanceof CREEPSEntityHotdog) && ((CREEPSEntityHotdog)entity).tamed || (entity instanceof CREEPSEntityArmyGuy) && ((CREEPSEntityArmyGuy)entity).loyal */)
        {
        	MCW.instance.currentfine += 50;
        }

        if (!undead)
        {
            if ((entity instanceof EntityPlayer) /* TODO || (entity instanceof CREEPSEntityHotdog) && ((CREEPSEntityHotdog)entity).tamed || (entity instanceof CREEPSEntityGuineaPig) && ((CREEPSEntityGuineaPig)entity).tamed || (entity instanceof CREEPSEntityArmyGuy) && ((CREEPSEntityArmyGuy)entity).loyal */)
            {
                if (lawyerstate == 0)
                {
                    lawyerstate = 1;
                }

                setRevengeTarget((EntityLivingBase) entity);
            }

            if (entity instanceof EntityPlayer)
            {
                if (lawyerstate == 0)
                {
                    lawyerstate = 1;
                }

                if (rand.nextInt(5) == 0)
                {
                    for (int j = 0; j < rand.nextInt(20) + 5; j++)
                    {
                        MCW.instance.currentfine += 25;
                        worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_LAWYER_MONEYHIT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        if(!worldObj.isRemote)
                        dropItem(MCItems.Money, 1);
                    }
                }

                if (rand.nextInt(5) == 0)
                {
                    for (int k = 0; k < rand.nextInt(3) + 1; k++)
                    {
                        MCW.instance.currentfine += 10;
                        worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_LAWYER_MONEYHIT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        if(!worldObj.isRemote)
                        dropItem(Items.PAPER, 1);
                    }
                }
            }
        }

        return super.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    class AIAttackEntity extends EntityAIBase
    {
		@Override
		public boolean shouldExecute()
		{
			return CREEPSEntityLawyerFromHell.this.findPlayerToAttack();
		}
		
		// TODO FIX THIS FUCKING DIST. TO ENTITY, CRASH EVERY TIME
		
		public void updateTask()
		{
			
			/*
			float f = CREEPSEntityLawyerFromHell.this.getDistanceToEntity(getAttackTarget());
			if(f < 256F)
			{
				attackEntity(CREEPSEntityLawyerFromHell.this.getAttackTarget(), f);
				CREEPSEntityLawyerFromHell.this.getLookHelper().setLookPositionWithEntity(CREEPSEntityLawyerFromHell.this.getAttackTarget(), 10.0F, 10.0F);
				CREEPSEntityLawyerFromHell.this.getNavigator().clearPathEntity();
				CREEPSEntityLawyerFromHell.this.getMoveHelper().setMoveTo(CREEPSEntityLawyerFromHell.this.getAttackTarget().posX, CREEPSEntityLawyerFromHell.this.getAttackTarget().posY, CREEPSEntityLawyerFromHell.this.getAttackTarget().posZ, 0.5D);
			}
			if(f < 1F)
			{
				//CREEPSEntityLawyerFromHell.this.attackEntityAsMob(CREEPSEntityKid.this.getAttackTarget());
			} */
		}
		
    }
    public boolean attackEntityAsMob(Entity entity)
    {
        if (this.getAttackTarget() instanceof EntityPlayer)
        {
            if (MCW.instance.currentfine <= 0 && !undead)
            {
                // pathToEntity = null;
                return foundplayer;
            }

            if (onGround)
            {
                float f1 = 1.0F;

                if (undead)
                {
                    f1 = 0.5F;
                }

                double d = entity.posX - posX;
                double d1 = entity.posZ - posZ;
                float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
                motionX = ((d / (double)f2) * 0.5D * 0.40000001192092893D + motionX * 0.20000000298023224D) * (double)f1;
                motionZ = ((d1 / (double)f2) * 0.5D * 0.30000001192092896D + motionZ * 0.20000000298023224D) * (double)f1;
                motionY = 0.40000000596046448D;
                if ((entity instanceof EntityPlayer) && !undead && rand.nextInt(10) == 0 && MCW.instance.currentfine >= 2500)
                {
                	MCW.proxy.addChatMessage(" ");
                    MCW.proxy.addChatMessage("\2474  BUSTED! \2476Sending guilty player to Jail");
                    MCW.proxy.addChatMessage(". . . . . . . . . . . . . . . . . . . . . . . . . . . . . .");
                    buildJail((EntityPlayer) entity);
                }
            }
                if (rand.nextInt(50) == 0 && (entity instanceof EntityPlayer))
                {
                    suckMoney((EntityPlayer) entity);
                }

                if (undead)
                {
                    this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
                }
                
                if ((entity instanceof EntityPlayer) && !undead && rand.nextInt(10) == 0 && MCW.instance.currentfine >= 2500)
                {
                	MCW.proxy.addChatMessage(" ");
                    MCW.proxy.addChatMessage("\2474  BUSTED! \2476Sending guilty player to Jail");
                    MCW.proxy.addChatMessage(". . . . . . . . . . . . . . . . . . . . . . . . . . . . . .");
                    buildJail((EntityPlayer) entity);
                }
                else if ((entity instanceof EntityPlayer) && !undead && rand.nextInt(10) == 0 && MCW.instance.currentfine >= 2500)
                {
                	MCW.proxy.addChatMessage(" ");
                    MCW.proxy.addChatMessage("\2474  BUSTED! \2476Sending guilty player to Jail");
                    MCW.proxy.addChatMessage(". . . . . . . . . . . . . . . . . . . . . . . . . . . . . .");
                    buildJail((EntityPlayer) entity);
                }

                return super.attackEntityAsMob(entity);
                
                // buildJail((EntityPlayer) entity);
                //super.attackEntity(entity, f);
            }
            //super.attackEntity(entity, f);
		return foundplayer;
        
    }

    public boolean buildJail(EntityPlayer entityplayersp)
    {
        int i = rand.nextInt(200) - 100;

        if (rand.nextInt(2) == 0)
        {
            i *= -1;
        }

        jailX = (int)(((EntityPlayer)(entityplayersp)).posX + (double)i);
        jailY = rand.nextInt(20) + 25;
        jailZ = (int)(((EntityPlayer)(entityplayersp)).posZ + (double)i);
        maxObstruct = 0x1869f;

        if (MCW.instance.jailBuilt)
        {
            jailX = MCW.instance.currentJailX;
            jailY = MCW.instance.currentJailY;
            jailZ = MCW.instance.currentJailZ;
        }
        else
        {
            if (!blockExists(worldObj, jailX, jailY, jailZ - 31) || !blockExists(worldObj, jailX + 14, jailY, jailZ - 31) || !blockExists(worldObj, jailX, jailY, jailZ + 45) || !blockExists(worldObj, jailX + 14, jailY, jailZ + 45))
            {
                return false;
            }

            if (!MCW.instance.jailBuilt)
            {
                area = 0;
                int j = -1;
                label0:

                do
                {
                    if (j >= 6)
                    {
                        break;
                    }

                    for (int k1 = -1; k1 < 14; k1++)
                    {
                        for (int l2 = -1; l2 < 14; l2++)
                        {
                            if (worldObj.getBlockState(new BlockPos(jailX + k1, jailY + j, jailZ + l2)).getBlock() == Blocks.AIR)
                            {
                                area++;
                            }

                            if (area > maxObstruct)
                            {
                                break label0;
                            }
                        }
                    }

                    j++;
                }
                while (true);
            }

            if (worldObj.getBlockState(new BlockPos(jailX + 15 + 1, jailY + 20, jailZ + 7)).getBlock() == Blocks.FLOWING_WATER || worldObj.getBlockState(new BlockPos(jailX + 15 + 1, jailY + 20, jailZ + 7)).getBlock() == Blocks.WATER)
            {
                area++;
            }

            if (area <= maxObstruct)
            {
                for (int k = -1; k < 6; k++)
                {
                    for (int l1 = -41; l1 < 55; l1++)
                    {
                        for (int i3 = -1; i3 < 16; i3++)
                        {
                            int j4 = rand.nextInt(100);

                            if (j4 < 1)
                            {
                                worldObj.setBlockState(new BlockPos(jailX + i3, jailY + k, jailZ + l1), Blocks.GRAVEL.getDefaultState());
                                continue;
                            }

                            if (j4 < 15)
                            {
                                worldObj.setBlockState(new BlockPos(jailX + i3, jailY + k, jailZ + l1), Blocks.MOSSY_COBBLESTONE.getDefaultState());
                            }
                            else
                            {
                                worldObj.setBlockState(new BlockPos(jailX + i3, jailY + k, jailZ + l1), Blocks.STONE.getDefaultState());
                            }
                        }
                    }
                }

                for (int l = 0; l < 5; l++)
                {
                    for (int i2 = 0; i2 < 13; i2++)
                    {
                        for (int j3 = 0; j3 < 13; j3++)
                        {
                            worldObj.setBlockState(new BlockPos(jailX + j3, jailY + l, jailZ + i2 + 1), Blocks.AIR.getDefaultState());
                            worldObj.setBlockState(new BlockPos(jailX + j3, jailY + l, jailZ + i2 + 1), Blocks.AIR.getDefaultState());
                        }
                    }
                }

                for (int i1 = 0; i1 < 5; i1++)
                {
                    for (int j2 = 3; j2 < 11; j2++)
                    {
                        for (int k3 = 3; k3 < 11; k3++)
                        {
                            worldObj.setBlockState(new BlockPos(jailX + k3, jailY + i1, jailZ + j2 + 1), Blocks.STONE.getDefaultState());
                            worldObj.setBlockState(new BlockPos(jailX + k3, jailY + i1, jailZ + j2 + 1), Blocks.STONE.getDefaultState());
                        }
                    }
                }

                for (int j1 = 0; j1 < 5; j1++)
                {
                    for (int k2 = 5; k2 < 9; k2++)
                    {
                        for (int l3 = 5; l3 < 9; l3++)
                        {
                            worldObj.setBlockState(new BlockPos(jailX + l3, jailY + j1, jailZ + k2 + 1), Blocks.AIR.getDefaultState());
                            worldObj.setBlockState(new BlockPos(jailX + l3, jailY + j1, jailZ + k2 + 1), Blocks.AIR.getDefaultState());
                        }
                    }
                }

                worldObj.setBlockState(new BlockPos(jailX + 7, jailY + 1, jailZ + 4), Blocks.AIR.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 7, jailY + 1, jailZ + 5), Blocks.IRON_BARS.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 3, jailY + 1, jailZ + 7), Blocks.GLASS.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 4, jailY + 1, jailZ + 7), Blocks.GLASS.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 10, jailY + 1, jailZ + 7), Blocks.AIR.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ + 7), Blocks.IRON_BARS.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 7, jailY + 1, jailZ + 11), Blocks.AIR.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 7, jailY + 1, jailZ + 10), Blocks.IRON_BARS.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 4, jailY, jailZ + 8), Blocks.AIR.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 3, jailY, jailZ + 8), Blocks.AIR.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 4, jailY + 1, jailZ + 8), Blocks.AIR.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 3, jailY + 1, jailZ + 8), Blocks.AIR.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 3, jailY, jailZ + 8), Blocks.OAK_DOOR.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 3, jailY, jailZ + 8), Blocks.OAK_DOOR.getDefaultState(), 0);//setBlockmetadatawithnotify
                worldObj.setBlockState(new BlockPos(jailX + 3, jailY + 1, jailZ + 8), Blocks.OAK_DOOR.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 3, jailY + 1, jailZ + 8), Blocks.OAK_DOOR.getDefaultState(), 8);//wtf? 8 flag ?
                byte byte0 = 15;
                byte byte1 = 7;
                int i4;

                for (i4 = 80; worldObj.getBlockState(new BlockPos(jailX + byte0, i4, jailZ + byte1)).getBlock() == Blocks.AIR || worldObj.getBlockState(new BlockPos(jailX + byte0, i4, jailZ + byte1)).getBlock() == Blocks.LEAVES.getDefaultState() || worldObj.getBlockState(new BlockPos(jailX + byte0, i4, jailZ + byte1)).getBlock() == Blocks.LOG.getDefaultState(); i4--) { }

                for (int k4 = 0; k4 < i4 - jailY; k4++)
                {
                    for (int i5 = 0; i5 < 2; i5++)
                    {
                        for (int i7 = 0; i7 < 2; i7++)
                        {
                            worldObj.setBlockState(new BlockPos(jailX + i5 + byte0, jailY + k4, jailZ + byte1 + i7), Blocks.AIR.getDefaultState());
                        }
                    }
                }

                int l4 = 0;

                for (int j5 = 0; j5 < i4 - jailY; j5++)
                {
                    if (l4 == 0)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + byte0, jailY + j5, jailZ + byte1), Blocks.STONE_STAIRS.getDefaultState());
                        worldObj.setBlockState(new BlockPos(jailX + byte0, jailY + j5, jailZ + byte1), Blocks.STONE_STAIRS.getDefaultState(), 3);
                    }

                    if (l4 == 1)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + byte0 + 1, jailY + j5, jailZ + byte1), Blocks.STONE_STAIRS.getDefaultState());
                        worldObj.setBlockState(new BlockPos(jailX + byte0 + 1, jailY + j5, jailZ + byte1), Blocks.STONE_STAIRS.getDefaultState(), 0);
                    }

                    if (l4 == 2)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + byte0 + 1, jailY + j5, jailZ + byte1 + 1), Blocks.STONE_STAIRS.getDefaultState());
                        worldObj.setBlockState(new BlockPos(jailX + byte0 + 1, jailY + j5, jailZ + byte1 + 1), Blocks.STONE_STAIRS.getDefaultState(), 2);
                    }

                    if (l4 == 3)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + byte0, jailY + j5, jailZ + byte1 + 1), Blocks.STONE_STAIRS.getDefaultState());
                        worldObj.setBlockState(new BlockPos(jailX + byte0, jailY + j5, jailZ + byte1 + 1), Blocks.STONE_STAIRS.getDefaultState(), 1);
                    }

                    if (l4++ == 3)
                    {
                        l4 = 0;
                    }
                }

                for (int k5 = 0; k5 < 3; k5++)
                {
                    worldObj.setBlockState(new BlockPos(jailX + 13 + k5, jailY, jailZ + 7), Blocks.AIR.getDefaultState());
                    worldObj.setBlockState(new BlockPos(jailX + 13 + k5, jailY + 1, jailZ + 7), Blocks.AIR.getDefaultState());
                }

                worldObj.setBlockState(new BlockPos(jailX + 13, jailY, jailZ + byte1), Blocks.IRON_DOOR.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 13, jailY, jailZ + byte1), Blocks.IRON_DOOR.getDefaultState(), 0);
                worldObj.setBlockState(new BlockPos(jailX + 13, jailY + 1, jailZ + byte1), Blocks.IRON_DOOR.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 13, jailY + 1, jailZ + byte1), Blocks.IRON_DOOR.getDefaultState(), 8);
                worldObj.setBlockState(new BlockPos(jailX + 15, jailY, jailZ + byte1), Blocks.STONE_STAIRS.getDefaultState());
                worldObj.setBlockState(new BlockPos(jailX + 15, jailY, jailZ + byte1), Blocks.STONE_STAIRS.getDefaultState(), 0);
                worldObj.setBlockState(new BlockPos(jailX + 14, jailY + 2, jailZ + byte1), Blocks.AIR.getDefaultState());

                for (int l5 = 0; l5 < 32; l5++)
                {
                    for (int j7 = 6; j7 < 9; j7++)
                    {
                        for (int l7 = 0; l7 < 4; l7++)
                        {
                            worldObj.setBlockToAir(new BlockPos(jailX + j7, jailY + l7, jailZ - l5 - 1));
                            worldObj.setBlockToAir(new BlockPos(jailX + j7, jailY + l7, jailZ + l5 + 15));
                        }
                    }
                }

                for (int i6 = 1; i6 < 5; i6++)
                {
                    for (int k7 = 0; k7 < 3; k7++)
                    {
                        for (int i8 = 0; i8 < 3; i8++)
                        {
                            for (int l8 = 0; l8 < 4; l8++)
                            {
                                worldObj.setBlockToAir(new BlockPos(jailX + 10 + i8, jailY + l8, (jailZ - i6 * 7) + k7));
                                worldObj.setBlockToAir(new BlockPos(jailX + 2 + i8, jailY + l8, (jailZ - i6 * 7) + k7));
                                worldObj.setBlockToAir(new BlockPos(jailX + 10 + i8, jailY + l8, jailZ + i6 * 7 + 12 + k7));
                                worldObj.setBlockToAir(new BlockPos(jailX + 2 + i8, jailY + l8, jailZ + i6 * 7 + 12 + k7));
                            }
                        }
                    }
                }

                worldObj.setBlockToAir(new BlockPos(jailX + 7, jailY, jailZ));
                worldObj.setBlockToAir(new BlockPos(jailX + 7, jailY + 1, jailZ));
                worldObj.setBlockToAir(new BlockPos(jailX + 7, jailY, jailZ + 14));
                worldObj.setBlockToAir(new BlockPos(jailX + 7, jailY + 1, jailZ + 14));

                for (int j6 = 0; j6 < 4; j6++)
                {
                    worldObj.setBlockState(new BlockPos(jailX + 5, jailY + 1, jailZ - j6 * 7 - 5 - 2), Blocks.IRON_BARS.getDefaultState());
                    worldObj.setBlockState(new BlockPos(jailX + 5, jailY, jailZ - j6 * 7 - 5), Blocks.OAK_DOOR.getDefaultState());
                    worldObj.setBlockState(new BlockPos(jailX + 5, jailY, jailZ - j6 * 7 - 5), Blocks.OAK_DOOR.getDefaultState(), 2);
                    worldObj.setBlockState(new BlockPos(jailX + 5, jailY + 1, jailZ - j6 * 7 - 5), Blocks.OAK_DOOR.getDefaultState());
                    worldObj.setBlockState(new BlockPos(jailX + 5, jailY + 1, jailZ - j6 * 7 - 5), Blocks.OAK_DOOR.getDefaultState(), 10);
                    worldObj.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ - j6 * 7 - 5 - 2), Blocks.IRON_BARS.getDefaultState());
                    worldObj.setBlockToAir(new BlockPos(jailX + 9, jailY, jailZ - j6 * 7 - 5));
                    worldObj.setBlockToAir(new BlockPos(jailX + 9, jailY + 1, jailZ - j6 * 7 - 5));
                    worldObj.setBlockState(new BlockPos(jailX + 9, jailY, jailZ - j6 * 7 - 5), Blocks.OAK_DOOR.getDefaultState());
                    worldObj.setBlockState(new BlockPos(jailX + 9, jailY, jailZ - j6 * 7 - 5), Blocks.OAK_DOOR.getDefaultState(), 0);
                    worldObj.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ - j6 * 7 - 5), Blocks.OAK_DOOR.getDefaultState());
                    worldObj.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ - j6 * 7 - 5), Blocks.OAK_DOOR.getDefaultState(), 8);
                    worldObj.setBlockState(new BlockPos(jailX + 5, jailY + 1, jailZ + j6 * 7 + 19 + 2), Blocks.IRON_BARS.getDefaultState());
                    worldObj.setBlockState(new BlockPos(jailX + 5, jailY, jailZ + j6 * 7 + 19), Blocks.OAK_DOOR.getDefaultState());
                    worldObj.setBlockState(new BlockPos(jailX + 5, jailY, jailZ + j6 * 7 + 19), Blocks.OAK_DOOR.getDefaultState(), 2);
                    worldObj.setBlockState(new BlockPos(jailX + 5, jailY + 1, jailZ + j6 * 7 + 19), Blocks.OAK_DOOR.getDefaultState());
                    worldObj.setBlockState(new BlockPos(jailX + 5, jailY + 1, jailZ + j6 * 7 + 19), Blocks.OAK_DOOR.getDefaultState(), 10);
                    worldObj.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ + j6 * 7 + 19 + 2), Blocks.IRON_BARS.getDefaultState());
                    worldObj.setBlockToAir(new BlockPos(jailX + 9, jailY, jailZ + j6 * 7 + 19));
                    worldObj.setBlockToAir(new BlockPos(jailX + 9, jailY + 1, jailZ + j6 * 7 + 19));
                    worldObj.setBlockState(new BlockPos(jailX + 9, jailY, jailZ + j6 * 7 + 19), Blocks.OAK_DOOR.getDefaultState());
                    worldObj.setBlockState(new BlockPos(jailX + 9, jailY, jailZ + j6 * 7 + 19), Blocks.OAK_DOOR.getDefaultState(), 0);
                    worldObj.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ + j6 * 7 + 19), Blocks.OAK_DOOR.getDefaultState());
                    worldObj.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ + j6 * 7 + 19), Blocks.OAK_DOOR.getDefaultState(), 8);

                    if (rand.nextInt(1) == 0)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + 12, jailY + 2, jailZ - j6 * 7 - 5), Blocks.TORCH.getDefaultState());
                    }

                    if (rand.nextInt(1) == 0)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + 2, jailY + 2, jailZ - j6 * 7 - 5), Blocks.TORCH.getDefaultState());
                    }

                    if (rand.nextInt(1) == 0)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + 12, jailY + 2, jailZ + j6 * 7 + 19), Blocks.TORCH.getDefaultState());
                    }

                    if (rand.nextInt(1) == 0)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + 2, jailY + 2, jailZ + j6 * 7 + 19), Blocks.TORCH.getDefaultState());
                    }
                }

                for (int k6 = 0; k6 < 9; k6++)
                {
                    if (rand.nextInt(2) == 0)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + 6, jailY + 2, jailZ - k6 * 4 - 2), Blocks.TORCH.getDefaultState());
                    }

                    if (rand.nextInt(2) == 0)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + 8, jailY + 2, jailZ - k6 * 4 - 2), Blocks.TORCH.getDefaultState());
                    }

                    if (rand.nextInt(2) == 0)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + 6, jailY + 2, jailZ + k6 * 4 + 18), Blocks.TORCH.getDefaultState());
                    }

                    if (rand.nextInt(2) == 0)
                    {
                        worldObj.setBlockState(new BlockPos(jailX + 8, jailY + 2, jailZ + k6 * 4 + 18), Blocks.TORCH.getDefaultState());
                    }
                }
            }
            else
            {
                return false;
            }
        }

        worldObj.setBlockState(new BlockPos(jailX + 12, jailY, jailZ + 13), Blocks.CHEST.getDefaultState());
        TileEntityChest tileentitychest = new TileEntityChest();
        worldObj.setTileEntity(new BlockPos(jailX + 12, jailY, jailZ + 13), tileentitychest);
        worldObj.setBlockState(new BlockPos(jailX + 12, jailY, jailZ + 1), Blocks.CHEST.getDefaultState());
        TileEntityChest tileentitychest1 = new TileEntityChest();
        worldObj.setTileEntity(new BlockPos(jailX + 12, jailY, jailZ + 1), tileentitychest1);
        worldObj.setBlockState(new BlockPos(jailX, jailY, jailZ + 13), Blocks.CHEST.getDefaultState());
        TileEntityChest tileentitychest2 = new TileEntityChest();
        worldObj.setTileEntity(new BlockPos(jailX, jailY, jailZ + 13), tileentitychest2);
        worldObj.setBlockState(new BlockPos(jailX, jailY, jailZ + 1), Blocks.CHEST.getDefaultState());
        TileEntityChest tileentitychest3 = new TileEntityChest();
        worldObj.setTileEntity(new BlockPos(jailX, jailY, jailZ + 1), tileentitychest3);

        for (int l6 = 1; l6 < tileentitychest.getSizeInventory(); l6++)
        {
            tileentitychest.setInventorySlotContents(l6, null);
            tileentitychest1.setInventorySlotContents(l6, null);
            tileentitychest2.setInventorySlotContents(l6, null);
        }

        Object obj = null;
        ItemStack aitemstack[] = ((EntityPlayer)(entityplayersp)).inventory.mainInventory;

        for (int j8 = 0; j8 < aitemstack.length; j8++)
        {
            ItemStack itemstack = aitemstack[j8];

            if (itemstack != null)
            {
                tileentitychest.setInventorySlotContents(j8, itemstack);
                ((EntityPlayer)(entityplayersp)).inventory.mainInventory[j8] = null;
            }
        }

        for (int k8 = 1; k8 < tileentitychest3.getSizeInventory(); k8++)
        {
            int i9 = rand.nextInt(10);

            if (i9 == 1)
            {
                tileentitychest3.setInventorySlotContents(k8, new ItemStack(MCItems.Bandaid, rand.nextInt(2) + 1, 0));
            }

            if (i9 == 2)
            {
                tileentitychest3.setInventorySlotContents(k8, new ItemStack(MCItems.Money, rand.nextInt(24) + 1, 0));
            }
        }

        worldObj.setBlockState(new BlockPos(jailX + 11, jailY, jailZ + 13), Blocks.MOB_SPAWNER.getDefaultState());
        TileEntityMobSpawner tileentitymobspawner = new TileEntityMobSpawner();
        worldObj.setTileEntity(new BlockPos(jailX + 11, jailY, jailZ + 13), tileentitymobspawner);
        tileentitymobspawner.getSpawnerBaseLogic().setEntityName("Skeleton");
        tileentitychest1.setInventorySlotContents(rand.nextInt(5), new ItemStack(Items.STONE_PICKAXE, 1, 0));
        tileentitychest1.setInventorySlotContents(rand.nextInt(5) + 5, new ItemStack(Items.APPLE, 1, 0));
        tileentitychest2.setInventorySlotContents(rand.nextInt(5) + 5, new ItemStack(Blocks.TORCH, rand.nextInt(16), 0));
        tileentitychest2.setInventorySlotContents(rand.nextInt(5), new ItemStack(Items.APPLE, 1, 0));
        worldObj.setBlockState(new BlockPos(jailX + 6, jailY + 2, jailZ + 9), Blocks.TORCH.getDefaultState());
        int j9 = rand.nextInt(11);

        for (int k9 = 0; k9 < 4; k9++)
        {
            for (int j10 = 0; j10 < 4; j10++)
            {
                int l10 = 0;
                int i11 = 0;

                switch (j10 + 1)
                {
                    case 1:
                        l10 = jailX + 12;
                        i11 = jailZ - k9 * 7 - 5;
                        break;

                    case 2:
                        l10 = jailX + 2;
                        i11 = jailZ - k9 * 7 - 5;
                        break;

                    case 3:
                        l10 = jailX + 12;
                        i11 = jailZ + k9 * 7 + 19;
                        break;

                    case 4:
                        l10 = jailX + 2;
                        i11 = jailZ + k9 * 7 + 19;
                        break;

                    default:
                        l10 = jailX + 12;
                        i11 = jailZ - k9 * 7 - 5;
                        break;
                }

                if (k9 * 4 + j10 == j9)
                {
                    populateCell(l10, i11, worldObj, entityplayersp, true);
                }
                else
                {
                    populateCell(l10, i11, worldObj, entityplayersp, false);
                }

                if (rand.nextInt(3) == 0)
                {
                    dropTreasure(worldObj, jailX + 12, jailY + 2, jailZ - k9 * 7 - 5 - 1);
                }

                if (rand.nextInt(3) == 0)
                {
                    dropTreasure(worldObj, jailX + 2, jailY + 2, jailZ - k9 * 7 - 5 - 1);
                }

                if (rand.nextInt(3) == 0)
                {
                    dropTreasure(worldObj, jailX + 12, jailY + 2, jailZ + k9 * 7 + 19 + 1);
                }

                if (rand.nextInt(3) == 0)
                {
                    dropTreasure(worldObj, jailX + 2, jailY + 2, jailZ + k9 * 7 + 19 + 1);
                }
            }
        }

        for (int l9 = 1; l9 < rand.nextInt(5) + 3; l9++)
        {
            CREEPSEntityLawyerFromHell creepsentitylawyerfromhell = new CREEPSEntityLawyerFromHell(worldObj);
            creepsentitylawyerfromhell.setLocationAndAngles(jailX + 8, jailY + 1, jailZ - 11 - 1, ((EntityPlayer)(entityplayersp)).rotationYaw, 0.0F);
            creepsentitylawyerfromhell.undead = true;
            creepsentitylawyerfromhell.texture =  new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_LAWYER_UNDEAD);
            creepsentitylawyerfromhell.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
            worldObj.spawnEntityInWorld(creepsentitylawyerfromhell);
            CREEPSEntityLawyerFromHell creepsentitylawyerfromhell2 = new CREEPSEntityLawyerFromHell(worldObj);
            creepsentitylawyerfromhell2.setLocationAndAngles(jailX + 8, jailY + 1, jailZ + 11 + 15, ((EntityPlayer)(entityplayersp)).rotationYaw, 0.0F);
            creepsentitylawyerfromhell2.undead = true;
            creepsentitylawyerfromhell2.texture =  new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_LAWYER_UNDEAD);
            creepsentitylawyerfromhell.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
            worldObj.spawnEntityInWorld(creepsentitylawyerfromhell2);
        }

        for (int i10 = 2; i10 < rand.nextInt(3) + 3; i10++)
        {
            CREEPSEntityLawyerFromHell creepsentitylawyerfromhell1 = new CREEPSEntityLawyerFromHell(worldObj);
            creepsentitylawyerfromhell1.setLocationAndAngles(jailX + i10, jailY + 2, jailZ + 2, ((EntityPlayer)(entityplayersp)).rotationYaw, 0.0F);
            creepsentitylawyerfromhell1.undead = true;
            creepsentitylawyerfromhell1.texture =  new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_LAWYER_UNDEAD);
            creepsentitylawyerfromhell1.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
            worldObj.spawnEntityInWorld(creepsentitylawyerfromhell1);
            CREEPSEntityLawyerFromHell creepsentitylawyerfromhell3 = new CREEPSEntityLawyerFromHell(worldObj);
            creepsentitylawyerfromhell3.setLocationAndAngles(jailX + 2, jailY + 2, jailZ + i10, ((EntityPlayer)(entityplayersp)).rotationYaw, 0.0F);
            creepsentitylawyerfromhell3.undead = true;
            creepsentitylawyerfromhell3.texture =  new ResourceLocation(Reference.MODID, Reference.TEXTURE_PATH_ENTITES + Reference.TEXTURE_LAWYER_UNDEAD);
            creepsentitylawyerfromhell1.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
            worldObj.spawnEntityInWorld(creepsentitylawyerfromhell3);
        }

        ((EntityPlayer) entityplayersp).setPosition(jailX + 7, jailY + 2, jailZ + 7);
        ((EntityPlayer) entityplayersp).heal(20F);

        if (rand.nextInt(5) == 0)
        {
            dropTreasure(worldObj, jailX + 8, jailY + 2, jailZ + 8);
        }

        List list = worldObj.getEntitiesWithinAABBExcludingEntity(entityplayersp, ((EntityPlayer)(entityplayersp)).getEntityBoundingBox().expand(4D, 4D, 4D));

        for (int k10 = 0; k10 < list.size(); k10++)
        {
            Entity entity = (Entity)list.get(k10);

            if (entity != null && !(entity instanceof EntityPlayer))
            {
                entity.setDead();
            }
        }

        MCW.instance.currentfine = 0;
        boolean flag = false;

        if (MCW.instance.currentfine < 0)
        {
        	MCW.instance.currentfine = 0;
        }

        MCW.instance.currentJailX = jailX;
        MCW.instance.currentJailY = jailY;
        MCW.instance.currentJailZ = jailZ;
        MCW.instance.jailBuilt = true;
        return true;
    }

    public void dropTreasure(World world, int i, int j, int k)
    {
        int l = rand.nextInt(12);
        ItemStack itemstack = null;

        switch (l)
        {
            case 1:
                itemstack = new ItemStack(Items.WHEAT, rand.nextInt(2) + 1);
                break;

            case 2:
                itemstack = new ItemStack(Items.COOKIE, rand.nextInt(3) + 3);
                break;

            case 3:
                itemstack = new ItemStack(Items.PAPER, 1);
                break;

            case 4:
                itemstack = new ItemStack(MCItems.Blorpcola, rand.nextInt(3) + 1);
                break;

            case 5:
                itemstack = new ItemStack(Items.BREAD, 1);
                break;

            case 6:
                itemstack = new ItemStack(MCItems.EvilEgg, rand.nextInt(2) + 1);
                break;

            case 7:
                itemstack = new ItemStack(Items.WATER_BUCKET, 1);
                break;

            case 8:
                itemstack = new ItemStack(Items.CAKE, 1);
                break;

            case 9:
                itemstack = new ItemStack(MCItems.Money, rand.nextInt(5) + 5);
                break;

            case 10:
                itemstack = new ItemStack(MCItems.Lolly, rand.nextInt(2) + 1);
                break;

            case 11:
                itemstack = new ItemStack(Items.CAKE, 1);
                break;

            case 12:
                itemstack = new ItemStack(MCItems.GooDonut, rand.nextInt(2) + 1);
                break;

            default:
                itemstack = new ItemStack(Items.COOKIE, rand.nextInt(2) + 1);
                break;
        }

        EntityItem entityitem = new EntityItem(world, i, j, k, itemstack);
        //entityitem.delayBeforeCanPickup = 10;
        if(!worldObj.isRemote)
        world.spawnEntityInWorld(entityitem);
    }

    public void populateCell(int i, int j, World world, EntityPlayer entityplayer, boolean flag)
    {
    	/*
    	if (flag)
        {
            List list = world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.getEntityBoundingBox().expand(26D, 26D, 26D));

            
            for (int l = 0; l < list.size(); l++)
            {
                Entity entity = (Entity)list.get(l);

                /* TODO if ((entity instanceof CREEPSEntityHotdog) && ((CREEPSEntityHotdog)entity).tamed)
                {
                    ((CREEPSEntityHotdog)entity).wanderstate = 1;
                    ((CREEPSEntityHotdog)entity).getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);

                    if (((CREEPSEntityHotdog)entity).dogsize > 1.0F)
                    {
                        ((CREEPSEntityHotdog)entity).dogsize = 1.0F;
                    }

                    ((CREEPSEntityHotdog)entity).setLocationAndAngles(i, jailY, j, entityplayer.rotationYaw, 0.0F);
                    continue;
                }

                if (!(entity instanceof CREEPSEntityGuineaPig) || !((CREEPSEntityGuineaPig)entity).tamed)
                {
                    continue;
                }

                ((CREEPSEntityGuineaPig)entity).wanderstate = 1;
                ((CREEPSEntityGuineaPig)entity).getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);

                if (((CREEPSEntityGuineaPig)entity).modelsize > 1.0F)
                {
                    ((CREEPSEntityGuineaPig)entity).modelsize = 1.0F;
                }

                ((CREEPSEntityGuineaPig)entity).setLocationAndAngles(i, jailY, j, entityplayer.rotationYaw, 0.0F);
            }

            return;
        } */

        int k = rand.nextInt(5);

        switch (k + 1)
        {
        	 case 1:
                CREEPSEntityRatMan creepsentityratman = new CREEPSEntityRatMan(world);
                creepsentityratman.setLocationAndAngles(i, jailY, j, entityplayer.rotationYaw, 0.0F);
                world.spawnEntityInWorld(creepsentityratman);
                break; 

            case 2:
                CREEPSEntityPrisoner creepsentityprisoner = new CREEPSEntityPrisoner(world);
                creepsentityprisoner.setLocationAndAngles(i, jailY, j, entityplayer.rotationYaw, 0.0F);
                world.spawnEntityInWorld(creepsentityprisoner);
                break;

             // TODO
             /* case 3:
                CREEPSEntityCamelJockey creepsentitycameljockey = new CREEPSEntityCamelJockey(world);
                creepsentitycameljockey.setLocationAndAngles(i, jailY, j, entityplayer.rotationYaw, 0.0F);
                world.spawnEntityInWorld(creepsentitycameljockey);
                break; */

            case 4:
                CREEPSEntityMummy creepsentitymummy = new CREEPSEntityMummy(world);
                creepsentitymummy.setLocationAndAngles(i, jailY, j, entityplayer.rotationYaw, 0.0F);
                world.spawnEntityInWorld(creepsentitymummy);
                break;

            case 5:
                CREEPSEntityPrisoner creepsentityprisoner1 = new CREEPSEntityPrisoner(world);
                creepsentityprisoner1.setLocationAndAngles(i, jailY, j, entityplayer.rotationYaw, 0.0F);
                world.spawnEntityInWorld(creepsentityprisoner1);
                break;

            default:
                CREEPSEntityPrisoner creepsentityprisoner2 = new CREEPSEntityPrisoner(world);
                creepsentityprisoner2.setLocationAndAngles(i, jailY, j, entityplayer.rotationYaw, 0.0F);
                world.spawnEntityInWorld(creepsentityprisoner2);
                break;
        }
    }

    public boolean suckMoney(EntityPlayer player)
    {
        Object obj = null;
        ItemStack aitemstack[] = player.inventory.mainInventory;
        int i = 0;

        for (int j = 0; j < aitemstack.length; j++)
        {
            ItemStack itemstack = aitemstack[j];

            if (itemstack == null || itemstack.getItem() != MCItems.Money)
            {
                continue;
            }

            if (!undead)
            {
                // worldObj.playSoundAtEntity(this, "morecreeps:lawyersuck", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
                worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_LAWYER_SUCK, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            }

            i = rand.nextInt(itemstack.stackSize) + 1;

            if (i > itemstack.stackSize)
            {
                i = itemstack.stackSize;
            }

            if (i == itemstack.stackSize)
            {
                player.inventory.mainInventory[j] = null;
            }
            else
            {
                itemstack.stackSize -= i;
            }
        }

        if (i > 0 && !undead)
        {
            // worldObj.playSoundAtEntity(this, "morecreeps:lawyertake", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_LAWYER_TAKE, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            
        }

        return true;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setInteger("LawyerState", lawyerstate);
        nbttagcompound.setInteger("LawyerTimer", lawyertimer);
        nbttagcompound.setInteger("JailX", jailX);
        nbttagcompound.setInteger("JailY", jailY);
        nbttagcompound.setInteger("JailZ", jailZ);
        nbttagcompound.setBoolean("Undead", undead);
        nbttagcompound.setFloat("ModelSize", modelsize);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        lawyerstate = nbttagcompound.getInteger("LawyerState");
        lawyertimer = nbttagcompound.getInteger("LawyerTimer");
        jailX = nbttagcompound.getInteger("JailX");
        jailY = nbttagcompound.getInteger("JailY");
        jailZ = nbttagcompound.getInteger("JailZ");
        undead = nbttagcompound.getBoolean("Undead");
        modelsize = nbttagcompound.getFloat("ModelSize");
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected SoundEvent getLivingSound()
    {
        if (!undead)
        {
            return MCSoundEvents.ENTITY_LAWYER;
        }
        else
        {
            return MCSoundEvents.ENTITY_LAWYER_UNDEAD;
        }
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected SoundEvent getHurtSound()
    {
        if (!undead)
        {
            return MCSoundEvents.ENTITY_LAWYER_HURT;
        }
        else
        {
            return MCSoundEvents.ENTITY_LAWYER_UNDEAD_HURT;
        }
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected SoundEvent getDeathSound()
    {
        if (!undead)
        {
            return MCSoundEvents.ENTITY_LAWYER_DEATH;
        }
        else
        {
            return MCSoundEvents.ENTITY_LAWYER_UNDEAD_DEATH;
        }
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

    private void smoke()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)((float)i * 0.5F), posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)((float)i * 0.5F), posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)((float)i * 0.5F)) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)((float)i * 0.5F) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)((float)i * 0.5F), posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)((float)i * 0.5F)) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)((float)i * 0.5F), posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)((float)i * 0.5F) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)((float)i * 0.5F), posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)((float)i * 0.5F)) - (double)width, d, d1, d2, new int[0]);
                worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)((float)i * 0.5F), posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)((float)i * 0.5F) - (double)width, d, d1, d2, new int[0]);
            }
        }
    }

    /**
     * Returns the item that this EntityLiving is holding, if any.
     */
    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }

    public void onDeath(DamageSource damagesource)
    {
    	Entity entity = damagesource.getEntity();
        if (rand.nextInt(3) == 0 && !undead && (entity instanceof EntityPlayer))
        {
        	
            for (int i = 0; i < rand.nextInt(4) + 3; i++)
            {
                CREEPSEntityLawyerFromHell creepsentitylawyerfromhell = new CREEPSEntityLawyerFromHell(worldObj);
                smoke();
                creepsentitylawyerfromhell.setLocationAndAngles(entity.posX + (double)(rand.nextInt(4) - rand.nextInt(4)), entity.posY - 1.5D, entity.posZ + (double)(rand.nextInt(4) - rand.nextInt(4)), rotationYaw, 0.0F);
                creepsentitylawyerfromhell.undead = true;
                creepsentitylawyerfromhell.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
                if(!worldObj.isRemote)
                worldObj.spawnEntityInWorld(creepsentitylawyerfromhell);
            }
        }

        else if (rand.nextInt(5) == 0 && !undead)
        {
            // worldObj.playSoundAtEntity(this, "morecreeps:lawyerbum", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            worldObj.playSound((EntityPlayer) null, getPosition(), MCSoundEvents.ENTITY_LAWYER_BUM, SoundCategory.NEUTRAL, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            CREEPSEntityBum creepsentitybum = new CREEPSEntityBum(worldObj);
            smoke();
            creepsentitybum.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
            worldObj.spawnEntityInWorld(creepsentitybum);
        } 
        smoke();
        super.onDeath(damagesource);
    }

    public static boolean blockExists(World parWorld, int x, int y, int z) 
    {
    	IBlockState state = parWorld.getBlockState(new BlockPos(x, y, z));
    	if (state != null)
    	return true;
    	else
    	return false;
    }
    
    static
    {
        defaultHeldItem = new ItemStack(Items.BONE, 1);
    }
}