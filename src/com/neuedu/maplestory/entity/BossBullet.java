package com.neuedu.maplestory.entity;

import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

public class BossBullet extends Bullet {

	public BossBullet(int x, int y, double angle) {
		super(ImageUtil.imgBullet.skill, x, y, angle, Constant.BOSS_BULLET_SPEED, 5);
	}

}
