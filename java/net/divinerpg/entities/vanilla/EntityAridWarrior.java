package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityAridWarrior extends EntityDivineRPGMob {

	private static final ItemStack defaultHeldItem = new ItemStack(VanillaItemsWeapons.shadowBow, 1);

	public EntityAridWarrior(World par1World) {
		super(par1World);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.aridWarriorHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.aridWarriorDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.aridWarriorSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.aridWarriorFollowRange);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			entityToAttack = worldObj.getClosestVulnerablePlayerToEntity(this, 16);
			if (entityToAttack != null && ticksExisted % 18 == 0)
				attackEntityWithRangedAttack((EntityLivingBase) entityToAttack);
		}
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.aridWarrior);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.aridWarriorHurt);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.aridWarriorHurt);
	}

	@Override
	public ItemStack getHeldItem() {
		return defaultHeldItem;
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		dropItem(Item.getItemFromBlock(Blocks.sandstone), rand.nextInt(10));
		entityDropItem(new ItemStack(Blocks.wool, rand.nextInt(10), 14), 0.0F);
	}

	public void attackEntityWithRangedAttack(EntityLivingBase e) {
		EntityArrow arrow = new EntityArrow(worldObj, this, e, 1.6F, 4.5F);
		arrow.setDamage(1.5);

		playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
		worldObj.spawnEntityInWorld(arrow);
	}

	@Override
	protected Item getDropItem() {
		return Item.getItemFromBlock(Blocks.sandstone);
	}

	@Override
	public String mobName() {
		return "Arid Warrior";
	}
}