package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public class LibrariaRoomPanel extends LibrariaRoom
{
	public LibrariaRoomPanel(LibrariaBiome biome)
	{
		super(biome);
	}

	@Override
	public Chunk build(World world, int chunkX, int chunkY, Random r)
	{
		IBlockState s = biome.fillerBlock.getDefaultState();
		ChunkPrimer p = new ChunkPrimer();

		int modX = chunkX % 12;
		int modY = chunkY % 12;

		if ((inside(modX, 0, 2) & inside(modY, 0, 2)) | inside(modX, 6, 8) & inside(modY, 6, 8))
		{
			for (int x = 0; x < 16; x++)
			{
				for (int y = 0; y < 256; y++)
				{
					p.setBlockState(15 - x, y, x, s);
				}
			}
		}
		if ((inside(modX, 0, 2) & inside(modY, 6, 8)) | (inside(modX, 6, 8) & inside(modY, 0, 2)))
		{
			for (int x = 0; x < 16; x++)
			{
				for (int y = 0; y < 256; y++)
				{
					p.setBlockState(x, y, x, s);
				}
			}
		}
		return new Chunk(world, p, chunkX, chunkY);
	}
}
