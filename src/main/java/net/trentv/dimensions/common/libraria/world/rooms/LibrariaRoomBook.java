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
		p.fill(state, 176, 255);

		p.buildChunk(world, chunkX, chunkY);

		int x = chunkX % 11;
		int y = chunkY % 11;

		// Layers 0 and 1
		if (x == 5 && y == 5)
		{
			p.addRoom(world, "libraria/" + biome.id + "/stairs/bottom/0", 96);
			p.addRoom(world, "libraria/" + biome.id + "/antechamber", 112);
		}
		else
		{
			for (int i = 96; i < 128; i += 16)
			{
				p.addRoom(world, "libraria/" + biome.id + "/room/" + r.nextInt(LibrariaRoomNormal.ROOM_COUNT) + "f", i);
			}
		}

		for (int i = 128; i < 176; i += 16)
		{
			if (x == 5 && y == 5 && i == 144)
				;// p.addRoom(world, "libraria/" + biome.id + "/bossroomtemp", i);
			else
				p.addRoom(world, "libraria/" + biome.id + "/bossroomtemp", i);
		}

		return p.getChunk();
	}
}
