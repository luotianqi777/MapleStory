package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;

public class NPC extends Shape {

	public int ABS;
	public int HP;
	public int MAX_HP;
	public int speed;
	public Direction dire;
	public boolean die, jump, up, down;
	public JP Jump = new JP();

	public NPC(Image[] img, int x, int y, int MAX_HP, int speed, Direction dire) {
		super(img, x, y);
		this.MAX_HP = MAX_HP;
		this.HP = this.MAX_HP;
		this.speed = speed;
		this.dire = dire;
		this.die = false;
		this.jump = true;
		this.up = false;
		this.down = false;
	}

	/**
	 * jump_Args
	 */
	public class JP {
		public double v_init = 60;
		public double v0 = v_init;
		public double vt = 0;
		public final double g = Constant.G;
		public final double t = 0.5;
		public double delta_height = 0;
		public boolean jump_up = true;
	};

	/**
	 * jump Method
	 */
	public void jump() {
		if (!Jump.jump_up) {
			Jump.jump_up = isOnGround();
		}
		if (Jump.jump_up) {
			Jump.vt = Jump.v0 - Jump.g * Jump.t;
			Jump.delta_height = Jump.v0 * Jump.t;
			Jump.v0 = Jump.vt;
			y -= Jump.delta_height;
			if (Jump.vt <= 0) {
				fallInit();
			}
		} else {
			fall();
		}
	}

	public void jumpInit() {

		this.jump = false;
		Jump.v0 = Jump.v_init;
		Jump.jump_up = true;
		Jump.vt = 0;
	}

	public void fallInit() {

		Jump.jump_up = false;
		Jump.vt = 0;
		Jump.v0 = 0;
	}

	public void fall() {
		Jump.vt = Jump.v0 + Jump.g * Jump.t;
		Jump.delta_height = Jump.v0 * Jump.t;
		Jump.v0 = Jump.vt;
		y += Jump.delta_height;
		if (isOnGround()) {
			onTheGround();
			jumpInit();
		}

	}

	@Override
	void draw(Graphics g) {

	}

	private Ground getGround() {
		for (Ground ground : MapleStoryClient.backGround.grounds) {
			if (this.getRectangle().intersects(ground.getRectangle()) && this.y < ground.y - ground.height + 50) {
				return ground;
			}
		}
		return null;
	}

	void onTheGround() {
		if (!isOnGround()) {
			return;
		}
		y = getGround().y - this.height + 10;
		jump = false;
	}

	public Boolean isOnGround() {
		for (Ground ground : MapleStoryClient.backGround.grounds) {
			if (this.getRectangle().intersects(ground.getRectangle()) && this.y < ground.y - ground.height + 50) {
				return true;
			}
		}
		return false;
	}

	public Boolean isOnRope() {
		for (Rope rope : MapleStoryClient.backGround.ropes) {
			if (this.getRectangle().intersects(rope.getRectangle())) {
				return true;
			}
		}
		return false;
	}

}
