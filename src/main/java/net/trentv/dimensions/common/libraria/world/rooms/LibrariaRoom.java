package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public abstract class LibrariaRoom
{
	public static final LibrariaRoom getRoom(int x, int y, Random r)
	{
		// Okay, before I start, this may as well be a magic function.
		// I can't figure out the right way to map an octagon with consistent
		// rooms. If you do know, ping me! Maybe measuring distance...

		// This really is required viewing to understand the following.
		// https://i.imgur.com/wVFSWGM.png
		if (x == 4 & inside(y, 0, 2))
			return new LibrariaRoomBridge();
		if (inside(x, 9, 11) & y == 7)
			return new LibrariaRoomBridge();
		if ((x == 2 | x == 6) & (y == 5 | y == 9))
			return new LibrariaRoomStair();
		if (Math.abs(x - 4) + Math.abs(y - 7) == 6 & inside(x, 0, 8) & inside(y, 3, 11))
			return new LibrariaRoomPanel();
		if (x == 4 & y == 7)
			return new LibrariaRoomBook();
		if ((x <= 8 & inside(y, 6, 8)) | (inside(x, 3, 5) & inside(y, 3, 11)))
			return new LibrariaRoomNormal();
		if ((x == 1 | x == 7) & (y == 4 | y == 10))
			return new LibrariaRoomNull();
		if (inside(x, 1, 7) & inside(y, 4, 10))
			return new LibrariaRoomNormal();
		return new LibrariaRoomNull();
	}

	private static final boolean inside(int target, int lower, int higher)
	{
		return (target >= lower & target <= higher);
	}

	public abstract Chunk build(World world, int chunkX, int chunkY, Random r);
}
