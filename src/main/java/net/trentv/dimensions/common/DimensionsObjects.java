package net.trentv.dimensions.common;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.trentv.dimensions.common.block.BlockEnormousBook;
import net.trentv.dimensions.common.libraria.DimensionLibraria;
import net.trentv.dimensions.common.libraria.LibrariaObjects;
import net.trentv.dimensions.common.smudged.DimensionSmudgedCity;
import net.trentv.dimensions.common.smudged.SmudgedObjects;

public class DimensionsObjects
{
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static ArrayList<ItemBlock> itemBlocks = new ArrayList<ItemBlock>();
	public static ArrayList<Biome> biomes = new ArrayList<Biome>();

	public static void init()
	{
		LibrariaObjects.init();
		SmudgedObjects.init();

		BlockEnormousBook.register("randomdimensions.enormous_book", "", LibrariaObjects.LIBRARIA_CREATIVE_TAB, DimensionLibraria.dimensionID, DimensionType.OVERWORLD.getId());
		BlockEnormousBook.register("randomdimensions.enormous_book_smudged", "_smudged", SmudgedObjects.SMUDGED_CITY_CREATIVE_TAB, DimensionSmudgedCity.dimensionID, DimensionType.OVERWORLD.getId());
	}

	public static void registerBlock(Block... toRegister)
	{
		for (Block in : toRegister)
		{
			blocks.add(in);
		}
	}

	public static void registerBiome(Biome... toRegister)
	{
		for (Biome in : toRegister)
		{
			biomes.add(in);
		}
	}

	public static void registerBlockAndItem(Block... toRegister)
	{
		for (Block in : toRegister)
		{
			blocks.add(in);
			ItemBlock a = new ItemBlock(in);
			a.setRegistryName(in.getRegistryName());
			itemBlocks.add(a);
		}
	}

	public static void registerItem(Item... toRegister)
	{
		for (Item in : toRegister)
		{
			items.add(in);
		}
	}
}
