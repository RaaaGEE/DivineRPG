package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityKarot extends EntityDivineRPGBoss {

	private int spawnTick;

	public EntityKarot(World var1) {
		super(var1);
		spawnTick = 240;
		setSize(3.25F, 4F);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.karotHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.karotDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.karotSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.karotFollowRange);
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	public void onLivingUpdate() {
		if (!worldObj.isRemote && spawnTick == 0) {
			EntityAngryBunny bunny = new EntityAngryBunny(worldObj);
			bunny.setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360.0F, 0.0F);
			worldObj.spawnEntityInWorld(bunny);
			worldObj.spawnParticle("reddust", bunny.posX, bunny.posY + 0.5D, bunny.posZ, rand.nextGaussian() * 2.0D - 1.0D, rand.nextGaussian() * 2.0D - 1.0D, rand.nextGaussian() * 2.0D - 1.0D);
			// this.worldObj.playSoundAtEntity(var2, Sound.KarotSummon, 10.0F, 1.0F);
			spawnTick = 6000 + rand.nextInt(6001); //5-10 minutes
		}

		spawnTick--;
		super.onLivingUpdate();
	}

	@Override
	protected String getLivingSound() {
		return null;
	}

	@Override
	protected String getHurtSound() {
		return null;
	}

	@Override
	protected String getDeathSound() {
		return null;
	}

	@Override
	public void onDeath(DamageSource source) {
		super.onDeath(source);
		if (!worldObj.isRemote) {
			for (int i = 0; i < 5; i++) {
				EntityAngryBunny bunny = new EntityAngryBunny(this.worldObj);
				bunny.setPosition(this.posX, this.posY, this.posZ);
				worldObj.spawnEntityInWorld(bunny);
			}
		}
		super.setDead();
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		dropItem(TwilightItemsWeapons.halitePhaser, 1);
		if (rand.nextBoolean())
			dropItem(Item.getItemFromBlock(VanillaBlocks.karotStatue), 1);
	}

	@Override
	public String mobName() {
		return "Karot";
	}

	@Override
	public String name() {
		return "Karot";
	}

	@Override
	public IChatComponent chat() {
		return null;
	}
}
