package com.swing.SwingTest;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.buffer.GradeBuffer;
import com.controller.GradeController;
import com.dao.GradeDAO.Grade;
import com.dao.factory.DAOFactory;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class WonderfulGrade {
	public GradeController gradeController;
	private JFrame frame;
	public WonderfulGrade() throws Exception {
		GradeBuffer.me.life();
		gradeController=new GradeController();
		initialize();
		this.frame.setVisible(true);
		this.frame.setResizable(false);
	}
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setTitle("优胜奖项信息");
		frame.setBounds(100, 100, 610, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setFont(new Font("微软雅黑",Font.PLAIN,20));
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
		
		JLabel bestGrade = new JLabel("<html><h1 style='font-size:44px;'>优  胜  奖</h1></html>");
		bestGrade.setHorizontalAlignment(SwingConstants.CENTER);
//		bestGrade.setFont(new Font("微软雅黑", Font.PLAIN,44));
		frame.getContentPane().add(bestGrade, BorderLayout.NORTH);
		
//		JLabel bestGrade = new JLabel("优胜奖");
//		bestGrade.setHorizontalAlignment(SwingConstants.CENTER);
//		bestGrade.setFont(new Font("微软雅黑",Font.PLAIN,18));
//		frame.getContentPane().add(bestGrade, BorderLayout.WEST);
		
		String[] headers = {"名次","姓名","编号","总分"};
		Object[][] cellData = null;
		DefaultTableModel model = new DefaultTableModel(cellData, headers);
		JTable table = new JTable(model);
		//实现表格内容居中。
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class,r);
		//设置表头字体大小。
		table.getTableHeader().setFont(new Font("微软雅黑",Font.PLAIN,28));
		table.setRowHeight(40);
		//设置列宽。
		for(int i=0;i<4;i++){
			table.getColumnModel().getColumn(i).setPreferredWidth(150);
		}
		table.setFont(new Font("微软雅黑",Font.PLAIN,20));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		//动态创建表格行数。
		int row = DAOFactory.getIPlayerDAOInstance().getSize();
		tableModel.setRowCount(row);
		List<Grade> list = gradeController.getListRank();
		//填充表格。
		for(int i=0;i<row;i++){
			
			table.setValueAt("第"+(i+1)+"名", i, 0);
			table.setValueAt(list.get(i).getName(), i,1);
			table.setValueAt(list.get(i).getId(), i, 2);
			table.setValueAt(list.get(i).getAllGrade(), i, 3);
			
		}
		
		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setAutoscrolls(true);
		scrollPane.setFont(new Font("微软雅黑",Font.PLAIN,20));
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		this.frame.getContentPane().add(panel, BorderLayout.SOUTH);
//		panel.setLayout(null);
//		GridLayout grid = new GridLayout();
//		grid.setVgap(30);
//		grid.setHgap(60);
//		panel.setLayout(grid);
		JButton bestBtn = new JButton(" 满 分 奖 ");
		bestBtn.setBounds(50, 50, 60, 30);
		bestBtn.setFont(new Font("微软雅黑",Font.PLAIN,22));
		bestBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				Grade best = gradeController.getAwards();
				if(best != null){
					JOptionPane.showMessageDialog(frame,"满分奖得主："+best.getName()+"    总分："+best.getAllGrade());
				}else{
					JOptionPane.showMessageDialog(frame,"没有满分奖");
				}
			}
		});
		
		JButton backBtn = new JButton("   返 回   ");
		backBtn.setFont(new Font("微软雅黑",Font.PLAIN,22));
		//为返回按钮添加监听事件。
		backBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//隐藏成绩信息面板。
				WonderfulGrade.this.frame.setVisible(false);
				//创建成绩查询面板。
//				new GradeSearchJFrame();
			}
			
		});
		
		panel.add(bestBtn);
		panel.add(backBtn);
		
	}

}
