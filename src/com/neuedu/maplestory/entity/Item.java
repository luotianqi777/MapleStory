package com.neuedu.maplestory.entity;

import java.awt.Image;

import com.neuedu.maplestory.client.MapleStoryClient;

public abstract class Item extends NPC {

	public Item(Image[] img, int x, int y) {
		super(img, x, y, 0, 0, 0, Direction.LEFT);
		this.jump();
	}

	public Item(Image[] img) {
		this(img, 0, 0);
	}

	public Item(Item item) {
		this(item.img);
	}

	@Override
	public int getTrueX() {
		return super.getTrueX() + MapleStoryClient.getBackX();
	}

	@Override
	public String toString() {
		String str;
		str = "x: " + this.x + " y: " + this.y + "class: " + this.getClass().toString();
		return str;
	}                 

	public abstract void work() ;

	@Override
	public boolean isDie() {
		return this.die;
	}

	public abstract Item copy();
}
