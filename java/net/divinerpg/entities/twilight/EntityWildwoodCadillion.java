package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityWildwoodCadillion extends EntityDivineRPGMob {

	public EntityWildwoodCadillion(World var1) {
		super(var1);
		setSize(1.0F, 1.3F);
		addAttackingAI();
		experienceValue = 40;
	}

	@Override
	protected void applyEntityAttributes() {
	    super.applyEntityAttributes();
	    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.wildwoodCadillionHealth);
	    getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.wildwoodCadillionDamage);
	    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.wildwoodCadillionSpeed);
	    getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.wildwoodCadillionFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.cadillion);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.growlHurt);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.growlHurt);
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		dropItem(ItemsFood.magicMeat, 1);
		
		final int stackCount = rand.nextInt(2 + lootingLevel);
		for(int i = 0; i < stackCount; i++) {
			dropItem(TwilightItemsOther.wildwoodSoul, 1);
		}
	}

	@Override
	protected void dropRareDrop(int var1) {
		this.dropItem(TwilightItemsOther.wildwoodSoul, 2);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.wildwoodSoul;
	}
	
	@Override
    public boolean isValidLightLevel() {
        return true;
    }

	@Override
	public String mobName() {
		return "Wildwood Cadillion";
	}
}
