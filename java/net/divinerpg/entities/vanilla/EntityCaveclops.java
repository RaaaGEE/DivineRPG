package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.vanilla.projectile.EntityCaveRock;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.init.Items;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCaveclops extends EntityDivineRPGMob implements IRangedAttackMob {

	public EntityCaveclops(World par1World) {
		super(par1World);
		tasks.addTask(1, new EntityAIArrowAttack(this, 0.25F, 30, 10.0F));
		addAttackingAI();
		setSize(1.0F, 2.9F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.caveclopsHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.caveclopsDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.caveclopsSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.caveclopsFollowRange);
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
		for (int i = 0; i < rand.nextInt(3 + lootingLevel); i++) {
			dropItem(VanillaItemsOther.realmiteIngot, 3);
		}

		for (int i = 0; i < rand.nextInt(3 + lootingLevel); i++) {
			dropItem(Items.golden_pickaxe, 1);
		}
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 20.0D && super.getCanSpawnHere();
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase e, float f) {
		final double dx = e.posX - posX;
		final double dy = e.posY + e.getEyeHeight() - 1.100000023841858D - posY;
		final double dz = e.posZ - posZ;
		final double distance = MathHelper.sqrt_double(dx * dx + dz * dz) * 0.2F;

		EntityCaveRock rock = new EntityCaveRock(worldObj, this);
		rock.setThrowableHeading(dx, dy + distance, dz, 1.6F, 12.0F);
		playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
		worldObj.spawnEntityInWorld(rock);
	}

	@Override
	public String mobName() {
		return "Caveclops";
	}
}