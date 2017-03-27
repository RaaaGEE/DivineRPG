package net.divinerpg.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 * @author n3k0nation
 *
 */
public final class WorldUtils {
	private WorldUtils() {
		throw new RuntimeException();
	}
	
	// TODO n3k0: [performance] make AABB step by box size
	public static boolean isCollide(double sx, double sy, double sz, double tx, double ty, double tz, World world, Entity entity, AxisAlignedBB aabb) {
		final double dx = tx - sx;
		final double dy = ty - sy;
		final double dz = tz - sz;
		final double distance = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
		if(distance < 1.0d) {
			return false;
		}
		
		final double nx = dx / distance; // normal vector coords
		final double ny = dy / distance;
		final double nz = dz / distance;

		for (int i = 1; i < distance; i++) {
			aabb.offset(nx, ny, nz);

			if (isBlockCollide(world, entity, aabb) || isEntityCollide(world, entity, aabb)) {
				return true;
			}
		}

		return false;
	}
	
	public static boolean isBlockCollide(World worldObj, Entity entity, AxisAlignedBB aabb) {
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

					block.addCollisionBoxesToList(worldObj, x, y, z, aabb, collides, entity);
					if (!collides.isEmpty()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean isEntityCollide(World worldObj, Entity entity, AxisAlignedBB aabb) {
		final int minx = MathHelper.floor_double((aabb.minX - World.MAX_ENTITY_RADIUS) / 16.0D);
		final int maxx = MathHelper.floor_double((aabb.maxX + World.MAX_ENTITY_RADIUS) / 16.0D);
		final int miny = MathHelper.floor_double((aabb.minY - World.MAX_ENTITY_RADIUS) / 16.0D);
		final int maxy = MathHelper.floor_double((aabb.maxY + World.MAX_ENTITY_RADIUS) / 16.0D);
		final int minz = MathHelper.floor_double((aabb.minZ - World.MAX_ENTITY_RADIUS) / 16.0D);
		final int maxz = MathHelper.floor_double((aabb.maxZ + World.MAX_ENTITY_RADIUS) / 16.0D);

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
						final Entity e = (Entity) entitys.get(i);
						if (e == entity) {
							continue;
						}

						if (e.boundingBox.intersectsWith(aabb)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}
}
