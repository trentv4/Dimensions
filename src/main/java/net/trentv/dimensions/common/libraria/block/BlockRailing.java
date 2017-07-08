package net.trentv.dimensions.common.libraria.block;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRailing extends BlockHorizontal
{
	private AxisAlignedBB[] AABBs = new AxisAlignedBB[] { new AxisAlignedBB(0.0, 0.0, 0.75, 1.0, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 0.25, 1.0, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.25), new AxisAlignedBB(0.75, 0.0, 0.0, 1.0, 1.0, 1.0), };

	public BlockRailing(Material materialIn)
	{
		super(materialIn);
		setDefaultState(getBlockState().getBaseState().withProperty(FACING, EnumFacing.SOUTH));
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		EnumFacing placement = EnumFacing.getFacingFromVector(hitX - 0.5F - facing.getDirectionVec().getX(), 0.0F, hitZ - 0.5F - facing.getDirectionVec().getZ());
		if (placement.getAxis() != EnumFacing.Axis.Y)
		{
			return getDefaultState().withProperty(FACING, placement);
		}
		return getDefaultState();
	}

	@Override
	public BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABBs[state.getValue(FACING).getHorizontalIndex()];
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getHorizontalIndex();
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
	public boolean isPassable(IBlockAccess world, BlockPos pos)
	{
		return false;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rotation)
	{
		return state.withProperty(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirror)
	{
		return state.withProperty(FACING, mirror.mirror(state.getValue(FACING)));
	}
}
