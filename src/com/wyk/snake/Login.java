package com.wyk.snake;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * 
 * @author 57715
 * 
 * 登陆界面的设置
 * 
 * 在反序列化的时候每个ObjectInputStream对象只会读取一个header,遇到第二个的时候就会抛出异常;
 * 解决方法:
 * 1.运用集合:
 * 2.
 * 3.
 * 
 */

public class Login {
	public static final int MAX_NUMBER = 15;			//系统可载入的玩家人数上限
	private Userinfo[] user = new Userinfo[MAX_NUMBER];
	public Login() {
		JFrame jf = new JFrame("登录界面");
		jf.setSize(new Dimension(250,164));
		jf.setVisible(true);
		
		
		
		jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		Container container = jf.getContentPane();
		container.setLayout(new GridLayout(3,1));
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setBackground(Color.white);
		JLabel id = new JLabel("账号:");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(id);
		JTextField line1 = new JTextField(16);
		panel1.add(line1);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.white);
		JLabel password = new JLabel("密码:");
		password.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(password);
		JPasswordField line2 = new JPasswordField(16);
		line2.setEchoChar('*');
		panel2.add(line2);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.setBackground(Color.white);
		JButton ensure = new JButton("确定");
		JButton resumeLoad= new JButton("重置");
		//重置-按钮响应
		resumeLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				line1.setText("");
				line2.setText("");
			}
		});
		//确定-按钮响应
		ensure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					//反序列化
					String path = "D:/吴仪坤/players_infomation.txt";
					File file = new File(path);
					MyObjectInputStream ois = new MyObjectInputStream(new FileInputStream(file));
					try {
						for (int i = 0; true; i++) {
							//在反序列化的时候每个ObjectInputStream对象只会读取一个header,遇到第二个的时候就会抛出异常
							user[i] = (Userinfo)ois.readObject();		
						}
					} catch (Exception e) {
						ois.close();
					}
					//查看该账号是否已经注册过
					boolean isExit = false;		//设置账号状态为:未注册
					int position = 0;
					try {
						for (int i=0; i<MAX_NUMBER; i++) {
							if(user[i].getName().equals(line1.getText())&&(user[i].getPassword().equals(String.valueOf(line2.getPassword())))) {
								isExit = true;		//设置账号状态为:已注册
								position = i;
								break;
							}
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(jf, "用户名或密码错误", "错误提示框", JOptionPane.ERROR_MESSAGE);
					}
					if(isExit) {
						Snake_Panel.setUser(user[position]);	//设置当前正在游戏的玩家
						jf.dispose();
						new Snake_();		//跳转游戏界面
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel3.add(ensure);
		panel3.add(resumeLoad);
		
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
	}
}
class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private float score;
	public Student(String name, int age, float score) {
		this.name = name;
		this.age = age;
		this.score = score;
	}
	@Override
	public String toString() {
		return "[姓名]:"+name+" [年龄]:"+age+" [成绩]:"+score;
	}
}