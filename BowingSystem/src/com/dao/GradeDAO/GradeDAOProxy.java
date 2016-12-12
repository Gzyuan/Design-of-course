package com.dao.GradeDAO;

import com.buffer.GradeBuffer;

public class GradeDAOProxy implements IGradeDAO{
	private IGradeDAO dao=null;
	public GradeDAOProxy(){
		dao=new GradeDAOImpl();
	}
	@Override
	public int doCreate(Grade grade) throws Exception {
		// TODO Auto-generated method stub
		if(grade==null){
			return 400;
		}
		try {
			dao.doCreate(grade);
		} catch (Exception e) {
			// TODO: handle exception
			return 300;
		}
		return 100;
	}

	@Override
	public int doDelete(int id) throws Exception {
		// TODO Auto-generated method stub
		if(id<0||id>this.getSize()){
			return 200;
		}
		try {
			dao.doDelete(id);
		} catch (Exception e) {
			// TODO: handle exception
			return 300;
		}
		return 100;
	}

	@Override
	public int doReset(int id, Grade grade) throws Exception {
		// TODO Auto-generated method stub
		if(id<0||id>this.getSize()){
			return 200;
		}else if(grade==null){
			return 400;
		}
		try {
			dao.doReset(id, grade);
		} catch (Exception e) {
			// TODO: handle exception
			return 300;
		}
		return 100;
	}

	@Override
	public Grade doSreach(int id) throws Exception {
		// TODO Auto-generated method stub
		if(id<0||id>this.getSize()){
			return null;
		}
		Grade grade=null;
		try {
			grade=dao.doSreach(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return grade;
	}

	@Override
	public int getSize() throws Exception {
		// TODO Auto-generated method stub
		int size=0;
		try {
			size=dao.getSize();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return size;
	}
	@Override
	public Grade doSreach(String name) throws Exception {
		// TODO Auto-generated method stub
		if(name==null){
			return null;
		}
		Grade p=null;
		try {
			p=dao.doSreach(name);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return p;
	}

}
