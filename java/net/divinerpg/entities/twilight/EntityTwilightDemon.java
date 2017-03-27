package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.twilight.projectile.EntityTwilightDemonShot;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityTwilightDemon extends EntityDivineRPGBoss {

	int shooting;

	public EntityTwilightDemon(World var1) {
		super(var1);
		setSize(2.0F, 4.0F);
		tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 40.0F, 50));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.twilightDemonHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.twilightDemonDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.twilightDemonSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.twilightDemonFollowRange);
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
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
		
		entityToAttack = worldObj.getClosestVulnerablePlayerToEntity(this, 40D);
		if(worldObj.isRemote) {
			return;
		}
		
		if (ticksExisted % 160 == 0) {
			shooting = 100;
		}
		
		if (entityToAttack != null && shooting > 0) {
			final double tx = entityToAttack.posX - posX;
			final double ty = entityToAttack.boundingBox.minY - posY - 2;
			final double tz = entityToAttack.posZ - posZ;
			final double angle = Math.atan(-tx / tz);
			
			EntityTwilightDemonShot e = new EntityTwilightDemonShot(worldObj, this);
			e.posZ += Math.sin(angle);
			e.posX += Math.cos(angle);
			e.setThrowableHeading(tx - Math.cos(angle), ty, tz - Math.sin(angle), 1.6f, 0);
			worldObj.spawnEntityInWorld(e);

			EntityTwilightDemonShot e1 = new EntityTwilightDemonShot(worldObj, this);
			e1.posZ -= Math.sin(angle);
			e1.posX -= Math.cos(angle);
			e1.setThrowableHeading(tx + Math.cos(angle), ty, tz + Math.sin(angle), 1.6f, 0);
			worldObj.spawnEntityInWorld(e1);
		}
		
		if (shooting > 0) {
			shooting--;
		}
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		switch (rand.nextInt(2)) {
		case 0:
			dropItem(TwilightItemsWeapons.haliteBlitz, 1);
			break;
		case 1:
			dropItem(TwilightItemsWeapons.haliteBow, 1);
			break;
		}
		
		if (rand.nextBoolean())
			dropItem(Item.getItemFromBlock(VanillaBlocks.twilightDemonStatue), 1);
	}

	@Override
	public String mobName() {
		return "Twilight Demon";
	}

	@Override
	public String name() {
		return "Twilight Demon";
	}

	@Override
	public IChatComponent chat() {
		return null;
	}
}