package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.neuedu.maplestory.client.MapleStoryClient;

public abstract class MobBase extends NPC implements Bloodable {

	protected boolean walk, hit;
	protected MobAction action;
	protected int count;
	protected int attack;

	MobBase(Image[] img, int x, int y, Direction dire, int MAX_HP, int speed, int attack) {
		super(img, x, y, MAX_HP, speed, dire);
		this.walk = false;
		this.hit = false;
		this.action = MobAction.WALK;
		this.attack = attack;
		this.count = 0;
	}

	public MobBase() {
		this(null, 0, 0, Direction.LEFT, 100, 0, 0);
	}

	@Override
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
		if (jump) {
			jump();
		}
	}

	public boolean hit(Hero hero) {

		if (this.getRectangle().intersects(hero.getRectangle())) {
			hero.HP -= attack;
		}
		if (hero.HP <= 0) {
			hero.die();
		}
		return false;
	}

	public void die() {
		this.die = true;
	}

	public boolean isDie() {
		return this.die;
	}

	void outOfBounds() {
		if (x <= MapleStoryClient.getBackX()) {
			x = MapleStoryClient.getBackX();
			this.dire = Direction.RIGHT;
		}
		if (x > MapleStoryClient.backGround.width - this.width) {
			x = MapleStoryClient.backGround.width - this.width;
			this.dire = Direction.LEFT;
		}
	}

	@Override
	public Rectangle getRectangle() {

		return new Rectangle(x + MapleStoryClient.getBackX(), y, width, height);
	}

}
