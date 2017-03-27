package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntitySoulStealer extends EntityDivineRPGMob {

	public EntitySoulStealer(World var1) {
		super(var1);
		addAttackingAI();
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.soulStealerHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.soulStealerDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.soulStealerSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.soulStealerFollowRange);
	}

	@Override
	public boolean attackEntityAsMob(Entity e) {
		if (super.attackEntityAsMob(e)) {
			if (e instanceof EntityPlayer) {
				final EntityPlayer player = (EntityPlayer) e;
				
				if(!player.isPotionActive(Potion.confusion)) {
					player.addPotionEffect(new PotionEffect(Potion.confusion.id, 12 * 20, 0));
				}
				
				if(!player.isPotionActive(Potion.blindness)) {
					player.addPotionEffect(new PotionEffect(Potion.blindness.id, 12 * 20, 0));
				}
			}
			return true;
		}
		
		return false;
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.insect);
	}

	@Override
	protected Item getDropItem() {
		return TwilightItemsOther.mortumSoul;
	}

	@Override
	public String mobName() {
		return "Soul Stealer";
	}
}