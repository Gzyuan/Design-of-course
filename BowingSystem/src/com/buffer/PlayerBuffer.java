package com.buffer;

import java.util.HashMap;
import java.util.Map;

import com.dao.PlayerDAO.Player;
import com.dao.factory.DAOFactory;

public class PlayerBuffer {
	//抽象，不用实例
	public static final PlayerBuffer me = new PlayerBuffer();
	//存放player对象，id为索引
	private Map<Integer,Player> idMapPlayer;
	//存放player的id，name为索引
	private Map<String,Integer> nameMapId;
	//保存size
	private int size;
	public PlayerBuffer(){
		this.setIdMapPlayer(new HashMap<Integer,Player>());
		this.setNameMapId(new HashMap<String,Integer>());
		this.size=-1;
	}
	//加入player进缓存
	public void setElement(int id,Player player){
		this.idMapPlayer.put(id, player);
		if(player.getId()!=0){
			this.nameMapId.put(player.getName(), id);
		}
	}
	//移除旧的，加入新的，防止信息有误
	public void updateElement(int id,Player player){	
		try {
		this.nameMapId.remove(this.idMapPlayer.get(id).getName());
		this.idMapPlayer.remove(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.setElement(id, player);
	}
	public Player getElement(int id){
		return this.idMapPlayer.get(id);
	}
	
	public Player getElement(String name){
		return this.idMapPlayer.get(this.nameMapId.get(name));
	}
	
	public void addSize(){
		this.size++;
	}
	
	
	public Map<Integer, Player> getIdMapPlayer() {
		return idMapPlayer;
	}
	public Map<String, Integer> getNameMapId() {
		return nameMapId;
	}
	public void setIdMapPlayer(Map<Integer, Player> idMapPlayer) {
		this.idMapPlayer = idMapPlayer;
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
}
