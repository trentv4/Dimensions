package net.trentv.dimensions.common;

import java.util.HashMap;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.Template.BlockInfo;
import net.minecraft.world.gen.structure.template.Template.EntityInfo;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class PrimerTemplate
{
	private HashMap<ResourceLocation, Template> preloadedTemplates = new HashMap<ResourceLocation, Template>();
	private static TemplateManager templateManager;

	private BlockInfo[] blocks;
	private EntityInfo[] entities;

	public PrimerTemplate(ResourceLocation template, World world)
	{
		if (templateManager == null)
		{
			templateManager = world.getSaveHandler().getStructureTemplateManager();
		}
		Template loadedTemplate;
		if (preloadedTemplates.containsKey(template))
		{
			loadedTemplate = preloadedTemplates.get(template);
		}
		else
		{
			loadedTemplate = templateManager.getTemplate(world.getMinecraftServer(), template);
			preloadedTemplates.put(template, loadedTemplate);
		}
		blocks = loadedTemplate.blocks.toArray(new BlockInfo[loadedTemplate.blocks.size()]);
		entities = loadedTemplate.entities.toArray(new EntityInfo[loadedTemplate.entities.size()]);
	}

	public void put(AdvancedChunkPrimer p, BlockPos offset)
	{
		for (BlockInfo i : blocks)
		{
			BlockPos pos = i.pos.add(offset);
			p.setBlockState(pos.getX(), pos.getY(), pos.getZ(), i.blockState);
		}
		for (EntityInfo i : entities)
		{
			BlockPos pos = i.blockPos.add(offset);
			p.addEntityData(i.entityData);
		}
	}
}
