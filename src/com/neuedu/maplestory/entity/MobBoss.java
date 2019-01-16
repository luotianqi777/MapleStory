package com.neuedu.maplestory.entity;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;
import com.neuedu.maplestory.util.ItemUtil;

public class MobBoss extends MobBase {

	private List<BossBullet> bullets = new LinkedList<>();

	public MobBoss(int x, int y, Direction dire) {
		super(ImageUtil.imgMob.boss.move.l, // image
				x, y, // location
				dire, // direction
				5000, // HP
				300, // MP
				3, // speed
				5 // attack
		);

		this.jump = false;
		// dropChange
		setDropChange(100);

		addDropItem(new ItemUtil.shootsin());

	}

	void shoot() {
		/*
		 */
		if (new Random().nextInt(100) < Constant.BOSS_SHOOT_P) {
			Hero hero = MapleStoryClient.hero;
			double atan = (double) ((hero.y - hero.height / 2) - (this.y - this.height / 2))
					/ ((hero.x + hero.width / 2) - (this.getTrueX()) + this.width / 2);
			atan = Math.atan(atan) + ((hero.x + hero.width / 2) > (this.getTrueX() + this.width / 2) ? Math.PI : 0);
			bullets.add(new BossBullet(this.x + this.width / 2, this.y + this.height / 2, (atan + Math.PI)));
		}

	}

	public void move() {
		super.move();
		Hero hero = MapleStoryClient.hero;
		if (this.getTrueX() > hero.x) {
			this.dire = Direction.LEFT;
		} else {
			this.dire = Direction.RIGHT;
		}
		switch (this.dire) {
		case LEFT:
			switch (this.action) {
			case DIE:
				img = ImageUtil.imgMob.boss.die.l;
				break;
			case HIT:
				img = ImageUtil.imgMob.boss.move.l;
				break;
			case WALK:
				img = ImageUtil.imgMob.boss.move.l;
				x -= speed;
				break;
			}
			break;
		case RIGHT:
			switch (this.action) {
			case DIE:
				img = ImageUtil.imgMob.boss.die.r;
				break;
			case HIT:
				img = ImageUtil.imgMob.boss.move.r;
				break;
			case WALK:
				img = ImageUtil.imgMob.boss.move.r;
				x += speed;
				break;
			}
			break;
		}
		outOfBounds();

	}

	void moveBullets() {
		for (Bullet bullet : bullets) {
			bullet.move();
			bullet.hit(MapleStoryClient.hero);
		}
	}

	public void draw(Graphics g) {
		move();
		shoot();
		moveBullets();
		for (Bullet bullet : bullets) {
			bullet.draw(g);
		}

		super.draw(g);

	}

	@Override
	void updataAction() {
		if (walk) {
			this.action = MobAction.WALK;
		}
	}
}
