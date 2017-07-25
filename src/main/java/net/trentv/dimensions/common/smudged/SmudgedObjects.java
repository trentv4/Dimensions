package net.trentv.dimensions.common.smudged;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class SmudgedObjects
{
	public static final DimensionsSmudgedCityCreativeTab SMUDGED_CITY_CREATIVE_TAB = new DimensionsSmudgedCityCreativeTab("randomdimensions.smudged_city");

	public static void init()
	{
		DimensionSmudgedCity.register(300, "smudged_city");
	}

	private static class DimensionsSmudgedCityCreativeTab extends CreativeTabs
	{
		public DimensionsSmudgedCityCreativeTab(String label)
		{
			super(label);
		}

		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Blocks.STONE);
		}
	}
}
