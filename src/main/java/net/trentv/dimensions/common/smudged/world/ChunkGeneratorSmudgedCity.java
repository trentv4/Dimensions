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
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.trentv.dimensions.Dimensions;
import net.trentv.dimensions.common.AdvancedChunkPrimer;
import net.trentv.dimensions.common.PrimerTemplate;

public class ChunkGeneratorSmudgedCity implements IChunkGenerator
{
	public static TemplateManager manager;
	private World world;

	private IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
	private IBlockState BRICKS = Blocks.BRICK_BLOCK.getDefaultState();
	private IBlockState STONE_BRICKS = Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT);

	private NoiseGeneratorPerlinBounded perlin;
	private HashMap<ResourceLocation, Template> templates = new HashMap<ResourceLocation, Template>();

	private final int SIZE_8_ROOM_COUNT = 4;
	private final int SIZE_16_ROOM_COUNT = 1;

	public ChunkGeneratorSmudgedCity(World world)
	{
		this.world = world;
		manager = world.getSaveHandler().getStructureTemplateManager();
		Random r = new Random();
		r.setSeed(world.getSeed());
		r.setSeed(r.nextLong());
		perlin = new NoiseGeneratorPerlinBounded(r, 16);
	}

	@Override
	public Chunk generateChunk(int cx, int cz)
	{
		AdvancedChunkPrimer p = new AdvancedChunkPrimer();

		Random r = new Random();
		r.setSeed(world.getSeed());
		r.setSeed(cx * r.nextLong() + cz * r.nextLong());
		generateBedrockLayer(p, r, 0);
		generateSewerLayer(p, r, 1);
		generateFactoryLayer(p, r, 17);
		generateResidenceLayer(p, r, cx, 65, cz);

		Chunk chunk = p.toChunk(world, cx, cz);
		chunk.generateSkylightMap();
		return chunk;
	}

	private void generateBedrockLayer(ChunkPrimer p, Random r, int y)
	{
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				p.setBlockState(x, y, z, BEDROCK);
			}
		}
	}

	private void generateSewerLayer(ChunkPrimer p, Random r, int yn)
	{
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				for (int y = 0; y < 16; y++)
				{
					p.setBlockState(x, y + yn, z, BRICKS);
				}
			}
		}
	}

	private void generateFactoryLayer(ChunkPrimer p, Random r, int yn)
	{
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				for (int y = 0; y < 48; y++)
				{
					p.setBlockState(x, y + yn, z, STONE_BRICKS);
				}
			}
		}
	}

	private void generateResidenceLayer(AdvancedChunkPrimer p, Random r, int xn, int yn, int zn)
	{
		int height = getHeightAtPosition(xn, zn);

		for (int i = 0; i < height; i += 8)
		{
			boolean is8 = r.nextInt(6) != 0;
			if (is8)
			{
				new PrimerTemplate(new ResourceLocation(Dimensions.MODID, "residence_8_" + r.nextInt(SIZE_8_ROOM_COUNT)), world).put(p, new BlockPos(0, yn + i, 0));
				new PrimerTemplate(new ResourceLocation(Dimensions.MODID, "residence_8_" + r.nextInt(SIZE_8_ROOM_COUNT)), world).put(p, new BlockPos(8, yn + i, 0));
				new PrimerTemplate(new ResourceLocation(Dimensions.MODID, "residence_8_" + r.nextInt(SIZE_8_ROOM_COUNT)), world).put(p, new BlockPos(0, yn + i, 8));
				new PrimerTemplate(new ResourceLocation(Dimensions.MODID, "residence_8_" + r.nextInt(SIZE_8_ROOM_COUNT)), world).put(p, new BlockPos(8, yn + i, 8));
			}
			else
			{
				new PrimerTemplate(new ResourceLocation(Dimensions.MODID, "residence_16_" + r.nextInt(SIZE_16_ROOM_COUNT)), world).put(p, new BlockPos(0, yn + i, 0));
			}
		}
	}

	public int getHeightAtPosition(double cx, double cz)
	{
		double scaling = 0.125;
		double val = perlin.getValue(cx * scaling, cz * scaling);
		int height = ((int) (val * 11)) * 16;

		return 65 + height;
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
