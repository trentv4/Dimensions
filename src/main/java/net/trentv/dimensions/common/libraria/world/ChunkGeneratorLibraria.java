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

		long roomSeed = getSeed(x, z, r);
		long biomeSeed = getSeed((int) Math.floor(x / 11d), (int) Math.floor(z / 11d), r);

		r.setSeed(biomeSeed);
		LibrariaBiome chunkBiome = LibrariaBiome.fromInt(r.nextInt(4));
		r.setSeed(roomSeed);

		Chunk chunk = LibrariaRoom.getRoom(x % 11, z % 11, r, chunkBiome).build(world, x, z, r);

		chunk.generateSkylightMap();
		chunk.enqueueRelightChecks();
		chunk.checkLight();
		return chunk;
	}

	private long getSeed(int x, int z, Random r)
	{
		long xRand = r.nextInt();
		long zRand = r.nextInt();

		if (xRand == 0)
			xRand = 1;
		if (zRand == 0)
			zRand = 1;

		return (x * xRand) + (z * zRand);
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
		return this.world.getBiome(pos).getSpawnableList(creatureType);
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
