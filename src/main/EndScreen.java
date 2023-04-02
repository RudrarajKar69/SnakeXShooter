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
public class EndScreen extends JPanel implements ActionListener {

	// Timer for animation
	Timer timer;
	// Window object for key event handling
	Window k;
	// command variable to keep track of user input
	int x = 0, command = 0;
	// DifficultyScreen object to be displayed next
	DifficultyScreen next;
	// Difficulty of the game that just ended
	String difficulty;

	// Constructor
	EndScreen(Window k, String difficulty) {
		// Initializing instance variables
		this.difficulty = difficulty;
		this.k = k;
		this.setBackground(Color.black);
		// Starting timer
		timer = new Timer(200, this);
		timer.start();
	}

	// Overriding paintComponent method to draw on JPanel
	protected void paintComponent(Graphics g) {
		draw(g);
	}

	// Custom draw method to draw graphics on the JPanel
	void draw(Graphics g) {
		// Filling the background with a purple color
		g.setColor(new Color(199, 99, 255));
		g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

		// Drawing the main text "Game!" in bold font with size 96
		g.setFont(g.getFont().deriveFont(Font.BOLD, 96F));
		String text = "Game!";
		int x = getXforCenteredText(text, g), y = (Game.SCREEN_HEIGHT) / 4;

		// Drawing the shadow of the main text in gray color with an offset of 5 pixels
		// in both x and y direction
		g.setColor(Color.gray);
		g.drawString(text, x + 5, y + 5);

		// Drawing the main text in white color
		g.setColor(Color.white);
		g.drawString(text, x, y);

		// Drawing an image of the player character in the center of the screen
		x = Game.SCREEN_WIDTH / 2 - (Game.tileSize / 2);
		y += (Game.SCREEN_HEIGHT) / 4 - (Game.tileSize / 2);
		g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("PLAYER.png")).getImage(), x, y, null);

		// Drawing the "Restart" and "Quit" options in bold font with size 40
		g.setFont(g.getFont().deriveFont(Font.BOLD, 40F));
		text = "Restart";
		x = getXforCenteredText(text, g);
		y += (Game.SCREEN_HEIGHT) / 4;
		g.drawString(text, x, y);
		if (command == 0)
			g.drawString(">", x - (Game.SCREEN_HEIGHT) / 2, y);

		text = "Quit";
		x = getXforCenteredText(text, g);
		y += (Game.SCREEN_HEIGHT) / 6;
		g.drawString(text, x, y);
		text = "Restart";
		x = getXforCenteredText(text, g);
		if (command == 1)
			g.drawString(">", x - (Game.SCREEN_HEIGHT) / 2, y);

	}

	// Helper method to get x coordinate for centered text
	private int getXforCenteredText(String text, Graphics g2) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = Game.SCREEN_WIDTH / 2 - length / 2;
		return x;
	}

	// Overriding actionPerformed method to handle
	@Override
	public void actionPerformed(ActionEvent e) {
    	// Get the key event from the window
    	KeyEvent e1 = k.getkeyPressed();
    	// Check if a key was pressed
    	if(e1!=null)
    	{
	        // Check which key was pressed
        	switch(e1.getKeyCode())
        	{
        	case KeyEvent.VK_UP:
            	// Move the selection to the "Restart" option
            	command=0;
            	break;
        	case KeyEvent.VK_DOWN:
            	// Move the selection to the "Quit" option
            	command=1;
            	break;
        	case KeyEvent.VK_ENTER:
            	// Check which option is currently selected
            	if(command==1)
	                // If "Quit" is selected, exit the program
	                System.exit(0);
		else if(command==0) {
	                // If "Restart" is selected, go back to the difficulty screen
                	next=new DifficultyScreen(k,difficulty);
                	k.add(next);
                	// Update the window's UI to show the new screen
                	SwingUtilities.updateComponentTreeUI(k);
                	// Stop the timer
                	timer.stop();
                	// Remove this screen from the window
                	k.remove(this);
            	}
            	break;
        	}
    	}
    	// Redraw the screen
    	repaint();
}
