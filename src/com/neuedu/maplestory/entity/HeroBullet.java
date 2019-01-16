package com.neuedu.maplestory.entity;


import com.neuedu.maplestory.constant.Constant;
import com.neuedu.maplestory.util.ImageUtil;

public class HeroBullet extends Bullet {

	public HeroBullet(int x, int y, double angle) {
		super(ImageUtil.imgBullet.norml, x, y, angle, Constant.BULLET_SPEED);
	}

}
