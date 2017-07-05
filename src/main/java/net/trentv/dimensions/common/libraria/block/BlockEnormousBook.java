package net.trentv.dimensions.common.libraria.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.trentv.dimensions.common.libraria.DimensionLibraria;
import net.trentv.dimensions.common.libraria.DimensionLibraria.TeleporterLibraria;
import net.trentv.dimensions.common.libraria.LibrariaObjects;

public class BlockEnormousBook extends BlockHorizontal
{
	public BlockEnormousBook(Material material)
	{
		super(material);
		this.blockHardness = 1;
	}

	public static enum EnumHorizontalRelativeFacing implements IStringSerializable
	{
		LEFT("left", 1, -1), RIGHT("right", 0, 1);

		public String name;
		public int oppositeOrdinal;
		public int rotation;

		EnumHorizontalRelativeFacing(String name, int oppositeOrdinal, int rotation)
		{
			this.name = name;
			this.oppositeOrdinal = oppositeOrdinal;
			this.rotation = rotation;
		}

		public EnumFacing map(EnumFacing facing)
		{
			if (facing.getHorizontalIndex() != -1)
			{
				return EnumFacing.getHorizontal((facing.getHorizontalIndex() + rotation) & 3);
			}
			else
			{
				return facing;
			}
		}

		public EnumFacing unmap(EnumFacing facing)
		{
			if (facing.getHorizontalIndex() != -1)
			{
				return EnumFacing.getHorizontal((facing.getHorizontalIndex() - rotation) & 3);
			}
			else
			{
				return facing;
			}
		}

		public EnumHorizontalRelativeFacing getOpposite()
		{
			return getFacing(oppositeOrdinal);
		}

		public static EnumHorizontalRelativeFacing getFacing(int ordinal)
		{
			return values()[ordinal];
		}

		@Override
		public String getName()
		{
			return name;
		}
	}

	public static class BlockEnormousBookClosed extends BlockEnormousBook
	{
		public BlockEnormousBookClosed(Material material)
		{
			super(material);
		}

		@Override
		public BlockStateContainer createBlockState()
		{
			return new BlockStateContainer(this, FACING);
		}

		@Override
		public int getMetaFromState(IBlockState state)
		{
			return state.getValue(FACING).getHorizontalIndex();
		}

		@Override
		public IBlockState getStateFromMeta(int meta)
		{
			return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
		}

		@Override
		public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
		{
			return getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
		}

		@Override
		public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
		{
			EnumFacing face = state.getValue(FACING);
			BlockPos leftPos = pos.offset(EnumHorizontalRelativeFacing.LEFT.map(face));
			if (world.getBlockState(leftPos).getBlock().isReplaceable(world, leftPos))
			{
				IBlockState baseState = LibrariaObjects.ENORMOUS_BOOK_OPEN.getDefaultState().withProperty(FACING, face);
				world.setBlockState(pos, baseState.withProperty(BlockEnormousBookOpen.SIDE, EnumHorizontalRelativeFacing.RIGHT));
				world.setBlockState(leftPos, baseState.withProperty(BlockEnormousBookOpen.SIDE, EnumHorizontalRelativeFacing.LEFT));
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	public static class BlockEnormousBookOpen extends BlockEnormousBook
	{
		private AxisAlignedBB AABB_HALF = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);
		public static final PropertyEnum<EnumHorizontalRelativeFacing> SIDE = PropertyEnum.create("side", EnumHorizontalRelativeFacing.class);

		public BlockEnormousBookOpen(Material material)
		{
			super(material);
		}

		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
		{
			return AABB_HALF;
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
		public BlockStateContainer createBlockState()
		{
			return new BlockStateContainer(this, BlockHorizontal.FACING, SIDE);
		}

		@Override
		public IBlockState getStateFromMeta(int meta)
		{
			return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3)).withProperty(SIDE, EnumHorizontalRelativeFacing.getFacing((meta << 2) & 1));
		}

		@Override
		public int getMetaFromState(IBlockState state)
		{
			return state.getValue(FACING).getHorizontalIndex() | state.getValue(SIDE).ordinal() << 2;
		}

		@Override
		public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
		{
			return tryClose(world, pos, state);
		}

		@Override
		public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
		{
			if (!world.isRemote)
			{
				checkAndDropBlock(world, pos, state);
			}
		}

		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune)
		{
			return Item.getItemFromBlock(LibrariaObjects.ENORMOUS_BOOK_CLOSED);
		}

		@Override
		public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
		{
			removeSafely(world, pos, state);
			return true;
		}

		public void removeSafely(World world, BlockPos pos, IBlockState state)
		{
			EnumFacing facing = state.getValue(FACING);
			EnumHorizontalRelativeFacing side = state.getValue(SIDE);
			BlockPos otherPos = pos.offset(side.map(facing).getOpposite());
			IBlockState otherState = world.getBlockState(otherPos);

			if (otherState == state.withProperty(SIDE, side.getOpposite()))
			{
				world.setBlockToAir(otherPos);
				world.setBlockToAir(pos);
			}
			else
			{
				world.setBlockToAir(pos);
			}
		}

		public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
		{
			EnumFacing facing = state.getValue(FACING);
			EnumHorizontalRelativeFacing side = state.getValue(SIDE);
			BlockPos otherPos = pos.offset(side.map(facing).getOpposite());
			return world.getBlockState(otherPos) == state.withProperty(SIDE, side.getOpposite());
		}

		public boolean tryClose(World world, BlockPos pos, IBlockState state)
		{
			EnumFacing facing = state.getValue(FACING);
			EnumHorizontalRelativeFacing side = state.getValue(SIDE);
			EnumFacing otherFacing = side.getOpposite().map(facing);
			BlockPos otherPos = pos.offset(otherFacing);
			IBlockState otherState = world.getBlockState(otherPos);
			
			if(otherState == state.withProperty(SIDE, side.getOpposite()))
			{
				IBlockState closedState = LibrariaObjects.ENORMOUS_BOOK_CLOSED.getDefaultState().withProperty(FACING, facing);
				if(side == EnumHorizontalRelativeFacing.LEFT)
				{
					world.setBlockState(pos, Blocks.AIR.getDefaultState());
					world.setBlockState(otherPos, closedState);
				}
				else if (side == EnumHorizontalRelativeFacing.RIGHT)
				{
					world.setBlockState(pos, Blocks.AIR.getDefaultState());
					world.setBlockState(otherPos, closedState.withProperty(FACING, facing.getOpposite()));
				}
				if(world instanceof WorldServer)
				{
					PlayerList playerList = world.getMinecraftServer().getPlayerList();
					if(playerList != null)
					{
						TeleporterLibraria teleporter = new TeleporterLibraria((WorldServer) world);
						AxisAlignedBB boundingBox = new AxisAlignedBB(pos, otherPos).expand(0, 0.5, 0).offset(0, 1.0, 0);
						
						world.getEntitiesInAABBexcluding(null, boundingBox, EntitySelectors.IS_ALIVE).forEach(it -> {
							it.setPortal(pos);
							if (it instanceof EntityPlayerMP)
							{
								playerList.transferPlayerToDimension((EntityPlayerMP) it, DimensionLibraria.dimensionID, teleporter);
							}
						});
					}
				}
			}

			return false;
		}

		public void checkAndDropBlock(World world, BlockPos pos, IBlockState state)
		{
			if(!canBlockStay(world, pos, state))
			{
				// dropBlockAsItem(world, pos, state, 0);
				removeSafely(world, pos, state);
			}
		}
	}
}
