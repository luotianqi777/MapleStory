package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;

public class Bullet extends Shape {
	private double angle;
	private double speed;
	private boolean die;
	private static final int growSpeed = Constant.GROW_SPEED;

	public Bullet(Image[] img, int x, int y, double angle, double speed) {
		super(img, x, y);
		this.angle = angle;
		this.speed = speed;
		this.die = false;
	}

	public Bullet(Image[] img, int x, int y, double angle) {
		this(img, x, y, angle, Constant.BULLET_SPEED);
	}

	public Bullet() {
		this(null, 0, 0, 0);
	}

	public void move() {
		x += speed * Math.cos(angle);
		y += speed * Math.sin(angle);
		outOfBounds();
	}

	private void outOfBounds() {

		if (x < 0 || x > MapleStoryClient.backGround.width) {
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
		if (img == null) {
			return;
		}
		count %= img.length;
		g.drawImage(img[count], getTrueX(), y, this.width, this.height, null);
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

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void addAngle(double angle) {
		this.angle += angle;
	}

	public boolean hit(NPC npc) {

		if (npc.isDie() || this.die) {
			return false;
		}
		if (this.getRectangle().intersects(npc.getRectangle())) {
			return true;
		} else {
			return false;
		}
	}

	public void hitNPCs(List<NPC> npcs) {
		for (NPC npc : npcs) {
			int hurt_val = (int) Math.sqrt(this.width * this.height) + new Random().nextInt(9) - 4;
			if (this.hit(npc)) {
				this.die();
				npc.HP -= hurt_val;
				if (npc.HP <= 0) {
					npc.die();
				} else {
					npc.beHited(hurt_val);
				}
			}
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
}
