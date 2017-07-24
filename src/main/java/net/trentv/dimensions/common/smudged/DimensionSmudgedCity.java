package net.trentv.dimensions.common.smudged;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorDebug;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.DimensionManager;
import net.trentv.dimensions.Dimensions;
import net.trentv.dimensions.common.DimensionsObjects;

public class DimensionSmudgedCity
{
	public static int dimensionID;
	public static WorldProvider provider;
	public static DimensionType dimensionType;

	public static Biome BIOME_SMUDGED;

	public static void register(int id, String dimName)
	{
		BIOME_SMUDGED = new BiomeSmudged(new Biome.BiomeProperties("Smudged City").setRainDisabled()).setRegistryName(Dimensions.MODID, "biome_smudged_city");

		DimensionsObjects.registerBiome(BIOME_SMUDGED);

		dimensionID = id;
		provider = new WorldProviderSmudged();
		dimensionType = DimensionType.register(dimName, "_" + dimName, dimensionID, provider.getClass(), false);
		DimensionManager.registerDimension(dimensionID, dimensionType);
	}

	public static class BiomeSmudged extends Biome
	{
		public BiomeSmudged(Biome.BiomeProperties properties)
		{
			super(properties);
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
			this.decorator = new BiomeDecorator();
		}
	}

	public static class WorldProviderSmudged extends WorldProvider
	{
		@Override
		protected void init()
		{
			this.biomeProvider = new BiomeProviderSingle(BIOME_SMUDGED);
			this.hasSkyLight = true;
		}

		@Override
		public DimensionType getDimensionType()
		{
			return DimensionSmudgedCity.dimensionType;
		}

		@Override
		public boolean isSurfaceWorld()
		{
			return false;
		}

		@Override
		public boolean isSkyColored()
		{
			return false;
		}

		@Override
		public IChunkGenerator createChunkGenerator()
		{
			return new ChunkGeneratorDebug(world);
		}

		@Override
		public BiomeProvider getBiomeProvider()
		{
			return biomeProvider;
		}
	}
}
