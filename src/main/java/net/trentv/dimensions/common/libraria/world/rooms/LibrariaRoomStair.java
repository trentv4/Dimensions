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
import net.trentv.dimensions.common.libraria.LibrariaObjects;

public class LibrariaRoomStair extends LibrariaRoom
{
	public LibrariaRoomStair(LibrariaBiome biome)
	{
		super(biome);
	}

	@Override
	public Chunk build(World world, int chunkX, int chunkY, Random r)
	{
		IBlockState state = LibrariaObjects.MARMOR.getDefaultState();
		ChunkPrimer p = new ChunkPrimer();
		for (int y = 0; y < 256; y++)
		{
			for (int z = 0; z < 16; z++)
			{
				p.setBlockState(z, y, 0, state);
				p.setBlockState(z, y, 15, state);
				p.setBlockState(0, y, z, state);
				p.setBlockState(15, y, z, state);
			}
			if (y == 96)
			{
				y = 176;
				for (int x = 0; x < 16; x++)
				{
					for (int z2 = 0; z2 < 16; z2++)
					{
						p.setBlockState(x, y, z2, state);
					}
				}
			}
		}

		Chunk start = new Chunk(world, p, chunkX, chunkY);

		ResourceLocation file = new ResourceLocation(Dimensions.MODID, "libraria/stairs_marmor_bottom_0");
		ChunkTemplate template = new ChunkTemplate(world, file);
		template.addAllToChunk(start, new BlockPos(0, 96, 0));

		for (int i = 1; i < 4; i++)
		{
			file = new ResourceLocation(Dimensions.MODID, "libraria/stairs_marmor_middle_0");
			template = new ChunkTemplate(world, file);
			template.addAllToChunk(start, new BlockPos(0, 96 + (i * 16), 0));
		}

		file = new ResourceLocation(Dimensions.MODID, "libraria/stairs_marmor_top_0");
		template = new ChunkTemplate(world, file);
		template.addAllToChunk(start, new BlockPos(0, 160, 0));

		return start;
	}
}
