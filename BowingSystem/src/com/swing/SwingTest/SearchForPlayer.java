package com.swing.SwingTest;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;

import com.buffer.GradeBuffer;
import com.dao.PlayerDAO.Player;
import com.dao.factory.DAOFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchForPlayer {

	private JFrame frame;
	private JTextField textField;
	private JButton backBtn;
	public String infoForPlayer;
	private Player player;
	private PlayerManageJFrame_2 playerReset; 

	public SearchForPlayer() {
		GradeBuffer.me.life();
		initialize();
		this.frame.setTitle("选手信息查询");
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}
	//初始化面板。
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 411, 275);
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
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer playerId = null;
				//捕获抛出字符串不能转换为数字对象时的异常。
				try{
					//获取选手姓名或编号查询输入框文本。
					infoForPlayer = SearchForPlayer.this.textField.getText();
					//将选手姓名或编号查询输入框文本转换为整数对象。
					playerId = Integer.parseInt(infoForPlayer);
				}catch(NumberFormatException e2){
					try {
						//通过选手名字遍历寻找与之匹配的选手对象。
						player = DAOFactory.getIPlayerDAOInstance().doSreach(infoForPlayer);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				while(playerId!=null){
					try {
						//通过选手的参赛编号遍历寻找与之匹配的选手对象。
						player = DAOFactory.getIPlayerDAOInstance().doSreach(playerId);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					break;
				}
				//简单数据校验。
				if(player == null){
					JOptionPane.showMessageDialog(frame,"查无此人");
					return;
				}
				//创建选手信息修改系统面板对象。
				playerReset = new PlayerManageJFrame_2();
				playerReset.setOldPlayer(player);
				//填充选手信息修改面板的内容。
				playerReset.textField_1.setText(player.getName());
				playerReset.textField_2.setText(player.getAge()+"");
				playerReset.textField_3.setText(player.getTelephone());
				playerReset.textField_4.setText(player.getAddress());
				
				//判断男女单选按钮并选中。
				if(player.getSex().equals("男")){
					playerReset.boyBtn.setSelected(true);
				}
				else{
					playerReset.girlBtn.setSelected(true);
				}
				//创建图片对象。
			    ImageIcon image = new ImageIcon(player.getPhotoPath());
			    //等比例缩放。
				image.setImage(image.getImage().getScaledInstance(170,188,Image.SCALE_DEFAULT));
				//为面板添加图标。
				playerReset.imgLabel.setIcon(image);
				//传递修改前后选手对象。
				playerReset.setPlayerReset(playerReset);
				playerReset.setPlayer(player);
				//隐藏选手查询面板。
				SearchForPlayer.this.frame.setVisible(false);
				
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
//				new PlayerManage();
				//隐藏选手查询面板。
				SearchForPlayer.this.frame.setVisible(false);
			}
		});
		backBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		backBtn.setBounds(218, 150, 140, 37);
		frame.getContentPane().add(backBtn);
	}
}

