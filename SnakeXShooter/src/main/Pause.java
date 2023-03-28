package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Pause extends JPanel implements ActionListener{
	
	Timer timer;
	Window k;
	int x = 0,command=0;
	Game next;
	
	Pause(Window k,Game g)
	{
		this.k=k;
		next=g;
		this.setBackground(Color.black);
		timer = new Timer(75,this);
		timer.start();
	}
	
	protected void paintComponent(Graphics g)
	{
		g.setColor(new Color(255,60,100));
		g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		draw(g);
	}
	
	void draw(Graphics g)
	{	
//		g.fillRect(x,((Game.SCREEN_HEIGHT)/2)-25, 50, 50);
		g.setFont(g.getFont().deriveFont(Font.BOLD,96F));
		String text = "PAUSED!";
		int x=getXforCenteredText(text,g),y=(Game.SCREEN_HEIGHT)/4;
		
		//Shadow
		g.setColor(Color.gray);
		g.drawString(text, x+5, y+5);
		
		//Main text
		g.setColor(Color.white);
		g.drawString(text, x, y);
		
		//Image
		x=Game.SCREEN_WIDTH/2-(Game.tileSize/2);
		y+=(Game.SCREEN_HEIGHT)/4-(Game.tileSize/2);
		g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("./PLAYER.png")).getImage(), x, y, null);
		
		//Menu
		g.setFont(g.getFont().deriveFont(Font.BOLD,40F));
		text="Pause";
		x=getXforCenteredText(text,g);
		y+=(Game.SCREEN_HEIGHT)/4;
		g.drawString(text, x, y);
		if(command==0)
			g.drawString(">", x-(Game.SCREEN_HEIGHT)/2, y);
		
		text="Quit";
		x=getXforCenteredText(text,g);
		y+=(Game.SCREEN_HEIGHT)/6;
		g.drawString(text, x, y);
		text="New Game";
		x=getXforCenteredText(text,g);
		if(command==1)
			g.drawString(">", x-(Game.SCREEN_HEIGHT)/2, y);

	}
	private int getXforCenteredText(String text,Graphics g2) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = Game.SCREEN_WIDTH/2-length/2;
		return x;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		KeyEvent e1 = k.getkeyPressed();
		if(e1!=null)
		{
			switch(e1.getKeyCode())
			{
			case KeyEvent.VK_UP:
				command=0;
				break;
			case KeyEvent.VK_DOWN:
				command=1;
				break;
			case KeyEvent.VK_ENTER:
				if(command==1)
					System.exit(0);
				else if(command==0) {
					{	
						next.timer.start();
						k.add(next);
						SwingUtilities.updateComponentTreeUI(k);
						timer.stop();
						k.remove(this);
					}
				}
				break;
			}
		}
		repaint();
	}
}

