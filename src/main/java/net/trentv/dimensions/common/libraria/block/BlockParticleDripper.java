package net.trentv.dimensions.common.libraria.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumFacing.AxisDirection;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockParticleDripper extends Block
{
	public final int randomDelay;
	public final EnumParticleTypes particle;
	public final int[] particleParams;

	public BlockParticleDripper(Material materialIn, EnumParticleTypes particle, int randomDelay, int... particleParams)
	{
		super(materialIn);
		this.randomDelay = randomDelay;
		this.particle = particle;
		this.particleParams = particleParams;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random)
	{
		if (random.nextInt(randomDelay) == 0)
		{
			return;
		}
		EnumFacing facing = EnumFacing.getFront(random.nextInt(6));
		if (facing == EnumFacing.UP)
			facing = EnumFacing.DOWN;

		Material nearbyMaterial = world.getBlockState(pos.offset(facing.getOpposite())).getMaterial();
		if (nearbyMaterial.blocksMovement() || nearbyMaterial.isLiquid())
		{
			return;
		}

		Vec3d newPos = new Vec3d(pos).add(new Vec3d(0.05, -0.1, 0.0));
		Vec3d faceVec = new Vec3d(facing.getDirectionVec());

		for (Axis axis : EnumFacing.Axis.values())
		{
			Vec3d unit = new Vec3d(EnumFacing.getFacingFromAxis(AxisDirection.POSITIVE, axis).getDirectionVec());
			if (axis != facing.getAxis())
			{
				newPos = newPos.add(unit.scale(random.nextDouble()));
			}
			else
			{
				if (facing.getAxisDirection() == AxisDirection.POSITIVE)
				{
					newPos = newPos.add(unit.scale(1.15));
				}
				else
				{
					newPos = newPos.add(unit.scale(-0.15));
				}
			}
		}

		world.spawnParticle(particle, newPos.x, newPos.y, newPos.z, 0, 0, 0, particleParams);
	}
}
