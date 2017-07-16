package net.trentv.dimensions.common.libraria.world;

public abstract class LibrariaZoneChild
{
	public static class LibrariaZoneChildList extends LibrariaZoneChild
	{
		public LibrariaZoneChild[] list;

		public LibrariaZoneChildList(LibrariaZoneChild[] list)
		{
			this.list = list;
		}

		public LibrariaZoneChildList(int size)
		{
			this.list = new LibrariaZoneChild[size];
		}

		public void set(int index, LibrariaZoneChild child)
		{
			list[index] = child;
		}
	}
}
