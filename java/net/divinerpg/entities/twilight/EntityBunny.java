package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBunny extends EntityDivineRPGTameable {

	public EntityBunny(World var1) {
		super(var1);
		setSize(0.5F, 0.5F);
		experienceValue = 40;
	}
	
	@Override
	public void entityInit() {
	    super.entityInit();
	    dataWatcher.addObject(19, 0);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		if(!isTamed())
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.bunnyHealth);
		else 
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.bunnySpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.bunnyFollowRange);
	}

	@Override
	protected boolean canDespawn() {
		return !isTamed();
	}

	@Override
	public void onDeath(DamageSource var1) {
		super.onDeath(var1);
		if(!worldObj.isRemote && !isTamed()) {
			Entity var3 = var1.getEntity();
			if(var3 instanceof EntityPlayer) {
				((EntityPlayer)var3).addStat(DivineRPGAchievements.friendOrFoe, 1);
			}
			this.transform();
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		double damage = EntityStats.bunnyDamage;
		if(isTamed()) {
			dataWatcher.updateObject(19, 1);
		}
		return target.attackEntityFrom(DamageSource.causeMobDamage(this), (float)damage);
	}
	
	@Override
	public void onUpdate() {
	    super.onUpdate();
	    if(!worldObj.isRemote) {
	        if(isTamed() && getAttackTarget() == null) 
	        	dataWatcher.updateObject(19, 0);
	    }
	}

	private void transform()  {
		if(!worldObj.isRemote) {
			EntityAngryBunny e = new EntityAngryBunny(worldObj);
			e.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			worldObj.spawnEntityInWorld(e);
			setDead();
		}
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack held = player.inventory.getCurrentItem();

		if(isTamed()) {
			if(held != null && held.getItem() instanceof ItemFood) {
				ItemFood food = (ItemFood)held.getItem();

				if(food.isWolfsFavoriteMeat() && getHealth() < 20) {
					if(!player.capabilities.isCreativeMode) {
						--held.stackSize;
					}
					heal((float)food.func_150905_g/*getHealAmount*/(held));
					if(held.stackSize <= 0) {
						player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
					}
					return true;
				}
			}
			if(player.getUniqueID().toString().equals(func_152113_b()) && !this.worldObj.isRemote) {
				aiSit.setSitting(!isSitting());
				isJumping = false;
				setPathToEntity((PathEntity)null);
			}
		}
		else if(held != null && held.getItem() == TwilightItemsOther.edenSparkles) {
			if(!player.capabilities.isCreativeMode) {
				--held.stackSize;
			}

			if(held.stackSize <= 0) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
			}

			if(!worldObj.isRemote) {
				if(rand.nextInt(3) == 0) {
					setTamed(true);
					setPathToEntity((PathEntity)null);
					setAttackTarget((EntityLiving)null);
					aiSit.setSitting(true);
					getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
					setHealth(20);
					func_152115_b(player.getUniqueID().toString());
					playTameEffect(true);
					worldObj.setEntityState(this, (byte)7);
				} else {
					playTameEffect(false);
					worldObj.setEntityState(this, (byte)6);
				}
			}
			return true;
		}
		return super.interact(player);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.bunny);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.bunnyHurt);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.bunnyHurt);
	}
	
	@Override
	protected void dropFewItems(boolean beenHit, int lootingLevel) {
		if(rand.nextBoolean()) 
			dropItem(TwilightItemsOther.edenSoul, 1+lootingLevel);
	}

	@Override
	public String mobName() {
		return "Bunny";
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}
	
	@Override
	public EntityLivingBase getAttackTarget() {
	    EntityLivingBase e = super.getAttackTarget();
	    if(e != null && ((isTamed() && getDistanceSqToEntity(e) < 144) || !isTamed())) 
	    	return e;
	    return null;
	}
}