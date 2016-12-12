package com.dao.PlayerDAO;

public class Player{
	private int id;
	private String name;
	private int age;
	private String sex;
	private String photoPath;
	private String telephone;
	private String address;
	public Player(String name, int age, String sex, String photoPath, String telephone, String address) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.photoPath = photoPath;
		this.telephone = telephone;
		this.address = address;
	}
	public Player() {
		this(null,0,null,null,null,null);
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getSex() {
		return sex;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public String getTelephone() {
		return telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	//得到选手的所有属性的字符串，|分开
	public String getAll(){
		return "|"+this.getName()+"|"+this.getAge()+"|"+this.getSex()+"|"+this.getPhotoPath()+
				"|"+this.getTelephone()+"|"+this.getAddress();
	}
}