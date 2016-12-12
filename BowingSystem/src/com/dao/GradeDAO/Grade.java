package com.dao.GradeDAO;

public class Grade {
	private int id;
	private int[] grades;
	private String name;
	public Grade(int id, String name,int[] grades) {
		//super();
		this.id = id;
		this.name=name;
		this.grades = grades;
	}
	public Grade(){
		this(0,null,null);
	}
	public int getId() {
		return id;
	}
	public int[] getGrades() {
		return grades;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setGrades(int[] grades) {
		this.grades = grades;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//得到成绩的所有属性的字符串，|分开
	public String getAll(){
		String str="";
		str+="|"+this.getName();
		for(int i=0;i<this.grades.length;i++){
			str+="|"+grades[i];
		}
		return str;
	}
	public int getAllGrade(){
		int all=0;
		if(grades!=null){
			for(int i=0;i<grades.length;i++){
				all+=grades[i];
			}
		}
		return all;
	}
}
