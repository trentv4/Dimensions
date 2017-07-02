package net.trentv.dimensions.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.trentv.dimensions.common.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{

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
