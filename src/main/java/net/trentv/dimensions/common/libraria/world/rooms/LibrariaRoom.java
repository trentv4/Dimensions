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
		// https://i.imgur.com/Ghwwn2Z.png

		if (inside(x, 4, 6) && inside(y, 4, 6))
			return ROOM_BOOK;

		if (x == 5 && (y == 0 || y == 10))
			return ROOM_BRIDGE;
		if (y == 5 && (x == 0 || x == 10))
			return ROOM_BRIDGE;

		if ((x == 3 || x == 7) && (y == 3 || y == 7))
			return ROOM_STAIR;

		if (inside(x, 4, 6) && inside(y, 1, 9))
			return ROOM_NORMAL;
		if (inside(y, 4, 6) && inside(x, 1, 9))
			return ROOM_NORMAL;

		if (Math.abs(x - 5) + Math.abs(y - 5) == 6)
			return ROOM_PANEL;

		if (inside(x, 2, 8) && inside(y, 2, 8))
			return ROOM_SOLID;

		return ROOM_NULL;
	}

	protected static final boolean inside(int target, int lower, int higher)
	{
		return (target >= lower & target <= higher);
	}

	public abstract Chunk build(LibrariaBiome biome, World world, int chunkX, int chunkY, Random r);
}
