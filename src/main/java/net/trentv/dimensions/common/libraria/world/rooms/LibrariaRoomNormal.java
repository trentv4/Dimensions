package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;
import net.trentv.dimensions.common.libraria.LibrariaObjects;

public class LibrariaRoomNormal extends LibrariaRoom
{
	public LibrariaRoomNormal(LibrariaBiome biome)
	{
		super(biome);
	}

	@Override
	public Chunk build(World world, int chunkX, int chunkY, Random r)
	{
		// TODO: switch to Template
		// This is temporary, just to make sure everything is lined up.
		IBlockState state = LibrariaObjects.MARMOR.getDefaultState();
		IBlockState books = LibrariaObjects.MARMOR_BOOKSHELF.getDefaultState();
		ChunkPrimer p = new ChunkPrimer();
		for (int x = 0; x < 16; x++)
		{
			for (int y = 0; y < 256; y++)
			{
				for (int z = 0; z < 16; z++)
				{
					p.setBlockState(x, y, z, books);
				}
			}
		}

		for (int y = 0; y < 256; y++)
		{
			for (int x = 0; x < 4; x++)
			{
				p.setBlockState(x, y, 0, state);
				p.setBlockState(15 - x, y, 15, state);
				p.setBlockState(0, y, x, state);
				p.setBlockState(15, y, 15 - x, state);
			}
		}

		return new Chunk(world, p, chunkX, chunkY);
	}
}
