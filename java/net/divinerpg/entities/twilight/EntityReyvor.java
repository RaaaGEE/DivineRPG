package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityReyvor extends EntityDivineRPGBoss implements IRangedAttackMob {

	private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.twilightBow, 1);

	public EntityReyvor(World var1) {
		super(var1);
		tasks.addTask(7, new EntityAIArrowAttack(this, 0.25F, 40, 64.0F));
		targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.reyvorHealth);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.reyvorDamage);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.reyvorSpeed);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.reyvorFollowRange);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(100));
	}

	@Override
	protected String getLivingSound() {
		return Sounds.getSoundName(Sounds.reyvor);
	}

	@Override
	protected String getHurtSound() {
		return Sounds.getSoundName(Sounds.reyvorHurt);
	}

	@Override
	protected String getDeathSound() {
		return null;
	}

	@Override
	public ItemStack getHeldItem() {
		return defaultHeldItem;
	}

	@Override
	public void dropFewItems(boolean beenHit, int lootingLevel) {
		dropItem(TwilightItemsArmor.haliteLeggings, 1);
		if (rand.nextInt(3) == 0)
			dropItem(Item.getItemFromBlock(VanillaBlocks.reyvorStatue), 1);
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float f) {
		EntityDivineArrow var2 = new EntityDivineArrow(worldObj, this, var1, 1.6F, 12.0F, 22, "furyArrow");
		playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
		worldObj.spawnEntityInWorld(var2);
	}

	@Override
	public String mobName() {
		return "Reyvor";
	}

	@Override
	public String name() {
		return "Reyvor";
	}

	@Override
	public IChatComponent chat() {
		return null;
	}
}