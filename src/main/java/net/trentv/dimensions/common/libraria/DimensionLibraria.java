package net.trentv.dimensions.common.libraria;

import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorDebug;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.DimensionManager;

public class DimensionLibraria
{
	public static int dimensionID;
	public static WorldProvider provider;
	public static DimensionType dimensionType;

	public static void register(int id, String dimName)
	{
		dimensionID = id;
		provider = new WorldProviderLibraria();
		dimensionType = DimensionType.register(dimName, "_" + dimName, dimensionID, provider.getClass(), false);
		DimensionManager.registerDimension(dimensionID, dimensionType);
	}

	public static class WorldProviderLibraria extends WorldProvider
	{
		public WorldProviderLibraria()
		{

		}

		@Override
		protected void init()
		{
			this.biomeProvider = new BiomeProviderSingle(Biomes.SKY);
			this.hasSkyLight = true;
		}

		@Override
		public DimensionType getDimensionType()
		{
			return DimensionLibraria.dimensionType;
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
