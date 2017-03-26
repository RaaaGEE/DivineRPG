package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityApalachiaTomo extends EntityPeacefulUntilAttacked {

	public EntityApalachiaTomo(World var1) {
		super(var1);
		setSize(1.1F, 1.0F);
		experienceValue = 40;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaTomoHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaTomoDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaTomoSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaTomoFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.croak);
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
		final int stackCount = rand.nextInt(2 + lootLevel);
		for (int i = 0; i < stackCount; i++) {
			dropItem(getDropItem(), 1);
			dropItem(ItemsFood.enrichedMagicMeat, 2);
		}
		dropItem(getDropItem(), 1);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.apalachiaSoul;
	}

	@Override
	public String mobName() {
		return "Apalachia Tomo";
	}
}
