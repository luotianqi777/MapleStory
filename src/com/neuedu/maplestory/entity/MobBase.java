package com.neuedu.maplestory.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.client.MapleStoryClient;

public abstract class MobBase extends NPC implements Bloodable, Dropable {

	protected int dropChance;
	protected boolean walk;
	protected MobAction action;
	protected int attack;
	protected List<Item> dropItems = new LinkedList<>();

	MobBase(Image[] img, int x, int y, Direction dire, int MAX_HP, int MAX_MP, int speed, int attack) {
		super(img, x, y, MAX_HP, MAX_MP, speed, dire);
		this.walk = false;
		this.action = MobAction.WALK;
		this.attack = attack;
		this.dropChance = 0;
	}

	public MobBase() {
		this(null, 0, 0, Direction.LEFT, 100, 0, 0, 0);
	}

	public void setDropChange(int chance) {
		this.dropChance = chance;
	}

	public void addDropItem(Item item) {
		dropItems.add(item);
	}

	abstract void updataAction();

	void move() {
		updataAction();
		if (jump) {
			jump();
		}
		hit(MapleStoryClient.hero);
	}

	public void Hiting() {
		if (hit && count == img.length) {
			hit = false;
			walk = true;
		}
	}

	private void hit(Hero hero) {

		if (this.getRectangle().intersects(hero.getRectangle())) {
			int hurt_val = attack + new Random().nextInt(5) - 2;
			hero.HP -= hurt_val;
			hero.beHited(hurt_val);
		}
		if (hero.HP <= 0) {
			hero.die();
		}
	}

	@Override
	public void beHited(int hurt_val) {
		super.beHited(hurt_val);
		this.headInfos
				.add(new HeadInfo(Integer.toString(hurt_val), this.getTrueX() + this.width / 3, this.y, Color.ORANGE));
	}

	@Override
	public void dropItem() {
		if (new Random().nextInt(100) < dropChance) {
			Item item = dropItems.get(new Random().nextInt(dropItems.size())).copy();
			item.x = this.x;
			item.y = this.y;
			item.jump = true;
			MapleStoryClient.items.add(item);
		}
	}

	@Override
	public void die() {
		super.die();
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
		if (y > MapleStoryClient.backGround.height) {
			die();
		}
	}

	@Override
	public Rectangle getRectangle() {

		return new Rectangle(getTrueX(), y, width, height);
	}

	@Override
	public int getTrueX() {
		return super.getTrueX() + MapleStoryClient.getBackX();
	}

	@Override
	public void draw(Graphics g) {
		move();
		drawBloodBar(g, this, true);
		super.draw(g);
	}

}
