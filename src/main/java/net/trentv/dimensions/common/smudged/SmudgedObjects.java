package net.trentv.dimensions.common.smudged;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.trentv.dimensions.Dimensions;
import net.trentv.dimensions.common.DimensionsObjects;

public class SmudgedObjects
{
	public static final DimensionsSmudgedCityCreativeTab SMUDGED_CITY_CREATIVE_TAB = new DimensionsSmudgedCityCreativeTab("randomdimensions.smudged_city");

	public static final Block SHEETROCK = new Block(Material.ROCK).setUnlocalizedName("sheetrock").setRegistryName(Dimensions.MODID, "sheetrock").setCreativeTab(SMUDGED_CITY_CREATIVE_TAB);
	public static final Block CONCRETE = new Block(Material.ROCK).setUnlocalizedName("concrete").setRegistryName(Dimensions.MODID, "concrete").setCreativeTab(SMUDGED_CITY_CREATIVE_TAB);
	public static final Block SCRAP_WOOD = new Block(Material.ROCK).setUnlocalizedName("scrap_wood").setRegistryName(Dimensions.MODID, "scrap_wood").setCreativeTab(SMUDGED_CITY_CREATIVE_TAB);
	public static final Block SCRAP_IRON = new Block(Material.ROCK).setUnlocalizedName("scrap_iron").setRegistryName(Dimensions.MODID, "scrap_iron").setCreativeTab(SMUDGED_CITY_CREATIVE_TAB);

	public static void init()
	{
		DimensionsObjects.registerBlockAndItem(SHEETROCK, CONCRETE, SCRAP_WOOD, SCRAP_IRON);
		DimensionSmudgedCity.register(300, "smudged_city");
	}

	private static class DimensionsSmudgedCityCreativeTab extends CreativeTabs
	{
		public DimensionsSmudgedCityCreativeTab(String label)
		{
			super(label);
		}

		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Blocks.STONE);
		}
	}
}
