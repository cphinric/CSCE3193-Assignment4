//Parker Hinrichs (03.10.2023) Assignment 4: Link's Adventure

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Link {
    
    int px, py;
    int x = 100;
    int y = 100;
    int w, h;
    double speed = 4;
    View view;

    BufferedImage[] link_images;
	int numImages = 50;
	int currentImage;
    int direction;

    @Override 
    public String toString()
    {
	    return "Pipe (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
    }

    Link(int x, int y)
    {
        link_images = new BufferedImage[numImages];

		for(int i = 0; i < numImages; i++)
		{
			link_images[i] = View.loadImage("images/link" + (i + 1) + ".png");
		}
        currentImage = 0;
        this.h = link_images[currentImage].getHeight();
        this.w = link_images[currentImage].getWidth();
    }

    void update()
    {

    }

    public void setPreviousPosition()
    {
        px = x;
        py = y;
    }

    public void getOutOfSprite(Tile p)
    {
        if((px + w) <= p.x && (x + w) >= p.x) //LEFT COLLISION FIXING
        {
            x = p.x - w; 
        }
        if(px >= (p.x + p.w) && x <= (p.x + p.w)) //RIGHT COLLISION FIXING
        {
            x = p.x + p.w;
        }
        if((py + h) <= p.y && (y + h) >= p.y) //VERTICAL COLLISION FIXING (TOP)
        {
            y = p.y - h;
        }
        if(py >= (p.y + p.h)) //VERTICAL COLLISION FIXING (BOTTOM)
        {
            y = p.y + p.h;
        }
    }

    public void updateImageNum(int dir) //int dir
    {
        if(dir == 0)
        {
            if ((currentImage < 38) && (currentImage >= 29))
            {
                currentImage++;
            }
            else{
                currentImage = 29;
            }
        }
        if(dir == 1)
        {
            if ((currentImage < 22) && (currentImage >= 13))
            {
                currentImage++;
            }
            else{
                currentImage = 13;
            }
        }
        if(dir == 2)
        {
            if ((currentImage < 49) && (currentImage >= 40))
            {
                currentImage++;
            }
            else{
                currentImage = 40;
            }
        }
        if(dir == 3)
        {
            
            if ((currentImage < 12) && (currentImage >= 3))
            {
                currentImage++;
            }
            else{
                currentImage = 3;
            }
        }
    }

    void drawYourself(Graphics g, int scroll_x, int scroll_y)
    {
        g.drawImage(link_images[currentImage], x + scroll_x, y + scroll_y, null);
    }
}
