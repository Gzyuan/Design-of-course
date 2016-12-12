package test;

import com.dao.GradeDAO.Grade;
import com.dao.PlayerDAO.Player;
import com.dao.factory.DAOFactory;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//Player player1=new Player("张三",20,"男","photo1.jpg","130000000","广工");
		//Player player2=new Player("李四",30,"男","photo1.jpg","130000000","龙洞");
		int[] i={1,2,3,4,1,2,100};
		Grade grade=new Grade(1,"lalal",i);
		DAOFactory.getIGradeDAOInstance().doCreate(grade);
		DAOFactory.getIGradeDAOInstance().doCreate(grade);
		System.out.println(DAOFactory.getIGradeDAOInstance().doSreach(1).getAll());
		//System.out.println(DAOFactory.getIGradeDAOInstance().doSreach(2).getAll());
		//DAOFactory.getIPlayerDAOInstance().doCreate(player1);
		//DAOFactory.getIPlayerDAOInstance().doReset(2, player2);
		//player2=DAOFactory.getIPlayerDAOInstance().doSreach(1);
		//player1=DAOFactory.getIPlayerDAOInstance().doSreach("李四");
		//System.out.println(player1.getAddress());
		System.out.println(DAOFactory.getIPlayerDAOInstance().getPhoto(1, "./data/选手成绩.dat"));
	}

}
