package com.neuedu.maplestory.entity;

import java.awt.Image;

public class NPC {

	public int HP;
	public int MAX_HP;
	public Image[] img;
	public int x;
	public int y;
	public int width;
	public int height;
	public int speed;
	public Direction dire;
	public boolean die;

	public NPC(Image[] img, int x, int y, int MAX_HP, int speed, Direction dire) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.MAX_HP = MAX_HP;
		this.HP = this.MAX_HP;
		this.speed = speed;
		this.dire = dire;
		this.die = false;
		if (img != null) {
			this.width = img[0].getWidth(null);
			this.height = img[0].getHeight(null);
		}
	}

}
