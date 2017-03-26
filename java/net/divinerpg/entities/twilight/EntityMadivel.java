package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMadivel extends EntityDivineRPGMob {

	public EntityMadivel(World var1) {
		super(var1);
		addBasicAI();
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.madivelHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.madivelDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.madivelSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.madivelFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.madivel);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.madivelHurt);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.madivelHurt);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.edenSoul;
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		super.dropFewItems(beenHit, lootingLevel);
		
		if (isBurning())
			dropItem(ItemsFood.empoweredMeat, 1);
		else
			dropItem(ItemsFood.rawEmpoweredMeat, 1);
	}

	@Override
	public String mobName() {
		return "Madivel";
	}
}