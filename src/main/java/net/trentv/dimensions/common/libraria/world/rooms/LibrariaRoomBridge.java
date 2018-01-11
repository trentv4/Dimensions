package net.trentv.dimensions.common.libraria.world.rooms;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;
import net.trentv.dimensions.common.libraria.LibrariaObjects;

public class LibrariaRoomBridge extends LibrariaRoom
{
	public LibrariaRoomBridge(LibrariaBiome biome)
	{
		super(biome);
	}

	@Override
	public Chunk build(World world, int chunkX, int chunkY, Random r)
	{
		// TODO: switch to Template
		IBlockState state = LibrariaObjects.MARMOR.getDefaultState();
		IBlockState s = LibrariaObjects.MARMOR_RAILING.getDefaultState();
		ChunkPrimer p = new ChunkPrimer();

		for (int x = 0; x < 16; x++)
		{
			for (int z = 4; z < 10; z++)
			{
				p.setBlockState(x, 128, z, state);
			}
			p.setBlockState(x, 129, 4, s);
			p.setBlockState(x, 129, 9, s);
		}

		return new Chunk(world, p, chunkX, chunkY);
	}
}
