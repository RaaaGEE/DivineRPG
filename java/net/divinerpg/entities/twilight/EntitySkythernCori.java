package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGFlying;
import net.divinerpg.entities.twilight.projectile.EntityCoriShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.WorldUtils;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySkythernCori extends EntityDivineRPGFlying {

	private int courseChangeCooldown = 1;
	private double waypointX = Double.NaN;
	private double waypointY = Double.NaN;
	private double waypointZ = Double.NaN;
	private Entity targetedEntity = null;
	private int aggroCooldown = 0;
	private int attackCounter = 0;

	public EntitySkythernCori(World var1) {
		super(var1);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernCoriHealth);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernCoriSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.skythernCoriFollowRange);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			setDead();
			return;
		}

		despawnEntity();
		
		final double dx = waypointX - posX;
		final double dy = waypointY - posY;
		final double dz = waypointZ - posZ;
		double distance = dx * dx + dy * dy + dz * dz;

		if (distance < 1.0D || distance > 3600.0D || distance == Double.NaN) {
			waypointX = posX + (rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
			waypointY = posY + (rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
			waypointZ = posZ + (rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
		}

		courseChangeCooldown--;
		if (courseChangeCooldown == 0) {
			courseChangeCooldown += rand.nextInt(5) + 2;
			distance = MathHelper.sqrt_double(distance);

			if (!WorldUtils.isCollide(posX, posY, posZ, waypointX, waypointY, waypointZ, worldObj, this, boundingBox.copy())) {
				motionX += dx / distance * 0.1D;
				motionY += dy / distance * 0.1D;
				motionZ += dz / distance * 0.1D;
			} else {
				waypointX = posX;
				waypointY = posY;
				waypointZ = posZ;
			}
		}

		if (targetedEntity == null || targetedEntity.isDead || aggroCooldown-- <= 0) {
			targetedEntity = worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

			if (targetedEntity != null) {
				aggroCooldown = 20;
			}
		}

		if (targetedEntity != null && targetedEntity.getDistanceSqToEntity(this) < 64*64) {
			double targetDx = targetedEntity.posX - posX;
			double targetDy = targetedEntity.boundingBox.minY + 1 - posY;
			double targetDz = targetedEntity.posZ - posZ;
			renderYawOffset = rotationYaw = -((float) Math.atan2(targetDx, targetDz)) * 180.0F / (float) Math.PI;

			if (canEntityBeSeen(targetedEntity)) {
				attackCounter++;

				if (attackCounter == 20) {
					worldObj.playSoundAtEntity(targetedEntity, Sounds.getSoundName(Sounds.coriShoot), 1.0F, 1.0F);
					EntityCoriShot shot = new EntityCoriShot(worldObj, this, 100);
					shot.setThrowableHeading(targetDx, targetDy, targetDz, 1.6f, 4);
					if (!worldObj.isRemote)
						worldObj.spawnEntityInWorld(shot);
					attackCounter = -40;
				}
			} else if (attackCounter > 0) {
				attackCounter--;
			}
		} else {
			renderYawOffset = rotationYaw = -((float) Math.atan2(motionX, motionZ)) * 180.0F / (float) Math.PI;

			if (attackCounter > 0) {
				attackCounter--;
			}
		}

		if (!worldObj.isRemote) {
			byte b1 = dataWatcher.getWatchableObjectByte(16);
			byte b0 = (byte) (attackCounter > 10 ? 1 : 0);

			if (b1 != b0) {
				dataWatcher.updateObject(16, Byte.valueOf(b0));
			}
		}
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.coriIdle);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.coriHurt);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.coriHurt);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.skythernSoul;
	}

	@Override
	public String mobName() {
		return "Advanced Cori";
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}
}