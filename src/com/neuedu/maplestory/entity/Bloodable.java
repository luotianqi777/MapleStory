package com.neuedu.maplestory.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.neuedu.maplestory.client.MapleStoryClient;

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


}
