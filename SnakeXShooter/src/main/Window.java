package main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Window extends JFrame implements KeyListener{
	
	KeyEvent e;
	public Window()
	{
		ImageIcon logo=new ImageIcon(getClass().getClassLoader().getResource("./Food.png"));
		Title o = new Title(this);
		this.setBackground(Color.black);
		this.setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setTitle("SnakeXShooter");
		this.setIconImage(logo.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		this.add(o);
		
		this.setVisible(true);
		this.setSize(Game.SCREEN_WIDTH+17, Game.SCREEN_HEIGHT+40);
		this.setLocationRelativeTo(null);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {this.e=e;}
	@Override
	public void keyReleased(KeyEvent e) {this.e = null;}
	
	public KeyEvent getkeyPressed()
	{
		return e;
	}
}
