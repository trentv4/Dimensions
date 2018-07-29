package net.trentv.dimensions.common.libraria.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPile extends Block
{
	private static final AxisAlignedBB BLOCK_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0 / 16.0, 1.0);

	public BlockPile()
	{
		super(Material.CIRCUITS);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockstate, IBlockAccess world, BlockPos pos)
	{
		return NULL_AABB;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState blockstate, IBlockAccess world, BlockPos pos)
	{
		return BLOCK_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		BlockPos posDown = pos.down();
		return world.getBlockState(posDown).isSideSolid(world, posDown, EnumFacing.UP);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
	{
		if (!canPlaceBlockAt(world, pos))
		{
			world.setBlockToAir(pos);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	public static class BlockPileTranslucent extends BlockPile
	{
		@Override
		@SideOnly(Side.CLIENT)
		public BlockRenderLayer getBlockLayer()
		{
			return BlockRenderLayer.TRANSLUCENT;
		}
	}
}
