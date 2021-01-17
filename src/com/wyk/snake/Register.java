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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

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
 * 定义用户信息 + 注册界面
 * 
 * 1.超出账号或者密码长度->弹出消息框报错
 * 2.利用序列化和反序列化使得Userinfo信息被保留
 * 
 */
public class Register{
	private Userinfo[] user = new Userinfo[Login.MAX_NUMBER];
	public Register() {
		JFrame register = new JFrame();
		register.setSize(new Dimension(270,240));
		register.setVisible(true);
		register.setTitle("用户注册窗口");
		register.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container container = register.getContentPane();
		container.setBackground(Color.WHITE);
		register.setLocationRelativeTo(null);		//居中显示
		container.setLayout(new GridLayout(4,1));
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setBackground(Color.white);
		JLabel id = new JLabel("账号:");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel space1 = new JLabel("      ");		//对齐
		panel1.add(id);
		panel1.add(space1);
		JTextField line1 = new JTextField(16);
		panel1.add(line1);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.white);
		JLabel password = new JLabel("密码:");
		password.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel space2 = new JLabel("      ");		//对齐
		panel2.add(password);
		panel2.add(space2);
		JPasswordField line2 = new JPasswordField(16);
		line2.setEchoChar('*');
		panel2.add(line2);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.setBackground(Color.white);
		JLabel password_check = new JLabel("确认密码:");
		password_check.setHorizontalAlignment(SwingConstants.CENTER);
		panel3.add(password_check);
		JPasswordField line3 = new JPasswordField(16);
		line3.setEchoChar('*');
		panel3.add(line3);
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(new FlowLayout());
		panel4.setBackground(Color.white);
		JButton sign_in = new JButton("注册");
		JButton exit= new JButton("退出");
		panel4.add(sign_in);
		panel4.add(exit);
		//返回上一个窗口
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register.dispose();
			}
		});
		//注册信息核对
		sign_in.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//序列化
				int id_length = new String(line1.getText()).length();
				int password_length = String.valueOf(line2.getPassword()).length();
				if((id_length<12)&&(password_length>7)&&(password_length<17)) {
					if(String.valueOf(line3.getPassword()).equals(String.valueOf(line2.getPassword()))){
						try {
							String path = "D:/吴仪坤/players_infomation.txt";
							File file = new File(path);
							MyObjectInputStream ois = new MyObjectInputStream(new FileInputStream(file));
							try {
								for (int i=0; true; i++) {
									user[i] = (Userinfo)ois.readObject();
								}
							} catch (Exception e) {
								ois.close();
							}
							boolean isRegister = false;
							for(int i=0;i<Login.MAX_NUMBER;i++) {
								if(user[i]==null) {
									break;
								}else if(user[i].getName().equals(line1.getText())) {
									isRegister = true;		//用户名已经有人注册过
									break;
								}
							}
							if (isRegister) {
								JOptionPane.showMessageDialog(register, "注册失败,该用户已存在");
								register.dispose();
							}else {
								FileOutputStream fos = new FileOutputStream("D:/吴仪坤/players_infomation.txt",true);
								MyObjectOutputStream oos = new MyObjectOutputStream(fos);
								oos.writeObject(new Userinfo(line1.getText(),String.valueOf(line2.getPassword())));
								JOptionPane.showMessageDialog(register, "注册成功");
								register.dispose();
								oos.close();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(register, "两次密码输入不一致");
					}
				}else {
					JOptionPane.showMessageDialog(register, "用户名长度不超过12位,密码长度为8-16位~");
				}
			}
		});
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
		container.add(panel4);
	}
}
class Userinfo implements Comparable<Userinfo>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;		//用户名
	private String password;	//密码
	private int score;			//成绩
	public Userinfo() {
		
	}
	public Userinfo(String name, String password) {
		this.name = name;
		this.password = password;
		this.score = 0;		//初始成绩
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Player:" + name + "--Score:" + score ;
	}
	//用于比较分数,设置排行榜
	@Override
	public int compareTo(Userinfo o) {
		if(this.score>o.score) {
			return 1;
		}else if(this.score<o.score) {
			return -1;
		}else {
			return 0;
		}
	}
}
class MyObjectOutputStream extends ObjectOutputStream{

	protected MyObjectOutputStream(OutputStream out) throws IOException{
		super(out);
	}
	
	@Override
	protected void writeStreamHeader() throws IOException {
		//重新读取头部信息方法:不写入头部信息
		super.reset();
	}
}
class MyObjectInputStream extends ObjectInputStream{

	public MyObjectInputStream(InputStream in) throws IOException {
		super(in);
	}
	
	@Override
	protected void readStreamHeader() throws IOException, StreamCorruptedException {
		//重写读取头部信息方法:什么也不做
	}
}