package net.trentv.dimensions;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.trentv.dimensions.common.CommonProxy;
import net.trentv.dimensions.common.DimensionsObjects;

@Mod(modid = Dimensions.MODID, version = Dimensions.VERSION, acceptedMinecraftVersions = "1.12")
public class Dimensions
{
	public static final String MODID = "dimensions";
	public static final String VERSION = "1.0.0";

	public static final DimensionsCreativeTab CREATIVE_TAB = new DimensionsCreativeTab("dimensions");

	public static Logger logger;

	@SidedProxy(clientSide = "net.trentv.dimensions.client.ClientProxy", serverSide = "net.trentv.dimensions.server.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();

		DimensionsObjects.init();

		proxy.registerRenderers();
		proxy.registerEventHandlers();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		logger.info("Dimensions initialized");
	}

	private static class DimensionsCreativeTab extends CreativeTabs
	{
		public DimensionsCreativeTab(String label)
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
