package net.divinerpg.entities.vanilla;

import java.util.List;

import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityCyclops extends EntityPeacefulUntilAttacked {

	public EntityCyclops(World var1) {
		super(var1);
		setSize(1.5F, 3.9F);
		experienceValue = 40;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.cyclopsHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.cyclopsDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.cyclopsSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.cyclopsFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.cyclops);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.cyclopsHurt);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.cyclopsHurt);
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		dropItem(VanillaItemsOther.cyclopsEye, rand.nextInt(2 + lootingLevel));
		dropItem(Items.gold_ingot, rand.nextInt(2 + lootingLevel));
		if (rand.nextInt(40) == 0)
			dropItem(VanillaItemsOther.bloodgem, 1);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float par2) {
		boolean hurt = super.attackEntityFrom(source, par2);
		if (hurt && source.getEntity() != null && source.getEntity() instanceof EntityPlayer) {
			List<Entity> entities = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(20, 20, 20));
			for (Entity e : entities) {
				if (e instanceof EntityCyclops)
					((EntityCyclops) e).makeAngryAt((EntityPlayer) source.getEntity());
			}
		}
		return hurt;
	}

	@Override
	public boolean isValidLightLevel() {
		int x = MathHelper.floor_double(this.posX);
		int y = MathHelper.floor_double(this.boundingBox.minY);
		int z = MathHelper.floor_double(this.posZ);

		if (worldObj.getSavedLightValue(EnumSkyBlock.Sky, x, y, z) > rand.nextInt(32))
			return false;
		
		int light = worldObj.getBlockLightValue(x, y, z);

		if (worldObj.isThundering()) {
			int lightSubstracted = worldObj.skylightSubtracted;
			worldObj.skylightSubtracted = 10;
			light = worldObj.getBlockLightValue(x, y, z);
			worldObj.skylightSubtracted = lightSubstracted;
		}

		return light <= rand.nextInt(8);
	}

	@Override
	public String mobName() {
		return "Cyclops";
	}
}
