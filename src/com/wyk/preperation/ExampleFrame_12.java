package com.wyk.preperation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ExampleFrame_12 extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		ExampleFrame_12 frame = new ExampleFrame_12();
		frame.setVisible(true);
	}
	
	public ExampleFrame_12() {
		super();
		getContentPane().setLayout(new GridBagLayout());
		setTitle("ʹ�ý�����");
		setBounds(100, 100, 266, 132);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JLabel label = new JLabel();
		label.setFont(new Font("", Font.BOLD, 16));
		label.setText("Loading...");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		getContentPane().add(label, gridBagConstraints);
		
		final JProgressBar progressBar = new JProgressBar();// ��������������
		progressBar.setStringPainted(true);// ������ʾ��ʾ��Ϣ
//		progressBar.setIndeterminate(false);// ���ò��ò�ȷ��������
//		progressBar.setString("Loading...");// ������ʾ��Ϣ,Ĭ��Ϊ���ֽ�����
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridx = 0;
		getContentPane().add(progressBar,gridBagConstraints_1);
		
		final JButton button = new JButton();
		button.setText("���");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_2.gridy = 2;
		gridBagConstraints_2.gridx = 1;
		getContentPane().add(button, gridBagConstraints_2);
		
		new Progress(progressBar, button).start();// �����߳�ģ��һ��������������
	}
	
	class Progress extends Thread {// �����߳�ģ��һ��������������
		private final int[] progressValue = { 6, 18, 27, 39, 51, 66, 81,
				100 };// ģ��������ɰٷֱ�
		private JProgressBar progressBar;// ����������
		private JButton button;// ��ɰ�ť����
		public Progress(JProgressBar progressBar, JButton button) {
			this.progressBar = progressBar;
			this.button = button;
		}
		
		public void run() {
			// ͨ��ѭ������������ɰٷֱ�
			for (int i = 0; i < progressValue.length; i++) {
				try {
					Thread.sleep(1000);// ���߳�����1��
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				progressBar.setValue(progressValue[i]);// ����������ɰٷֱ�
			}
//			progressBar.setIndeterminate(false);// ���ò���ȷ��������
			progressBar.setString("��ʼ��Ϸ");// ������ʾ��Ϣ
			button.setEnabled(true);// ���ð�ť����
		}
	}
}