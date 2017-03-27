package net.divinerpg.entities.vanilla;

import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityAyeracoPurple extends EntityAyeraco {

	private EntityAyeraco green;
	private EntityAyeraco blue;
	private EntityAyeraco red;
	private EntityAyeraco yellow;
	private String greenUUID;
	private String blueUUID;
	private String redUUID;
	private String yellowUUID;

	public EntityAyeracoPurple(World par1World) {
		super(par1World, "Purple");
	}

	public void initOthers(EntityAyeraco green, EntityAyeraco blue, EntityAyeraco red, EntityAyeraco yellow) {
		this.green = green;
		this.blue = blue;
		this.red = red;
		this.yellow = yellow;
	}

	@Override
	protected boolean canBlockProjectiles() {
		return green != null && green.isAbilityActive();
	}

	@Override
	protected boolean canTeleport() {
		return true;
	}

	@Override
	protected void dropRareDrop(int par1) {
		dropItem(VanillaItemsWeapons.enderSword, 1);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			return;
		}

		if (green == null && greenUUID != null) {
			green = (EntityAyeraco) Util.findEntityByUUID(greenUUID, this.worldObj);
			greenUUID = null;
		}
		if (blue == null && blueUUID != null) {
			blue = (EntityAyeraco) Util.findEntityByUUID(blueUUID, this.worldObj);
			blueUUID = null;
		}
		if (red == null && redUUID != null) {
			red = (EntityAyeraco) Util.findEntityByUUID(redUUID, this.worldObj);
			redUUID = null;
		}
		if (yellow == null && yellowUUID != null) {
			yellow = (EntityAyeraco) Util.findEntityByUUID(yellowUUID, this.worldObj);
			yellowUUID = null;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		greenUUID = tag.getString("greenUUID");
		blueUUID = tag.getString("blueUUID");
		redUUID = tag.getString("redUUID");
		yellowUUID = tag.getString("yellowUUID");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setString("greenUUID", green.getPersistentID().toString());
		tag.setString("blueUUID", blue.getPersistentID().toString());
		tag.setString("redUUID", red.getPersistentID().toString());
		tag.setString("yellowUUID", yellow.getPersistentID().toString());
	}

	@Override
	protected void tickAbility() {

	}
}