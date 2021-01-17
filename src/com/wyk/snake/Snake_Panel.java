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
 * 贪吃蛇面板 + 排行榜
 * 
 */
public class Snake_Panel extends JPanel implements KeyListener,ActionListener {
	private static final long serialVersionUID = 1L;
	//加载玩家信息
//	private Userinfo[] users = new Userinfo[Login.MAX_NUMBER];		//用于对象数组的比较
	//导入图片
	ImageIcon up = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\up.png");
	ImageIcon down = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\down.png");
	ImageIcon left = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\left.png");
	ImageIcon right = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\right.png");
	ImageIcon title = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\title.jpg");
	ImageIcon food = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\food.png");
	ImageIcon body = new ImageIcon("D:\\Software\\eclipse\\eclipse\\eclipse\\eclipse-workspace\\Gluttonous Snake\\src\\body.png");
	//蛇的数据结构的设计
	int[] snakex = new int[750];
	int[] snakey = new int[750];
	int len=3;
	String direction = "R";
	//食物的数据
	Random r = new Random();
	int foodx = r.nextInt(34)*25+25;
	int foody = r.nextInt(24)*25+75;
	//游戏是否开始
	boolean isStarted = false;
	//游戏是否失败
	boolean isFaild = false;
	Timer timer = new Timer(150, this);
	//统计分数 
	private int score =0;
	//当前玩家
	private static Userinfo user = null;
	public static Userinfo getUser() {
		return user;
	}
	public static void setUser(Userinfo user) {
		Snake_Panel.user = user;
	}

	public Snake_Panel(){
		this.setFocusable(true);
		initSnake();//放置静态蛇
		this.addKeyListener(this);	//为面板添加键盘监听接口
		timer.start();
	}
	
	//初始化蛇
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
		//设置画布的背景颜色
		this.setBackground(Color.black);
		g.fillRect(25, 75, 850, 600);
		//设置标题
		title.paintIcon(this, g, 25, 11);
		//画蛇头
		if(direction.equals("R")){
			right.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("L")){
			left.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("U")){
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}else if(direction.equals("D")){
			down.paintIcon(this, g, snakex[0], snakey[0]);
		}
		//画蛇身
		for(int i=1;i<len;i++){
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		//画食物
		food.paintIcon(this, g, foodx, foody);
		//提示语
		if(!isStarted){
			g.setColor(Color.WHITE);
			g.setFont(new Font("华文新魏", Font.BOLD, 30));
			g.drawString("Press Space to Start or Pause~", 250, 275);
			score = 0;		//重新开始,成绩重计
		}
		//画失败提示语--同时检测排行榜
		if(isFaild){
			g.setColor(Color.WHITE);
			g.setFont(new Font("华文新魏", Font.BOLD, 30));
			g.drawString("Game Over ! Press Space to Restart~", 200, 300);
//			user.setScore(score);
			//记录本用户的历史最高记录(当修改文件中的某一对象的属性,那么就将全部对象读出修改后再读入,否则再次打开程序时,以往数据会丢失)
//			int length=0;		//统计人数
//			try {
//				String path = "D:/吴仪坤/players_infomation.txt";
//				File file_alter = new File(path);
//				MyObjectInputStream ois = new MyObjectInputStream(new FileInputStream(file_alter));
//				try {
//					for (int i = 0; true; i++) {
//						users[i] = (Userinfo)ois.readObject();
//						if(users[i].getName().equals(user.getName())&&users[i].getScore()<user.getScore()) {
//							users[i].setScore(score); 		//设置可写入文件的对象
//							len++;
//						}
//					}
//				} catch (Exception e) {
//					ois.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			//重新写入文件中
//			try {
//				FileOutputStream fos = new FileOutputStream("D:/吴仪坤/players_infomation.txt");		//不追加,直接覆盖
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
		//分数的统计
		g.setColor(Color.WHITE);
		g.setFont(new Font("行楷",Font.PLAIN,30));
		g.drawString("Score:"+score, 735, 45);
		//排行榜
		g.setFont(new Font("汉标秃笔体",Font.BOLD,20));
//		File file = new File("D:/吴仪坤/players_infomation.txt");
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
		//防止直接反向转身
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
			//移动身体
			for(int i=len;i>0;i--){
				snakex[i] = snakex[i-1];
				snakey[i] = snakey[i-1];
			}
			//头移动
			if(direction.equals("R")){
				//横坐标 +25
				snakex[0] = snakex[0]+25;
				//碰壁直接穿透
				if(snakex[0] >850){
					snakex[0] = 25;
				}
			}else if(direction.equals("L")){
				//横坐标 -25
				snakex[0] = snakex[0]-25;
				//碰壁直接穿透
				if(snakex[0] <25){
					snakex[0] = 850;
				}
			}else if(direction.equals("U")){
				//纵坐标 -25
				snakey[0] = snakey[0] -25;
				//碰壁直接穿透
				if(snakey[0] <75){
					snakey[0] = 650;
				}
			}else if(direction.equals("D")){
				//纵坐标 +25
				snakey[0] = snakey[0] +25;
				//碰壁直接穿透
				if(snakey[0]>650){
					snakey[0] = 75;
				}
			}
			//吃食物的逻辑
			if(snakex[0] == foodx && snakey[0] == foody){
				len++;
				score+=3;
				//重置食物
				foodx = r.nextInt(34)*25+25;
				foody = r.nextInt(24)*25+75;
				//解决食物出现在蛇身内的bug
				for(int i=1;i<len;i++) {
					if(snakex[i]==foodx && snakey[i]==foody) {
						foodx = r.nextInt(34)*25+25;
						foody = r.nextInt(24)*25+75;
					}
				}
			}
			//判断游戏失败
			for(int i=1;i<len;i++){
				if(snakex[0] == snakex[i] && snakey[0] == snakey[i]){
					isFaild = true;
				}
			}
		}
		repaint();
	}
}