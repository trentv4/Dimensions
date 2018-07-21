package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public class LibrariaRoomSolid extends LibrariaRoom
{
	public LibrariaRoomSolid(LibrariaBiome biome)
	{
		super(biome);
	}

	@Override
	public Chunk build(World world, int chunkX, int chunkY, Random r)
	{
		IBlockState s = biome.fillerBlock.getDefaultState();
		ChunkPrimer p = new ChunkPrimer();

		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				for (int y = 0; y < 256; y++)
				{
					p.setBlockState(x, y, z, s);
				}
			}
		}

		return new Chunk(world, p, chunkX, chunkY);
	}
}
