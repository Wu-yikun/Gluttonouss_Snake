package com.wyk.snake;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 * 
 * @author 57715
 * 
 * ̰���ߴ���:���̰�������
 * 
 */
public class Snake_ {

	public Snake_() {
		JFrame jf = new JFrame(Snake_Panel.getUser().getName()+"___̰����1.6(���°汾)");
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setSize(new Dimension(900,720));
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		Snake_Panel panel = new Snake_Panel();
		Container container = jf.getContentPane();
		container.add(panel);
	}
}