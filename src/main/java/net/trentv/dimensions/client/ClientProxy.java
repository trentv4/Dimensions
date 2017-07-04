package net.trentv.dimensions.client;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.trentv.dimensions.common.CommonProxy;

public class ClientProxy extends CommonProxy
{
	public static ArrayList<Block> toBlockModels = new ArrayList<Block>();
	public static ArrayList<Item> toItemModels = new ArrayList<Item>();
	
	@Override
	public void registerRenderers()
	{
		for(Block block : toBlockModels)
		{
			setBlockModel(block);
		}
		for(Item item : toItemModels)
		{
			setItemModel(item);
		}
	}

	private void setBlockModel(Block obj)
	{
		ModelLoader.setCustomModelResourceLocation(ItemBlock.REGISTRY.getObject(obj.getRegistryName()), 0, new ModelResourceLocation((obj.getRegistryName()), "inventory"));
	}

	private void setItemModel(Item in)
	{
		ModelLoader.setCustomModelResourceLocation(in, 0, new ModelResourceLocation(in.getRegistryName(), "inventory"));
	}

	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
	}
}
