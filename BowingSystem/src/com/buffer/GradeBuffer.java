package com.buffer;

import java.util.HashMap;
import java.util.Map;

import com.dao.GradeDAO.Grade;
import com.dao.PlayerDAO.Player;

public class GradeBuffer {
	//抽象，不用实例
	public static final GradeBuffer me=new GradeBuffer();
	//存放player对象，id为索引
	private Map<Integer, Grade> idMapGrade;
	//存放player的id，name为索引
	private Map<String,Integer> nameMapId;
	//保存size
	private int size;
	public GradeBuffer(){
		this.setIdMapGrade(new HashMap<Integer,Grade>());
		this.setNameMapId(new HashMap<String,Integer>());
		this.setSize(-1);
	}
	
	//加入grade进缓存
		public void setElement(int id,Grade grade){
			this.idMapGrade.put(id, grade);
			if(grade.getId()!=0){
				this.nameMapId.put(grade.getName(), id);
			}
		}
	//取出grade
		public Grade getElement(int id){
			return this.idMapGrade.get(id);
		}
		
		public Grade getElement(String name){
			return this.idMapGrade.get(this.nameMapId.get(name));
		}
	
		//移除旧的，加入新的，防止信息有误
		public void updateElement(int id,Grade grade){	
			try {
			this.nameMapId.remove(this.idMapGrade.get(id).getName());
			this.idMapGrade.remove(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			this.setElement(id, grade);
		}	
	
	public void addSize(){
		this.size++;
	}
		
	public Map<Integer, Grade> getIdMapGrade() {
			return idMapGrade;
		}

		public Map<String, Integer> getNameMapId() {
			return nameMapId;
		}

		public void setIdMapGrade(Map<Integer, Grade> idMapGrade) {
			this.idMapGrade = idMapGrade;
		}

		public void setNameMapId(Map<String, Integer> nameMapId) {
			this.nameMapId = nameMapId;
		}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void life(){}
}
