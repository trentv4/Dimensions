package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.trentv.dimensions.common.ChunkBuilder;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public class LibrariaRoomBook extends LibrariaRoom
{
	@Override
	public Chunk build(LibrariaBiome biome, World world, int chunkX, int chunkY, Random r)
	{
		IBlockState state = biome.fillerBlock.getDefaultState();
		ChunkBuilder p = new ChunkBuilder();

		p.fill(state, 0, 95);
		p.fill(state, 174, 255);

		return p.buildChunk(world, chunkX, chunkY);
	}
}
