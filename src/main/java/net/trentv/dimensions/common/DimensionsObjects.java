package net.trentv.dimensions.common;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.trentv.dimensions.common.libraria.LibrariaObjects;

public class DimensionsObjects
{
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Item> items = new ArrayList<Item>();

	public static void init()
	{
		LibrariaObjects.init();
	}

	public static void registerBlock(Block... toRegister)
	{
		for (Block in : toRegister)
		{
			blocks.add(in);
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
