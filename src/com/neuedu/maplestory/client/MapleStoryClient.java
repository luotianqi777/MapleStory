package com.neuedu.maplestory.client;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.neuedu.maplestory.util.GameUtil;

/**
 * Create Frame Of Client: 1.Extends Frame Class 2.Defined LoadFrame Method
 * 3.Write Main Method
 * Load Image on Frame:
 * 1.
 * @author Lain
 *
 */

public class MapleStoryClient extends Frame {

	/**
	 * Load Frame
	 */
	void loadFrame() {
		// 1.set size
		this.setSize(1500, 900);
		// 2.set frame location
		this.setLocationRelativeTo(null);
		// 3.Load frame
		this.setVisible(true);
		// 4.Set Closable
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// close frame
				System.exit(0);
			}
		});
		// 5.Set frame Title
		this.setTitle("Neuedu_MapleStory");
		// 6.Set Background
	}

	/**
	 * Draw Background Image
	 */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		String imgPath = "com/neuedu/maplestory/img/test.jpg";
		g.drawImage(GameUtil.getImage(imgPath), 0, 0, null);
	}
	
	/**
	 * Main Method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MapleStoryClient();
	}

	public MapleStoryClient() {
		// TODO Auto-generated constructor stub
		// load frame
		loadFrame();
	}
}
