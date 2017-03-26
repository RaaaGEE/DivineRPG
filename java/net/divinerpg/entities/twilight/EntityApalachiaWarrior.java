package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityApalachiaWarrior extends EntityDivineRPGMob {

	private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.apalachiaBlade, 1);

	public EntityApalachiaWarrior(World var1) {
		super(var1);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaWarriorHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaWarriorDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaWarriorSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.apalachiaWarriorFollowRange);
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	public void onLivingUpdate() {
		if (!worldObj.isRemote && !isBurning() && worldObj.isDaytime()) {
			final float brightness = getBrightness(1.0F);
			if (brightness > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && rand.nextFloat() * 30.0F < (brightness - 0.4F) * 2.0F) {
				setFire(8);
			}
		}
		super.onLivingUpdate();
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.hiss);
	}

	@Override
	public ItemStack getHeldItem() {
		return defaultHeldItem;
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
	protected void dropFewItems(boolean beenHit, int lootLevel) {
		int stackCount = this.rand.nextInt(2 + lootLevel);
		for (int i = 0; i < stackCount; i++)
			dropItem(getDropItem(), 1);
		dropItem(getDropItem(), 1);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.apalachiaSoul;
	}

	@Override
	public String mobName() {
		return "Apalachia Warrior";
	}
}
