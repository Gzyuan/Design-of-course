package com.swing.SwingTest;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.buffer.GradeBuffer;
import com.dao.PlayerDAO.Player;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerManageJFrame {

	public JFrame frame;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
	public JLabel imgLabel;
	public JRadioButton boyBtn;
	public JRadioButton girlBtn;
	public String photoPath;
	public Player player;
	public String name;
	public int age;
	public String sex;
	public String telephone;
	public String address;
	public String playerPhotoPath;
	public int id;
	public boolean hasSave = false; 
	public Player oldPlayer;
	
	public PlayerManageJFrame() {
		GradeBuffer.me.life();
		this.initialize();
		this.frame.setVisible(true);
		this.frame.setResizable(false);
	}

	//初始化面板
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("微软雅黑", Font.PLAIN, 18));
		frame.setSize(521, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
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
		
		//创建用于插入参赛选手头像的imgLabel。
		imgLabel = new JLabel("头  像");
		//为imgLabel添加边框。
		imgLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		//设置imgLabel沿X轴对齐。
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		imgLabel.setBounds(26, 33, 155, 188);
		//将imgLabel添加到面板。
		frame.getContentPane().add(imgLabel);
		
		//创建上传头像按钮。
		JButton upPhotoBtn = new JButton("上 传 头 像");
		//为上传按钮添加监听事件。
		upPhotoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建文件对话框
				JFileChooser chooser = new JFileChooser();
				//筛选后缀名jpg或gif的文件
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG & GIF Images", "jpg", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    //选中目标文件。
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	//获取本地文件绝对路径
			    	photoPath = chooser.getSelectedFile().getAbsolutePath();
			    }
			    else{
			    	
			    	//获取以保存参赛选手头像的相对路径。
			    	if(oldPlayer!=null){
			    		photoPath = oldPlayer.getPhotoPath();
			    	}
			    	
			    }
			    //创建图片对象。
			    ImageIcon image = new ImageIcon(photoPath);
			    //等比例缩放。
				image.setImage(image.getImage().getScaledInstance(170,188,Image.SCALE_DEFAULT));
				//为面板添加图标。
				imgLabel.setIcon(image);
			}
		});
		//设置按钮字体，颜色，大小。
		upPhotoBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		upPhotoBtn.setBounds(26, 248, 155, 31);
		frame.getContentPane().add(upPhotoBtn);
		
		//创建存放参赛选手信息的面板，并添加到frame。
		JLabel labelInfo_1 = new JLabel("姓 名：");
		labelInfo_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		labelInfo_1.setBounds(210, 33, 59, 31);
		frame.getContentPane().add(labelInfo_1);
		
		//创建名字输入框。
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_1.setBounds(278, 33, 212, 31);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel labelInfo_2 = new JLabel("年 龄：");
		labelInfo_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		labelInfo_2.setBounds(210, 87, 59, 31);
		frame.getContentPane().add(labelInfo_2);
		
		//创建年龄输入框。
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_2.setBounds(278, 87, 212, 31);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel labelInfo_3 = new JLabel("性 别：");
		labelInfo_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		labelInfo_3.setEnabled(true);
		labelInfo_3.setBounds(210, 139, 59, 29);
		frame.getContentPane().add(labelInfo_3);
		
		//创建单选按钮。
		boyBtn = new JRadioButton("男");
		boyBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		boyBtn.setBounds(308, 140, 59, 27);
		frame.getContentPane().add(boyBtn);
		
		girlBtn = new JRadioButton("女");
		girlBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		girlBtn.setBounds(389, 140, 66, 27);
		frame.getContentPane().add(girlBtn);
		
		//将单选按钮添加到互斥作用域。
		ButtonGroup group = new ButtonGroup();
		group.add(boyBtn);
		group.add(girlBtn);
		
		JLabel labelInfo_4 = new JLabel("电 话：");
		labelInfo_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		labelInfo_4.setBounds(210, 190, 59, 31);
		frame.getContentPane().add(labelInfo_4);
		
		//创建电话号码输入框。
		textField_3 = new JTextField();
		textField_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_3.setBounds(278, 190, 212, 31);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel labelInfo_5 = new JLabel("地 址：");
		labelInfo_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		labelInfo_5.setBounds(210, 246, 59, 34);
		frame.getContentPane().add(labelInfo_5);
		
		//创建地址输入框。
		textField_4 = new JTextField();
		textField_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_4.setBounds(278, 248, 212, 31);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
	}
	//获取原先旧选手对象。
	public void setOldPlayer(Player player){
		this.oldPlayer = player;
	}
}
