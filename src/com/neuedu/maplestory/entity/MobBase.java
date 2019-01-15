package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.neuedu.maplestory.client.MapleStoryClient;

public abstract class MobBase extends NPC implements Bloodable {

	protected boolean walk, hit;
	protected MobAction action;
	protected int count;

	MobBase(Image[] img, int x, int y, Direction dire, int MAX_HP, int speed) {
		super(img, x, y, MAX_HP, speed, dire);
		this.walk = false;
		this.hit = false;
		this.action = MobAction.WALK;
		this.count = 0;
	}

	public MobBase() {
		this(null, 0, 0, Direction.LEFT, 100, 0);
	}

	public void draw(Graphics g) {
		if (img.length <= 0) {
			return;
		}
		drawBloodBar(g, this, true);
		count %= img.length;
		g.drawImage(img[count], x + MapleStoryClient.getBackX(), y, null);
		count++;
	}

	abstract void updataAction();

	void move() {
		updataAction();
	}

	abstract boolean hit(Hero hero);

	public void die() {
		this.die = true;
	}

	public boolean isDie() {
		return this.die;
	}

	/**
	 * get Rectangle
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}

	void outOfBounds() {
		if (x <= MapleStoryClient.getBackX()) {
			x = MapleStoryClient.getBackX();
			this.dire = Direction.RIGHT;
		}
		if (x > MapleStoryClient.backGround.weight - this.width) {
			x = MapleStoryClient.backGround.weight - this.width;
			this.dire = Direction.LEFT;
		}
	}
}
