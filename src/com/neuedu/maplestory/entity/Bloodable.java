package com.neuedu.maplestory.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.neuedu.maplestory.client.MapleStoryClient;

public interface Bloodable {

	public default void drawBloodBar(Graphics g, NPC npc, boolean move) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.drawRect(npc.x + (move ? MapleStoryClient.getBackX() : 0), npc.y - npc.height / 4, npc.width, npc.width / 5);
		g.setColor(Color.GREEN);
		g.fillRect(npc.x + (move ? MapleStoryClient.getBackX() : 0), npc.y - npc.height / 4,
				(int) (npc.width * (npc.HP / (double) npc.MAX_HP)), npc.width / 5);
		g.setColor(c);
	}

}
