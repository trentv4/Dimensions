package net.trentv.dimensions.common.smudged.world;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.Template.BlockInfo;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.trentv.dimensions.Dimensions;

public class ChunkGeneratorSmudgedCity implements IChunkGenerator
{
	public static TemplateManager manager;
	private World world;

	private IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
	private IBlockState BRICKS = Blocks.BRICK_BLOCK.getDefaultState();
	private IBlockState STONE_BRICKS = Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT);

	private HashMap<ResourceLocation, Template> templates = new HashMap<ResourceLocation, Template>();

	public ChunkGeneratorSmudgedCity(World world)
	{
		this.world = world;
		manager = world.getSaveHandler().getStructureTemplateManager();
	}

	@Override
	public Chunk generateChunk(int cx, int cz)
	{
		ChunkPrimer p = new ChunkPrimer();

		Random r = new Random();
		r.setSeed(world.getSeed());
		r.setSeed(cx * r.nextLong() + cz * r.nextLong());

		// 0: bedrock
		// 1-17: sewers
		// 18-66: factories
		// 67-243: residences
		// 244-255: 11 blocks of working area

		// Bedrock

		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				p.setBlockState(x, 0, z, BEDROCK);
			}
		}

		// Sewers

		for (int y = 1; y <= 16; y++)
		{
			for (int x = 0; x < 16; x++)
			{
				for (int z = 0; z < 16; z++)
				{
					p.setBlockState(x, y, z, BRICKS);
				}
			}
		}

		// Factories

		for (int y = 17; y <= 48; y++)
		{
			for (int x = 0; x < 16; x++)
			{
				for (int z = 0; z < 16; z++)
				{
					p.setBlockState(x, y, z, STONE_BRICKS);
				}
			}
		}

		// Residences

		spawnResidences(p, 0, 0, r);
		spawnResidences(p, 8, 0, r);
		spawnResidences(p, 0, 8, r);
		spawnResidences(p, 8, 8, r);

		Chunk chunk = new Chunk(world, p, cx, cz);
		chunk.generateSkylightMap();
		return chunk;
	}

	public void spawnResidences(ChunkPrimer p, int sx, int sz, Random r)
	{
		int toHeight = 49 + (r.nextInt(22) * 8);

		for (int y = 49; y < toHeight; y += 8)
		{
			Template t = getTemplate(new ResourceLocation(Dimensions.MODID, "residence_" + r.nextInt(2)));
			for (BlockInfo info : t.blocks)
			{
				BlockPos pos = info.pos.add(sx, y, sz);
				p.setBlockState(pos.getX(), pos.getY(), pos.getZ(), info.blockState);
			}
		}
	}

	public Template getTemplate(ResourceLocation location)
	{
		if (templates.containsKey(location))
		{
			return templates.get(location);
		}
		Template t = manager.getTemplate(world.getMinecraftServer(), location);
		templates.put(location, t);
		return t;
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
