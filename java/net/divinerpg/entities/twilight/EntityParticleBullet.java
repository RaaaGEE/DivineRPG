package net.divinerpg.entities.twilight;

import java.awt.Color;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.DivineRPG;
import net.divinerpg.entities.fx.EntityColoredFX;
import net.divinerpg.entities.vanilla.projectile.EntityShooterBullet;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityParticleBullet extends EntityShooterBullet {

	public EntityParticleBullet(World w) {
		super(w);
	}

	public EntityParticleBullet(World world, EntityLivingBase entity, float damage, String texture, String particle) {
		super(world, entity, damage, texture);
		setParticle(particle);
	}

	public EntityParticleBullet(World world, EntityLivingBase entity, float damage, Item ammo, String particle) {
		super(world, entity, damage, ammo);
		setParticle(particle);
	}

	public EntityParticleBullet(World world, EntityLivingBase entity, float damage, String texture, Color c) {
		super(world, entity, damage, texture);
		setColor(c);
	}

	public EntityParticleBullet(World world, EntityLivingBase entity, float damage, Item ammo, Color c) {
		super(world, entity, damage, ammo);
		setColor(c);
	}

	public EntityParticleBullet(World world, double posX, double posY, double posZ, float damage, String texture, Color c) {
		super(world, posX, posY, posZ, damage, texture);
		setColor(c);
	}

	@Override
	public void entityInit() {
		super.entityInit();
		dataWatcher.addObject(19, 0);
		dataWatcher.addObject(20, 0);
		dataWatcher.addObject(21, 0);
		dataWatcher.addObject(22, "");
		dataWatcher.addObject(23, 0);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setString("Particle", getParticle());
		tag.setInteger("Red", getColor().getRed());
		tag.setInteger("Green", getColor().getGreen());
		tag.setInteger("Blue", getColor().getBlue());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		setParticle(tag.getString("Particle"));
		if (tag.hasKey("Red")) {
			setColor(new Color(tag.getInteger("Red"), tag.getInteger("Green"), tag.getInteger("Blue")));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!getParticle().equals("")) {
			for (int i = 0; i < 8; ++i) {
				DivineRPG.proxy.spawnParticle(worldObj, posX, posY, posZ, getParticle(), true);
			}
		} else {
			for (int i = 0; i < 8; i++) {
				final double tx = posX + (rand.nextDouble() - rand.nextDouble()) / 4;
				final double ty = posY + (rand.nextDouble() - rand.nextDouble()) / 4;
				final double tz = posZ + (rand.nextDouble() - rand.nextDouble()) / 4;

				EntityColoredFX e = new EntityColoredFX(worldObj, tx, ty, tz, 0, 0, 0, getColor());
				if (getBiggerParticles())
					e.bigger = true;
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(e);
			}
		}
	}

	public EntityParticleBullet setMoreParticles() {
		setBiggerParticles();
		return this;
	}

	public void setColor(Color c) {
		dataWatcher.updateObject(19, c.getRed());
		dataWatcher.updateObject(20, c.getGreen());
		dataWatcher.updateObject(21, c.getBlue());
	}

	public Color getColor() {
		return new Color(dataWatcher.getWatchableObjectInt(19), dataWatcher.getWatchableObjectInt(20), dataWatcher.getWatchableObjectInt(21));
	}

	public String getParticle() {
		return dataWatcher.getWatchableObjectString(22);
	}

	public void setParticle(String p) {
		dataWatcher.updateObject(22, p);
	}

	public void setBiggerParticles() {
		dataWatcher.updateObject(23, 1);
	}

	public boolean getBiggerParticles() {
		return dataWatcher.getWatchableObjectInt(23) == 1;
	}
}
