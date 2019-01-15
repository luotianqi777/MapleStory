package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.maplestory.util.ImageUtil;

public class Ground extends Shape {

	public static final Image IMAGE  = ImageUtil.imgGround[0];

	public Ground(int x, int y) {
		super(ImageUtil.imgGround, x, y);
	}

	@Override
	void draw(Graphics g) {
		g.drawImage(img[0], x, y, null);
	}

}
