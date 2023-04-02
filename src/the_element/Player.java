package the_element;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Player extends GameObject{
	
	int speedx,speedy,length=1,foodx,foody;
	public int NO_Eaten=0; //Saves no of apples eaten
	ArrayList<Integer> x,y; //List that stores the x and y position of the snake's body parts
	ArrayList<GameObject> enemies; //List that stores the enemies in the screen
	String directions; //Direction of the player
	public boolean eaten=false; //Checks if player has eaten an apple or not
	
	public void setObjects(ArrayList<GameObject> enemies) {
		this.enemies = enemies;
	}

	public Player(int x, int y, int width, int height, boolean dead, ID id, Image[] image,int rows,int cols,int tileSize,int speed,int foodX,int foodY, ArrayList<GameObject> objects) {
		super(x, y, width, height, dead, id, image,rows,cols,tileSize);
		setSpeedX(speed);
		setSpeedY(speed);
		this.foodx=foodX;
		this.x = new ArrayList<Integer>();
		this.x.add(getX());
		this.y = new ArrayList<Integer>();
		this.y.add(getY());
		foody=foodY;
		this.enemies = objects;
		setDirections("right");
	}
	
	//Changes the direction of the snake
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

	//Checks for wall and enemies
	void WallChecker()
	{
		//Reverses the player's direction if it collides with a screen boundary
		if(getX()==0 && getSpeedX()<0)
			setSpeedX(getSpeedX()*-1);
		if(getX()>=(getCols()*getTileSize())-getWidth() && getSpeedX()>0)
			setSpeedX(getSpeedX()*-1);
		if(getY()==0 && getSpeedY()<0)
			setSpeedY(getSpeedY()*-1);
		if(getY()>=(getRows()*getTileSize())-getHeight() && getSpeedY()>0)
			setSpeedY(getSpeedY()*-1);
		
		for(GameObject x:enemies) //Loops through all the enemy in the list enemies and see if the player collides with their tile
		{
			if(getX()/getTileSize()==x.getX()/x.getTileSize() && getY()/getTileSize()==x.getY()/x.getTileSize())
			{
				setDead(true);
				x.setDead(true);
			}
		}
	}
	
	//Shortens the snake if it collides with itself
	void shortening()
	{
		boolean a=true;
		int tech=0; //Stores the part of the snake body where the snake's head has collided
		
		for(int i=1;i<x.size();i++)
		{
			if(getX()==x.get(i) && getY() == y.get(i)) //If the snake collides with itself
			{
				if(i>2)
				{
					a=false;
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
				length=tech;
				x.subList(length, x.size()-1).clear(); //Reduces the size of the snake body
				y.subList(length,y.size()-1).clear();  //Reduces the size of the snake body
			}
		}
	}
	
	@Override
	public void update() {
		if(!isDead())
		{
			shortening();
			//Updates the snake body
			for(int i =x.size()-1;i>0;i--) {
				x.set(i, x.get(i-1));
				y.set(i, y.get(i-1));
			}
			setDirector();
			
			WallChecker();
			
			setX(getX()+getSpeedX());
			setY(getY()+getSpeedY());
			
			//Checks if player collides with food
			if(getX()/getTileSize()==foodx/getTileSize() && getY()/getTileSize()==foody/getTileSize())
			{
				length++;
				x.add(x.get(x.size()-1));
				y.add(y.get(y.size()-1));
				eaten=true;
				NO_Eaten++;
			}
			
			x.set(0,getX()); //Sets the first index of list `x` to the position of the snake's head
			y.set(0, getY()); //Sets the first index of list `y` to the position of the snake's head
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		for(int i=0;i<x.size();i++)
		{
			if(i == 0) {
				g.drawImage(getImage()[0], getX(), getY(),getWidth(),getHeight(), null);
			}
			else {
				g.drawImage(getImage()[1],x.get(i), y.get(i), getWidth(),getHeight(),null);
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
