package net.trentv.dimensions.common.libraria.world;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.trentv.dimensions.common.libraria.LibrariaObjects;

public class ChunkGeneratorLibraria implements IChunkGenerator
{
	private static final IBlockState MARMOR = LibrariaObjects.MARMOR.getDefaultState();
	private static final IBlockState STONE = Blocks.STONE.getDefaultState();
	private static final IBlockState GRASS = Blocks.GRASS.getDefaultState();
	private World world;

	public ChunkGeneratorLibraria(World world)
	{
		this.world = world;
	}

	@Override
	public Chunk generateChunk(int x, int z)
	{
		ChunkPrimer p = new ChunkPrimer();
		IBlockState state = MARMOR;
		if ((x % 2 == 0 & z % 2 == 0) | (x % 2 == 1 & z % 2 == 1))
		{
			state = STONE;
		}
		for(int i = 0; i < 16; i++)
		{
			for(int g = 0; g < 16; g++)
			{
				p.setBlockState(i, 0, g, state);
			}
		}
		Chunk chunk = new Chunk(world, p, x, z);
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int x, int z)
	{

	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z)
	{
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
	{
		return null;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
	{
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z)
	{

	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos)
	{
		return false;
	}

}
