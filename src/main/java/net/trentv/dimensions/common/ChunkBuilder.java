package net.trentv.dimensions.common;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.trentv.dimensions.Dimensions;

public class ChunkBuilder extends ChunkPrimer
{
	private Chunk chunk;

	public void fill(IBlockState state, int startY, int endY)
	{
		if (chunk != null)
		{
			Dimensions.logger.error("Trying to manipulate (fill) the primer after chunk is built!");
			return;
		}
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				for (int y = startY; y <= endY; y++)
				{
					setBlockState(x, y, z, state);
				}
			}
		}
	}

	public Chunk buildChunk(World world, int chunkX, int chunkY)
	{
		chunk = new Chunk(world, this, chunkX, chunkY);
		return chunk;
	}

	public void addChunkTemplate(World world, ResourceLocation path, BlockPos pos)
	{
		ChunkTemplate template = ChunkTemplate.getChunkTemplate(world, path);
		template.addAllToChunk(chunk, pos);
	}

	public void addRoom(World world, String path, int y)
	{
		addChunkTemplate(world, new ResourceLocation(Dimensions.MODID, path), new BlockPos(0, y, 0));
	}

	@Nullable
	public Chunk getChunk()
	{
		return chunk;
	}
}
