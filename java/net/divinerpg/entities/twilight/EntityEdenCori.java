package net.divinerpg.entities.twilight;

import java.util.ArrayList;
import java.util.List;

import net.divinerpg.entities.base.EntityDivineRPGFlying;
import net.divinerpg.entities.twilight.projectile.EntityCoriShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class EntityEdenCori extends EntityDivineRPGFlying {

	private int courseChangeCooldown = 1;
	private double waypointX = Double.NaN;
	private double waypointY = Double.NaN;
	private double waypointZ = Double.NaN;
	private Entity targetedEntity = null;
	private int aggroCooldown = 0;
	private int attackCounter = 0;
	private ChunkCoordinates currentFlightTarget;

	public EntityEdenCori(World world) {
		super(world);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.edenCoriHealth);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.edenCoriSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.edenCoriFollowRange);
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

			if (isCourseTraversable(dx, dy, dz, distance)) {
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
					EntityCoriShot shot = new EntityCoriShot(worldObj, this, 30);
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
			byte b1 = this.dataWatcher.getWatchableObjectByte(16);
			byte b0 = (byte) (attackCounter > 10 ? 1 : 0);

			if (b1 != b0) {
				dataWatcher.updateObject(16, Byte.valueOf(b0));
			}
		}
	}

	// TODO n3k0: [performance] make AABB step by box size
	private boolean isCourseTraversable(double x, double y, double z, double distance) {
		final double nx = x / distance; // normal vector coords
		final double ny = y / distance;
		final double nz = z / distance;

		final AxisAlignedBB aabb = boundingBox.copy();
		for (int i = 1; i < distance; i++) {
			aabb.offset(nx, ny, nz);

			if (isBlockCollide(aabb) || isEntityCollide(aabb)) {
				return false;
			}
		}

		return true;
	}

	// TODO n3k0: [refactor] move this code part to WorldUtils as faster collide
	// check
	private boolean isBlockCollide(AxisAlignedBB aabb) {
		final int minx = MathHelper.floor_double(aabb.minX);
		final int maxx = MathHelper.floor_double(aabb.maxX + 1.0D);
		final int miny = MathHelper.floor_double(aabb.minY);
		final int maxy = MathHelper.floor_double(aabb.maxY + 1.0D);
		final int minz = MathHelper.floor_double(aabb.minZ);
		final int maxz = MathHelper.floor_double(aabb.maxZ + 1.0D);

		final List collides = new ArrayList();
		for (int x = minx; x < maxx; x++) {
			for (int z = minz; z < maxz; z++) {
				if (!worldObj.blockExists(x, 64, z)) {
					continue;
				}

				for (int y = miny; y < maxy; y++) {
					final Block block;
					if (x >= -30000000 && x < 30000000 && z >= -30000000 && z < 30000000) {
						block = worldObj.getBlock(x, y, z);
					} else {
						block = Blocks.stone;
					}

					block.addCollisionBoxesToList(worldObj, x, y, z, aabb, collides, this);
					if (!collides.isEmpty()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean isEntityCollide(AxisAlignedBB aabb) {
		final int minx = MathHelper.floor_double((aabb.minX - World.MAX_ENTITY_RADIUS) / 16.0D);
		final int maxx = MathHelper.floor_double((aabb.maxX + World.MAX_ENTITY_RADIUS) / 16.0D);
		final int miny = MathHelper.floor_double((aabb.minY - World.MAX_ENTITY_RADIUS) / 16.0D);
		final int maxy = MathHelper.floor_double((aabb.maxY + World.MAX_ENTITY_RADIUS) / 16.0D);
		final int minz = MathHelper.floor_double((aabb.minZ - World.MAX_ENTITY_RADIUS) / 16.0D);
		final int maxz = MathHelper.floor_double((aabb.maxZ + World.MAX_ENTITY_RADIUS) / 16.0D);

		final List collides = new ArrayList();
		for (int x = minx; x <= maxx; x++) {
			for (int z = minz; z <= maxz; z++) {
				if (!worldObj.checkChunksExist(x, 0, z, x, 0, z)) {
					continue;
				}

				final Chunk chunk = worldObj.getChunkFromChunkCoords(x, z);
				final int chunkMinY = MathHelper.clamp_int(miny, 0, chunk.entityLists.length - 1);
				final int chunkMaxY = MathHelper.clamp_int(maxy, 0, chunk.entityLists.length - 1);

				for (int y = chunkMinY; y <= chunkMaxY; y++) {
					final List entitys = chunk.entityLists[y];

					for (int i = 0; i < entitys.size(); i++) {
						final Entity entity = (Entity) entitys.get(i);
						if (entity == this) {
							continue;
						}

						if (entity.boundingBox.intersectsWith(aabb)) {
							return true;
						}
					}
				}
			}
		}

		return false;
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
		return TwilightItemsOther.edenSoul;
	}

	@Override
	public String mobName() {
		return "Weak Cori";
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}
}