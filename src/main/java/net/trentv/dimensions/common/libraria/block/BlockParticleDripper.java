package net.trentv.dimensions.common.libraria.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockParticleDripper extends Block
{
	private int randomDelay;
	private EnumParticleTypes particle;

	public BlockParticleDripper(Material materialIn, EnumParticleTypes particle, int randomDelay)
	{
		super(materialIn);
		this.randomDelay = randomDelay;
		this.particle = particle;
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

		if (faceVec.y == -1)
		{
			newPos = newPos.add(new Vec3d(random.nextDouble(), 0, random.nextDouble()));
		}
		else
		{
			double newX = 0;
			double newZ = 0;
			if (faceVec.x == -1)
			{
				newX++;
				newZ = random.nextDouble();
			}
			else if (faceVec.x == 1)
			{
				newZ = random.nextDouble();
			}
			if (faceVec.z == -1)
			{
				newZ++;
				newX = random.nextDouble();
			}
			else if (faceVec.z == 1)
			{
				newX = random.nextDouble();
			}
			newPos = newPos.add(new Vec3d(newX, random.nextDouble(), newZ).scale(1.1));
		}
		// Probably a cleaner way to do this. Almost certainly is. I don't care.
		world.spawnParticle(particle, newPos.x, newPos.y, newPos.z, 0, 0, 0);
	}
}
