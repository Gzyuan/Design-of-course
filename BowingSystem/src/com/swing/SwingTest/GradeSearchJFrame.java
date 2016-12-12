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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeSearchJFrame {

	private JFrame frame;
	
	public GradeSearchJFrame() {
		GradeBuffer.me.life();
		initialize();
		this.frame.setTitle("成绩查询系统");
		this.frame.setLayout(new BorderLayout());
		this.frame.setVisible(true);
		this.frame.setResizable(false);
	}
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100,100,640,452);
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
		
		JButton gradeSortBtn = new JButton("成  绩  查  询");
		gradeSortBtn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		gradeSortBtn.setBounds(60, 80, 240, 60);
		gradeSortBtn.setBackground(c);
		gradeSortBtn.setOpaque(false);
//		frame.getContentPane().add(gradeSortBtn);
		//为查询按钮添加监听事件。
		gradeSortBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				try {
					if(DAOFactory.getIGradeDAOInstance().getSize()<=0){
						
						JOptionPane.showMessageDialog(frame,"没有成绩，请录入成绩");
						
					}else{
					//创建查询成绩面板对象。
					new SearchForGrade();
					//隐藏成绩查询面板。
//					GradeSearchJFrame.this.frame.setVisible(false);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		JButton bestPlayerBtn = new JButton("优  胜  奖  项");
		bestPlayerBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					//创建优胜奖项面板。
					if(DAOFactory.getIGradeDAOInstance().getSize()<=0){
						
						JOptionPane.showMessageDialog(frame,"没有成绩，请录入成绩");
						
					}else{
						//创建优胜奖项面板。
						new WonderfulGrade();
						//隐藏成绩查询面板。
//						GradeSearchJFrame.this.frame.setVisible(false);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		bestPlayerBtn.setFont(new Font("微软雅黑", Font.PLAIN, 26));
		bestPlayerBtn.setBounds(60, 178, 240, 60);
		bestPlayerBtn.setBackground(c);
		bestPlayerBtn.setOpaque(false);
//		frame.getContentPane().add(bestPlayerBtn);
		
		JButton backToMainJFrameBtn = new JButton("返  回  主  菜  单");
		backToMainJFrameBtn.setFont(new Font("微软雅黑",Font.PLAIN,26));
		backToMainJFrameBtn.setBounds(60, 276, 240, 60);
		backToMainJFrameBtn.setBackground(c);
		backToMainJFrameBtn.setOpaque(false);
//		frame.getContentPane().add(backBtn);
		//为返回按钮添加监听事件。
		backToMainJFrameBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//创建保龄球计分系统主菜单。
				new MainJFrame();
				//隐藏成绩查询面板。
				GradeSearchJFrame.this.frame.setVisible(false);
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
        imagePanel.add(gradeSortBtn);
        imagePanel.add(bestPlayerBtn);
        imagePanel.add(backToMainJFrameBtn);
		
		/************************************************/
	}
}
