package com.swing.SwingTest;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.buffer.GradeBuffer;
import com.controller.AgainstController;
import com.dao.factory.DAOFactory;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgainstJFrame {

	private JFrame frame;

	public AgainstJFrame() {
		GradeBuffer.me.life();
		try {
			initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}

	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setTitle("比赛对阵详情");
		frame.setBounds(100, 100, 610, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
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
		
		JLabel labelForCompetition = new JLabel("<html><h1 style='font-size:44px'>比 赛 对 阵 表</h1></html>");
		labelForCompetition.setHorizontalAlignment(SwingConstants.CENTER);
//		labelForCompetition.setFont(new Font("微软雅黑", Font.PLAIN, 44));
		frame.getContentPane().add(labelForCompetition, BorderLayout.NORTH);
		
		String[] headers = new String[DAOFactory.getIPlayerDAOInstance().getSize()];
		headers[0] = "姓名";
		for(int i = 1;i<headers.length;i++){
			headers[i] = "第"+i+"天";
		}
		Object[][] cellData = null;
		DefaultTableModel model = new DefaultTableModel(cellData, headers);
		JTable table = new JTable(model);
		table.setRowHeight(40);
		//设置表格列宽。
		for(int j=0;j<headers.length;j++){
			table.getColumnModel().getColumn(j).setPreferredWidth(150);
		}
		//实现表格内容居中。
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,r);
		
		table.setFont(new Font("微软雅黑",Font.PLAIN,22));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setFont(new Font("微软雅黑",Font.PLAIN,28));
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		//动态生成天数
		
		int row = DAOFactory.getIPlayerDAOInstance().getSize();
		tableModel.setRowCount(row);
		int[][] againstTable=AgainstController.me.getTable(); 
		
		for(int i=0;i<againstTable.length;i++){
			for(int j=0;j<againstTable[i].length;j++){
				String name=DAOFactory.getIPlayerDAOInstance().doSreach(againstTable[i][j]).getName();
				table.setValueAt(name, i, j);
				
				
			}
			
		}
		
		JScrollPane scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton backBtn = new JButton("  返  回  ");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建赛事管理系统面板。
//				new CompetitionManage();
				//隐藏当前面板。
				AgainstJFrame.this.frame.setVisible(false);
			}
		});
		backBtn.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		panel.add(backBtn);
	}
}
