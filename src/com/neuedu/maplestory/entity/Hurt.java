package com.neuedu.maplestory.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Hurt {
	int val;
	int x, y;
	Font font;

	Hurt(int val, int x, int y) {
		this.val = val;
		this.x = x;
		this.y = y;
		font = new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20);
	}

	public void draw(Graphics g) {

		move();

		Color c = g.getColor();
		Font f = g.getFont();
		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString(Integer.toString(val), x, y);
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
