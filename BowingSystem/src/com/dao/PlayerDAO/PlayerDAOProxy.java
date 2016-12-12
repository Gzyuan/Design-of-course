package com.dao.PlayerDAO;

public class PlayerDAOProxy implements IPlayerDAO{
	private IPlayerDAO dao=null;
	public PlayerDAOProxy(){
		dao=new PlayerDAOImpl();
	}
	@Override
	public int doCreate(Player player) throws Exception {
		// TODO Auto-generated method stub
		if(player==null){
			return 400;
		}
		try {
			dao.doCreate(player);
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
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
	public int doReset(int id, Player player) throws Exception {
		// TODO Auto-generated method stub
		if(id<0||id>this.getSize()){
			return 200;
		}else if(player==null){
			return 400;
		}
		try {
			dao.doReset(id, player);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 300;
		}
		return 100;
	}

	@Override
	public Player doSreach(int id) throws Exception {
		// TODO Auto-generated method stub
		if(id<0||id>this.getSize()){
			return null;
		}
		Player player=null;
		try {
			player=dao.doSreach(id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return player;
	}

	@Override
	public Player doSreach(String name) throws Exception {
		// TODO Auto-generated method stub
		if(name==null){
			return null;
		}
		Player player=null;
		try {
			player=dao.doSreach(name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return player;
	}

	@Override
	public int getSize() throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		try {
			i=dao.getSize();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return i;
	}
	@Override
	public int getPhoto(int id, String path) throws Exception {
		// TODO Auto-generated method stub
		if(id<0||id>this.getSize()){
			return 200;
		}
		try {
			dao.getPhoto(id, path);
		} catch (Exception e) {
			// TODO: handle exception
			return 300;
		}
		return 100;
	}
}
