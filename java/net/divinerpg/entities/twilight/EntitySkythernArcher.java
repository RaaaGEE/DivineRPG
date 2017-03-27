package net.divinerpg.entities.twilight;

import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySkythernArcher extends EntityApalachiaArcher {

	private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.skythernBow, 1);

	public EntitySkythernArcher(World var1) {
		super(var1);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernArcherHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernArcherDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernArcherSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernArcherFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.archer);
	}

	@Override
	public ItemStack getHeldItem() {
		return defaultHeldItem;
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.highHit);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.highHit);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.skythernSoul;
	}

	@Override
	public String mobName() {
		return "Skythern Archer";
	}
}