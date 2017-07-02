package net.trentv.dimensions.common;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class DimensionsObjects
{
	public static void init()
	{
		
	}

	private static void registerBlock(Block in)
	{
		ItemBlock a = new ItemBlock(in);
		a.setRegistryName(in.getRegistryName());
		ForgeRegistries.BLOCKS.register(in);
		ForgeRegistries.ITEMS.register(a);
	}
}
