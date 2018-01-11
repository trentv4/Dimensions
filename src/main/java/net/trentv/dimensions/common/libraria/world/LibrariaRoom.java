package net.trentv.dimensions.common.libraria.world;

import static net.trentv.dimensions.common.libraria.world.LibrariaRoomType.BOSS_ROOM;
import static net.trentv.dimensions.common.libraria.world.LibrariaRoomType.BRIDGE;
import static net.trentv.dimensions.common.libraria.world.LibrariaRoomType.EMPTY_SPACE;
import static net.trentv.dimensions.common.libraria.world.LibrariaRoomType.NORMAL_ROOM;
import static net.trentv.dimensions.common.libraria.world.LibrariaRoomType.OUTSIDE_PANEL;
import static net.trentv.dimensions.common.libraria.world.LibrariaRoomType.STAIR_ROOM;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public class LibrariaRoom
{
	private LibrariaRoomType roomType;

	public LibrariaRoom(int modX, int modY, Random r)
	{
		roomType = getRoomType(modX, modY);
	}

	private LibrariaRoomType getRoomType(int x, int y)
	{
		// Okay, before I start, this may as well be a magic function.
		// I can't figure out the right way to map an octagon with consistent
		// rooms. If you do know, ping me! Maybe measuring distance...

		// This really is required viewing to understand the following.
		// https://i.imgur.com/wVFSWGM.png
		if (x == 4 & inside(y, 0, 2))
			return BRIDGE;
		if (inside(x, 9, 11) & y == 7)
			return BRIDGE;
		if ((x == 2 | x == 6) & (y == 5 | y == 9))
			return STAIR_ROOM;
		if (Math.abs(x - 4) + Math.abs(y - 7) == 6 & inside(x, 0, 8) & inside(y, 3, 11))
			return OUTSIDE_PANEL;
		if (x == 4 & y == 7)
			return BOSS_ROOM;
		if ((x <= 8 & inside(y, 6, 8)) | (inside(x, 3, 5) & inside(y, 3, 11)))
			return NORMAL_ROOM;
		if ((x == 1 | x == 7) & (y == 4 | y == 10))
			return EMPTY_SPACE;
		if (inside(x, 1, 7) & inside(y, 4, 10))
			return NORMAL_ROOM;

		return EMPTY_SPACE;
	}

	private boolean inside(int target, int lower, int higher)
	{
		return (target >= lower & target <= higher);
	}

	// abstract this so children rooms can handle this
	public Chunk build(World world, int chunkX, int chunkY)
	{
		IBlockState s;
		switch (roomType)
		{
			case BRIDGE:
				s = Blocks.STONE.getDefaultState();
				break;
			case BOSS_ROOM:
				s = Blocks.BEDROCK.getDefaultState();
				break;
			case EMPTY_SPACE:
				s = Blocks.AIR.getDefaultState();
				break;
			case NORMAL_ROOM:
				s = Blocks.SANDSTONE.getDefaultState();
				break;
			case STAIR_ROOM:
				s = Blocks.EMERALD_BLOCK.getDefaultState();
				break;
			default:
				s = Blocks.LAPIS_BLOCK.getDefaultState();
				break;
		}
		ChunkPrimer p = new ChunkPrimer();
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				p.setBlockState(x, 0, z, Blocks.COAL_BLOCK.getDefaultState());
			}
		}
		for (int x = 1; x < 15; x++)
		{
			for (int z = 1; z < 15; z++)
			{
				p.setBlockState(x, 0, z, s);
			}
		}
		return new Chunk(world, p, chunkX, chunkY);
	}
}
