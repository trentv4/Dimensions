package net.trentv.dimensions.common.libraria.world;

import static net.trentv.dimensions.common.libraria.DimensionLibraria.BIOMES;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.trentv.dimensions.Dimensions;
import net.trentv.dimensions.common.libraria.world.LibrariaZoneChild.LibrariaZoneChildList;

public class LibrariaZone
{
	// https://pastebin.com/2Tb0Uv9R
	// https://libraryofbabel.info/libraryofbabel.html
	// https://libraryofbabel.info/
	// https://bitbucket.org/jamieswhiteshirt/libraria/src/acf8f5667994/src/main/kotlin/com/jamieswhiteshirt/libraria/common/world/?at=master

	public final LibrariaZonePos pos;
	public final World world;

	private LibrariaZoneChildList children = new LibrariaZoneChildList(8);
	private Random r;

	public final Biome biome;

	private static final int MINIMUM_SIZE = 8;

	public LibrariaZone(LibrariaZonePos pos, World world)
	{
		this.pos = pos;
		this.world = world;
		r = new Random();
		r.setSeed(world.getSeed() + seedify(pos.x + "," + pos.z));
		this.biome = BIOMES[r.nextInt(BIOMES.length)];
		generate(256, children);
	}

	private void generate(int roomSize, LibrariaZoneChildList children)
	{
		if (roomSize >= MINIMUM_SIZE)
		{
			for (int i = 0; i < children.list.length; i++)
			{
				if (r.nextInt(8) == 0)
				{
					children.set(i, new LibrariaRoom(new ResourceLocation(Dimensions.MODID, "marmor_16_0"), 16, this));
				}
				else
				{
					LibrariaZoneChildList newChildren = new LibrariaZoneChildList(8);
					children.set(i, newChildren);
					generate(roomSize / 2, newChildren);
				}
			}
		}
	}

	public LibrariaRoom[] getRooms(int chunkX, int chunkZ)
	{
		int actualX = chunkX % 16;
		int actualZ = chunkZ % 16;

		return new LibrariaRoom[] { new LibrariaRoom(new ResourceLocation(Dimensions.MODID, "marmor_16_0"), 16, this) };
	}

	private static final long seedify(String s)
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
