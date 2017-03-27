package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMortumDemon extends EntityDivineRPGMob {

	public EntityMortumDemon(World var1) {
		super(var1);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumDemonHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumDemonDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumDemonSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumDemonFollowRange);
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.demonDarkness);
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
		return TwilightItemsOther.mortumSoul;
	}

	@Override
	public String mobName() {
		return "Demon of Darkness";
	}
}