package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySunArcher extends EntityDivineRPGMob implements IRangedAttackMob {

	private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.edenBow, 1);

	public EntitySunArcher(World var1) {
		super(var1);
		tasks.addTask(4, new EntityAIArrowAttack(this, this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(), 50, 10));
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 32.0F));
		tasks.addTask(9, new EntityAILookIdle(this));
		tasks.addTask(6, new EntityAIWander(this, getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * 4));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.sunArcherHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.sunArcherDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.sunArcherSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.sunArcherFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return "mob.zombie.say";
	}

	@Override
	public ItemStack getHeldItem() {
		return defaultHeldItem;
	}

	@Override
	protected String getHurtSound() {
		return "mob.zombie.hit";
	}

	@Override
	protected String getDeathSound() {
		return "mob.zombie.death";
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		if (isBurning())
			dropItem(ItemsFood.empoweredMeat, 1);
		else
			dropItem(ItemsFood.rawEmpoweredMeat, 1);
		dropItem(TwilightItemsOther.edenSoul, rand.nextInt(2 + rand.nextInt(2 + lootingLevel)));
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	public boolean isValidLightLevel() {
		return true;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase e, float f) {
		EntityDivineArrow arrow = new EntityDivineArrow(worldObj, this, e, 1.6F, 12.0F, 9, "edenArrow");
		playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
		worldObj.spawnEntityInWorld(arrow);
	}

	@Override
	public String mobName() {
		return "Sun Archer";
	}
}