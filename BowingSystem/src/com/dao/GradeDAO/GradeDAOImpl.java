package com.dao.GradeDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.management.GarbageCollectorMXBean;

import com.buffer.GradeBuffer;
import com.buffer.PlayerBuffer;

public class GradeDAOImpl implements IGradeDAO{
	private PrintWriter out= null;
	private BufferedReader in =null;
	private String filePath = null;
	public GradeDAOImpl(){
		this.filePath="./data/选手成绩.dat";
	}
	@Override
	public int doCreate(Grade grade) throws Exception {
		// TODO Auto-generated method stub
		out=new PrintWriter(new FileWriter(filePath,true));
		//int size=this.getSize();
		out.println(grade.getId()+grade.getAll());
		out.flush();
		out.close();
		
		//保存grade对象进缓存区
		//grade.setId(size+1);
			GradeBuffer.me.setElement(grade.getId(), grade);
			GradeBuffer.me.addSize();
			return 100;
	}

	@Override
	public int doDelete(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doReset(int id, Grade grade) throws Exception {
		// TODO Auto-generated method stub
		File oldFile = new File(filePath);
		//备份文件
		File newFile = new File(filePath+".tmp");
		out=new PrintWriter(new FileWriter(newFile,true));
		in=new BufferedReader(new FileReader(oldFile));
		//前面编号信息照写
		for(int i=1;i<id;i++){
			out.println(in.readLine());
		}
		//写出要改的信息
		out.println(id+grade.getAll());
		//跳过一行
		String line=in.readLine();
		//剩下的也输出
		while((line=in.readLine())!=null){
			out.println(line);
		}
		out.flush();
		in.close();
		out.close();
		//删除旧文件
		oldFile.delete();
		//重命名新文件
		newFile.renameTo(new File(filePath));
		//更新缓存区
		GradeBuffer.me.updateElement(id, grade);
		return 100;
	}

	@Override
	public Grade doSreach(int id) throws Exception {
		// TODO Auto-generated method stub
		//先查询缓存区
		Grade grade = GradeBuffer.me.getElement(id);
		if(grade!=null){
			return grade;
		}
		
		in=new BufferedReader(new FileReader(filePath));
		//跳过前面的id
		for(int i=1;i<id;i++){
			in.readLine();
		}
		//取出符合的信息
		String str=in.readLine();
		in.close();
		String[]allStr = str.split("\\|");		
		int[] grades = new int[allStr.length-2];
		
		for(int i=2;i<allStr.length;i++){
			grades[i-2]=Integer.valueOf(allStr[i]);
		}
		//创建对象
		grade = new Grade(id,allStr[1],grades);
		//保存grade对象进缓存区
		GradeBuffer.me.setElement(id, grade);
		return grade;
	}

	@Override
	public int getSize() throws Exception {
		// TODO Auto-generated method stub
		int size=GradeBuffer.me.getSize();
		if(size!=-1){
			return size;
		}
		//size重置为0
		size=0;
		in=new BufferedReader(new FileReader(filePath));
		//遍历文件有多少行，就有多少个人
		while(in.readLine()!=null){
			size++;
		}
		in.close();
		//保存size进缓存区
		GradeBuffer.me.setSize(size);
		return size;
	}
	@Override
	public Grade doSreach(String name) throws Exception {
		// TODO Auto-generated method stub
		//先查询缓存区
		Grade grade = GradeBuffer.me.getElement(name);
		if(grade!=null){
			return grade;
		}
		in=new BufferedReader(new FileReader(filePath));
		String str = null;
		while((str=in.readLine())!=null){
			String[]allStr = str.split("\\|");
			int[]grades = null;
			//如果名字相同的话就取出来
			if(allStr[1].equals(name)){
				 grades=new int[allStr.length-2];
				for(int i=0;i<allStr.length-2;i++){
					grades[i]=Integer.valueOf(allStr[i+2]);
				}
				in.close();
				grade=new Grade(Integer.valueOf(allStr[0]),allStr[1],grades);
				//保存grade对象进缓存区
				GradeBuffer.me.setElement(grade.getId(), grade);		
				return grade;
			}
		}
		in.close();
		return null;
		
	}

}
