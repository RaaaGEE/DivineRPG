package net.divinerpg.entities.vanilla;

import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityEnderSpider extends EntityEnderman {

	public EntityEnderSpider(World var1) {
		super(var1);
		setSize(0.9F, 0.9F);
		experienceValue = 20;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.enderSpiderHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.enderSpiderDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.enderSpiderSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.enderSpiderFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.hellSpider);
	}

	@Override
	protected Item getDropItem() {
		return VanillaItemsOther.enderShards;
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		dropItem(VanillaItemsOther.enderShards, 1);
	}
}