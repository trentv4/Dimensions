package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class LibrariaRoomNull extends LibrariaRoom
{
	@Override
	public Chunk build(World world, int chunkX, int chunkY, Random r)
	{
		return new Chunk(world, chunkX, chunkY);
	}
}
