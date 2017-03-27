package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.twilight.projectile.EntityTwilightMageShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntitySorcerer extends EntityDivineRPGMob {

	public EntitySorcerer(World var1) {
		super(var1);
		setSize(0.5F, 2F);
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.sorcererHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.sorcererDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.sorcererSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.sorcererFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.insect);
	}

	@Override
	protected String getHurtSound() {
		return getLivingSound();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (ticksExisted % 10 == 0) {
			entityToAttack = worldObj.getClosestVulnerablePlayerToEntity(this, 16D);
			if (!worldObj.isRemote && entityToAttack != null) {
				final double tx = entityToAttack.posX - posX;
				final double ty = entityToAttack.boundingBox.minY - posY;
				final double tz = entityToAttack.posZ - posZ;
				EntityTwilightMageShot e = new EntityTwilightMageShot(worldObj, this, 97, 31, 54);
				e.setThrowableHeading(tx, ty, tz, 1.6f, 0);
				worldObj.spawnEntityInWorld(e);
				worldObj.playSoundAtEntity(entityToAttack, Sounds.mageFire.getPrefixedName(), 1, 1);
			}
		}
	}

	@Override
	public boolean isValidLightLevel() {
		return true;
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.mortumSoul;
	}

	@Override
	public String mobName() {
		return "Sorcerer";
	}
}
