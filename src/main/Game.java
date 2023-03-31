package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import the_element.Bullet;
import the_element.Enemy;
import the_element.GameObject;
import the_element.ID;
import the_element.Player;

@SuppressWarnings("serial")
public class Game extends JLabel implements ActionListener,MouseListener{

	ArrayList<GameObject> objects,enemy;
	Timer timer;
	Image[] playerImage = new Image[2],enemyImage = new Image[1],bulletImage = new Image[3];
	Image floor,food;
	Random rnd;
	EndScreen next;
	ID ok;

	public static final int original_tileSize = 16;
	public static final int Scaler = 4;
	public static final int tileSize = original_tileSize*Scaler;
	
	public static final int maxRows=10;
	public static final int maxCols=15;
	
	public static final int SCREEN_WIDTH=maxCols*tileSize;
	public static final int SCREEN_HEIGHT=maxRows*tileSize;
	
	int player_x=0,player_y=0,indi=-1,foodX,foodY,bullet_x=-1,bullet_y=-1,score;
	int No_Of_Enemies;
	int[] ex,ey;
	String player_direction="",difficulty;

	Window k;	
	Game(Window k,String difficulty)
	{
		ok=null;
		this.k=k;
		this.difficulty=difficulty;
		objects = new ArrayList<GameObject>();
		enemy = new ArrayList<GameObject>();
		
		rnd=new Random();
		
		if(this.difficulty=="Easy")
			No_Of_Enemies=rnd.nextInt(5)+5;
		if(this.difficulty=="Medium")
			No_Of_Enemies=rnd.nextInt(5)+10;
		if(this.difficulty=="Hard")
			No_Of_Enemies=rnd.nextInt(5)+15;
		
		ex = new int[No_Of_Enemies];
		ey = new int[No_Of_Enemies];
		
		foodX=rnd.nextInt(maxCols)*tileSize;
		foodY=rnd.nextInt(maxRows)*tileSize;
		
		timer=new Timer(150,this);
		timer.start();
		
		playerImage[0]=new ImageIcon(getClass().getClassLoader().getResource("PLAYER.png")).getImage();
		playerImage[1]=new ImageIcon(getClass().getClassLoader().getResource("Player-1.png")).getImage();
		
		bulletImage[0]=new ImageIcon(getClass().getClassLoader().getResource("BULLET-1.png")).getImage();
		bulletImage[1]=new ImageIcon(getClass().getClassLoader().getResource("BULLET-2.png")).getImage();
		bulletImage[2]=new ImageIcon(getClass().getClassLoader().getResource("BULLET-3.png")).getImage();
		
		enemyImage[0] = new ImageIcon(getClass().getClassLoader().getResource("Enemy.png")).getImage();;
		
		floor = new ImageIcon(getClass().getClassLoader().getResource("Floor1.png")).getImage();
		food = new ImageIcon(getClass().getClassLoader().getResource("Food.png")).getImage();
		
		for(int i=0;i<No_Of_Enemies;i++)
		{
			ex[i]=rnd.nextInt(maxCols)*tileSize;
			ey[i]=rnd.nextInt(maxRows)*tileSize;
			Enemy temp =new Enemy(ex[i],ey[i], tileSize,tileSize, false,ID.Enemy, enemyImage,maxCols,maxRows,tileSize, bullet_x, bullet_y) ;
			objects.add(temp);
			enemy.add(temp);
		}
		
		objects.add(new Player(rnd.nextInt(maxCols)*tileSize, rnd.nextInt(maxRows)*tileSize, tileSize, tileSize, false, ID.Player, playerImage, maxRows, maxCols, tileSize, tileSize,foodX,foodY,enemy));
		
		this.addMouseListener(this);
	}
	
	 public void paintComponent(Graphics g)
	 {
		 super.paintComponent(g);
		 
		 for(int i=0;i<maxCols;i++)
		 {
			 for(int j=0;j<maxRows;j++)
			 {
				 g.drawImage(floor,i*tileSize, j*tileSize, tileSize,tileSize,null);
			 }
		 }
//		 g.drawImage(floor, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
		
//		 grid(g);
		 
		 drawApple(g);
	     
		 for(GameObject x : objects){
				x.draw(g);
			}
	}
	 
	void grid(Graphics g)
	{
		g.setColor(Color.white);
		 // draw vertical lines
	    for (int i = 0; i <= maxCols; i++) {
	        g.drawLine(i * tileSize, 00, i * tileSize, maxRows * tileSize);
	    }
	
	    // draw horizontal lines
	    for (int i = 0; i <= maxRows; i++) {
	        g.drawLine(0, i * tileSize, maxCols * tileSize, i * tileSize);
	    }
	}
	 
	void newApple()
	{
		foodX=rnd.nextInt((maxCols-4)+2)*tileSize;
		foodY=rnd.nextInt((maxRows-4)+2)*tileSize;
	}
	
	void drawApple(Graphics g)
	{
		g.drawImage(food, foodX, foodY, tileSize, tileSize, null);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(ok!=null)
		{
			if(player_direction=="right")
				objects.add(new Bullet(((player_x/tileSize)+2)*tileSize, player_y, tileSize, tileSize,false,ok, bulletImage, maxRows, maxCols, tileSize,player_direction, enemy));
			if(player_direction=="down")
				objects.add(new Bullet(player_x, ((player_y/tileSize)+3)*tileSize, tileSize, tileSize,false,ok, bulletImage, maxRows, maxCols, tileSize,player_direction, enemy));
			if(player_direction=="left")
				objects.add(new Bullet(((player_x/tileSize)-3)*tileSize, player_y, tileSize, tileSize,false,ok, bulletImage, maxRows, maxCols, tileSize,player_direction, enemy));
			if(player_direction=="up")
				objects.add(new Bullet(player_x, ((player_y/tileSize)-3)*tileSize, tileSize, tileSize,false,ok, bulletImage, maxRows, maxCols, tileSize,player_direction, enemy));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	void PlayerMover(Player p,KeyEvent e1)
	{
		Player temp = (Player) p;
		switch(e1.getKeyCode())
		{
		case KeyEvent.VK_UP:
			temp.setDirections("up");
			break;
		case KeyEvent.VK_DOWN:
			temp.setDirections("down");
			break;
		case KeyEvent.VK_LEFT:
			temp.setDirections("left");
			break;	
		case KeyEvent.VK_RIGHT:
			temp.setDirections("right");
			break;
		case KeyEvent.VK_W:
			temp.setDirections("up");
			break;
		case KeyEvent.VK_S:
			temp.setDirections("down");
			break;
		case KeyEvent.VK_A:
			temp.setDirections("left");
			break;	
		case KeyEvent.VK_D:
			temp.setDirections("right");
			break;
		case KeyEvent.VK_P:
			Pause temp1 = new Pause(k, this);
			k.add(temp1);
			SwingUtilities.updateComponentTreeUI(k);
			timer.stop();
			k.remove(this);
			break;
		}
		player_x=temp.getX();
		player_y=temp.getY();
		player_direction = temp.getDirections();
		if(temp.eaten)
		{
			newApple();
			temp.setFoodx(foodX);
			temp.setFoody(foodY);
			temp.eaten=false;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		KeyEvent e1 = k.getkeyPressed();
		boolean ENDED=false;
		for(GameObject x : objects){
			if(x.getId()==ID.Player)
			{
				if(e1!=null)
				{
					if(!x.isDead())
					{
						Player temp = (Player) x;
						PlayerMover(temp,e1);
						if(temp.NO_Eaten>3)
							ok=ID.Bullet;
						if(temp.NO_Eaten>5)
							ok=ID.Bullet1;
						if(temp.NO_Eaten>8)
							ok=ID.Bullet2;
						score=temp.NO_Eaten;
					}
					else if(x.isDead())
					{
						indi= objects.indexOf(x);
						ENDED=true;
						break;
//						x.setDead(false); //Sets Immortality
					}
				}
				
			}
			if(x.getId()==ID.Bullet)
			{
				if(x.isDead())
				{
					indi = objects.indexOf(x);
					break;
				}
				else
				{
					bullet_x=x.getX();
					bullet_y=x.getY();
				}
			}
			if(x.getId()==ID.Enemy)
			{
				if(x.isDead())
				{
					indi = objects.indexOf(x);
					enemy.remove(x);
					No_Of_Enemies--;
					break;
				}
				else
				{
					Enemy temp = (Enemy)x;
					if(temp.getBx()!=bullet_x)
						temp.setBx(bullet_x);
					if(temp.getBy()!=bullet_y)
						temp.setBy(bullet_y);
				}
			}
			x.update();
		}
		if(indi>=0)
		{
			objects.remove(indi);
			indi=-1;
		}
		if(ENDED)
		{
			next=new EndScreen(k, difficulty);
			k.add(next);
			SwingUtilities.updateComponentTreeUI(k);
			timer.stop();
			k.remove(this);
		}
		if(No_Of_Enemies<=0)
		{
			WinScreen temp;
			temp=new WinScreen(k,difficulty,score);
			k.add(temp);
			SwingUtilities.updateComponentTreeUI(k);
			timer.stop();
			k.remove(this);
		}
		repaint();
	}
}
