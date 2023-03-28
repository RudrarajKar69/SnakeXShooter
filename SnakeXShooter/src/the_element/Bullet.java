package the_element;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Bullet extends GameObject{
	
	String directions;
	int s[];
	int range=30;
	ArrayList<GameObject> objects;
	public Bullet(int x, int y, int width, int height, boolean dead, ID id, Image[] image,int rows,int cols,int tileSize,String directions,ArrayList<GameObject> objects) {
		super(x, y, width, height, dead, id, image, rows, cols, tileSize);
		this.directions=directions;
		s= new int[2];
		s[0]=x;
		s[1]=y;
		this.objects=objects;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(!isDead())
		{
			//(getId());
			if(getId()==ID.Bullet)
				range=getTileSize()/2;
			if(getId()==ID.Bullet1)
				range=getTileSize();
			if(getId()==ID.Bullet2)
				range=getTileSize()*2;
			
			if(directions=="left") 
			{
				setX(getX()-range);
			}
			if(directions=="right")
			{
				setX(getX()+range);
			}
			if(directions=="up")
			{
				setY(getY()-range);
			}
			if(directions=="down")
			{	
				setY(getY()+range);
			}
			
			
			if((getY())<=(getY()-range)||(getY())>=(getY()+range))
				setDead(true);
			
			for(GameObject x:objects)
			{
				if(getX()/getTileSize()==x.getX()/x.getTileSize() && getY()/getTileSize() == x.getY()/x.getTileSize())
				{
					x.setDead(true);
					setDead(true);
				}
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		if(!isDead())
		{
			double xd=0,yd=0;
			if(directions=="up")
				yd=Math.min((getY()+getTileSize()), (getY()+(getHeight()/2)))-Math.max((getY()+getTileSize()), (getY()+(getHeight()/2)));
			if(directions=="down")
				yd=Math.max((getX()+getTileSize()), (getX()+(getWidth()/2)))-Math.min((getX()+getTileSize()), (getX()+(getWidth()/2)));
			if(directions=="right")
				xd=Math.max((getX()+getTileSize()), (getX()+(getWidth()/2)))-Math.min((getX()+getTileSize()), (getX()+(getWidth()/2)));
			if(directions=="left")
				xd=Math.min((getX()+getTileSize()), (getX()+(getWidth()/2)))-Math.max((getX()+getTileSize()), (getX()+(getWidth()/2)));
			double degree=Math.toDegrees(Math.atan2(yd, xd));
			Graphics2D g2d = (Graphics2D) g;
			AffineTransform old = g2d.getTransform();
			g2d.rotate(Math.toRadians(degree+90), getX()+(getWidth()/2), getY()+(getHeight()/2));
			//draw shape/image (will be rotated)
			
			if(getId()==ID.Bullet)
				g.drawImage(getImage()[0], getX(), getY(), getWidth(), getHeight(), null);
			if(getId()==ID.Bullet1)
				g.drawImage(getImage()[1], getX(), getY(), getWidth(), getHeight(), null);
			if(getId()==ID.Bullet2)
				g.drawImage(getImage()[2], getX(), getY(), getWidth(), getHeight(), null);
			g2d.setTransform(old);
		}
	}
}
