package com.dao.PlayerDAO;
/*error：
 * 100：成功
 * 200：格式出错,输入有误
 * 300：出现异常
 * 400：空对象
 */
public interface IPlayerDAO {
	/* 数据增加的方法
	 * player是要增加的数据对象
	 * return 是返回是否成功的结果
	 * throws Exception异常交给调用处处理
	 */
	public int doCreate(Player player)throws Exception;
	/* 数据删除的方法
	 * id是要删除的数据对象palyer的id属性
	 * return 是返回是否成功的代码
	 * throws Exception异常交给调用处处理
	 */
	public int doDelete(int id)throws Exception;
	/* 数据需要改的方法
	 * id是未删改前的palyer对象的id属性
	 * player是要修改的数据对象
	 * return 是返回是否成功的代码
	 * throws Exception异常交给调用处处理
	 */
	public int doReset(int id,Player player)throws Exception;
	/* 通过id查找数据的方法
	 * id是palyer对象的id属性
	 * return 是返回是否成功的代码
	 * throws Exception异常交给调用处处理
	 */
	public Player doSreach(int id)throws Exception;
	/* 通过name查找数据的方法
	 * id是palyer对象的id属性
	 * return 是返回player对象
	 * throws Exception异常交给调用处处理
	 */
	public Player doSreach(String name)throws Exception;
	/*
	 * 返回数据数量的方法
	 * return 是返回player对象
	 * throws Exception异常交给调用处处理
	 * 
	 */
	public int getSize()throws Exception;
	/*
	 * 
	 * 
	 * 
	 * 
	 */
	public int getPhoto(int id,String path)throws Exception;
}
