package com.swing.SwingTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.buffer.GradeBuffer;
import com.controller.AgainstController;
import com.dao.GradeDAO.Grade;
import com.dao.factory.DAOFactory;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaveGradeJFrame {

	private JFrame frame;
	private MyModel myModel;
	private boolean hasSave=false;
	public SaveGradeJFrame() {
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
		
		JLabel labelForCompetition = new JLabel("<html><h1 style='font-size:44px'>比 赛 计 分 面 板</h1></html>");
		labelForCompetition.setHorizontalAlignment(SwingConstants.CENTER);
//		labelForCompetition.setFont(new Font("微软雅黑", Font.PLAIN, 44));
		frame.getContentPane().add(labelForCompetition, BorderLayout.NORTH);
		
		String[] headers = new String[DAOFactory.getIPlayerDAOInstance().getSize()];
		headers[0] = "积分";
		for(int i = 1;i<headers.length;i++){
			headers[i] = "第"+i+"天";
		}
		Object[][] cellData = null;
		//创建表格对象模型。
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
		table.setFont(new Font("微软雅黑",Font.PLAIN,24));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setFont(new Font("微软雅黑",Font.PLAIN,28));
//		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
		MyModel myModel = new MyModel((DefaultTableModel) table.getModel(),frame);
		Thread t = new Thread(myModel);
		t.start();
		int row = DAOFactory.getIPlayerDAOInstance().getSize();
		myModel.m.setRowCount(row);
		//填充表格数据内容。
		for(int i=0;i<row;i++){
			String name=DAOFactory.getIPlayerDAOInstance().doSreach(i+1).getName();
			myModel.m.setValueAt(name,i, 0);
			
		}		
		JScrollPane scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton saveBtn = new JButton(" 保  存 ");
		//为保存按钮添加监听事件。
		saveBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				/*******************/
			if(!hasSave){	
				String str;
				int[] grades=new int[row-1];
				int temp;
				//控制行，id操作
				for(int i=0;i<row;i++){
					//控制列
					for(int j=1;j<row;j++){
						//遍历拿出每一格里的元素
						str=(String)myModel.m.getValueAt(i, j);
						try {
							temp = Integer.valueOf(str);
							//如果填的数字超过范围，就弹出警示框
							if(temp>=0&&temp<=2){
								//加入成绩数组
								grades[j-1]=temp;
								
							}else{
								
								JOptionPane.showMessageDialog(frame,"只能输入0-2数字！");
								return;
							}
						}catch(Exception e1){
							//e1.printStackTrace();
							JOptionPane.showMessageDialog(frame,"只能输入0-2数字！");
							return;
						}
						
					}
					try {
							//得到名字
							String name = DAOFactory.getIPlayerDAOInstance().doSreach(i+1).getName();
							//创建对象
							Grade grade=new Grade(i+1,name,grades);
							//看文件有没有成绩记录，没有加，有就替换
						if(DAOFactory.getIGradeDAOInstance().doSreach(i+1)==null){
							DAOFactory.getIGradeDAOInstance().doCreate(grade);
						}else{
							DAOFactory.getIGradeDAOInstance().doReset(i+1,grade);
						}
					
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						JOptionPane.showMessageDialog(frame,"保存失败，重新保存！");
					}
				}	
				
				JOptionPane.showMessageDialog(frame,"保存成功！");
				hasSave=true;
			}else{
				JOptionPane.showMessageDialog(frame,"不要重复保存");
			}
				
				/********************/
			}
		});
		saveBtn.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		JButton backBtn = new JButton(" 返  回 ");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建赛事管理系统面板。
//				new CompetitionManage();
				//隐藏当前面板。
				SaveGradeJFrame.this.frame.setVisible(false);
			}
		});
		backBtn.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		panel.add(saveBtn);
		panel.add(backBtn);
	}
	
	public class MyModel extends DefaultTableModel implements Runnable{
		DefaultTableModel m;
		JFrame frame;
		public MyModel(DefaultTableModel m,JFrame frame){
			super();
			this.m=m;
			}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
			int size = DAOFactory.getIPlayerDAOInstance().getSize();
			//保存分数内容
			int temp=0;
			//保存表格内容
			String str;
			
			while(true){
				Thread.sleep(2000);
				//i控制行，id
				for(int i=0;i<size;i++){
					//j控制列，天数
					for(int j=1;j<size;j++){
						//遍历的到每一格的内容
						str=(String)m.getValueAt(i, j);
						if(str!=null){
							try {
								temp = Integer.parseInt(str);
								if(temp>=0&&temp<=2){

										//生成数据数组，保存id，天数，分数
										int[] a={i+1,j,temp};
										//自动生成对手的静态方法
										int[]b=AgainstController.me.getOpponentGrade(a);
										m.setValueAt(b[2]+"", b[0]-1, b[1]);
										
									
								}
							} catch (Exception e) {
								
								
							}
						}
					}
				}
				
				
				
				
				
				
				m.fireTableDataChanged();
				
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
