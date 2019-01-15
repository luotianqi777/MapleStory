package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

public class Background {

	public Image img;
	protected int x;
	protected int y;
	public int height;
	public int weight;
	private int speed;

	public int getX(){
		return x;
	}
	
	public Background(Image img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.height = img.getHeight(null);
		this.weight = img.getWidth(null);
		this.speed = Constant.HERO_SPEED;
	}

	public Background() {
		this(ImageUtil.imgBackground, 0, 0);
		this.y = Constant.GAME_HEIGHT - this.height;
	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}

	public void move(Direction direction) {
		switch (direction) {
		case LEFT:
			this.x -= speed;
			if (x < Constant.GAME_WIDTH - this.weight) {
				x = Constant.GAME_WIDTH - this.weight;
			}
			break;
		case RIGHT:
			this.x += speed;
			if (x > 0) {
				x = 0;
			}
			break;
		}
	}

}
