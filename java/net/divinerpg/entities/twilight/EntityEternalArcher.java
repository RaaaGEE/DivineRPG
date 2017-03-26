package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.twilight.projectile.EntityEternalArcherArrow;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityEternalArcher extends EntityDivineRPGBoss {

	private int armSelected;
	private int abilityTick = 1;

	public EntityEternalArcher(World world) {
		super(world);
		setSize(3, 5);
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 80));
		experienceValue = 250;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.eternalArcherHealth);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.eternalArcherSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.eternalArcherFollowRange);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (entityToAttack != null)
			getLookHelper().setLookPosition(entityToAttack.posX, entityToAttack.posY + entityToAttack.getEyeHeight(), entityToAttack.posZ, 10.0F, 5);

		if (entityToAttack == null || rand.nextInt(200) == 0)
			entityToAttack = worldObj.getClosestVulnerablePlayerToEntity(this, 48);

		if (entityToAttack != null && ((entityToAttack instanceof EntityPlayer && ((EntityPlayer) entityToAttack).capabilities.isCreativeMode) || entityToAttack.isDead))
			entityToAttack = null;

		abilityTick--;
		if (abilityTick == 0) {
			if (armSelected < 5)
				armSelected++;
			else if (armSelected == 5)
				armSelected = 0;
			abilityTick = 400;
		}

		if (!worldObj.isRemote && entityToAttack != null && abilityTick % 30 == 0) {
			worldObj.spawnEntityInWorld(new EntityEternalArcherArrow(worldObj, this, (EntityLivingBase) entityToAttack, armSelected));
		}
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		switch (rand.nextInt(2)) {
		case 0:
			dropItem(TwilightItemsArmor.haliteBoots, 1);
			break;
		case 1:
			dropItem(TwilightItemsArmor.haliteHelmet, 1);
		}
		if (rand.nextInt(2) == 0)
			dropItem(Item.getItemFromBlock(VanillaBlocks.eternalArcherStatue), 1);
	}

	public int getSelectedArm() {
		return this.armSelected;
	}

	@Override
	public ItemStack getHeldItem() {
		return new ItemStack(TwilightItemsWeapons.haliteBow);
	}

	@Override
	public String name() {
		return "Eternal Archer";
	}

	@Override
	public IChatComponent chat() {
		return null;
	}

	@Override
	public String mobName() {
		return "Eternal Archer";
	}

}