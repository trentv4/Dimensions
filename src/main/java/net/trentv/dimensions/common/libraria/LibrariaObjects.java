package net.trentv.dimensions.common.libraria;

import static net.minecraft.block.material.Material.ROCK;
import static net.minecraft.block.material.Material.WOOD;
import static net.minecraft.util.EnumParticleTypes.BLOCK_DUST;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.trentv.dimensions.Dimensions;
import net.trentv.dimensions.common.DimensionsObjects;
import net.trentv.dimensions.common.libraria.block.BlockLibrariaBookshelf;
import net.trentv.dimensions.common.libraria.block.BlockMarmorDoor;
import net.trentv.dimensions.common.libraria.block.BlockMarmorSlab;
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

	// Marmor biome
	public static final Block MARMOR = build(ROCK, "marmor");
	public static final Block MARMOR_TILE = build(ROCK, "marmor_tile");
	public static final Block MARMOR_LAMP = build(ROCK, "marmor_lamp").setLightLevel(1);
	public static final Block MARMOR_LAMP_BROKEN = build(ROCK, "marmor_lamp_broken");

	public static final Block MARMOR_TILE_STAIRS = build(new BlockModStairs(MARMOR_TILE.getDefaultState()), "marmor_tile_stairs");
	public static final Block MARMOR_PILLAR = build(new BlockModRotatedPillar(ROCK), "marmor_pillar");
	public static final Block MARMOR_BOOKSHELF = build(new BlockLibrariaBookshelf(ROCK), "marmor_bookshelf");
	public static final Block MARMOR_RAILING = build(new BlockRailing(ROCK), "marmor_railing");
	public static final Block MARMOR_SLAB = build(new BlockMarmorSlab.Half(), "marmor_slab");
	public static final Block MARMOR_SLAB_FULL = build(new BlockMarmorSlab.Double(), "marmor_slab_full");

	public static final Block MARMOR_DOOR = build(new BlockMarmorDoor(ROCK), "marmor_door");
	public static final Item ITEM_MARMOR_DOOR = new ItemDoor(MARMOR_DOOR).setUnlocalizedName("randomdimensions.marmor_door").setRegistryName(Dimensions.MODID, "marmor_door_item").setCreativeTab(LIBRARIA_CREATIVE_TAB);

	public static final Block PAPER_PILE = build(new BlockPile(), "paper_pile");
	public static final Block PAPER_NOTES = build(new BlockPile(), "paper_notes");

	// Vanilla (wood) biome
	public static final Block OAK_RAILING = build(new BlockRailing(WOOD), "oak_railing");

	// Charred biome
	public static final Block CHARRED_PLANKS = build(WOOD, "charred_planks");
	public static final Block CHARRED_BOOKSHELF = build(new BlockLibrariaBookshelf(WOOD), "charred_bookshelf");
	public static final Block SMOLDERING_PLANKS = build(new BlockSmoldering(WOOD, BLOCK_DUST, 60, Block.getStateId(CHARRED_PLANKS.getDefaultState())), "smoldering_planks");
	public static final Block SMOLDERING_BOOKSHELF = build(new BlockSmoldering(WOOD, BLOCK_DUST, 60, Block.getStateId(CHARRED_PLANKS.getDefaultState())), "smoldering_bookshelf");
	public static final Block ASH_PILE = build(new BlockPile(), "ash_pile");

	// Wet biome

	public static final Block WET_PLANKS = build(WOOD, "wet_planks");
	public static final Block WET_BOOKSHELF = build(new BlockLibrariaBookshelf(WOOD), "wet_bookshelf");
	public static final Block SOAKED_PLANKS = build(new BlockParticleDripper(WOOD, EnumParticleTypes.DRIP_WATER, 40), "soaked_planks");
	public static final Block SOAKED_BOOKSHELF = build(new BlockParticleDripper(WOOD, EnumParticleTypes.DRIP_WATER, 40), "soaked_bookshelf");
	public static final Block PUDDLE = build(new BlockPileTranslucent(), "puddle");

	public static void init()
	{
		DimensionsObjects.registerBlockAndItem(MARMOR, MARMOR_TILE, MARMOR_TILE_STAIRS, MARMOR_PILLAR, MARMOR_BOOKSHELF, MARMOR_RAILING, MARMOR_LAMP, MARMOR_LAMP_BROKEN, MARMOR_SLAB);
		DimensionsObjects.registerItem(ITEM_MARMOR_DOOR);
		DimensionsObjects.registerBlock(MARMOR_SLAB_FULL, MARMOR_DOOR);

		DimensionsObjects.registerBlockAndItem(CHARRED_PLANKS, CHARRED_BOOKSHELF, SMOLDERING_PLANKS, SMOLDERING_BOOKSHELF);
		DimensionsObjects.registerBlockAndItem(WET_PLANKS, WET_BOOKSHELF, SOAKED_PLANKS, SOAKED_BOOKSHELF);
		DimensionsObjects.registerBlockAndItem(PAPER_PILE, PAPER_NOTES, ASH_PILE, PUDDLE);
		DimensionsObjects.registerBlockAndItem(OAK_RAILING);

		DimensionLibraria.register(200, "libraria");
	}

	private static Block build(Block starter, String name)
	{
		starter.setUnlocalizedName("randomdimensions." + name);
		starter.setRegistryName(Dimensions.MODID, name);
		starter.setCreativeTab(LIBRARIA_CREATIVE_TAB);
		return starter;
	}

	private static Block build(Material material, String name)
	{
		return build(new Block(material), name);
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
			return new ItemStack(MARMOR);
		}
	}
}
