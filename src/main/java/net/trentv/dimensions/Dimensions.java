package net.trentv.dimensions;

import org.apache.logging.log4j.Logger;

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
	public static final String MODID = "randomdimensions";
	public static final String VERSION = "1.0.0";

	public static Logger logger;

	@SidedProxy(clientSide = "net.trentv.dimensions.client.ClientProxy", serverSide = "net.trentv.dimensions.server.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();

		DimensionsObjects.init();

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
}
