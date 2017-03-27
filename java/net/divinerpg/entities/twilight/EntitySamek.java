package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntitySamek extends EntityPeacefulUntilAttacked {

	public EntitySamek(World var1) {
		super(var1);
		setSize(0.6F, 1.8F);
		experienceValue = 40;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.samekHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.samekDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.samekSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.samekFollowRange);
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
		return TwilightItemsOther.skythernSoul;
	}

	@Override
	public String mobName() {
		return "Samek";
	}
}