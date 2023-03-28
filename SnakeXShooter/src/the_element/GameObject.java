package the_element;

import java.awt.Graphics;
import java.awt.Image;

public abstract class GameObject {
	
	private int x,y,width,height,rows,cols,tileSize;
	private boolean dead;
	private ID id;
	private Image[] image;

	GameObject(int x,int y,int width,int height,boolean dead,ID id,Image[] image,int rows, int cols, int tileSize)
	{
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setDead(dead);
		setId(id);
		setImage(image);
		setRows(rows);
		setCols(cols);
		setTileSize(tileSize);
	}
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}	
	
	public Image[] getImage() {
		return image;
	}

	public void setImage(Image[] image) {
		this.image = image;
	}

	public abstract void update();
	public abstract void draw(Graphics g);
}
