package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.trentv.dimensions.common.ChunkBuilder;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public class LibrariaRoomPanel extends LibrariaRoom
{
	@Override
	public Chunk build(LibrariaBiome biome, World world, int chunkX, int chunkY, Random r)
	{
		IBlockState s = biome.fillerBlock.getDefaultState();
		ChunkBuilder p = new ChunkBuilder();

		int modX = chunkX % 11;
		int modY = chunkY % 11;

		if ((inside(modX, 1, 3) & inside(modY, 1, 3)) | inside(modX, 7, 9) & inside(modY, 7, 9))
		{
			for (int x = 0; x < 16; x++)
			{
				for (int y = 0; y < 256; y++)
				{
					p.setBlockState(15 - x, y, x, s);
				}
			}
		}
		if ((inside(modX, 1, 3) & inside(modY, 7, 9)) | (inside(modX, 7, 9) & inside(modY, 1, 3)))
		{
			for (int x = 0; x < 16; x++)
			{
				for (int y = 0; y < 256; y++)
				{
					p.setBlockState(x, y, x, s);
				}
			}
		}
		return p.buildChunk(world, chunkX, chunkY);
	}
}
