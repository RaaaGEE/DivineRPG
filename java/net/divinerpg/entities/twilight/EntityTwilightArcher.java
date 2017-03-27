package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityTwilightArcher extends EntityApalachiaArcher {

	private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.twilightBow, 1);

	public EntityTwilightArcher(World var1) {
		super(var1);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernArcherHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernArcherDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernArcherSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernArcherFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.archer);
	}

	@Override
	public ItemStack getHeldItem() {
		return defaultHeldItem;
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.highHit);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.highHit);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.mortumSoul;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float f) {
		EntityDivineArrow arrow = new EntityDivineArrow(worldObj, this, var1, 1.6F, 12.0F, 17, "furyArrow");
		playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
		worldObj.spawnEntityInWorld(arrow);
	}

	@Override
	public String mobName() {
		return "Twilight Archer";
	}
}