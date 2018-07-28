package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.trentv.dimensions.common.ChunkBuilder;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public class LibrariaRoomStair extends LibrariaRoom
{
	@Override
	public Chunk build(LibrariaBiome biome, World world, int chunkX, int chunkY, Random r)
	{
		IBlockState state = biome.fillerBlock.getDefaultState();
		ChunkBuilder p = new ChunkBuilder();

		p.fill(state, 0, 95);
		p.fill(state, 174, 255);

		p.buildChunk(world, chunkX, chunkY);

		p.addRoom(world, "libraria/" + biome.id + "/stairs/bottom/0", 96);

		for (int i = 112; i < 160; i += 16)
		{
			p.addRoom(world, "libraria/" + biome.id + "/stairs/middle/0", i);
		}

		p.addRoom(world, "libraria/" + biome.id + "/stairs/top/0", 160);

		return p.getChunk();
	}
}
