package com.neuedu.maplestory.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HeadInfo {
	String info;
	int x, y;
	Font font;
	private Color color;

	public HeadInfo(String info, int x, int y, Color color) {
		this.info = info;
		this.x = x;
		this.y = y;
		this.color = color;
		font = new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20);
	}

	public void draw(Graphics g) {

		move();

		Color c = g.getColor();
		Font f = g.getFont();
		g.setColor(color);
		g.setFont(font);
		g.drawString(info, x, y - 30);
		g.setColor(c);
		g.setFont(f);

	}

	public void move() {
		y -= 3;
		x -= 1;
		font = new Font(font.getFontName(), font.getStyle(), font.getSize() + 4);
	}

	public boolean isDie() {
		return font.getSize() > 50;
	}

}
