package com.swing.SwingTest;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;

import com.buffer.GradeBuffer;
import com.dao.GradeDAO.Grade;
import com.dao.PlayerDAO.Player;
import com.dao.factory.DAOFactory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchForGrade {

	private JFrame frame;
	private JTextField textField;
	private JButton backBtn;
	public String infoForPlayer;
	public SearchForGrade() {
		GradeBuffer.me.life();
		initialize();
		this.frame.setTitle("选手成绩查询");
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}
	//初始化面板。
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 410, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
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
		//实现面板居中。
		this.frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		//面板内容填充。
		JLabel labelForInfo = new JLabel("姓名/参赛编号：");
		labelForInfo.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		labelForInfo.setBounds(39, 70, 141, 37);
		frame.getContentPane().add(labelForInfo);
		
		//创建选手姓名或编号查询输入框。
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField.setBounds(173, 76, 185, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		//创建按钮对象。
		JButton searchBtn = new JButton("查   询");
		//为按钮添加监听事件。
		searchBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				/***********************/
				
				if(textField.getText()==null||textField.getText().equals("")){
					JOptionPane.showMessageDialog(frame,"请输入编号或名字！");
					//new SearchForGrade();
					//隐藏成绩查询面板。
					//SearchForGrade.this.frame.setVisible(false);
					return;
				}
				Grade grade=null;
				int id = 0;
				try {
					id = Integer.parseInt(textField.getText());
					grade = DAOFactory.getIGradeDAOInstance().doSreach(id);
				} catch (Exception e1) {
					// TODO: handle exception
					try {
						grade = DAOFactory.getIGradeDAOInstance().doSreach(textField.getText());
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				if(grade != null){
					try {
						//创建选手分数详情面板。
						new InfoForPlayerGrade(grade);
						//隐藏分数查询面板。
						SearchForGrade.this.frame.setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else{
					JOptionPane.showMessageDialog(frame,"查无此人");
					//new SearchForGrade();
					//隐藏成绩查询面板。
					//SearchForGrade.this.frame.setVisible(false);
					//return;
				}
				
				/************************/

				
				
			}
			
		});
		searchBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		searchBtn.setBounds(39, 150, 141, 37);
		frame.getContentPane().add(searchBtn);
		
		backBtn = new JButton("返    回 ");
		//为按钮添加监听事件。
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建选手信息管理面板。
//				new GradeSearchJFrame();
				//隐藏选手查询面板。
				SearchForGrade.this.frame.setVisible(false);
			}
		});
		backBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		backBtn.setBounds(218, 150, 140, 37);
		frame.getContentPane().add(backBtn);
	}
}

