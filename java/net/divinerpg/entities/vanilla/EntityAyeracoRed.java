package net.divinerpg.entities.vanilla;

import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityAyeracoRed extends EntityAyeraco {

	private EntityAyeraco green;
	private EntityAyeraco blue;
	private EntityAyeraco yellow;
	private EntityAyeraco purple;
	private String greenUUID;
	private String blueUUID;
	private String yellowUUID;
	private String purpleUUID;

	public EntityAyeracoRed(World par1World) {
		super(par1World, "Red");
	}

	public void initOthers(EntityAyeraco green, EntityAyeraco blue, EntityAyeraco yellow, EntityAyeraco purple) {
		this.green = green;
		this.blue = blue;
		this.yellow = yellow;
		this.purple = purple;
	}

	@Override
	protected boolean canBlockProjectiles() {
		return green != null && this.green.isAbilityActive();
	}

	@Override
	protected void tickAbility() {
		if (green != null && !green.isDead) {
			green.heal(1);
		}

		if (blue != null && !blue.isDead) {
			blue.heal(1);
		}

		if (yellow != null && !yellow.isDead) {
			yellow.heal(1);
		}

		if (purple != null && !purple.isDead) {
			purple.heal(1);
		}
	}

	@Override
	protected boolean canTeleport() {
		return purple != null && purple.isAbilityActive();
	}

	@Override
	protected void dropRareDrop(int par1) {
		dropItem(VanillaItemsWeapons.redEnderSword, 1);
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
		blueUUID = tag.getString("blueUUID");
		yellowUUID = tag.getString("yellowUUID");
		purpleUUID = tag.getString("purpleUUID");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setString("greenUUID", green.getPersistentID().toString());
		tag.setString("blueUUID", blue.getPersistentID().toString());
		tag.setString("yellowUUID", yellow.getPersistentID().toString());
		tag.setString("purpleUUID", purple.getPersistentID().toString());
	}
}