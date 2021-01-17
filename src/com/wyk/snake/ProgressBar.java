package com.wyk.snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class ProgressBar {
	
	public ProgressBar() {
		JFrame jf = new JFrame("Gluttonous Snake");
		jf.setVisible(true);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setBounds(600, 300, 350, 200);
		Container container = jf.getContentPane();
		container.setBackground(Color.WHITE);
		container.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		container.add(panel);
		
		JLabel jl = new JLabel("-Gluttonous Snake-");
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		Font font = new Font("华文新魏",Font.BOLD,20);
		jl.setFont(font);
		final GridBagConstraints constraints1 = new GridBagConstraints();	//网格组布局管理器
		constraints1.insets = new Insets(5,0,20,0);
		constraints1.gridx = 0;
		constraints1.gridy = 0;
		panel.add(jl,constraints1);
		
		JProgressBar bar = new JProgressBar();
		bar.setIndeterminate(false);
		bar.setStringPainted(true);
		final GridBagConstraints constraints2 = new GridBagConstraints();
		constraints2.insets = new Insets(10,0,0,0);
 		constraints2.gridx = 0;
		constraints2.gridy = 1;
		panel.add(bar,constraints2);
		
		JButton button1 = new JButton("登录");
		button1.setEnabled(false);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				new Login();
			}
		});
		final GridBagConstraints constraints3 = new GridBagConstraints();
		constraints3.insets = new Insets(25,-100,0,0);
		constraints3.gridx = 1;
		constraints3.gridy = 2;
		panel.add(button1,constraints3);
		
		JButton button2 = new JButton("注册");
		button2.setEnabled(false);
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Register();
			}
		});
		final GridBagConstraints constraints4 = new GridBagConstraints();
		constraints4.insets = new Insets(25,-80,0,0);
		constraints4.gridx = 0;
		constraints4.gridy = 2;
		panel.add(button2,constraints4);
		
		final GridBagConstraints constraints5 = new GridBagConstraints();
		constraints5.insets = new Insets(10,0,0,0);
		constraints5.gridx = 2;
		constraints5.gridy = 0;
		constraints5.gridheight = 3;
		constraints5.gridwidth = 2;
		constraints5.fill = GridBagConstraints.BOTH;
		JLabel jlabel = new JLabel();
		URL url = ProgressBar.class.getResource("snake.png");
		Icon icon = new ImageIcon(url);
		jlabel.setIcon(icon);
		panel.add(jlabel,constraints5);
		
		Threading thread = new Threading(bar,button1,button2);
		Thread agency = new Thread(thread);
		agency.start();
	}
	public static void main(String[] args) {
		new ProgressBar();			//贪吃蛇游戏的入口~
	}
}
class Threading implements Runnable{
	private JProgressBar bar = null;
	private JButton button1 = null;
	private JButton button2 = null;
	private final int progressValue[] = {5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100};	//模拟任务百分比
	public Threading(JProgressBar bar, JButton button1, JButton button2) {
		this.bar = bar;
		this.button1 = button1;
		this.button2 = button2;
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
		button1.setEnabled(true);
		button2.setEnabled(true);
		bar.setString("LoadComplete!");
	}
}