package net.divinerpg.entities.vanilla;

import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityAyeracoGreen extends EntityAyeraco {

	private EntityAyeraco blue;
	private EntityAyeraco red;
	private EntityAyeraco yellow;
	private EntityAyeraco purple;
	private String blueUUID;
	private String redUUID;
	private String yellowUUID;
	private String purpleUUID;

	public EntityAyeracoGreen(World par1World) {
		super(par1World, "Green");
	}

	public void initOthers(EntityAyeraco blue, EntityAyeraco red, EntityAyeraco yellow, EntityAyeraco purple) {
		this.blue = blue;
		this.red = red;
		this.yellow = yellow;
		this.purple = purple;
	}

	@Override
	protected boolean canBlockProjectiles() {
		return true;
	}

	@Override
	protected boolean canTeleport() {
		return purple != null && purple.isAbilityActive();
	}

	@Override
	protected void dropRareDrop(int par1) {
		dropItem(VanillaItemsWeapons.greenEnderSword, 1);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote) {
			return;
		}

		if (blue == null && blueUUID != null) {
			blue = (EntityAyeraco) Util.findEntityByUUID(blueUUID, worldObj);
			blueUUID = null;
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

		blueUUID = tag.getString("blueUUID");
		redUUID = tag.getString("redUUID");
		yellowUUID = tag.getString("yellowUUID");
		purpleUUID = tag.getString("purpleUUID");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setString("blueUUID", blue.getPersistentID().toString());
		tag.setString("redUUID", red.getPersistentID().toString());
		tag.setString("yellowUUID", yellow.getPersistentID().toString());
		tag.setString("purpleUUID", purple.getPersistentID().toString());
	}

	@Override
	protected void tickAbility() {

	}
}