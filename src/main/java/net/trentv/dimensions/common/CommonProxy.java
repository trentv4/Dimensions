package net.trentv.dimensions.common;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public abstract class CommonProxy
{
	public abstract void registerRenderers();

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

		@SubscribeEvent
		public void registerModels(ModelRegistryEvent event)
		{
			for (Item item : DimensionsObjects.items)
			{
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
			}
			for (Block block : DimensionsObjects.blocks)
			{
				ModelLoader.setCustomModelResourceLocation(ItemBlock.REGISTRY.getObject(block.getRegistryName()), 0, new ModelResourceLocation((block.getRegistryName()), "inventory"));
			}
		}
	}
}
