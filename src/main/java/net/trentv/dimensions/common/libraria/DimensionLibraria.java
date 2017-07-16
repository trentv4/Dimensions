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
	
	public static Biome BIOME_MARMOR;
	public static Biome BIOME_SMOLDERING;
	public static Biome BIOME_WET;
	public static Biome BIOME_WOOD;
	public static Biome[] BIOMES;

	public static void register(int id, String dimName)
	{
		BIOME_MARMOR = new BiomeLibraria(new Biome.BiomeProperties("Marmor Library").setRainDisabled()).setRegistryName(Dimensions.MODID, "biome_libraria_marmor");
		BIOME_SMOLDERING = new BiomeLibraria(new Biome.BiomeProperties("Smoldering Library").setRainDisabled()).setRegistryName(Dimensions.MODID, "biome_libraria_smoldering");
		BIOME_WET = new BiomeLibraria(new Biome.BiomeProperties("Wet Library").setRainDisabled()).setRegistryName(Dimensions.MODID, "biome_libraria_wet");
		BIOME_WOOD = new BiomeLibraria(new Biome.BiomeProperties("Wooden Library").setRainDisabled()).setRegistryName(Dimensions.MODID, "biome_libraria_wood");
		BIOMES = new Biome[] { BIOME_MARMOR, BIOME_SMOLDERING, BIOME_WET, BIOME_WOOD };

		dimensionID = id;
		provider = new WorldProviderLibraria();
		dimensionType = DimensionType.register(dimName, "_" + dimName, dimensionID, provider.getClass(), false);
		DimensionManager.registerDimension(dimensionID, dimensionType);
	}

	public static class WorldProviderLibraria extends WorldProvider
	{
		@Override
		protected void init()
		{
			this.biomeProvider = new BiomeProviderSingle(BIOME_MARMOR);
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

	public static class BiomeProviderLibraria extends BiomeProvider
	{

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
