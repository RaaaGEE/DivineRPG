package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntitySkythernGolem extends EntityDivineRPGMob {

	public EntitySkythernGolem(World var1) {
		super(var1);
		setSize(1F, 2.8F);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernGolemHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernGolemDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernGolemSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernGolemFollowRange);
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
		return TwilightItemsOther.skythernSoul;
	}

	@Override
	public String mobName() {
		return "Skythern Golem";
	}
}