package net.trentv.dimensions.common.libraria.block;

import java.util.Random;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.dimensions.common.libraria.LibrariaObjects;

public abstract class BlockMarmorSlab extends BlockSlab
{
	public static final PropertyEnum<BlockMarmorSlab.Variant> VARIANT = PropertyEnum.<BlockMarmorSlab.Variant>create("variant", BlockMarmorSlab.Variant.class);

	public BlockMarmorSlab()
	{
		super(Material.ROCK, MapColor.WHITE_STAINED_HARDENED_CLAY);
		IBlockState iblockstate = this.blockState.getBaseState();

		if (!this.isDouble())
		{
			iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
		}

		this.setDefaultState(iblockstate.withProperty(VARIANT, BlockMarmorSlab.Variant.DEFAULT));
		this.setSoundType(SoundType.STONE);
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(LibrariaObjects.MARMOR_SLAB);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(LibrariaObjects.MARMOR_SLAB);
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockMarmorSlab.Variant.DEFAULT);

		if (!this.isDouble())
		{
			iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
		}

		return iblockstate;
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;

		if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
		{
			i |= 8;
		}

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return this.isDouble() ? new BlockStateContainer(this, new IProperty[] { VARIANT }) : new BlockStateContainer(this, new IProperty[] { HALF, VARIANT });
	}

	/**
	 * Returns the slab block name with the type associated with it
	 */
	@Override
	public String getUnlocalizedName(int meta)
	{
		return super.getUnlocalizedName();
	}

	@Override
	public IProperty<?> getVariantProperty()
	{
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack)
	{
		return BlockMarmorSlab.Variant.DEFAULT;
	}

	public static class Double extends BlockMarmorSlab
	{
		@Override
		public boolean isDouble()
		{
			return true;
		}
	}

	public static class Half extends BlockMarmorSlab
	{
		@Override
		public boolean isDouble()
		{
			return false;
		}
	}

	public static enum Variant implements IStringSerializable
	{
		DEFAULT;

		@Override
		public String getName()
		{
			return "default";
		}
	}
}