package net.trentv.dimensions.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.biome.Biome;
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
		public void registerBiomes(RegistryEvent.Register<Biome> event)
		{
			for (Biome biome : DimensionsObjects.biomes)
			{
				event.getRegistry().register(biome);
			}
		}

		@SubscribeEvent
		public void registerItems(RegistryEvent.Register<Item> event)
		{
			for (Item item : DimensionsObjects.items)
			{
				event.getRegistry().register(item);
			}
			for (ItemBlock itemBlock : DimensionsObjects.itemBlocks)
			{
				event.getRegistry().register(itemBlock);
			}
		}
	}
}
