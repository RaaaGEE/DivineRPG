package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntitySoulSpider extends EntityDivineRPGMob {

	public EntitySoulSpider(World w) {
		super(w);
		setSize(0.3F, 0.3F);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.soulSpiderHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.soulSpiderDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.soulSpiderSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.soulSpiderFollowRange);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && ticksExisted > 600)
			setDead();
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
	public String mobName() {
		return "Soul Spider";
	}
}