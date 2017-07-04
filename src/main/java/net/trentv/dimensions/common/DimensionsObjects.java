package net.trentv.dimensions.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.trentv.dimensions.client.ClientProxy;
import net.trentv.dimensions.common.libraria.LibrariaObjects;

public class DimensionsObjects
{
	public static void init()
	{
		LibrariaObjects.init();
	}

	public static void registerBlock(Block... toRegister)
	{
		for (Block in : toRegister)
		{
			ItemBlock a = new ItemBlock(in);
			a.setRegistryName(in.getRegistryName());
			ForgeRegistries.BLOCKS.register(in);
			ForgeRegistries.ITEMS.register(a);
			ClientProxy.toBlockModels.add(in);
		}
	}

	public static void registerItem(Item... toRegister)
	{
		for (Item in : toRegister)
		{
			ForgeRegistries.ITEMS.register(in);
			ClientProxy.toItemModels.add(in);
		}
	}
}
