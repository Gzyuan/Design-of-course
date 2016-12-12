package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dao.GradeDAO.Grade;
import com.dao.factory.DAOFactory;

public class GradeController {
	//排名集合
	List<Grade> rankList=null;
	//人数
	int size=0;
	public GradeController() {
		
		rankList = new ArrayList<Grade>();
		//先获取有多少人
		try {
			size=DAOFactory.getIGradeDAOInstance().getSize();
		
			//依次取出成绩
			for(int i=0;i<size;i++){
				rankList.add(DAOFactory.getIGradeDAOInstance().doSreach(i+1));
			}
			//排序
			Collections.sort(rankList, new GradeComparator());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//返回成绩排名集合
	public List<Grade> getListRank(){
		
		return rankList;
		
	}
	//返回全胜奖对象
	public Grade getAwards(){
		Grade awards =null;
		for(int i=0;i<size;i++){
			Grade temp=this.rankList.get(i);
			//如果总分等于（参赛人数-1）*2那么就是全胜
			if(temp.getAllGrade()==2*(size-1)){
				awards=temp;
			}
			
		}
		return awards;
		
	}
	
	//通过id找出他的排名
	public int sreachRank(int id){
		int rank = 1;
		//因为集合的index从0开始，所以减一
		for(;(rank-1)<this.rankList.size();rank++){
			if(id==this.rankList.get(rank-1).getId()){
				break;
			}
		}
		return rank;
	}
	//通过姓名找出他的排名
	public int sreachRank(String name){
		int rank = 1;
		//因为集合的index从0开始，所以减一
		for(;(rank-1)<this.rankList.size();rank++){
			if(name.equals(this.rankList.get(rank-1).getName())){
				break;
			}
		}
		return rank;
	}
	
	
	//自定义排序，从高到低排序
		static class GradeComparator implements Comparator<Grade> {  
			public int compare(Grade object1, Grade object2) {// 实现接口中的方法  
				Grade p1 = (Grade) object1; // 强制转换  
				Grade p2 = (Grade) object2;  
				return new Double(p2.getAllGrade()).compareTo(new Double(p1.getAllGrade())); 

            } 
        }  
}
	


