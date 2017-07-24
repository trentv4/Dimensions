package net.trentv.dimensions.common;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterAcceptAll extends Teleporter
{
	public TeleporterAcceptAll(WorldServer world)
	{
		super(world);
	}

	@Override
	public boolean makePortal(Entity entity)
	{
		return true;
	}
}
