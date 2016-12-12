package com.dao.factory;

import com.dao.GradeDAO.GradeDAOProxy;
import com.dao.GradeDAO.IGradeDAO;
import com.dao.PlayerDAO.IPlayerDAO;
import com.dao.PlayerDAO.PlayerDAOProxy;

public class DAOFactory {
	public static IPlayerDAO getIPlayerDAOInstance()throws Exception{
		return new PlayerDAOProxy();
		}
	public static IGradeDAO getIGradeDAOInstance()throws Exception{
		return new GradeDAOProxy();
		}
}
