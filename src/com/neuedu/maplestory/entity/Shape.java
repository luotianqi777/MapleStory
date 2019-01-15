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
	private int count;

	public Shape(Image[] img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
		if (img != null) {
			this.width = img[0].getWidth(null);
			this.height = img[0].getHeight(null);
		}
	}

	void draw(Graphics g) {
		if (img == null) {
			return;
		}
		count %= img.length;
		g.drawImage(img[count], x, y, null);
		count++;
	}

	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
