package net.trentv.dimensions.common.libraria;

import net.minecraft.entity.Entity;
import net.minecraft.world.DimensionType;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.DimensionManager;
import net.trentv.dimensions.Dimensions;
import net.trentv.dimensions.common.libraria.world.ChunkGeneratorLibraria;

public class DimensionLibraria
{
	public static int dimensionID;
	public static WorldProvider provider;
	public static DimensionType dimensionType;
	
	public static Biome BIOME_LIBRARIA;

	public static void register(int id, String dimName)
	{
		BIOME_LIBRARIA = new BiomeLibraria(new Biome.BiomeProperties("Libraria").setRainDisabled()).setRegistryName(Dimensions.MODID, "biome_libraria");

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
			this.biomeProvider = new BiomeProviderSingle(BIOME_LIBRARIA);
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

		@Override
		public IChunkGenerator createChunkGenerator()
		{
			return new ChunkGeneratorLibraria(world);
		}

		@Override
		public BiomeProvider getBiomeProvider()
		{
			return biomeProvider;
		}
	}

	public static class TeleporterLibraria extends Teleporter
	{
		public TeleporterLibraria(WorldServer world)
		{
			super(world);
		}

		@Override
		public boolean makePortal(Entity entity)
		{
			return true;
		}
	}

	public static class BiomeLibraria extends Biome
	{
	    public BiomeLibraria(Biome.BiomeProperties properties)
	    {
	        super(properties);
	        this.spawnableMonsterList.clear();
	        this.spawnableCreatureList.clear();
	        this.spawnableWaterCreatureList.clear();
	        this.spawnableCaveCreatureList.clear();
			this.decorator = new BiomeDecorator();
	    }
	}
}
