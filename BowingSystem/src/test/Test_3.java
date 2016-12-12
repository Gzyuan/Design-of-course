package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buffer.GradeBuffer;
import com.controller.GradeController;
import com.dao.GradeDAO.Grade;
import com.dao.factory.DAOFactory;

public class Test_3 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Grade grade=null;
		for(int i=1;i<5;i++){
			grade=DAOFactory.getIGradeDAOInstance().doSreach(i);
			//GradeBuffer.me.setElement(i, grade);
		}
		List<Grade> list=new GradeController().getListRank();
		for(int j=1;j<5;j++){
			System.out.println(list.get(j-1).getAll());
		}
		GradeBuffer.me=null;
	}

}

