package net.divinerpg.entities.vanilla;

import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityAyeracoYellow extends EntityAyeraco {

	private EntityAyeraco green;
	private EntityAyeraco blue;
	private EntityAyeraco red;
	private EntityAyeraco purple;
	private String greenUUID;
	private String blueUUID;
	private String redUUID;
	private String purpleUUID;

	public EntityAyeracoYellow(World par1World) {
		super(par1World, "Yellow");
	}

	public void initOthers(EntityAyeraco green, EntityAyeraco blue, EntityAyeraco red, EntityAyeraco purple) {
		this.green = green;
		this.blue = blue;
		this.red = red;
		this.purple = purple;
	}

	@Override
	protected boolean canBlockProjectiles() {
		return green != null && green.isAbilityActive();
	}

	@Override
	protected boolean canTeleport() {
		return purple != null && purple.isAbilityActive();
	}

	@Override
	protected void tickAbility() {
		addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1, 1));

		if (green != null && !green.isDead) {
			green.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1, 1));
		}

		if (blue != null && !blue.isDead) {
			blue.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1, 1));
		}

		if (red != null && !red.isDead) {
			red.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1, 1));
		}

		if (purple != null && !purple.isDead) {
			purple.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1, 1));
		}
	}

	@Override
	protected void dropRareDrop(int par1) {
		dropItem(VanillaItemsWeapons.yellowEnderSword, 1);
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
		if (blue == null && blueUUID != null) {
			blue = (EntityAyeraco) Util.findEntityByUUID(blueUUID, worldObj);
			blueUUID = null;
		}
		if (red == null && redUUID != null) {
			red = (EntityAyeraco) Util.findEntityByUUID(redUUID, worldObj);
			redUUID = null;
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
		blueUUID = tag.getString("blueUUID");
		redUUID = tag.getString("redUUID");
		purpleUUID = tag.getString("purpleUUID");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setString("greenUUID", green.getPersistentID().toString());
		tag.setString("blueUUID", blue.getPersistentID().toString());
		tag.setString("redUUID", red.getPersistentID().toString());
		tag.setString("purpleUUID", purple.getPersistentID().toString());
	}
}
