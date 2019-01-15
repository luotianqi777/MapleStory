package com.neuedu.maplestory.entity;

import java.awt.Graphics;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.util.ImageUtil;

public class MobSnail extends MobBase {

	public MobSnail(int x, int y, Direction dire) {
		super(ImageUtil.imgMob.snail.move.l, x, y, dire, 500, 5, 4);
	}

	public MobSnail() {
		this(0, 0, Direction.LEFT);
	}

	public void Hiting() {
		if (hit && count == img.length) {
			hit = false;
			walk = true;
		}
	}

	@Override
	public void draw(Graphics g) {
		move();
		hit(MapleStoryClient.hero);
		super.draw(g);
		Hiting();
	}

	@Override
	void updataAction() {
		if (walk) {
			this.action = MobAction.WALK;
		}
		if (hit) {
			this.action = MobAction.HIT;
		}
		if (die) {
			this.action = MobAction.DIE;
		}
	}

	@Override
	void move() {
		super.move();
		switch (this.dire) {
		case LEFT:
			switch (this.action) {
			case DIE:
				img = ImageUtil.imgMob.snail.die.l;
				break;
			case HIT:
				img = ImageUtil.imgMob.snail.hit.l;
				break;
			case WALK:
				if (!isOnGround()) {
					fallCheck();
				} else {
					img = ImageUtil.imgMob.snail.move.l;
					x -= speed;
				}
				break;
			}
			break;
		case RIGHT:
			switch (this.action) {
			case DIE:
				img = ImageUtil.imgMob.snail.die.r;
				break;
			case HIT:
				img = ImageUtil.imgMob.snail.hit.r;
				break;
			case WALK:
				if (!isOnGround()) {
					fallCheck();
				} else {
					img = ImageUtil.imgMob.snail.move.r;
					x += speed;
				}
				break;
			}
			break;
		}
		outOfBounds();
	}

	@Override
	public void die() {
		super.die();
	}

	@Override
	public boolean isDie() {
		return this.die && (count == img.length - 1);
	}

}
