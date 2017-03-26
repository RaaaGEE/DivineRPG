
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsCrops;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityApalachiaGolem extends EntityDivineRPGMob {

	public EntityApalachiaGolem(World var1) {
		super(var1);
		addAttackingAI();
		setSize(1F, 2.8F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaGolemHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaGolemDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaGolemSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaGolemFollowRange);
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.growl);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.growlHurt);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.growlHurt);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.apalachiaSoul;
	}

	@Override
	protected void dropFewItems(boolean beenHit, int lootLevel) {
		dropItem(ItemsFood.enrichedMagicMeat, 1);
		dropItem(TwilightItemsOther.apalachiaSoul, rand.nextInt(3) + 1);

		final int rnd = rand.nextInt(4);
		if (rnd == 0)
			dropItem(TwilightItemsCrops.pinkGlowboneSeeds, rand.nextInt(3) + 1);
		else if (rnd == 1)
			dropItem(TwilightItemsCrops.purpleGlowboneSeeds, rand.nextInt(3) + 1);
	}

	@Override
	public String mobName() {
		return "Apalachia Golem";
	}
}
