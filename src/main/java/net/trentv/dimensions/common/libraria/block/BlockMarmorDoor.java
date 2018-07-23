package net.trentv.dimensions.common.libraria.block;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.trentv.dimensions.common.libraria.LibrariaObjects;

public class BlockMarmorDoor extends BlockDoor
{
	public BlockMarmorDoor(Material materialIn)
	{
		super(materialIn);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(LibrariaObjects.MARMOR_HIDDEN_DOOR);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return MapColor.WHITE_STAINED_HARDENED_CLAY;
	}
}
