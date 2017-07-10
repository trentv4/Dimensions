package net.trentv.dimensions.common.libraria;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.trentv.dimensions.Dimensions;
import net.trentv.dimensions.common.DimensionsObjects;
import net.trentv.dimensions.common.libraria.block.BlockEnormousBook.BlockEnormousBookClosed;
import net.trentv.dimensions.common.libraria.block.BlockEnormousBook.BlockEnormousBookOpen;
import net.trentv.dimensions.common.libraria.block.BlockMarmorBookshelf;
import net.trentv.dimensions.common.libraria.block.BlockModRotatedPillar;
import net.trentv.dimensions.common.libraria.block.BlockModStairs;
import net.trentv.dimensions.common.libraria.block.BlockParticleDripper;
import net.trentv.dimensions.common.libraria.block.BlockPile;
import net.trentv.dimensions.common.libraria.block.BlockPileTranslucent;
import net.trentv.dimensions.common.libraria.block.BlockRailing;
import net.trentv.dimensions.common.libraria.block.BlockSmoldering;

public class LibrariaObjects
{
	public static final DimensionsLibrariaCreativeTab LIBRARIA_CREATIVE_TAB = new DimensionsLibrariaCreativeTab("randomdimensions.libraria");

	public static final Block ENORMOUS_BOOK_OPEN = new BlockEnormousBookOpen(Material.WOOD).setUnlocalizedName("randomdimensions.enormous_book").setRegistryName(Dimensions.MODID, "enormous_book_open");
	public static final Block ENORMOUS_BOOK_CLOSED = new BlockEnormousBookClosed(Material.WOOD).setUnlocalizedName("randomdimensions.enormous_book").setRegistryName(Dimensions.MODID, "enormous_book_closed").setCreativeTab(LIBRARIA_CREATIVE_TAB);

	public static final Block MARMOR = new Block(Material.ROCK).setUnlocalizedName("randomdimensions.marmor").setRegistryName(Dimensions.MODID, "marmor").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block MARMOR_TILE = new Block(Material.ROCK).setUnlocalizedName("randomdimensions.marmor_tile").setRegistryName(Dimensions.MODID, "marmor_tile").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block MARMOR_PILLAR = new BlockModRotatedPillar(Material.ROCK).setUnlocalizedName("randomdimensions.marmor_pillar").setRegistryName(Dimensions.MODID, "marmor_pillar").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block MARMOR_BOOKSHELF = new BlockMarmorBookshelf(Material.ROCK).setUnlocalizedName("randomdimensions.marmor_bookshelf").setRegistryName(Dimensions.MODID, "marmor_bookshelf").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block MARMOR_TILE_STAIRS = new BlockModStairs(MARMOR_TILE.getDefaultState()).setUnlocalizedName("randomdimensions.marmor_tile_stairs").setRegistryName(Dimensions.MODID, "marmor_tile_stairs").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block MARMOR_RAILING = new BlockRailing(Material.ROCK).setUnlocalizedName("randomdimensions.marmor_railing").setRegistryName(Dimensions.MODID, "marmor_railing").setCreativeTab(LIBRARIA_CREATIVE_TAB);

	public static final Block MARMOR_LAMP = new Block(Material.ROCK).setLightLevel(1).setUnlocalizedName("marmor_lamp").setRegistryName(Dimensions.MODID, "marmor_lamp").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block MARMOR_LAMP_BROKEN = new Block(Material.ROCK).setUnlocalizedName("marmor_lamp_broken").setRegistryName(Dimensions.MODID, "marmor_lamp_broken").setCreativeTab(LIBRARIA_CREATIVE_TAB);

	public static final Block OAK_RAILING = new BlockRailing(Material.WOOD).setUnlocalizedName("randomdimensions.oak_railing").setRegistryName(Dimensions.MODID, "oak_railing").setCreativeTab(LIBRARIA_CREATIVE_TAB);

	public static final Block CHARRED_PLANKS = new Block(Material.WOOD).setUnlocalizedName("randomdimensions.charred_planks").setRegistryName(Dimensions.MODID, "charred_planks").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block CHARRED_BOOKSHELF = new Block(Material.WOOD).setUnlocalizedName("randomdimensions.charred_bookshelf").setRegistryName(Dimensions.MODID, "charred_bookshelf").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block SMOLDERING_PLANKS = new BlockSmoldering(Material.WOOD, EnumParticleTypes.BLOCK_DUST, 60, Block.getStateId(CHARRED_PLANKS.getDefaultState())).setUnlocalizedName("randomdimensions.smoldering_planks").setRegistryName(Dimensions.MODID, "smoldering_planks").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block SMOLDERING_BOOKSHELF = new BlockSmoldering(Material.WOOD, EnumParticleTypes.BLOCK_DUST, 60, Block.getStateId(CHARRED_PLANKS.getDefaultState())).setUnlocalizedName("randomdimensions.smoldering_bookshelf").setRegistryName(Dimensions.MODID, "smoldering_bookshelf").setCreativeTab(LIBRARIA_CREATIVE_TAB);

	public static final Block WET_PLANKS = new Block(Material.WOOD).setUnlocalizedName("randomdimensions.wet_planks").setRegistryName(Dimensions.MODID, "wet_planks").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block WET_BOOKSHELF = new Block(Material.WOOD).setUnlocalizedName("randomdimensions.wet_bookshelf").setRegistryName(Dimensions.MODID, "wet_bookshelf").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block SOAKED_PLANKS = new BlockParticleDripper(Material.WOOD, EnumParticleTypes.DRIP_WATER, 40).setUnlocalizedName("randomdimensions.soaked_planks").setRegistryName(Dimensions.MODID, "soaked_planks").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block SOAKED_BOOKSHELF = new BlockParticleDripper(Material.WOOD, EnumParticleTypes.DRIP_WATER, 40).setUnlocalizedName("randomdimensions.soaked_bookshelf").setRegistryName(Dimensions.MODID, "soaked_bookshelf").setCreativeTab(LIBRARIA_CREATIVE_TAB);

	public static final Block PAPER_PILE = new BlockPile().setUnlocalizedName("randomdimensions.paper_pile").setRegistryName(Dimensions.MODID, "paper_pile").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block ASH_PILE = new BlockPile().setUnlocalizedName("randomdimensions.ash_pile").setRegistryName(Dimensions.MODID, "ash_pile").setCreativeTab(LIBRARIA_CREATIVE_TAB);
	public static final Block PUDDLE = new BlockPileTranslucent().setUnlocalizedName("randomdimensions.puddle").setRegistryName(Dimensions.MODID, "puddle").setCreativeTab(LIBRARIA_CREATIVE_TAB);


	public static void init()
	{
		DimensionsObjects.registerBlock(MARMOR, MARMOR_TILE, MARMOR_TILE_STAIRS, MARMOR_PILLAR, MARMOR_BOOKSHELF, MARMOR_RAILING, MARMOR_LAMP, MARMOR_LAMP_BROKEN);
		DimensionsObjects.registerBlock(CHARRED_PLANKS, CHARRED_BOOKSHELF, SMOLDERING_PLANKS, SMOLDERING_BOOKSHELF);
		DimensionsObjects.registerBlock(WET_PLANKS, WET_BOOKSHELF, SOAKED_PLANKS, SOAKED_BOOKSHELF);
		DimensionsObjects.registerBlock(PAPER_PILE, ASH_PILE, PUDDLE);
		DimensionsObjects.registerBlock(ENORMOUS_BOOK_OPEN, ENORMOUS_BOOK_CLOSED, OAK_RAILING);
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
