package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityAncientEntity extends EntityDivineRPGBoss {

	public EntityAncientEntity(World par1World) {
		super(par1World);
		setSize(4.0F, 6.5F);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.ancientEntityHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.ancientEntityDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.ancientEntitySpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.ancientEntityFollowRange);
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		super.attackEntityAsMob(par1Entity);
		
		if (entityToAttack != null) {
			entityToAttack.addVelocity(motionX * 10.0D, 3.0D, motionZ * 10.0D);
			
			if (entityToAttack instanceof EntityPlayer) {
				final EntityPlayer player = (EntityPlayer) entityToAttack;
				
				if(!player.isPotionActive(Potion.moveSlowdown)) {
					player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 0));
				}
				
				playSound("mob.irongolem.throw", 1.0F, 1.0F);
			}
			return true;
		}
		return false;
	}

	@Override
	protected String getHurtSound() {
		return "mob.irongolem.hit";
	}

	@Override
	protected String getDeathSound() {
		return "mob.irongolem.death";
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		dropItem(VanillaItemsOther.divineShards, rand.nextInt(4) + 3);
		dropItem(VanillaItemsWeapons.sandslash, 1);
		dropItem(Item.getItemFromBlock(VanillaBlocks.ancientEntityStatue), 1);
	}

	@Override
	public String mobName() {
		return "Ancient Entity";
	}

	@Override
	public String name() {
		return "Ancient Entity";
	}

	@Override
	public IChatComponent chat() {
		return null;
	}
}