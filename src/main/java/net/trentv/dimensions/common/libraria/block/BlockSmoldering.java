package net.trentv.dimensions.common.libraria.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumParticleTypes;

public class BlockSmoldering extends BlockParticleDripper
{
	public BlockSmoldering(Material materialIn, EnumParticleTypes particle, int randomDelay, int... particleParams)
	{
		super(materialIn, particle, randomDelay, particleParams);
	}
}
