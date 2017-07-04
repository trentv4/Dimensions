package net.trentv.dimensions.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.trentv.dimensions.common.CommonProxy;
import net.trentv.dimensions.common.DimensionsObjects;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}

	public static class ClientEvents
	{
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
