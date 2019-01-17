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
		// if (new Random().nextInt(100) < Constant.BOSS_SHOOT_P) {
		// Hero hero = MapleStoryClient.hero;
		// double atan = (double) ((hero.y + hero.height / 2) - (this.y +
		// this.height / 2))
		// / ((hero.getTrueX() - MapleStoryClient.getBackX() + hero.width /
		// 2)
		// - (this.getTrueX() + this.width / 2) + this.width / 2);
		// atan = Math.atan(atan) + ((hero.getTrueX() -
		// MapleStoryClient.getBackX()
		// + hero.width / 2) > (this.getTrueX() + this.width / 2) ? 0 :
		// Math.PI);
		// bullets.add(new BossBullet(this.x + this.width / 2, this.y +
		// this.height / 2, atan));
		// }
		int count = Math.min(MAX_HP / HP, 10);
		for (int i = 0; i < count; i++) {

			if (new Random().nextInt(100) < Constant.SKILL_P) {
				bullets.add(new BossBullet(this.x + this.width / 2, this.y + this.height / 2, Math.PI * 2 / count * i));
			}
		}
		skill();

	}

	void skill() {
		HP += Math.min(100, MAX_HP / HP);
		Hero hero = MapleStoryClient.hero;
		if (new Random().nextInt(1000) < (Constant.SKILL_P + Math.min(MAX_HP / HP, 10))
				&& (MP > Math.min(MAX_HP / HP, 10))) {
			MP -= Math.min(MAX_HP / HP, 10);
			int X = hero.x - MapleStoryClient.getBackX();
			for (BossBullet bullet : bullets) {
				double atan = (double) (bullet.y - hero.y) / (bullet.x - X);
				atan = Math.atan(atan) + (bullet.x > X ? Math.PI : 0);
				bullet.setAngle(atan);
			}
		}
	}

	public void move() {
		super.move();
		Hero hero = MapleStoryClient.hero;
		if (this.getTrueX() > hero.x + hero.width + this.width) {
			this.dire = Direction.LEFT;
		} else if (this.getTrueX() < hero.x - hero.width - this.width) {
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
