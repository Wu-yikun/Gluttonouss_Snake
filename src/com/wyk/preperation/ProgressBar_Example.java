package com.wyk.preperation;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
/**
 * 
 * @author 57715
 * 
 * 贪吃蛇进度条的设置
 * 
 */
public class ProgressBar_Example {
	public ProgressBar_Example() {
		JFrame jf = new JFrame("进度条君");
		jf.setVisible(true);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setBounds(650, 330, 266, 132);
		Container container = jf.getContentPane();
		container.setBackground(Color.WHITE);
		container.setLayout(new GridBagLayout());
		
		JLabel jl = new JLabel("-Gluttonous Snake-");
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		Font font = new Font("华文新魏",Font.BOLD,16);
		jl.setFont(font);
		final GridBagConstraints constraints1 = new GridBagConstraints();
		constraints1.insets = new Insets(5,0,0,0);
		constraints1.gridx = 0;
		constraints1.gridy = 0;
		container.add(jl,constraints1);
		
		JProgressBar bar = new JProgressBar();
		bar.setIndeterminate(false);
		bar.setStringPainted(true);
		final GridBagConstraints constraints2 = new GridBagConstraints();	//网格组布局管理器
		constraints2.insets = new Insets(5,0,0,0);
 		constraints2.gridx = 0;
		constraints2.gridy = 1;
		container.add(bar,constraints2);
		
		JButton button = new JButton("开始游戏!");
		button.setEnabled(false);
		button.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		final GridBagConstraints constraints3 = new GridBagConstraints();
		constraints3.insets = new Insets(5,0,0,0);
		constraints3.gridx = 0;
		constraints3.gridy = 2;
		container.add(button,constraints3);
		
		Threading t = new Threading(bar,button);
		Thread thread = new Thread(t);
		thread.start();
	}
	public static void main(String[] args) {
		new ProgressBar_Example();
	}
}
class Threading implements Runnable{
	private JProgressBar bar = null;
	private JButton button = null;
	private final int progressValue[] = {5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100};	//模拟任务百分比
	public Threading(JProgressBar bar, JButton button) {
		this.bar = bar;
		this.button = button;
	}

	@Override
	public void run() {
		for(int i=0; i<progressValue.length; i++) {
			try {
				Thread.sleep(100);
				bar.setValue(progressValue[i]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		button.setEnabled(true);
		bar.setString("LoadComplete!");
	}
	
}