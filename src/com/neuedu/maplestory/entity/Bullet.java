package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

public class Bullet {
	private Image[] img;
	private int x, y;
	private int width, height;
	private double angle;
	private double speed;
	private boolean die;
	private static final int growSpeed = Constant.GROW_SPEED;

	public Bullet(Image[] img, int x, int y, double angle, double speed) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = img[0].getWidth(null);
		this.height = img[0].getHeight(null);
		this.angle = angle;
		this.speed = speed;
		this.die = false;
	}

	public Bullet(int x, int y, double angle) {
		this(ImageUtil.imgBullet.norml, x, y, angle, Constant.BULLET_SPEED);
	}

	public Bullet() {
		this(0, 0, 0);
	}

	public Bullet(Bullet bullet) {
		this();
		this.img = bullet.img;
		this.x = bullet.x;
		this.y = bullet.y;
		this.width = bullet.width;
		this.height = bullet.height;
		this.angle = bullet.angle;
		this.speed = bullet.speed;
		this.die = bullet.die;
	}

	public void move() {
		x += speed * Math.cos(angle);
		y += speed * Math.sin(angle);
		outOfBounds();
	}

	private void outOfBounds() {

		if (x < 0 || x > MapleStoryClient.backGround.weight) {
			die();
		}
		if (y < 0 || y > MapleStoryClient.backGround.height) {
			die();
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
		g.drawImage(img[count], x + MapleStoryClient.getBackX(), y, this.width, this.height, null);
		count++;
	}

	public void grow() {
		this.width += growSpeed;
		this.height += growSpeed;
	}

	public void die() {
		this.die = true;
	}

	/**
	 * 
	 * @return {@link Boolean}
	 */
	public boolean isDie() {
		return die;
	}

	public void addAngle(double angle) {
		this.angle += angle;
	}

	public boolean hit(MobBase mob) {
		if (mob.isDie() || this.die) {
			return false;
		}
		if (this.getRectangle().intersects(mob.getRectangle())) {
			return true;
		} else {
			return false;
		}
	}

	public void hitMods(List<MobBase> mobs) {
		for (MobBase mob : mobs) {
			if (this.hit(mob)) {
				this.die();
				mob.HP -= Math.sqrt(this.width * this.height);
				if (mob.HP <= 0) {
					mob.die();
				} else {
					mob.hit = true;
				}
			}
		}
	}

	/**
	 * get Rectangle
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
