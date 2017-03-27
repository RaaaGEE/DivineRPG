package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityVerek extends EntityDivineRPGMob {

	public EntityVerek(World var1) {
		super(var1);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.verekHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.verekDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.verekSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.verekFollowRange);
	}

	@Override
	public int getTotalArmorValue() {
		return 6;
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.verek);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.verekHurt);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.verekHurt);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.wildwoodSoul;
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		dropItem(ItemsFood.magicMeat, 1);
		final int stackCount = rand.nextInt(2 + lootingLevel);
		for (int i = 0; i < stackCount; i++) {
			dropItem(TwilightItemsOther.wildwoodSoul, 2);
		}
	}

	@Override
	public String mobName() {
		return "Verek";
	}
}