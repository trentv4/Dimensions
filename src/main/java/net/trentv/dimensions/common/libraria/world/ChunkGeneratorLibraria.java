package net.trentv.dimensions.common.libraria.world;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.trentv.dimensions.common.libraria.DimensionLibraria.LibrariaBiome;
import net.trentv.dimensions.common.libraria.world.rooms.LibrariaRoom;

public class ChunkGeneratorLibraria implements IChunkGenerator
{
	private World world;

	public ChunkGeneratorLibraria(World world)
	{
		this.world = world;
	}

	@Override
	public Chunk generateChunk(int x, int z)
	{
		Random r = new Random();
		r.setSeed(world.getSeed());

		long roomSeed = x * r.nextInt() + z * r.nextInt();
		long biomeSeed = (long) (Math.floor(x / 12d) * r.nextInt() + Math.floor(z / 12d) * r.nextInt());

		r.setSeed(biomeSeed);
		LibrariaBiome chunkBiome = LibrariaBiome.fromInt(r.nextInt(4));
		r.setSeed(roomSeed);

		Chunk chunk = LibrariaRoom.getRoom(x % 12, z % 12, r, chunkBiome).build(world, x, z, r);

		chunk.generateSkylightMap();
		chunk.enqueueRelightChecks();
		chunk.checkLight();
		return chunk;
	}

	/* BLAH BLAH DON'T CARE RIGHT NOW BLAH BLAH */

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
