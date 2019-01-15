package com.neuedu.maplestory.entity;

import java.awt.Graphics;

import com.neuedu.maplestory.util.ImageUtil;

public class Rope extends Shape {

	int length;

	public Rope(int x, int y, int length) {
		super(ImageUtil.rope, x, y);
		this.length = length;
	}

	public Rope() {
		this(0, 0, 1);
	}

	@Override
	void draw(Graphics g) {
		g.drawImage(img[0], x, y, null);
		for (int i = 0; i < length; i++) {
			g.drawImage(img[1], x + 3, y + img[0].getHeight(null) + img[1].getHeight(null) * i, null);
		}
		g.drawImage(img[2], x + 1, y + img[0].getHeight(null) + img[1].getHeight(null) * length, null);
	}
}
