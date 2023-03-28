package the_element;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Player extends GameObject{
	
	int speedx,speedy,length=1,foodx,foody;
	public int NO_Eaten=0;
	int[] x,y;
	ArrayList<GameObject> objects;
	String directions;
	public boolean eaten=false;
	
	public void setObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
	}

	public Player(int x, int y, int width, int height, boolean dead, ID id, Image[] image,int rows,int cols,int tileSize,int speed,int foodX,int foodY, ArrayList<GameObject> objects) {
		super(x, y, width, height, dead, id, image,rows,cols,tileSize);
		setSpeedX(speed);
		setSpeedY(speed);
		this.x= new int[length];
		this.y=new int[length];
		this.foodx=foodX;
		foody=foodY;
		this.objects = objects;
		setDirections("right");
	}
	
	void setDirector()
	{
		switch(getDirections())
		{
		case "right":
			if(!(getSpeedX()<0)) //If not going left
			{
				setSpeedX(getTileSize());
				setSpeedY(0);
			}
			break;
		case "left":
			if(!(getSpeedX()>0)) //If not going right
			{	
				setSpeedX(getTileSize()*-1);
				setSpeedY(0);
			}
			break;
		case "up":
			if(!(getSpeedY()>0)) //If not going down
			{
				setSpeedY(getTileSize()*-1);
				setSpeedX(0);
			}
			break;
		case "down":
			if(!(getSpeedY()<0)) //If not going down
			{
				setSpeedY(getTileSize());
				setSpeedX(0);
			}
			break;
		}
	}

	void WallChecker()
	{
		if(getX()==0 && getSpeedX()<0)
			setSpeedX(getSpeedX()*-1);
		if(getX()>=(getCols()*getTileSize())-getWidth() && getSpeedX()>0)
			setSpeedX(getSpeedX()*-1);
		if(getY()==0 && getSpeedY()<0)
			setSpeedY(getSpeedY()*-1);
		if(getY()>=(getRows()*getTileSize())-getHeight() && getSpeedY()>0)
			setSpeedY(getSpeedY()*-1);
		
		for(GameObject x:objects)
		{
			if(getX()/getTileSize()==x.getX()/x.getTileSize() && getY()/getTileSize()==x.getY()/x.getTileSize())
			{
				setDead(true);
				x.setDead(true);
			}
		}
	}
	
	void shortening()
	{
		boolean a=true;
		int tech=0;
		
		for(int i=1;i<x.length;i++)
		{
			if(getX()==x[i] && getY() == y[i])
			{
				if(i>2)
				{
					a=false;
					System.out.println("X = "+getX()+"="+i+"\nY ="+getY()+"="+i);
					tech=i;
					break;
				}
				else 
				{
					setDead(true);
					return;
				}
			}
		}
		if(a==false) {
			if(length>1)
			{
				length=tech-1;
				x=new int[length];
				y=new int[length];
				System.out.println(length);
			}
		}
	}
	
	@Override
	public void update() {
		if(!isDead())
		{
			for(int i = length-1;i>0;i--) {
				x[i] = x[i-1];
				y[i] = y[i-1];
			}
			setDirector();
			
			WallChecker();
			
			setX(getX()+getSpeedX());
			setY(getY()+getSpeedY());
			
			if(getX()/getTileSize()==foodx/getTileSize() && getY()/getTileSize()==foody/getTileSize())
			{
				length++;
				x=new int[length];
				y=new int[length];
				eaten=true;
				NO_Eaten++;
			}
			
			x[0]= getX();
			y[0]=getY();
			shortening();
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		for(int i=0;i<length;i++)
		{
			if(i == 0) {
				g.drawImage(getImage()[0], getX(), getY(),getWidth(),getHeight(), null);
			}
			else {
				g.drawImage(getImage()[1],x[i], y[i], getWidth(),getHeight(),null);
			}
		}
	}
	
	public int getSpeedX() {
		return speedx;
	}

	public void setSpeedX(int speedx) {
		this.speedx = speedx;
	}
	
	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public int getSpeedY() {
		return speedy;
	}

	public void setSpeedY(int speedy) {
		this.speedy = speedy;
	}

	public int getFoodx() {
		return foodx;
	}

	public void setFoodx(int foodx) {
		this.foodx = foodx;
	}

	public int getFoody() {
		return foody;
	}

	public void setFoody(int foody) {
		this.foody = foody;
	}

	public void setLength(int length) {
		this.length = length;
	}
}