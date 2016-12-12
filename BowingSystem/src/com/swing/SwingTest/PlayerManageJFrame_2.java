package com.swing.SwingTest;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.buffer.GradeBuffer;
import com.controller.ErrorController;
import com.dao.PlayerDAO.Player;
import com.dao.factory.DAOFactory;

public class PlayerManageJFrame_2 extends PlayerManageJFrame {

	private PlayerManageJFrame_2 playerReset;
	public PlayerManageJFrame_2(){
		GradeBuffer.me.life();
		this.frame.setTitle("选手信息修改系统");
		this.setMyButton();
		//每次创建面板是清空错误报告。
		ErrorController.me.setError("");
	}
	public void setMyButton(){
		//创建保存按钮。
		JButton resetBtn = new JButton("修  改");
		//为按钮添加监听事件
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取选手名字。
				name = ErrorController.me.checkName(playerReset.textField_1.getText());
				//获取选手的年龄。
				age = ErrorController.me.checkAge(playerReset.textField_2.getText());
				//获取选手性别。
				if(playerReset.boyBtn.isSelected()){
					sex="男";
				}
				else if(playerReset.girlBtn.isSelected()){
					sex="女";
				}
				else{
					ErrorController.me.addError("请选择性别！");
				}
				//获取选手号码。
				telephone = ErrorController.me.checkTele(playerReset.textField_3.getText());
				//获取选手地址。
				address = ErrorController.me.checkAddress(playerReset.textField_4.getText());
				//将参赛选手的头像保存到固定文件夹并按照相应的文件命名规范保存。
				playerPhotoPath = "./data/img/photo_"+player.getId()+".jpg";
				try {
					//获取本地照片并复制照片到指定文件夹。
					DAOFactory.getIPlayerDAOInstance().getPhoto(player.getId(), photoPath);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				//创建参赛选手对象。
				Player afterPlayerReset = new Player(name,age,sex,playerPhotoPath,telephone,address);
				int numberForState = 0;
				if(ErrorController.me.getError().equals("")){
					try {
						//记录状态码。
						numberForState = DAOFactory.getIPlayerDAOInstance().doReset(player.getId(), afterPlayerReset);
						//弹出修改状态信息警示框。
						if(numberForState == 100){
							JOptionPane.showMessageDialog(frame,"修改成功!");
							}
						else{
								JOptionPane.showMessageDialog(frame,"修改失败！");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				}
				else{
						//弹出错误报告警示框。
						JOptionPane.showMessageDialog(frame,ErrorController.me.getError());
						//清空错误报告。
						ErrorController.me.setError("");
					}	
			}
		});
					resetBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
					resetBtn.setBounds(278, 317, 93, 33);
					this.frame.getContentPane().add(resetBtn);
	
					//创建返回按钮。
					JButton backBtn = new JButton("返  回");
					//为按钮添加监听事件。
					backBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//创建保龄球计分系统主菜单面板。
//							new PlayerManage();
							//隐藏选手修改系统面板。
							PlayerManageJFrame_2.this.frame.setVisible(false);
						}
					});
					backBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
					backBtn.setBounds(397, 318, 93, 33);
					this.frame.getContentPane().add(backBtn);
			}
	//设置修改重置的选手对象。
	public void setPlayerReset(PlayerManageJFrame_2 playerReset){
		this.playerReset = playerReset;
	}
	//设置旧选手对象。
	public void setPlayer(Player player){
		this.player = player;
	}
}

