package net.trentv.dimensions.common.libraria.world;

public class LibrariaZonePos
{
	public final int x;
	public final int z;

	public LibrariaZonePos(int x, int z)
	{
		this.x = x;
		this.z = z;
	}

	public static final LibrariaZonePos fromChunk(int x, int z)
	{
		return new LibrariaZonePos(x >> 4, z >> 4);
	}
}
