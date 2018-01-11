package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public class LibrariaRoomNull extends LibrariaRoom
{
	public LibrariaRoomNull(LibrariaBiome biome)
	{
		super(biome);
	}

	@Override
	public Chunk build(World world, int chunkX, int chunkY, Random r)
	{
		return new Chunk(world, chunkX, chunkY);
	}
}
