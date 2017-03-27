package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityDesertCrawler extends EntityDivineRPGMob {

	public EntityDesertCrawler(World par1World) {
		super(par1World);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.desertCrawlerHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.desertCrawlerDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.desertCrawlerSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.desertCrawlerFollowRange);
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
		return Item.getItemFromBlock(Blocks.sandstone);
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		for (int i = 0; i < rand.nextInt(3 + lootingLevel); i++) {
			dropItem(getDropItem(), 20);
		}
	}

	@Override
	public String mobName() {
		return "Desert Crawler";
	}
}