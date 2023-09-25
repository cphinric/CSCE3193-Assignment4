//Parker Hinrichs (02.24.2023) Assignment 4: Link Sprite

import java.util.ArrayList;

class Model
{
	int mouse_x;
	int mouse_y;
	Link link;
	static boolean editMode = false;
	static ArrayList<Tile> tiles;

	//Constructor
	Model()
	{
		tiles = new ArrayList<Tile>();
		link = new Link(0, 0);
	}

	//Unmarshaling constructor
	public void unmarshal(Json ob)
	{
		tiles = new ArrayList<Tile>();
		Json tmpList = ob.get ("tiles");
		for(int i = 0; i < tmpList.size(); i++)
		{
			tiles.add(new Tile(tmpList.get(i)));
		}
	}

	//Marshal Constructor
	Json marshal()
	{
		Json ob = Json.newObject();
		Json tmpList = Json.newList();
		ob.add("tiles", tmpList);
		for(int i = 0; i < tiles.size(); i++)
		{
			tmpList.add(tiles.get(i).marshal());
		}
		return ob;
	}

	public void update()
	{
		for(int i = 0; i < tiles.size(); i++)
		{
			tiles.get(i).update();
			if (isCollision(tiles.get(i)))
			{
				link.getOutOfSprite(tiles.get(i));
			}
		}
	}

	boolean isCollision(Tile t)
	{
		if((link.x + link.w) <= t.x)
		{
			return false;
		}
		if(link.x >= (t.x + t.w))
		{
			return false;
		}
		if((link.y + link.h) <= t.y)
		{
			return false;
		}
		if(link.y >= (t.y + t.h))
		{
			return false;
		}
		return true;
		
	}

	public void setPosition(int x, int y)
	{
		mouse_x = x - ((x % 50 + 50) % 50);
		mouse_y = y - ((y % 50 + 50) % 50);
		boolean tileFound = false;
		Tile t = new Tile(mouse_x, mouse_y);

		if (editMode == true)
		{
			for (int i = 0; i < tiles.size(); i++)
			{
				if (tiles.get(i).isTile(mouse_x, mouse_y) == true)
				{
					tileFound = true;
					break;
				}
			}
			if (!tileFound)
			{
				tiles.add(t);
			}
		}
	}
}