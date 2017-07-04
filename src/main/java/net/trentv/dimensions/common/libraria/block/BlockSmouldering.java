package net.trentv.dimensions.common.libraria.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSmouldering extends BlockParticleDripper
{
	public BlockSmouldering(Material materialIn, EnumParticleTypes particle, int randomDelay)
	{
		super(materialIn, particle, randomDelay);
	}

	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
	{

	}
}
