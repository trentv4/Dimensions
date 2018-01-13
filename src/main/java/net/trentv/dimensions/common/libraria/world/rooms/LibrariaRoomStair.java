package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.trentv.dimensions.Dimensions;
import net.trentv.dimensions.common.ChunkTemplate;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public class LibrariaRoomStair extends LibrariaRoom
{
	public LibrariaRoomStair(LibrariaBiome biome)
	{
		super(biome);
	}

	@Override
	public Chunk build(World world, int chunkX, int chunkY, Random r)
	{
		Chunk start = new Chunk(world, chunkX, chunkY);
		for (int i = 0; i <= 5; i++)
		{
			ResourceLocation file = new ResourceLocation(Dimensions.MODID, "stairs_marmor_middle_0");
			ChunkTemplate template = new ChunkTemplate(world, file);
			template.addAllToChunk(start, new BlockPos(0, 96 + (i * 16), 0));
		}

		return start;
	}
}
