package net.trentv.dimensions.common.libraria.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.dimensions.client.GuiScreenBookOutput;

public class BlockMarmorBookshelf extends Block
{
	private char[] alphabet = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ',', ' ', '.' };

	public BlockMarmorBookshelf(Material material)
	{
		super(material);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 3;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.BOOK;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
		{
			Random random = new Random();
			random.setSeed(seedify(pos.getX() + "," + pos.getY() + "," + pos.getZ()));
			String s = "";
			for (int i = 0; i < 310; i++)
			{
				s += alphabet[random.nextInt(alphabet.length)];
			}
			Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBookOutput(s));
		}
		return false;
	}

	private static long seedify(String s)
	{
		if (s != null)
		{
			long hash = 0;
			for (char c : s.toCharArray())
			{
				hash = 31L * hash + c;
			}
			return hash;
		}
		return 0;
	}
}
