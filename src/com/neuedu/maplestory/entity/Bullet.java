package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

public class Bullet {
	private Image[] img;
	private int x, y;
	private double angle;
	private double speed;
	private boolean die;

	public Bullet(Image[] img, int x, int y, double angle, double speed) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		this.die = false;
	}

	public Bullet(int x, int y, double angle) {
		this(ImageUtil.imgBullet.img, x, y, angle, 25);
	}

	public Bullet() {
		this(0, 0, 0);
	}

	public void move() {
		x += speed * Math.cos(angle);
		y += speed * Math.sin(angle);
		if (x < 0 || x > Constant.GAME_WIDTH) {
			this.die = true;
		}
		if (y < 0 || y > Constant.GAME_HEIGHT) {
			this.die = true;
		}
	}

	/**
	 * draw_Arg
	 */
	private int count = 0;

	/**
	 * draw
	 * 
	 * @param g
	 */

	public void draw(Graphics g) {
		count %= ImageUtil.imgBullet.size;
		g.drawImage(img[count], x, y, null);
		count++;
	}

	public boolean Die() {
		return die;
	}
}
