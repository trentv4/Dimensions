package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.trentv.dimensions.common.ChunkBuilder;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public class LibrariaRoomNormal extends LibrariaRoom
{
	// todo determine automatically
	public static final int ROOM_COUNT = 7;

	@Override
	public Chunk build(LibrariaBiome biome, World world, int chunkX, int chunkY, Random r)
	{
		IBlockState state = biome.fillerBlock.getDefaultState();
		ChunkBuilder p = new ChunkBuilder();

		p.fill(state, 0, 95);
		p.fill(state, 174, 255);

		p.buildChunk(world, chunkX, chunkY);

		int x = chunkX % 11;
		int y = chunkY % 11;

		for (int i = 96; i < 176; i += 16)
		{
			if (i == 128 && ((x == 5 && (y == 1 || y == 9)) || y == 5 && (x == 1 || x == 9)))
			{
				p.addRoom(world, "libraria/" + biome.id + "/entrance", i);
				continue;
			}

			String roomQuality = "";
			switch (r.nextInt(1))
			{
				case 0:
					roomQuality = "f";
					break;
				case 1:
					roomQuality = "b";
					break;
				case 2:
					roomQuality = "d";
					break;
			}

			p.addRoom(world, "libraria/" + biome.id + "/room/" + r.nextInt(ROOM_COUNT) + roomQuality, i);
		}

		return p.getChunk();
	}
}
