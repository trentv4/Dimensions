package net.trentv.dimensions.common;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.Template.BlockInfo;
import net.minecraft.world.gen.structure.template.Template.EntityInfo;

public class ChunkTemplate
{
	private final static HashMap<ResourceLocation, ChunkTemplate> CACHE = new HashMap<ResourceLocation, ChunkTemplate>();
	private final Template TEMPLATE;
	private World world;

	private ChunkTemplate(World world, ResourceLocation file)
	{
		this.world = world;
		this.TEMPLATE = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), file);
	}

	public static ChunkTemplate getChunkTemplate(World world, ResourceLocation file)
	{
		if (CACHE.containsKey(file))
		{
			return CACHE.get(file);
		}
		ChunkTemplate temp = new ChunkTemplate(world, file);
		CACHE.put(file, temp);
		return temp;
	}

	public void addBlocksToChunk(Chunk in, BlockPos offset, int rotationCount)
	{
		for (BlockInfo block : TEMPLATE.blocks)
		{
			in.setBlockState(rotate(rotationCount, block.pos.add(offset)), block.blockState);
		}
	}

	public void addEntitiesToChunk(Chunk in, BlockPos offset, int rotationCount)
	{
		for (EntityInfo ent : TEMPLATE.entities)
		{
			Entity t = EntityList.createEntityFromNBT(ent.entityData, world);
			BlockPos position = rotate(rotationCount, t.getPosition());
			t.posX = offset.getX() + position.getX();
			t.posY = offset.getY() + position.getY();
			t.posZ = offset.getZ() + position.getZ();
			in.addEntity(t);
		}
	}

	public void addAllToChunk(Chunk in, BlockPos offset, int rotationCount)
	{
		addBlocksToChunk(in, offset, rotationCount);
		addEntitiesToChunk(in, offset, rotationCount);
	}

	public void addAllToChunk(Chunk in, BlockPos offset)
	{
		addBlocksToChunk(in, offset, 0);
		addEntitiesToChunk(in, offset, 0);
	}

	private BlockPos rotate(int count, BlockPos original)
	{
		if (count > 0)
		{
			BlockPos newPos = new BlockPos(original.getZ(), original.getY(), original.getX());
			return rotate(count - 1, newPos);
		}
		else
		{
			return original;
		}
	}
}
