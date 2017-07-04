package net.trentv.dimensions.common.libraria;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.trentv.dimensions.Dimensions;
import net.trentv.dimensions.common.DimensionsObjects;
import net.trentv.dimensions.common.libraria.block.BlockPile;
import net.trentv.dimensions.common.libraria.block.BlockPileTranslucent;
import net.trentv.dimensions.common.libraria.block.BlockSmouldering;

public class LibrariaObjects
{
	public static final DimensionsLibrariaCreativeTab LIBRARIA_CREATIVE_TAB = new DimensionsLibrariaCreativeTab("dimensions.libraria");

	public static final Block MARMOR = new Block(Material.ROCK).setUnlocalizedName("randomdimensions.marmor").setRegistryName(Dimensions.MODID, "marmor").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block MARMOR_TILE = new Block(Material.ROCK).setUnlocalizedName("randomdimensions.marmor_tile").setRegistryName(Dimensions.MODID, "marmor_tile").setCreativeTab(LIBRARIA_CREATIVE_TAB);

	public static final Block CHARRED_PLANKS = new Block(Material.WOOD).setUnlocalizedName("randomdimensions.charred_planks").setRegistryName(Dimensions.MODID, "charred_planks").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block CHARRED_BOOKSHELF = new Block(Material.WOOD).setUnlocalizedName("randomdimensions.charred_bookshelf").setRegistryName(Dimensions.MODID, "charred_bookshelf").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block SMOLDERING_PLANKS = new BlockSmouldering().setUnlocalizedName("randomdimensions.smouldering_planks").setRegistryName(Dimensions.MODID, "smouldering_planks").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block SMOLDERING_BOOKSHELF = new BlockSmouldering().setUnlocalizedName("randomdimensions.smouldering_bookshelf").setRegistryName(Dimensions.MODID, "smouldering_bookshelf").setCreativeTab(LIBRARIA_CREATIVE_TAB);

	public static final Block PAPER_PILE = new BlockPile().setUnlocalizedName("randomdimensions.paper_pile").setRegistryName(Dimensions.MODID, "paper_pile").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block ASH_PILE = new BlockPile().setUnlocalizedName("randomdimensions.ash_pile").setRegistryName(Dimensions.MODID, "ash_pile").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block PUDDLE = new BlockPileTranslucent().setUnlocalizedName("randomdimensions.puddle").setRegistryName(Dimensions.MODID, "puddle").setCreativeTab(LIBRARIA_CREATIVE_TAB);

	public static void init()
	{
		DimensionsObjects.registerBlock(MARMOR);
		DimensionsObjects.registerBlock(MARMOR_TILE);
		DimensionsObjects.registerBlock(CHARRED_PLANKS);
		DimensionsObjects.registerBlock(CHARRED_BOOKSHELF);
		DimensionsObjects.registerBlock(SMOLDERING_PLANKS);
		DimensionsObjects.registerBlock(SMOLDERING_BOOKSHELF);
		DimensionsObjects.registerBlock(PAPER_PILE);
		DimensionsObjects.registerBlock(ASH_PILE);
		DimensionsObjects.registerBlock(PUDDLE);
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
