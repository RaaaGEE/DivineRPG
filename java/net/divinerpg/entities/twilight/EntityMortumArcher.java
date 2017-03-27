package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMortumArcher extends EntityDivineRPGMob implements IRangedAttackMob {
	
	private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.mortumBow, 1);

    public EntityMortumArcher(World var1) {
        super(var1);
        tasks.addTask(1, new EntityAIArrowAttack(this, 0.25F, 60, 10.0F));
        setSize(2.0F, 5.0F);
    }

	@Override
	protected void applyEntityAttributes() {
	    super.applyEntityAttributes();
	    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumArcherHealth);
	    getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumArcherDamage);
	    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumArcherSpeed);
	    getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.entities.base.EntityStats.mortumArcherFollowRange);
	}

    @Override
    public int getTotalArmorValue() {
        return 10;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.archer);
    }

    @Override
    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }
 
    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.highHit);
    }
 
    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.highHit);
    }
 
    @Override
    protected Item getDropItem() {
        return TwilightItemsOther.mortumSoul;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase var1, float f) {
    	EntityDivineArrow var2 = new EntityDivineArrow(worldObj, this, var1, 1.6F, 12.0F, 16, "furyArrow");
        playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
        worldObj.spawnEntityInWorld(var2);
    }

	@Override
	public String mobName() {
		return "Mortum Archer";
	}
}