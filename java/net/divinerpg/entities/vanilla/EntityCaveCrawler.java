package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCaveCrawler extends EntityDivineRPGMob {

	public EntityCaveCrawler(World par1World) {
		super(par1World);
		setSize(1.5F, 2.0F);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.caveCrawlerHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.caveCrawlerDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.caveCrawlerSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.caveCrawlerFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.crawler);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.crawlerHurt);
	}

	@Override
	protected String getDeathSound() {
		return Sounds.getSoundName(Sounds.crawlerHurt);
	}

	@Override
	protected Item getDropItem() {
		return VanillaItemsOther.realmiteIngot;
	}

	@Override
	protected void dropRareDrop(int par1) {
		if (rand.nextBoolean()) {
			entityDropItem(new ItemStack(Items.potionitem, 1, 8196), 0.0F);
		}
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 35.0D && super.getCanSpawnHere();
	}

	@Override
	public String mobName() {
		return "Cave Crawler";
	}
}