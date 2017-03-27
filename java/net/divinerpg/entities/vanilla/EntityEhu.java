package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGTameable;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsCrops;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityEhu extends EntityDivineRPGTameable {

	public EntityEhu(World par1World, EntityPlayer p) {
		this(par1World);
		setTamed(true);
		func_152115_b(p.getUniqueID().toString());
	}

	public EntityEhu(World par1World) {
		super(par1World);
		setSize(0.6f, 0.8f);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.ehuHealth);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.ehuSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.ehuFollowRange);
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.growl);
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
	public boolean interact(EntityPlayer player) {
		if (!isTamed()) {
			setTamed(true);
			func_152115_b(player.getUniqueID().toString());
			return super.interact(player);
		}
		
		if(getHealth() >= getMaxHealth()) {
			return super.interact(player);
		}

		final ItemStack stack = player.inventory.getCurrentItem();
		if(stack == null) {
			return super.interact(player);
		}

		if (stack.getItem() instanceof ItemFood) {
			ItemFood food = (ItemFood) stack.getItem();

			if (food == Items.carrot || food == Items.apple || food == TwilightItemsCrops.moonbulb) {
				if (!player.capabilities.isCreativeMode) {
					stack.stackSize--;
				}

				heal(food.func_150905_g/* getHealAmount */(stack));

				if (stack.stackSize <= 0) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}

				return true;
			}
		}

		return super.interact(player);
	}

	@Override
	public boolean attackEntityAsMob(Entity e) {
		return e.attackEntityFrom(DamageSource.causeMobDamage(this), (float) EntityStats.ehuDamage);
	}

	@Override
	public String mobName() {
		return "Ehu";
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}
}