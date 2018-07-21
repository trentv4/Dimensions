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

public class LibrariaRoomNormal extends LibrariaRoom
{
	public LibrariaRoomNormal(LibrariaBiome biome)
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
		for (int i = 0; i <= 5; i++)
		{
			ResourceLocation file = new ResourceLocation(Dimensions.MODID, "libraria/" + biome.id + "/room/" + r.nextInt(4) + "_fresh");
			ChunkTemplate template = ChunkTemplate.getChunkTemplate(world, file);
			template.addAllToChunk(start, new BlockPos(0, 96 + (i * 16), 0));
		}

		return start;
	}
}
