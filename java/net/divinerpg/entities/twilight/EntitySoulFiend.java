package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.twilight.projectile.EntitySoulFiendProjectile;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySoulFiend extends EntityDivineRPGBoss {

	public EntitySoulFiend(World var1) {
		super(var1);
		addAttackingAI();
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.soulFiendHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.soulFiendDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.soulFiendSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.soulFiendFollowRange);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1);
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && ticksExisted % 300 == 0) {
			for (int i = 0; i < 4; i++) {
				EntitySoulFiendProjectile e = new EntitySoulFiendProjectile(worldObj, this);
				e.setThrowableHeading(rand.nextDouble() - rand.nextDouble(), -0.25, rand.nextDouble() - rand.nextDouble(), 0.5f, 12);
				worldObj.spawnEntityInWorld(e);
			}
		}
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
			
			e.addVelocity(-MathHelper.sin(rotationYaw * (float) Math.PI / 180.0F) * 2.5, 0.4D, MathHelper.cos(rotationYaw * (float) Math.PI / 180.0F) * 2.5);
			motionX *= 0.6D;
			motionZ *= 0.6D;
			return true;
		}
		
		return false;
	}

	@Override
	public void dropFewItems(boolean par1, int par2) {
		dropItem(TwilightItemsWeapons.haliteSlicer, 200);
		if (rand.nextBoolean())
			dropItem(Item.getItemFromBlock(VanillaBlocks.soulFiendStatue), 1);
	}

	@Override
	public String mobName() {
		return "Soul Fiend";
	}

	@Override
	public String name() {
		return "Soul Fiend";
	}

	@Override
	public IChatComponent chat() {
		return null;
	}
}