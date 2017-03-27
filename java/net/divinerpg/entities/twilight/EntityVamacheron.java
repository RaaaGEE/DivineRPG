package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityVamacheron extends EntityDivineRPGBoss {

	private int waitTick;

	public EntityVamacheron(World var1) {
		super(var1);
		addAttackingAI();
		setSize(1.25F, 2F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.vamacheronHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.vamacheronDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.vamacheronSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.vamacheronFollowRange);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsWeapons.haliteBlade;
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		dropItem(getDropItem(), 1);

		if (rand.nextBoolean())
			dropItem(Item.getItemFromBlock(VanillaBlocks.vamacheronStatue), 1);
	}

	@Override
	public String mobName() {
		return "Vamacheron";
	}

	@Override
	public String name() {
		return "Vamacheron";
	}

	@Override
	public IChatComponent chat() {
		return null;
	}
}