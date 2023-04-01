package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class DifficultyScreen extends JPanel implements ActionListener{
	
	Timer timer;
	Window k;
	int x = 0,command=0;
	Game next;
	
	//Constructor that accepts only the window
	DifficultyScreen(Window k)
	{
		this.k=k;
		this.setBackground(Color.black);
		timer = new Timer(200,this);
		timer.start();
	}
	
	//Constructor that accepts both the window and the difficulty level
	DifficultyScreen(Window k,String difficulty)
	{
		this.k=k;
		this.setBackground(Color.black);
		
		if(difficulty == "Easy")	//
			command = 0;		//
		if(difficulty == "Medium")	// This part automatically sets the difficulty 
			command = 1;		// based on the difficulty accepted by it
		if(difficulty == "Hard")	//
			command = 2;		//
		
		timer = new Timer(200,this);
		timer.start();
	}
	
	protected void paintComponent(Graphics g)
	{
		draw(g);
	}
	
	void draw(Graphics g)
	{
		g.setColor(new Color(200,200,200));
		g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		
		g.setFont(g.getFont().deriveFont(Font.BOLD,96F));
		String text = "Difficulty Option";
		int x=getXforCenteredText(text,g),y=(Game.SCREEN_HEIGHT)/4;
		
		//Shadow
		g.setColor(Color.gray);
		g.drawString(text, x+5, y+5);
		
		//Main text
		g.setColor(Color.white);
		g.drawString(text, x, y);
		
		//Displays the difficulty options
		
		g.setFont(g.getFont().deriveFont(Font.BOLD,40F));
		text="Easy";
		x=getXforCenteredText(text,g);
		y+=(Game.SCREEN_HEIGHT)/4;
		g.drawString(text, x, y);
		if(command==0)	//Sets the arrow based on the difficulty selected
			g.drawString(">", x-(Game.SCREEN_HEIGHT)/2, y);
		
		text="Medium";
		x=getXforCenteredText(text,g);
		y+=(Game.SCREEN_HEIGHT)/8;
		g.drawString(text, x, y);
		text="Easy";
		x=getXforCenteredText(text,g);
		if(command==1)
			g.drawString(">", x-(Game.SCREEN_HEIGHT)/2, y);
		
		text="Hard";
		x=getXforCenteredText(text,g);
		y+=(Game.SCREEN_HEIGHT)/8;
		g.drawString(text, x, y);
		text="Easy";
		x=getXforCenteredText(text,g);
		if(command==2)
			g.drawString(">", x-(Game.SCREEN_HEIGHT)/2, y);

	}
	
	private int getXforCenteredText(String text,Graphics g2) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = Game.SCREEN_WIDTH/2-length/2;
		return x;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Gets key input using the window instance passed to it
		KeyEvent e1 = k.getkeyPressed();
		if(e1!=null)
		{
			switch(e1.getKeyCode())
			{
			case KeyEvent.VK_UP:
				if(command>0)
					command--;
				break;
			case KeyEvent.VK_DOWN:
				if(command<2)
					command++;
				break;
			case KeyEvent.VK_ENTER:
				if(command==0) 
					{	
						next=new Game(k,"Easy");
						k.add(next);
						SwingUtilities.updateComponentTreeUI(k);
						timer.stop();
						k.remove(this);
					}
				if(command==1) 
					{	
						next=new Game(k,"Medium");
						k.add(next);
						SwingUtilities.updateComponentTreeUI(k);
						timer.stop();
						k.remove(this);
					}
				if(command==2) 
					{	
						next=new Game(k,"Hard");
						k.add(next);
						SwingUtilities.updateComponentTreeUI(k);
						timer.stop();
						k.remove(this);
					}
				break;
			}
		}
		repaint();
	}
}
