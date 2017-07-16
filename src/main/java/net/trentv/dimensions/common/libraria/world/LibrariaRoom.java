package net.trentv.dimensions.common.libraria.world;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.Template.BlockInfo;

public class LibrariaRoom extends LibrariaZoneChild
{
	private Template template;
	private final ResourceLocation ROOM_ID;
	private final int ROOM_SIZE;

	public int size;

	public LibrariaRoom(ResourceLocation roomID, int size, LibrariaZone zone)
	{
		this.ROOM_ID = roomID;
		this.ROOM_SIZE = size;
		template = ChunkGeneratorLibraria.manager.getTemplate(zone.world.getMinecraftServer(), roomID);
	}

	public void build(ChunkPrimer primer, int x, int y, int z)
	{
		for (BlockInfo info : template.blocks)
		{
			BlockPos pos = info.pos.add(x, y, z);
			primer.setBlockState(pos.getX(), pos.getY(), pos.getZ(), info.blockState);
		}
	}
}
