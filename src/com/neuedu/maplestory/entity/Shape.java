package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Shape {
	public Image[] img;
	public int x;
	public int y;
	public int width;
	public int height;

	public Shape(Image[] img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
		if (img != null) {
			this.width = img[0].getWidth(null);
			this.height = img[0].getHeight(null);
		}
	}
	
	abstract void draw(Graphics g);
	
	public Rectangle getRectangle(){
		return new Rectangle(x, y, width, height);
	}
}
