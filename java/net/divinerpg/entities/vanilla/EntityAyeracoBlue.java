package net.divinerpg.entities.vanilla;

import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityAyeracoBlue extends EntityAyeraco {

	private EntityAyeraco green;
	private EntityAyeraco red;
	private EntityAyeraco yellow;
	private EntityAyeraco purple;
	private String greenUUID;
	private String redUUID;
	private String yellowUUID;
	private String purpleUUID;

	public EntityAyeracoBlue(World par1World) {
		super(par1World, "Blue");
	}

	public void initOthers(EntityAyeraco green, EntityAyeraco red, EntityAyeraco yellow, EntityAyeraco purple) {
		this.green = green;
		this.red = red;
		this.yellow = yellow;
		this.purple = purple;
	}

	@Override
	protected boolean canBlockProjectiles() {
		return green != null && green.isAbilityActive();
	}

	@Override
	protected void tickAbility() {
		addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1, 2));
		
		if (green != null && !green.isDead) {
			green.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1, 2));
		}
		
		if (yellow != null && !yellow.isDead) {
			yellow.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1, 2));
		}
		
		if (red != null && !red.isDead) {
			red.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1, 2));
		}
		
		if (purple != null && !purple.isDead) {
			purple.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1, 2));
		}
	}

	@Override
	protected boolean canTeleport() {
		return purple != null && purple.isAbilityActive();
	}

	@Override
	protected void dropRareDrop(int par1) {
		dropItem(VanillaItemsWeapons.blueEnderSword, 1);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			return;
		}
		
		if (green == null && greenUUID != null) {
			green = (EntityAyeraco) Util.findEntityByUUID(greenUUID, worldObj);
			greenUUID = null;
		}
		if (red == null && redUUID != null) {
			red = (EntityAyeraco) Util.findEntityByUUID(redUUID, worldObj);
			redUUID = null;
		}
		if (yellow == null && yellowUUID != null) {
			yellow = (EntityAyeraco) Util.findEntityByUUID(yellowUUID, worldObj);
			yellowUUID = null;
		}
		if (purple == null && purpleUUID != null) {
			purple = (EntityAyeraco) Util.findEntityByUUID(purpleUUID, worldObj);
			purpleUUID = null;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		greenUUID = tag.getString("greenUUID");
		redUUID = tag.getString("redUUID");
		yellowUUID = tag.getString("yellowUUID");
		purpleUUID = tag.getString("purpleUUID");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setString("greenUUID", green.getPersistentID().toString());
		tag.setString("redUUID", red.getPersistentID().toString());
		tag.setString("yellowUUID", yellow.getPersistentID().toString());
		tag.setString("purpleUUID", purple.getPersistentID().toString());
	}
}