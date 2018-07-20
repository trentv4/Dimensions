package net.trentv.dimensions.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.trentv.dimensions.common.libraria.DimensionLibraria;
import net.trentv.dimensions.common.smudged.DimensionSmudgedCity;

public class TeleporterAcceptAll extends Teleporter
{
	public TeleporterAcceptAll(WorldServer world)
	{
		super(world);
	}

	@Override
	public void placeInPortal(Entity entity, float rotationYaw)
	{
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			BlockPos position = player.getBedLocation() == null ? entity.world.getSpawnPoint() : player.getBedLocation();

			if (player.dimension == DimensionSmudgedCity.dimensionID)
				position = new BlockPos(0, 128, 0);
			if (player.dimension == DimensionLibraria.dimensionID)
				position = new BlockPos(72, 98, 72);

			player.setPosition(position.getX(), position.getY(), position.getZ());
		}
	}

	@Override
	public boolean makePortal(Entity entity)
	{
		return true;
	}
}
