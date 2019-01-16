package com.neuedu.maplestory.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.neuedu.maplestory.client.MapleStoryClient;
import com.neuedu.maplestory.constant.Constant;

public interface Bloodable {

	public default void drawBloodBar(Graphics g, NPC npc, boolean move) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		// ground
		g.drawRect(npc.x + (move ? MapleStoryClient.getBackX() : 0), npc.y - npc.height / 2, // location
				npc.width, npc.width / 5); // size
		g.drawRect(npc.x + (move ? MapleStoryClient.getBackX() : 0), npc.y - npc.height / 2 + npc.width / 5, // location
				npc.width, npc.width / 5); // size
		g.setColor(Color.GREEN);
		// HP
		g.fillRect(npc.x + (move ? MapleStoryClient.getBackX() : 0), npc.y - npc.height / 2, // location
				(int) (npc.width * (npc.HP / (double) npc.MAX_HP)), npc.width / 5);// size
		g.setColor(Color.BLUE);
		// MP
		g.fillRect(npc.x + (move ? MapleStoryClient.getBackX() : 0), npc.y - npc.height / 2 + npc.width / 5, // location
				(int) (npc.width * (npc.MP / (double) npc.MAX_MP)), npc.width / 5);// size
		g.setColor(c);
	}

	public default void drawMenu(Graphics g, Hero hero) {

		Color c = g.getColor();
		g.setColor(Color.RED);
		g.setFont(new Font(null, Font.BOLD, 20));
		g.drawString("HP: " + hero.HP, Constant.GAME_WIDTH - 150, 70);
		g.setColor(Color.BLUE);
		g.drawString("MP: " + hero.MP, Constant.GAME_WIDTH - 150, 100);
		g.setColor(Color.GREEN);
		g.drawString("Speed: " + hero.speed, Constant.GAME_WIDTH - 150, 130);
		g.setColor(Color.PINK);
		g.drawString("Kill: " + hero.kill, Constant.GAME_WIDTH - 150, 160);
		g.setColor(c);
	}
	

}
