package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public abstract class LibrariaRoom
{
	private static final LibrariaRoomBook ROOM_BOOK = new LibrariaRoomBook();
	private static final LibrariaRoomBridge ROOM_BRIDGE = new LibrariaRoomBridge();
	private static final LibrariaRoomStair ROOM_STAIR = new LibrariaRoomStair();
	private static final LibrariaRoomNormal ROOM_NORMAL = new LibrariaRoomNormal();
	private static final LibrariaRoomPanel ROOM_PANEL = new LibrariaRoomPanel();
	private static final LibrariaRoomSolid ROOM_SOLID = new LibrariaRoomSolid();
	private static final LibrariaRoomNull ROOM_NULL = new LibrariaRoomNull();

	public static final LibrariaRoom getRoom(int x, int y, Random r)
	{
		// Okay, before I start, this may as well be a magic function.
		// I can't figure out the right way to map an octagon with consistent
		// rooms. If you do know, ping me! Maybe measuring distance...

		// This really is required viewing to understand the following.
		// https://i.imgur.com/0nhtEMY.png

		if (x == 4 && y == 4)
			return ROOM_BOOK;

		if (x == 4 & inside(y, 9, 10))
			return ROOM_BRIDGE;
		if (y == 4 & inside(x, 9, 10))
			return ROOM_BRIDGE;

		if ((x == 2 || x == 6) && (y == 2 || y == 6))
			return ROOM_STAIR;

		if (inside(x, 3, 5) && inside(y, 0, 8))
			return ROOM_NORMAL;
		if (inside(y, 3, 5) && inside(x, 0, 8))
			return ROOM_NORMAL;

		if (Math.abs(x - 4) + Math.abs(y - 4) == 6)
			return ROOM_PANEL;

		if (inside(x, 1, 7) && inside(y, 1, 7))
			return ROOM_SOLID;

		return ROOM_NULL;
	}

	protected static final boolean inside(int target, int lower, int higher)
	{
		return (target >= lower & target <= higher);
	}

	public abstract Chunk build(LibrariaBiome biome, World world, int chunkX, int chunkY, Random r);
}
