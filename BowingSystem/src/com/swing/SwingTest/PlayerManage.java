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
import com.dao.factory.DAOFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerManage {

	private JFrame frame;
	
	public PlayerManage() {
		GradeBuffer.me.life();
		initialize();
		this.frame.setTitle("选手信息管理系统");
		this.frame.setLayout(new BorderLayout());
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}
	//初始化面板
	private void initialize() {
		//面板居中。
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 452);
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
		//实现居中.
		this.frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JPanel imagePanel = new JPanel();
//		imagePanel.setBounds(171, 0, 349, 445);
		frame.getContentPane().add(imagePanel);
		imagePanel.setLayout(null);
		
		//创建按钮。
		JButton saveInfoBtn = new JButton("选 手 信 息 录 入");
		saveInfoBtn.setBackground(c);
		saveInfoBtn.setOpaque(false);
		//为按钮添加监听事件。
		saveInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建选手信息录入面板对象。
				new PlayerManageJFrame_1();
				//隐藏选手信息管理面板。
//				PlayerManage.this.frame.setVisible(false);
			}
		});
		saveInfoBtn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		saveInfoBtn.setBounds(60, 80, 240, 60);
//		frame.getContentPane().add(saveInfoBtn);
		
		JButton resetInfoBtn = new JButton("选 手 信 息 查 询");
		resetInfoBtn.setBackground(c);
		resetInfoBtn.setOpaque(false);
		//为按钮添加监听事件。
		resetInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(DAOFactory.getIPlayerDAOInstance().getSize()<=0){
						
						JOptionPane.showMessageDialog(frame,"没有选手信息，请录入选手信息");
						
						
					}else{
					//创建查询参赛选手信息面板。
					new SearchForPlayer();
					//隐藏选手信息管理系统面板。
//					PlayerManage.this.frame.setVisible(false);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		resetInfoBtn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		resetInfoBtn.setBounds(60, 178, 240, 60);
//		frame.getContentPane().add(resetInfoBtn);
		
		JButton backToMainJFrameBtn = new JButton("返 回 主 菜 单");
		backToMainJFrameBtn.setBackground(c);
		backToMainJFrameBtn.setOpaque(false);
		//为按钮添加监听事件。
		backToMainJFrameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建保龄球计分系统主菜单。
				new MainJFrame();
				//隐藏选手信息管理系统面板。
				PlayerManage.this.frame.setVisible(false);
			}
		});
		backToMainJFrameBtn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		backToMainJFrameBtn.setBounds(60, 276, 240, 60);
//		frame.getContentPane().add(backToMainJFrameBtn);
		
		
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
        imagePanel.add(saveInfoBtn);
        imagePanel.add(resetInfoBtn);
        imagePanel.add(backToMainJFrameBtn);
		
		
		
		
		/************************************************/
		
	}
}

