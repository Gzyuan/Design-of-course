package com.swing.SwingTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JPanel;

import com.buffer.GradeBuffer;
import com.controller.AgainstController;
import com.dao.factory.DAOFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainJFrame {

	private JFrame frame;

	public MainJFrame() {
		GradeBuffer.me.life();
		initialize();
		this.frame.setLayout(new BorderLayout());
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("保龄球计分系统主菜单");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//背影颜色随便设任意值,只起占位作用。
		Color c = new Color(0,0,255);   
		//获取面板宽度。
		int windowWidth = this.frame.getWidth();
		//获取面板高度.
		int windowHeight = this.frame.getHeight();
		//引入工具包
		Toolkit kit = Toolkit.getDefaultToolkit();
		//获取屏幕大小。
		Dimension screenSize = kit.getScreenSize();
		//获取屏幕宽度.
		int screenWidth = screenSize.width;
		//获取屏幕高度。
		int screenHeight = screenSize.height;
		//实现面板居中.
		this.frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JPanel imagePanel = new JPanel();
//		imagePanel.setBounds(171, 0, 349, 445);
		frame.getContentPane().add(imagePanel);
		imagePanel.setLayout(null);
		
		JButton btnForPlayerManage = new JButton("选 手 管 理");
		btnForPlayerManage.setBackground(c);
		//设置背景透明  
		btnForPlayerManage.setOpaque(false);
		btnForPlayerManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建选手管理面板对象。
				new PlayerManage();
				//隐藏保龄球计分系统主菜单。
				MainJFrame.this.frame.setVisible(false);
			}
		});
		btnForPlayerManage.setBounds(60, 86, 320, 80);
		btnForPlayerManage.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		
		
		JButton btnForCompetition = new JButton("赛 事 管 理");
		btnForCompetition.setBackground(c);
		//设置背景透明  
		btnForCompetition.setOpaque(false);
		btnForCompetition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//显示赛事管理系统面板。
				new CompetitionManage();
				//隐藏主菜单面板。
				MainJFrame.this.frame.setVisible(false);
			}
		});
		btnForCompetition.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		btnForCompetition.setBounds(60, 252, 320, 80);
		
		
		JButton btnForGradeSearch = new JButton("成 绩 管 理");
		btnForGradeSearch.setBackground(c);
		//设置背景透明  
		btnForGradeSearch.setOpaque(false);
		btnForGradeSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(DAOFactory.getIGradeDAOInstance().getSize()>0){
						//创建选手成绩查询系统面板。
						new GradeSearchJFrame();
						//隐藏保龄球计分系统主菜单。
						MainJFrame.this.frame.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(MainJFrame.this.frame,"比赛还没开始，暂无成绩！");
							
						}
				} catch (HeadlessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		btnForGradeSearch.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		btnForGradeSearch.setBounds(60, 408, 320, 80);
		//创建图像对象。
		ImageIcon img = new ImageIcon("./data/backgroundImg/background.jpg");
		JLabel imgLabel = new JLabel(img);
		
//		imgLabel.setBounds(0, 0, this.frame.getWidth(), this.frame.getHeight());
		
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		this.frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imagePanel = (JPanel)this.frame.getContentPane();
		imagePanel.setOpaque(false);
        imagePanel.setLayout(new FlowLayout());
        imagePanel.add(btnForPlayerManage);
        imagePanel.add(btnForCompetition);
        imagePanel.add(btnForGradeSearch);
        
	}
}
