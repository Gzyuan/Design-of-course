package com.swing.SwingTest;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import com.buffer.GradeBuffer;

public class MyJButton extends JButton {
	public MyJButton(String string){
		GradeBuffer.me.life();
		super(string);
		this.setFont(new Font("微软雅黑",Font.PLAIN,24));
		this.setPreferredSize(new Dimension(300,60));
	}
}
