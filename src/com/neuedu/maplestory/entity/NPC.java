package com.neuedu.maplestory.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;

public class NPC extends Shape implements Bloodable {

	public int ABS;
	public int HP, MP;
	public int MAX_HP, MAX_MP;
	public int speed;
	public Direction dire;
	protected boolean die, jump, up, down, hit;
	public JP Jump = new JP();
	public List<HeadInfo> headInfos = new LinkedList<>();

	public NPC(Image[] img, int x, int y, int MAX_HP, int MAX_MP, int speed, Direction dire) {
		super(img, x, y);
		this.MAX_HP = MAX_HP;
		this.MAX_MP = MAX_MP;
		this.HP = this.MAX_HP;
		this.MP = this.MAX_MP;
		this.speed = speed;
		this.dire = dire;
		this.die = false;
		this.jump = true;
		this.up = false;
		this.down = false;
		this.hit = false;
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

	public void fallCheck() {
		if (Jump.jump_up) {
			fallInit();
		}
		fall();
	}

	private int fix = Ground.IMAGE.getHeight(null) / 2 + this.height;

	protected void setFix(int fix) {
		this.fix = fix;
	}

	private Ground getGround() {
		for (Ground ground : MapleStoryClient.backGround.grounds) {
			if (this.getRectangle().intersects(ground.getRectangle()) && this.y < ground.y - ground.height + fix) {
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
			if (this.getRectangle().intersects(ground.getRectangle()) && this.y < ground.y - ground.height + fix) {
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

	public void die() {
		this.die = true;
	}

	public boolean isDie() {
		return this.die && (count == img.length - 1);
	}

	public void beHited(int hurt_val) {
		this.HP -= hurt_val;
		this.hit = true;
		this.headInfos
				.add(new HeadInfo(Integer.toString(hurt_val), this.getTrueX() + this.width / 3, this.y, Color.RED));
	}

	public void drawHeadInfos(Graphics g) {

		headInfos.removeIf((e) -> {
			return e.isDie();
		});

		for (HeadInfo HeadInfo : headInfos) {
			HeadInfo.draw(g);
		}

	}

	@Override
	public void draw(Graphics g) {

		drawHeadInfos(g);
		super.draw(g);

	}

}
