package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
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
		IBlockState state = biome.fillerBlock.getDefaultState();
		ChunkPrimer p = new ChunkPrimer();
		fillPrimer(p, state, 0, 95);
		fillPrimer(p, state, 192, 255);

		Chunk start = new Chunk(world, p, chunkX, chunkY);
		ResourceLocation file = new ResourceLocation(Dimensions.MODID, "libraria/" + biome.id + "/stairs/bottom/0");
		ChunkTemplate template = ChunkTemplate.getChunkTemplate(world, file);
		template.addAllToChunk(start, new BlockPos(0, 96, 0));

		for (int i = 1; i <= 4; i++)
		{
			file = new ResourceLocation(Dimensions.MODID, "libraria/" + biome.id + "/stairs/middle/0");
			template = ChunkTemplate.getChunkTemplate(world, file);
			template.addAllToChunk(start, new BlockPos(0, 96 + (i * 16), 0));
		}

		file = new ResourceLocation(Dimensions.MODID, "libraria/" + biome.id + "/stairs/top/0");
		template = ChunkTemplate.getChunkTemplate(world, file);
		template.addAllToChunk(start, new BlockPos(0, 176, 0));

		return start;
	}
}
