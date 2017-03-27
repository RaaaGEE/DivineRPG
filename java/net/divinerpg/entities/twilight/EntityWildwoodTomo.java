package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityWildwoodTomo extends EntityPeacefulUntilAttacked {

	public EntityWildwoodTomo(World var1) {
		super(var1);
		setSize(1.1F, 1.0F);
		experienceValue = 40;
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes() {
	    super.applyEntityAttributes();
	    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.wildwoodTomoHealth);
	    getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.wildwoodTomoDamage);
	    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.wildwoodTomoSpeed);
	    getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.wildwoodTomoFollowRange);
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
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		super.dropFewItems(beenHit, lootingLevel);
		dropItem(ItemsFood.magicMeat, 1);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.wildwoodSoul;
	}

	@Override
	public String mobName() {
		return "Wildwood Tomo";
	}
}