package com.neuedu.maplestory.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.neuedu.maplestory.constant.Constant;

/**
 * Create Frame Of Client:
 * 
 * 1.Extends Frame Class
 * 
 * 2.Defined LoadFrame Method
 * 
 * 3.Write Main Method
 * 
 * @author Lain
 *
 */
@SuppressWarnings("serial")
public class FrameUtil extends Frame {

	/**
	 * Load Frame
	 */
	public void loadFrame() {
		// 1.set size
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
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
		// 6.Flush Thread
		new FlushThread().start();
	}

	/**
	 * Flush Thread
	 * 
	 * @author Lain
	 *
	 */
	class FlushThread extends Thread {
		@Override
		public void run() {
			// repaint
			for (;;) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 解决图片闪烁的问题，用双缓冲方法解决闪烁问题
	Image backImg = null;

	// 重写update()方法，在窗口的里层添加一个虚拟的图片
	@Override
	public void update(Graphics g) {
		if (backImg == null) {
			// 如果虚拟图片不存在，创建一个和窗口一样大小的图片
			backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		}
		// 获取到虚拟图片的画笔
		Graphics backg = backImg.getGraphics();
		Color c = backg.getColor();
		backg.setColor(Color.white);
		backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backg.setColor(c);
		// 调用虚拟图片的paint()方法，每50ms刷新一次
		paint(backg);
		g.drawImage(backImg, 0, 0, null);
	}

}
