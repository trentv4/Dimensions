package net.trentv.dimensions.common.libraria.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;

public class BlockSmoldering extends BlockParticleDripper
{
	public BlockSmoldering(Material materialIn, EnumParticleTypes particle, int randomDelay, int... particleParams)
	{
		super(materialIn, particle, randomDelay, particleParams);
		this.setLightLevel(1 / 15);
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return MinecraftForgeClient.getRenderLayer() == BlockRenderLayer.CUTOUT ? 1 : 0;
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
	{
		return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.CUTOUT;
	}

	@Override
	public int getPackedLightmapCoords(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		if(MinecraftForgeClient.getRenderLayer() == BlockRenderLayer.CUTOUT)
		{
			// Just return 255? I dunno, don't see what this accomplishes in particular.
			int result = source.getCombinedLight(pos, 1);
			int skylight = (result >> 16) & 0xFFFF;
			return (skylight << 16) | (15 << 4);
		}
		else
		{
			return super.getPackedLightmapCoords(state, source, pos);
		}
	}
}
