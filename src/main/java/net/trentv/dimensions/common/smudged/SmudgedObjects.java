package net.trentv.dimensions.common.smudged;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class SmudgedObjects
{
	public static void init()
	{
		DimensionSmudgedCity.register(300, "smudged_city");
	}

	private static class DimensionsSmudgedCreativeTab extends CreativeTabs
	{
		public DimensionsSmudgedCreativeTab(String label)
		{
			super(label);
		}

		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Blocks.BEACON);
		}
	}
}
