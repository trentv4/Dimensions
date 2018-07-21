package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.trentv.dimensions.Dimensions;
import net.trentv.dimensions.common.ChunkTemplate;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;

public class LibrariaRoomBridge extends LibrariaRoom
{
	public LibrariaRoomBridge(LibrariaBiome biome)
	{
		super(biome);
	}

	@Override
	public Chunk build(World world, int chunkX, int chunkY, Random r)
	{
		Chunk start = new Chunk(world, chunkX, chunkY);
		ResourceLocation file = new ResourceLocation(Dimensions.MODID, "libraria/" + biome.id + "/bridge/horizontal/" + r.nextInt(5));
		ChunkTemplate template = ChunkTemplate.getChunkTemplate(world, file);
		template.addAllToChunk(start, new BlockPos(0, 128, 0));

		return start;
	}
}
