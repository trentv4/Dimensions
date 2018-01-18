package net.trentv.dimensions.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.trentv.dimensions.client.GuiScreenBookOutput;
import net.trentv.dimensions.common.libraria.LibrariaObjects;
import net.trentv.dimensions.common.libraria.block.BlockLibrariaBookshelf;

public abstract class CommonProxy
{
	public void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
	}

	public static class CommonEvents
	{
		private char[] alphabet = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ',', ' ', '.' };

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

		@SubscribeEvent
		public void onRightClick(RightClickBlock event)
		{
			BlockPos pos = event.getPos();
			Block block = event.getWorld().getBlockState(pos).getBlock();
			// This needs work.
			if (block instanceof BlockLibrariaBookshelf | block == LibrariaObjects.SMOLDERING_BOOKSHELF | block == LibrariaObjects.SOAKED_BOOKSHELF)
			{
				if (!event.getEntityPlayer().isSneaking())
				{
					Random random = new Random();

					random.setSeed(pos.hashCode());
					String s = "";
					for (int i = 0; i < 300; i++)
					{
						s += alphabet[random.nextInt(alphabet.length)];
					}
					if (event.getWorld().isRemote)
					{
						Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBookOutput(s));
					}
					event.setCanceled(true);
				}
			}
		}
	}
}
