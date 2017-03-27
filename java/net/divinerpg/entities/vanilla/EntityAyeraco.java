package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.vanilla.projectile.EntityEnderTripletFireball;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsArmor;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityAyeraco extends EntityDivineRPGBoss {

	private final static float moveSpeed = 0.3F;
	
	private String color;
	private int waitTick;
	private ChunkCoordinates currentFlightTarget;
	
	private boolean attacks = false;
	private boolean halfHp = false;
	
	private double moveX;
	private double moveZ;
	
	private int beamX;
	private int beamY;
	private int beamZ;

	public EntityAyeraco(World var1, String color) {
		super(var1);
		setSize(2.8F, 1.2F);
		this.color = color;
		moveX = rand.nextGaussian() * 0.25 - 0.125; //XXX n3k0: its really needed in constructor? moving vector never changed...
		moveZ = rand.nextGaussian() * 0.25 - 0.125;
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.ayeracoHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.ayeracoDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.ayeracoSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.ayeracoFollowRange);
	}
	
	protected abstract void tickAbility();
	protected abstract boolean canBlockProjectiles();
	protected abstract boolean canTeleport();
	
	@Override
	public void onDeath(DamageSource damageSource) {
		super.onDeath(damageSource);
		worldObj.setBlock(beamX, beamY, beamZ, Blocks.air);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(100));
	}

	public String getEntityName() {
		return "Ayeraco " + color;
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.ayeraco);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.ayeracoHurt);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.ayeracoHurt);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		motionY *= 0.6000000238418579D;
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();

		if (getHealth() * 2 < getMaxHealth()) {
			tickAbility();
			if(!halfHp) {
				halfHp = true;
				worldObj.playSoundAtEntity(this, Sounds.getSoundName(Sounds.ayeracoHalfHealth), 20.0F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
			}
		}

		final EntityPlayer player = worldObj.getClosestVulnerablePlayerToEntity(this, 128);
		if (player != null) {
			setAttackTarget(player);
		}
		
		final EntityLivingBase attackTarget = getAttackTarget();
		if(attackTarget == null) {
			return;
		}

		if (waitTick == 0) {
			final int x = (int) getAttackTarget().posX;
			final int y = (int) getAttackTarget().posY;
			final int z = (int) getAttackTarget().posZ;
			currentFlightTarget = new ChunkCoordinates(x, y, z);

			final double dx = currentFlightTarget.posX - posX;
			final double dy = currentFlightTarget.posY - posY;
			final double dz = currentFlightTarget.posZ - posZ;

			if (Math.signum(dx) != 0 || Math.signum(dy) != 0 || Math.signum(dz) != 0) {
				motionX = Math.signum(dx) * moveSpeed;
				motionY += (Math.signum(dy) * 5.699999988079071D - motionY) * 0.10000000149011612D;
				motionZ = Math.signum(dz) * moveSpeed;
				float angle = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
				angle = MathHelper.wrapAngleTo180_float(angle - rotationYaw);
				moveForward = 0.5F;
				rotationYaw += angle;
			}
		} else {
			waitTick--;
			motionY = 0.3;
			motionX = moveX;
			motionZ = moveZ;
		}

		if (!attacks) {
			waitTick = 80;
			attacks = true;
			if (canTeleport()) {
				waitTick = 0;
				teleportRandomUp(worldObj);
			}
		}

	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	protected void updateFallState(double par1, boolean par3) {
	}

	@Override
	public boolean attackEntityFrom(DamageSource ds, float par2) {
		if (isEntityInvulnerable() || (ds.isProjectile() && canBlockProjectiles()) || ds.getSourceOfDamage() instanceof EntityEnderTripletFireball)
			return false;
		
		if (ds.getSourceOfDamage() instanceof EntityPlayer) {
			attacks = false;
		}
		return super.attackEntityFrom(ds, par2);
	}

	@Override
	protected void dropFewItems(boolean var1, int var2) {
		dropItem(VanillaItemsOther.divineShards, 2 + rand.nextInt(2));
		
		if (rand.nextInt(100) < 2) {
			if(rand.nextBoolean()) {
				dropItem(VanillaItemsArmor.divineBody, 1);
			} else {
				dropItem(VanillaItemsArmor.divineLegs, 1);
			}
		}

		if (rand.nextInt(100) < 5) {
			dropItem(VanillaItemsArmor.divineBoots, 1);
		}

		if (rand.nextInt(100) < 3) {
			dropItem(VanillaItemsArmor.divineHelmet, 1);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		attacks = false;
		return super.attackEntityAsMob(par1Entity);
	}

	public boolean isAbilityActive() {
		return !isDead && getHealth() * 2 <= getMaxHealth();
	}

	protected void teleportRandomUp(World par1) {
		par1.playSoundAtEntity(this, Sounds.ayeracoTeleport.getPrefixedName(), 2.0F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
		motionY = 20;
		motionX = rand.nextInt(5);
		motionZ = rand.nextInt(5);
	}

	public void setBeamLocation(int x, int y, int z) {
		beamX = x;
		beamY = y;
		beamZ = z;
	}

	@Override
	public IChatComponent func_145748_c_/* getFormattedCommandSenderName */() {
		return null;
	}

	@Override
	protected Item getDropItem() {
		return VanillaItemsOther.divineShards;
	}

	@Override
	public String mobName() {
		return "Ayeraco " + color;
	}

	@Override
	public String name() {
		return mobName();
	}

	@Override
	public IChatComponent chat() {
		return null;
	}
}
