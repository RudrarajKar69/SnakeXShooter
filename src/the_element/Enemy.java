package the_element;

import java.awt.Graphics;
import java.awt.Image;


public class Enemy extends GameObject{

	int bx,by;
	public Enemy(int x, int y, int width, int height, boolean dead, ID id, Image[] image,int rows,int cols,int tileSize,int bx,int by) {
		super(x, y, width, height, dead, id, image, rows, cols, tileSize);
		this.bx=bx;
		this.by=by;
	}

	@Override
	public void update() {}

	public int getBx() {
		return bx;
	}

	public int getBy() {
		return by;
	}

	public void setBx(int bx) {
		this.bx = bx;
	}

	public void setBy(int by) {
		this.by = by;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(!isDead())
			g.drawImage(getImage()[0], getX(), getY(), getWidth(), getHeight(), null);
	}
}
