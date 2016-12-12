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

public class PlayerManageJFrame_1 extends PlayerManageJFrame {
	public PlayerManageJFrame_1(){
		GradeBuffer.me.life();
		this.frame.setTitle("选手信息录入系统");
		this.setMyButton();
		//每次创建面板时清空错误信息。
		ErrorController.me.setError("");
	}
	public void setMyButton(){
		//创建保存按钮。
		JButton saveBtn = new JButton("保  存");
		//为按钮添加监听事件
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//获取选手名字。
				name = ErrorController.me.checkName(textField_1.getText());
				age = ErrorController.me.checkAge(textField_2.getText());
				//获取选手性别。
				if(boyBtn.isSelected()){
					sex="男";
				}
				else if(girlBtn.isSelected()){
					sex="女";
				}else{
					ErrorController.me.addError("请选择性别！");
				}
				//获取选手号码。
				telephone = ErrorController.me.checkTele(textField_3.getText());
				//获取选手地址。
				address = ErrorController.me.checkAddress(textField_4.getText());
				//简单校验图片路径。
				String photo = ErrorController.me.checkPhoto(photoPath);
				try {
					//获取参赛选手的id。
					id=DAOFactory.getIPlayerDAOInstance().getSize()+1;
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
					//将参赛选手的头像保存到固定文件夹并按照相应的文件命名规范保存。
					playerPhotoPath = "./data/img/photo_"+id+".jpg";
					//创建参赛选手对象。
					player = new Player(name,age,sex,playerPhotoPath,telephone,address);
					try {
						//判断参赛选手信息是否保存，且参赛选手信息只能保存一次。
						if(!hasSave){
							if(ErrorController.me.getError().equals("")){
							//保存参赛选手信息。
							DAOFactory.getIPlayerDAOInstance().doCreate(player);
							//将本地绝对路径下的头像文件复制保存到指定文件夹。
							DAOFactory.getIPlayerDAOInstance().getPhoto(id, photo);
							hasSave = true;
							//弹出保存成功信息警示框。
							JOptionPane.showMessageDialog(frame,"保存成功！您的参赛编号为："+id);
							}else{
								//弹出错误信息报告。
								JOptionPane.showMessageDialog(frame,ErrorController.me.getError());
								//清空错误信息。
								ErrorController.me.setError("");
							}
						}
						else{
							//弹出重复保存警示框。	
							JOptionPane.showMessageDialog(frame,"请不要重复保存！");				
							}
					
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
					saveBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
					saveBtn.setBounds(278, 317, 93, 33);
					this.frame.getContentPane().add(saveBtn);
	
					//创建返回按钮。
					JButton backBtn = new JButton("返  回");
					//为按钮添加监听事件。
					backBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//创建保龄球计分系统主菜单面板。
//							new PlayerManage();
							//隐藏选手信息录入系统面板。
							PlayerManageJFrame_1.this.frame.setVisible(false);
						}
					});
					backBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
					backBtn.setBounds(397, 318, 93, 33);
					this.frame.getContentPane().add(backBtn);
			}
}


