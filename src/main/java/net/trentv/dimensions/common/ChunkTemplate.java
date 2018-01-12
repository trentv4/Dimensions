package net.trentv.dimensions.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.structure.template.Template;

public class ChunkTemplate extends Template
{
	private final Template TEMPLATE;
	private World world;

	public ChunkTemplate(World world, ResourceLocation file)
	{
		this.world = world;
		this.TEMPLATE = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), file);
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
