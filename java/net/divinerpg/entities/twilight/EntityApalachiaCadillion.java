package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityApalachiaCadillion extends EntityDivineRPGMob {

	public EntityApalachiaCadillion(World var1) {
		super(var1);
		setSize(1.0F, 1.3F);
		addAttackingAI();
		experienceValue = 40;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaCadillionHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaCadillionDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaCadillionSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaCadillionFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.cadillion);
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
	protected void dropFewItems(boolean beenHit, int lootLevel) {
		dropItem(TwilightItemsOther.apalachiaSoul, rand.nextInt(2 + lootLevel));
		dropItem(ItemsFood.enrichedMagicMeat, 1);
	}

	@Override
	public boolean isValidLightLevel() {
		return true;
	}

	@Override
	public String mobName() {
		return "Apalachia Cadillion";
	}
}