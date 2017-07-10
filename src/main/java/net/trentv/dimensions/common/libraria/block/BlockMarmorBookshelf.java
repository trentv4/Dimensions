package net.trentv.dimensions.common.libraria.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockMarmorBookshelf extends Block
{
	public BlockMarmorBookshelf(Material material)
	{
		super(material);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 3;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.BOOK;
	}
}
