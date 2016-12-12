package com.swing.SwingTest;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.buffer.GradeBuffer;
import com.controller.GradeController;
import com.dao.GradeDAO.Grade;
import com.dao.factory.DAOFactory;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InfoForPlayerGrade {
	
	private JFrame frame;
	public Grade grade;
	public GradeController gradeController;
	public InfoForPlayerGrade(Grade grade) throws Exception {
		GradeBuffer.me.life();
		this.setGrade(grade);
		gradeController = new GradeController();
		initialize();
		this.frame.setVisible(true);
		this.frame.setResizable(false);
		
	}
	private void initialize() throws Exception {
		frame = new JFrame();

		frame.setTitle("选手成绩信息");
		frame.setBounds(100, 100, 610, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
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
		
		
		//填充面板。
		JLabel infoForGrade = new JLabel("<html><h1 style ='font-size:22px;'>编号: "+grade.getId()+"&nbsp&nbsp姓名: "+grade.getName()+"&nbsp&nbsp排名: "+gradeController.sreachRank(grade.getId())+"&nbsp&nbsp总分: "+grade.getAllGrade()+"</h1></html>");
		infoForGrade.setHorizontalAlignment(SwingConstants.CENTER);
//		infoForGrade.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		frame.getContentPane().add(infoForGrade, BorderLayout.NORTH);
		
		//创建表格存储选手成绩信息。
		String[] headers = new String[DAOFactory.getIGradeDAOInstance().getSize()];
		headers[0] = "局数";
		for(int i = 1;i<headers.length;i++){
			headers[i] = "第"+i+"局";
			}
		Object[][] cellData = null;
		DefaultTableModel model = new DefaultTableModel(cellData, headers);
		JTable table = new JTable(model);
		//设置表格列宽。
		for(int j=0;j<headers.length;j++){
			table.getColumnModel().getColumn(j).setPreferredWidth(150);
		}
		
		//实现表格内容居中。
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,r);
		table.setRowHeight(40);
		table.setFont(new Font("微软雅黑",Font.PLAIN,20));
		//自动调整表格。
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setFont(new Font("微软雅黑",Font.PLAIN,22));
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(1);
		
		/**************************/
		table.setValueAt("分数", 0, 0);
		int[] grades=grade.getGrades();
		for(int i=0;i<grades.length;i++){
			//填充选手成绩信息表格。
			table.setValueAt(grades[i], 0, i+1);
		}
		
		
		/**************************/
		//创建滚动条窗口。
		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setAutoscrolls(true);
		scrollPane.setFont(new Font("微软雅黑",Font.PLAIN,18));
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
		
		JPanel panel = new JPanel();
		this.frame.add(panel,BorderLayout.SOUTH);
		JButton backBtn = new JButton("  返  回  ");
		backBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//隐藏成绩信息面板。
				InfoForPlayerGrade.this.frame.setVisible(false);
				//创建成绩查询面板。
//				new GradeSearchJFrame();
			}
			
		});
		backBtn.setFont(new Font("微软雅黑",Font.PLAIN,22));
		panel.add(backBtn);
		
		
	}

	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}

}
