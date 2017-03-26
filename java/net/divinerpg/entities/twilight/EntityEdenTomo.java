package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityEdenTomo extends EntityPeacefulUntilAttacked {

	public EntityEdenTomo(World var1) {
		super(var1);
		setSize(1.1F, 1.0F);
		experienceValue = 40;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.edenTomoHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.edenTomoDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.edenTomoSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.edenTomoFollowRange);
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
	protected void dropFewItems(boolean beenHit, int lootingLevel) {
		super.dropFewItems(beenHit, lootingLevel);

		if (rand.nextInt(2) == 0)
			dropItem(TwilightItemsOther.edenSoul, 1 + lootingLevel);

		if (isBurning())
			dropItem(ItemsFood.empoweredMeat, 1);
		else
			dropItem(ItemsFood.rawEmpoweredMeat, 1);

		if (rand.nextInt(3) == 0)
			dropItem(Items.gold_ingot, rand.nextInt(3) + 1);
	}

	@Override
	public String mobName() {
		return "Eden Tomo";
	}
}