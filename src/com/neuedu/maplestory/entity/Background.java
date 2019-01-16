package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

public class Background extends Shape {

	private int speed;
	public List<Rope> ropes = new LinkedList<>();
	public List<Ground> grounds = new LinkedList<>();

	public int getX() {
		return x;
	}

	public void addRope(Rope rope) {
		ropes.add(rope);
	}

	public void addGround(Ground ground) {
		grounds.add(ground);
	}

	public List<Rope> getRopes() {
		return ropes;
	}

	public Background(Image[] img, int x, int y) {
		super(img, x, y);
		this.speed = Constant.HERO_SPEED;
	}

	public Background() {
		this(ImageUtil.imgBackGround.background, 0, 0);
		this.y = Constant.GAME_HEIGHT - this.height;
	}

	public void draw(Graphics g) {
		super.draw(g);

		for (Ground ground : grounds) {
			ground.draw(g);
		}

		for (Rope rope : ropes) {
			rope.draw(g);
		}
	}

	public void move(Direction direction) {
		this.speed = MapleStoryClient.hero.speed;
		switch (direction) {
		case LEFT:
			this.x -= speed;
			if (x < Constant.GAME_WIDTH - this.width) {
				x = Constant.GAME_WIDTH - this.width;
			} else {
				for (Rope rope : ropes) {
					rope.x -= speed;
				}
				for (Ground ground : grounds) {
					ground.x -= speed;
				}
			}
			break;
		case RIGHT:
			this.x += speed;
			if (x > 0) {
				x = 0;
			} else {
				for (Rope rope : ropes) {
					rope.x += speed;
				}
				for (Ground ground : grounds) {
					ground.x += speed;
				}
			}
			break;
		}
	}

}
