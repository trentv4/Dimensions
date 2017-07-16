package net.trentv.dimensions.common.libraria.world;

import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class ChunkGeneratorLibraria implements IChunkGenerator
{
	public static TemplateManager manager;
	private static final HashMap<LibrariaZonePos, LibrariaZone> zones = new HashMap<LibrariaZonePos, LibrariaZone>();
	private World world;

	public ChunkGeneratorLibraria(World world)
	{
		this.world = world;
		manager = world.getSaveHandler().getStructureTemplateManager();
	}

	@Override
	public Chunk generateChunk(int x, int z)
	{
		ChunkPrimer p = new ChunkPrimer();

		LibrariaZonePos pos = LibrariaZonePos.fromChunk(x, z);
		LibrariaZone zone;
		if (!zones.containsKey(pos))
		{
			zone = new LibrariaZone(pos, world);
		}
		else
		{
			zone = zones.get(pos);
		}
		LibrariaRoom[] rooms = zone.getRooms(x, z);
		int y = 0;

		for (LibrariaRoom room : rooms)
		{
			room.build(p, 0, y, 0);
			y += room.size;
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
