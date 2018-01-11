package net.trentv.dimensions.common;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class AdvancedChunkPrimer extends ChunkPrimer
{
	private ArrayList<NBTTagCompound> entityData = new ArrayList<NBTTagCompound>();

	public void addEntityData(NBTTagCompound a)
	{
		entityData.add(a);
	}

	public Chunk toChunk(World world, int x, int z)
	{
		Chunk a = new Chunk(world, x, z);
		int i = 256;
		boolean flag = world.provider.hasSkyLight();

		for (int j = 0; j < 16; ++j)
		{
			for (int k = 0; k < 16; ++k)
			{
				for (int l = 0; l < 256; ++l)
				{
					IBlockState iblockstate = getBlockState(j, l, k);

					if (iblockstate.getMaterial() != Material.AIR)
					{
						int i1 = l >> 4;

						if (a.storageArrays[i1] == a.NULL_BLOCK_STORAGE)
						{
							a.storageArrays[i1] = new ExtendedBlockStorage(i1 << 4, flag);
						}

						a.storageArrays[i1].set(j, l & 15, k, iblockstate);
					}
				}
			}
		}

		return a;
	}
}
