package com.wyk.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.Arrays;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * 
 * @author 57715
 * 
 * ̰������� + ���а�
 * 
 */
public class Snake_Panel extends JPanel implements KeyListener,ActionListener {
	private static final long serialVersionUID = 1L;
	//���������Ϣ
//	private Userinfo[] users = new Userinfo[Login.MAX_NUMBER];		//���ڶ�������ıȽ�
	//����ͼƬ
	ImageIcon up = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\up.png");
	ImageIcon down = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\down.png");
	ImageIcon left = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\left.png");
	ImageIcon right = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\right.png");
	ImageIcon title = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\title.jpg");
	ImageIcon food = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\food.png");
	ImageIcon body = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\body.png");
	//�ߵ����ݽṹ�����
	int[] snakex = new int[750];
	int[] snakey = new int[750];
	int len=3;
	String direction = "R";
	//ʳ�������
	Random r = new Random();
	int foodx = r.nextInt(34)*25+25;
	int foody = r.nextInt(24)*25+75;
	//��Ϸ�Ƿ�ʼ
	boolean isStarted = false;
	//��Ϸ�Ƿ�ʧ��
	boolean isFaild = false;
	Timer timer = new Timer(150, this);
	//ͳ�Ʒ��� 
	private int score =0;
	//��ǰ���
	private static Userinfo user = null;
	public static Userinfo getUser() {
		return user;
	}
	public static void setUser(Userinfo user) {
		Snake_Panel.user = user;
	}

	public Snake_Panel(){
		this.setFocusable(true);
		initSnake();//���þ�̬��
		this.addKeyListener(this);	//Ϊ�����Ӽ��̼����ӿ�
		timer.start();
	}
	
	//��ʼ����
	public void initSnake(){
		isStarted = false;
		isFaild = false;
		len = 3;
		direction = "R";
		snakex[0] = 100;
		snakey[0] = 100;
		snakex[1] = 75;
		snakey[1] = 100;
		snakex[2] = 50;
		snakey[2] = 100;
	}
	
	public void paint(Graphics g){
		//���û����ı�����ɫ
		this.setBackground(Color.black);
		g.fillRect(25, 75, 850, 600);
		//���ñ���
		title.paintIcon(this, g, 25, 11);
		//����ͷ
		if(direction.equals("R")){
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("L")){
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("U")){
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("D")){
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		//������
		for(int i=1;i<len;i++){
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		//��ʳ��
		food.paintIcon(this, g, foodx, foody);
		//��ʾ��
		if(!isStarted){
			g.setColor(Color.WHITE);
			g.setFont(new Font("������κ", Font.BOLD, 30));
			g.drawString("Press Space to Start or Pause~", 250, 275);
			score = 0;		//���¿�ʼ,�ɼ��ؼ�
		}
		//��ʧ����ʾ��--ͬʱ������а�
		if(isFaild){
			g.setColor(Color.WHITE);
			g.setFont(new Font("������κ", Font.BOLD, 30));
			g.drawString("Game Over ! Press Space to Restart~", 200, 300);
//			user.setScore(score);
			//��¼���û�����ʷ��߼�¼(���޸��ļ��е�ĳһ���������,��ô�ͽ�ȫ����������޸ĺ��ٶ���,�����ٴδ򿪳���ʱ,�������ݻᶪʧ)
//			int length=0;		//ͳ������
//			try {
//				String path = "D:/������/players_infomation.txt";
//				File file_alter = new File(path);
//				MyObjectInputStream ois = new MyObjectInputStream(new FileInputStream(file_alter));
//				try {
//					for (int i = 0; true; i++) {
//						users[i] = (Userinfo)ois.readObject();
//						if(users[i].getName().equals(user.getName())&&users[i].getScore()<user.getScore()) {
//							users[i].setScore(score); 		//���ÿ�д���ļ��Ķ���
//							len++;
//						}
//					}
//				} catch (Exception e) {
//					ois.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			//����д���ļ���
//			try {
//				FileOutputStream fos = new FileOutputStream("D:/������/players_infomation.txt");		//��׷��,ֱ�Ӹ���
//				MyObjectOutputStream oos = new MyObjectOutputStream(fos);
//				for (int i = 0; i < length; i++) {
//					oos.writeObject(users[i]);
//				}
//				oos.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		//������ͳ��
		g.setColor(Color.WHITE);
		g.setFont(new Font("�п�",Font.PLAIN,30));
		g.drawString("Score:"+score, 735, 45);
		//���а�
		g.setFont(new Font("����ͺ����",Font.BOLD,20));
//		File file = new File("D:/������/players_infomation.txt");
//		try {
//			MyObjectInputStream ois = new MyObjectInputStream(new FileInputStream(file));
//			try {
//				for (int i = 0; true; i++) {
//					users[i] = (Userinfo)ois.readObject();
//				}
//			} catch (IOException e) {
//				ois.close();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Arrays.sort(users);
//		for(int i=0; i<3; i++) {
//			g.drawString(users[i].toString(),90,25+20*i);
//		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE){
			if(isFaild){
				initSnake();
			}else{
				isStarted = !isStarted;
			}
		//��ֱֹ�ӷ���ת��
		}else if(keyCode == KeyEvent.VK_UP && !direction.equals("D")){
			direction="U";
		}else if(keyCode == KeyEvent.VK_DOWN && !direction.equals("U")){
			direction="D";
		}else if(keyCode == KeyEvent.VK_LEFT && !direction.equals("R")){
			direction="L";
		}else if(keyCode == KeyEvent.VK_RIGHT && !direction.equals("L")){
			direction="R";
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(isStarted && !isFaild){
			//�ƶ�����
			for(int i=len;i>0;i--){
				snakex[i] = snakex[i-1];
				snakey[i] = snakey[i-1];
			}
			//ͷ�ƶ�
			if(direction.equals("R")){
				//������ +25
				snakex[0] = snakex[0]+25;
				//����ֱ�Ӵ�͸
				if(snakex[0] >850){
					snakex[0] = 25;
				}
			}else if(direction.equals("L")){
				//������ -25
				snakex[0] = snakex[0]-25;
				//����ֱ�Ӵ�͸
				if(snakex[0] <25){
					snakex[0] = 850;
				}
			}else if(direction.equals("U")){
				//������ -25
				snakey[0] = snakey[0] -25;
				//����ֱ�Ӵ�͸
				if(snakey[0] <75){
					snakey[0] = 650;
				}
			}else if(direction.equals("D")){
				//������ +25
				snakey[0] = snakey[0] +25;
				//����ֱ�Ӵ�͸
				if(snakey[0]>650){
					snakey[0] = 75;
				}
			}
			//��ʳ����߼�
			if(snakex[0] == foodx && snakey[0] == foody){
				len++;
				score+=3;
				//����ʳ��
				foodx = r.nextInt(34)*25+25;
				foody = r.nextInt(24)*25+75;
				//���ʳ������������ڵ�bug
				for(int i=1;i<len;i++) {
					if(snakex[i]==foodx && snakey[i]==foody) {
						foodx = r.nextInt(34)*25+25;
						foody = r.nextInt(24)*25+75;
					}
				}
			}
			//�ж���Ϸʧ��
			for(int i=1;i<len;i++){
				if(snakex[0] == snakex[i] && snakey[0] == snakey[i]){
					isFaild = true;
				}
			}
		}
		repaint();
	}
}