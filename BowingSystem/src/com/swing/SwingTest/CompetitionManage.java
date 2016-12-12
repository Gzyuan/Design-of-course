package com.swing.SwingTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.buffer.GradeBuffer;
import com.controller.AgainstController;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompetitionManage {

	private JFrame frame;
	
	public CompetitionManage() {
		GradeBuffer.me.life();
		initialize();
		this.frame.setTitle("赛事管理系统");
		this.frame.setLayout(new BorderLayout());
		this.frame.setVisible(true);
		this.frame.setResizable(false);
	}
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100,100,640, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Color c = new Color(0,0,255);
		
		JPanel imagePanel = new JPanel();
//		imagePanel.setBounds(171, 0, 349, 445);
		frame.getContentPane().add(imagePanel);
		imagePanel.setLayout(null);
		
		//获取面板的宽度。
		int windowWidth = this.frame.getWidth();
		//获取面板的高度
		int windowHeight = this.frame.getHeight();
		//引入工具包。
		Toolkit kit = Toolkit.getDefaultToolkit();
		//获取屏幕大小。
		Dimension screenSize = kit.getScreenSize();
		//获取屏幕宽度。
		int screenWidth = screenSize.width;
		//获取屏幕高度。
		int screenHeight = screenSize.height;
		//面板居中。
		this.frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JButton competitionBtn = new JButton("比 赛 对 阵 表");
		competitionBtn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		competitionBtn.setBounds(60, 80, 240, 60);
//		frame.getContentPane().add(competitionBtn);
		competitionBtn.setBackground(c);
		competitionBtn.setOpaque(false);
		//为按钮添加监听事件。
		competitionBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					AgainstController.me.checkReady();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(AgainstController.me.isReady()){
					
					//创建赛事对阵表面板对象。
					new AgainstJFrame();
					//隐藏成绩查询面板。
//					CompetitionManage.this.frame.setVisible(false);
					
					
					
				}else{
					JOptionPane.showMessageDialog(frame,"比赛对阵表生成失败，参赛人数应为2^k个！");
					
				}
				
			}
			
		});
		
		JButton gradeSaveBtn = new JButton("成 绩 录 入");
		gradeSaveBtn.setBackground(c);
		gradeSaveBtn.setOpaque(false);
		gradeSaveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(AgainstController.me.isReady()){
				//创建成绩保存面板。
				new SaveGradeJFrame();
				//隐藏赛事管理系统面板。
//				CompetitionManage.this.frame.setVisible(false);
				}else{
					
					JOptionPane.showMessageDialog(frame,"比赛还没开始，无法录入成绩！");
					
				}
			}
		});
		gradeSaveBtn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		gradeSaveBtn.setBounds(60, 178, 240, 60);
//		frame.getContentPane().add(gradeSaveBtn);
		
		JButton backToMainJFrameBtn = new JButton("返 回 主 菜 单");
		backToMainJFrameBtn.setBackground(c);
		backToMainJFrameBtn.setOpaque(false);
		backToMainJFrameBtn.setFont(new Font("微软雅黑",Font.PLAIN,26));
		backToMainJFrameBtn.setBounds(60, 276, 240, 60);
//		frame.getContentPane().add(backBtn);
		//为返回按钮添加监听事件。
		backToMainJFrameBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//创建保龄球计分系统主菜单。
				new MainJFrame();
				//隐藏成绩查询面板。
				CompetitionManage.this.frame.setVisible(false);
			}
			
		});
		
		/****************************************/
		
		ImageIcon img = new ImageIcon("./data/backgroundImg/background.jpg");
		img.setImage(img.getImage().getScaledInstance(640,452,Image.SCALE_DEFAULT));
		JLabel imgLabel = new JLabel(img);
		
//		imgLabel.setBounds(0, 0, this.frame.getWidth(), this.frame.getHeight());
		
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		this.frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imagePanel = (JPanel)this.frame.getContentPane();
		imagePanel.setOpaque(false);
        imagePanel.setLayout(new FlowLayout());
        imagePanel.add(competitionBtn);
        imagePanel.add(gradeSaveBtn);
        imagePanel.add(backToMainJFrameBtn);
		
		
		
		
		/************************************************/
		
	}
}