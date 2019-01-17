package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;

public class Bullet extends Shape {
	protected double angle;
	protected double speed;
	private boolean die;
	private static final int growSpeed = Constant.GROW_SPEED;
	private int attack;

	public Bullet(Image[] img, int x, int y, double angle, double speed, int attack) {
		super(img, x, y);
		this.angle = angle;
		this.speed = speed;
		this.die = false;
		this.attack = attack;
	}

	public Bullet() {
		this(null, 0, 0, 0, 0, 0);
	}

	public void move() {
		x += speed * Math.cos(angle);
		y += speed * Math.sin(angle);
		outOfBounds();
	}

	protected void outOfBounds() {

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

	public void hit(NPC npc) {

		if (npc.isDie() || this.die) {
			return;
		}
		int hurt_val = attack + new Random().nextInt(9) - 4;
		if (this.getRectangle().intersects(npc.getRectangle())) {
			this.die();
			npc.HP -= hurt_val;
			if (npc.HP <= 0) {
				npc.die();
			} else {
				npc.beHited(hurt_val);
			}
		}
	}

	public void hitNPCs(List<NPC> npcs) {
		for (NPC npc : npcs) {
			hit(npc);
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
