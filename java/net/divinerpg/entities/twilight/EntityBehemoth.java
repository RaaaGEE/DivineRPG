package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityBehemoth extends EntityDivineRPGMob {
	private final static float moveSpeed = 1.0f;
	private int eatX;
	private int eatY;
	private int eatZ;
	private boolean shouldEat = false;
	private int ability;

	public EntityBehemoth(World world) {
		super(world);
		addAttackingAI();
		setSize(1.2f, 1);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.behemothHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.behemothDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.behemothSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.behemothFollowRange);
	}

	@Override
	protected void updateAITasks() {
		if (!shouldEat && getHealth() < getMaxHealth() * 0.5) {
			// look to around for wood blocks only onto current height
			loop: for (int i = (int) posX - 16; i < (int) posX + 16; i++) { 
				for (int j = (int) posZ - 16; j < (int) posZ + 16; j++) {
					boolean isWoodBlock = worldObj.getBlock(i, (int) posY, j).getMaterial() == Material.wood;
					if (isWoodBlock) {
						shouldEat = true;
						eatX = i;
						eatY = (int) this.posY;
						eatZ = j;
						break loop;
					}
				}
			}
		}

		super.updateAITasks();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!shouldEat) {
			return;
		}

		if (worldObj.getBlock(eatX, eatY, eatZ).getMaterial() != Material.wood) {
			shouldEat = false;
			return;
		}

		if (getDistance(eatX, eatY, eatZ) < 2) {
			heal(70 / 8);
			worldObj.setBlock(eatX, eatY, eatZ, Blocks.air);
			shouldEat = false;
			ability = 5;
		} else {
			if (ability == 0) {
				getNavigator().tryMoveToXYZ(eatX, eatY, eatZ, moveSpeed);
				getLookHelper().setLookPosition(eatX, eatY, eatZ, 15F, 15F);
				moveEntityWithHeading(0F, moveSpeed / 4);
			} else {
				ability--;
			}
		}
	}

	@Override
	protected float getSoundVolume() {
		return 0.7F;
	}

	@Override
	protected String getLivingSound() {
		return Sounds.endiku.getPrefixedName();
	}

	@Override
	protected String getHurtSound() {
		return Sounds.endikuHurt.getPrefixedName();
	}

	@Override
	protected String getDeathSound() {
		return getHurtSound();
	}

	@Override
	protected void dropFewItems(boolean beenHit, int lootLevel) {
		dropItem(TwilightItemsOther.wildwoodSoul, rand.nextInt(3 + lootLevel));
		dropItem(ItemsFood.magicMeat, 1);
	}

	@Override
	public boolean isValidLightLevel() {
		return true;
	}

	@Override
	public String mobName() {
		return "ModelBehemoth";
	}
}