package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public abstract class LibrariaRoom
{
	protected final LibrariaBiome biome;

	public LibrariaRoom(LibrariaBiome biome)
	{
		this.biome = biome;
	}

	public static final LibrariaRoom getRoom(int x, int y, Random r, LibrariaBiome newBiome)
	{
		// Okay, before I start, this may as well be a magic function.
		// I can't figure out the right way to map an octagon with consistent
		// rooms. If you do know, ping me! Maybe measuring distance...

		// This really is required viewing to understand the following.
		// https://i.imgur.com/0nhtEMY.png

		if (x == 4 && y == 4)
			return new LibrariaRoomBook(newBiome);

		if (x == 4 & inside(y, 9, 10))
			return new LibrariaRoomBridge(newBiome);
		if (y == 4 & inside(x, 9, 10))
			return new LibrariaRoomBridge(newBiome);

		if ((x == 2 || x == 6) && (y == 2 || y == 6))
			return new LibrariaRoomStair(newBiome);

		if (inside(x, 3, 5) && inside(y, 0, 8))
			return new LibrariaRoomNormal(newBiome);
		if (inside(y, 3, 5) && inside(x, 0, 8))
			return new LibrariaRoomNormal(newBiome);

		if (Math.abs(x - 4) + Math.abs(y - 4) == 6)
			return new LibrariaRoomPanel(newBiome);

		if (inside(x, 1, 7) && inside(y, 1, 7))
			return new LibrariaRoomSolid(newBiome);

		return new LibrariaRoomNull(newBiome);
	}

	protected static final boolean inside(int target, int lower, int higher)
	{
		return (target >= lower & target <= higher);
	}

	public abstract Chunk build(World world, int chunkX, int chunkY, Random r);
}
