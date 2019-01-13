package com.neuedu.maplestory.client;

import java.awt.Frame;

/**
 * 运行冒险岛项目主文件，客户端 生成窗口 
 * 1.extends Frame类库 
 * 2.自定义加载窗口的方法
 * 3.主函数启动窗口
 * 
 * @author Lain
 *
 */

public class MapleStoryClient extends Frame {

	/**
	 * 加载窗口的方法
	 */
	void loadFrame() {
		// 设置窗口大小
		this.setSize(1920, 1080);
		// 设置窗口生成时的位置
		this.setLocation(0, 0);
		// 显示窗口
		this.setVisible(true);
		// 设置可关闭
	}

	/**
	 * 主函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MapleStoryClient();
	}
	
	/**
	 * 构造方法
	 */
	public MapleStoryClient() {
		// TODO Auto-generated constructor stub
		loadFrame();
	}
}
