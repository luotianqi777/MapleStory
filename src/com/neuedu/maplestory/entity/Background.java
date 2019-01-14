package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

public class Background {
	
	public Image img;
	public int x;
	public int y;
	public int height;
	public int weight;

	public Background(Image img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.height = img.getHeight(null);
		this.weight = img.getWidth(null);
	}

	public Background() {
		this(ImageUtil.imgBackground, 0, 0);
		this.y = Constant.GAME_HEIGHT - this.height;
	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	

}
