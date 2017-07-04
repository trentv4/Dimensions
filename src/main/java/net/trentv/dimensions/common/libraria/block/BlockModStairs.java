package net.trentv.dimensions.common.libraria.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockModStairs extends BlockStairs
{
	// BlockStairs's constructor is protected - this just exposes it.
	public BlockModStairs(IBlockState modelState)
	{
		super(modelState);
		this.useNeighborBrightness = true;
	}
}
