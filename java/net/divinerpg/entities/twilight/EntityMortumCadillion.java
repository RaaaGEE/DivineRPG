package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMortumCadillion extends EntityDivineRPGMob {

	public EntityMortumCadillion(World var1) {
		super(var1);
		addAttackingAI();
		setSize(1.0F, 1.3F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumCadillionHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumCadillionDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumCadillionSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumCadillionFollowRange);
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
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
	protected Item getDropItem() {
		return TwilightItemsOther.mortumSoul;
	}

	@Override
	public String mobName() {
		return "Mortum Cadillion";
	}
}
