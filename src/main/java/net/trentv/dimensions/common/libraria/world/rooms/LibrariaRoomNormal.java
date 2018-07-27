package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.trentv.dimensions.common.ChunkBuilder;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public class LibrariaRoomNormal extends LibrariaRoom
{
	@Override
	public Chunk build(LibrariaBiome biome, World world, int chunkX, int chunkY, Random r)
	{
		IBlockState state = biome.fillerBlock.getDefaultState();
		ChunkBuilder p = new ChunkBuilder();

		p.fill(state, 0, 95);
		p.fill(state, 192, 255);

		p.buildChunk(world, chunkX, chunkY);

		int x = chunkX % 11;
		int y = chunkY % 11;

		for (int i = 96; i <= 176; i += 16)
		{
			if (i == 128 & ((x == 4 & (y == 0 | y == 8)) | y == 4 & (x == 0 | x == 8)))
			{
				p.addRoom(world, "libraria/" + biome.id + "/entrance", i);
				continue;
			}
			p.addRoom(world, "libraria/" + biome.id + "/room/" + r.nextInt(4) + "_fresh", i);
		}

		return p.getChunk();
	}
}
