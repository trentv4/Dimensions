package net.trentv.dimensions.common.libraria;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.trentv.dimensions.Dimensions;
import net.trentv.dimensions.common.DimensionsObjects;

public class LibrariaObjects
{
	public static final DimensionsLibrariaCreativeTab LIBRARIA_CREATIVE_TAB = new DimensionsLibrariaCreativeTab("dimensions.libraria");

	public static final Block MARMOR = new Block(Material.ROCK).setUnlocalizedName("randomdimensions.marmor").setRegistryName(Dimensions.MODID, "marmor").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block MARMOR_TILE = new Block(Material.ROCK).setUnlocalizedName("randomdimensions.marmor_tile").setRegistryName(Dimensions.MODID, "marmor_tile").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	
	public static void init()
	{
		DimensionsObjects.registerBlock(MARMOR);
		DimensionsObjects.registerBlock(MARMOR_TILE);
	}

	private static class DimensionsLibrariaCreativeTab extends CreativeTabs
	{
		public DimensionsLibrariaCreativeTab(String label)
		{
			super(label);
		}

		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Blocks.BEACON);
		}
	}
}
