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

		int lost = (MAX_HP - HP) * 10 / MAX_HP;
		Hero hero = MapleStoryClient.hero;
		int hero_center_x = hero.x + hero.width / 2;
		int hero_center_y = hero.y + hero.height / 2;
		int boss_center_x = this.getTrueX() + this.width / 2;
		int boss_center_y = this.y + this.height / 2;
		if (new Random().nextInt(100) < Constant.BOSS_SHOOT_P + lost) {
			double atan = (double) (boss_center_y - hero_center_y) / (boss_center_x - hero_center_x);
			atan = Math.atan(atan) + (hero_center_x > boss_center_x ? 0 : Math.PI);
			bullets.add(new BossBullet(this.x + this.width / 2, boss_center_y, atan));
		}

		// skill
		if (new Random().nextInt(1000) < Constant.BOSS_SKILL_P + lost) {
			skill();
		}
	}

	void skill() {
		Hero hero = MapleStoryClient.hero;
		int hero_center_x = hero.getTrueX() + hero.width / 2;
		int hero_center_y = hero.y + hero.height / 2;
		MP -= 10;
		for (BossBullet bullet : bullets) {
			bullet.speed *= 2;
			int bullet_center_x = bullet.getTrueX() + bullet.width / 2;
			int bullet_center_y = bullet.y + bullet.height / 2;
			double atan = (double) (bullet_center_y - hero_center_y) / (bullet_center_x - hero_center_x);
			atan = Math.atan(atan) + (hero_center_x > bullet_center_x ? 0 : Math.PI);
			bullet.setAngle(atan);
		}
	}

	public void move() {
		super.move();
		Hero hero = MapleStoryClient.hero;
		if (this.getTrueX() > hero.x + this.width * 3 / 2) {
			this.dire = Direction.LEFT;
		} else if (this.getTrueX() < hero.x - this.width * 3 / 2) {
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
		bullets.removeIf((e) -> {
			return e.isDie();
		});
		for (BossBullet bullet : bullets) {
			bullet.move();
			bullet.hit(MapleStoryClient.hero);
		}
	}

	public void draw(Graphics g) {
		move();
		shoot();
		moveBullets();
		for (BossBullet bullet : bullets) {
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
