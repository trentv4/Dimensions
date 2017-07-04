package net.trentv.dimensions.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public abstract class CommonProxy
{
	public void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
	}

	public static class CommonEvents
	{
		@SubscribeEvent
		public void registerBlocks(RegistryEvent.Register<Block> event)
		{
			for (Block block : DimensionsObjects.blocks)
			{
				event.getRegistry().register(block);
			}
		}

		@SubscribeEvent
		public void registerItems(RegistryEvent.Register<Item> event)
		{
			for (Item item : DimensionsObjects.items)
			{
				event.getRegistry().register(item);
			}
			for (Block block : DimensionsObjects.blocks)
			{
				ItemBlock a = new ItemBlock(block);
				a.setRegistryName(block.getRegistryName());
				event.getRegistry().register(a);
			}
		}
	}
}
