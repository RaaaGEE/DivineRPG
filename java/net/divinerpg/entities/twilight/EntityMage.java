package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.twilight.projectile.EntityTwilightMageShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMage extends EntityDivineRPGMob {

	public EntityMage(World var1) {
		super(var1);
		setSize(0.5F, 2F);
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.mageHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.mageDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.mageSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.mageFollowRange);
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
				
				EntityTwilightMageShot e = new EntityTwilightMageShot(worldObj, this, 62, 212, 254);
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
	protected void dropFewItems(boolean var1, int var2) {
		super.dropFewItems(var1, var2);
		dropItem(ItemsFood.magicMeat, 1);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.wildwoodSoul;
	}

	@Override
	public String mobName() {
		return "Mage";
	}
}
