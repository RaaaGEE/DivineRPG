package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGreenfeet extends EntityDivineRPGMob {

	public EntityGreenfeet(World w) {
		super(w);
		addAttackingAI();
		setSize(1, 2);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.greenfeetHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.greenfeetDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.greenfeetSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.greenfeetFollowRange);
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	public void onLivingUpdate() {
		if (!worldObj.isRemote && !isBurning() && worldObj.isDaytime()) {
			final float brightness = getBrightness(1.0F);

			if (brightness > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) 
					&& rand.nextFloat() * 30.0F < (brightness - 0.4F) * 2.0F) {
				setFire(8);
			}
		}
		super.onLivingUpdate();
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.nesro);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.nesroHurt);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.nesroHurt);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.edenSoul;
	}

	@Override
	public String mobName() {
		return "Greenfeet";
	}
}