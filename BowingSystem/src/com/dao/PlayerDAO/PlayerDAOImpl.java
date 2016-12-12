package com.dao.PlayerDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.buffer.PlayerBuffer;
/*
 * 这是操作数据的真实实现层
 *
 */
public class PlayerDAOImpl implements IPlayerDAO{
	//输出流
	private PrintWriter out= null;
	//输入流
	private BufferedReader in =null;
	//文件路径
	private String filePath = null;
	public PlayerDAOImpl(){
		this.filePath="./data/选手资料.dat";
	}
	@Override
	public int doCreate(Player player) throws Exception {
		// TODO Auto-generated method stub
		
		out=new PrintWriter(new FileWriter(filePath,true));
		//写一行
		int size=this.getSize();
		out.println((size+1)+player.getAll());
		out.flush();
		out.close();
		//保存player对象进缓存区
		player.setId(size+1);
		PlayerBuffer.me.setElement(player.getId(), player);
		PlayerBuffer.me.addSize();
		
		return 100;
	}

	@Override
	public int doDelete(int id) throws Exception {
		// TODO Auto-generated method stub
		//不作处理
		return 100;
	}

	@Override
	public int doReset(int id, Player player) throws Exception {
		// TODO Auto-generated method stub
		File oldFile = new File(filePath);
		//备份文件
		File newFile = new File(filePath+".tmp");
		out=new PrintWriter(new FileWriter(newFile,true));
		in=new BufferedReader(new FileReader(oldFile));
		//前面编号信息照写
		for(int i=1;i<id;i++){
			out.println(in.readLine());
		}
		//写出要改的信息
		out.println(id+player.getAll());
		//跳过一行
		String line=in.readLine();
		//剩下的也输出
		while((line=in.readLine())!=null){
			out.println(line);
		}
		out.flush();
		in.close();
		out.close();
		//删除旧文件
		oldFile.delete();
		//重命名新文件
		newFile.renameTo(new File(filePath));
		//更新缓存区
		player.setId(id);
		PlayerBuffer.me.updateElement(id, player);
		return 100;
	}

	@Override
	public Player doSreach(int id) throws Exception {
		// TODO Auto-generated method stub
		//先查询缓存区
		Player player = PlayerBuffer.me.getElement(id);
		
		if(player!=null){
			return player;
		}
				in=new BufferedReader(new FileReader(filePath));
				//跳过前面的id
				for(int i=1;i<id;i++){
					in.readLine();
				}
				//取出符合的信息
				String str=in.readLine();
				in.close();
				String[]allStr = str.split("\\|");
				//创建对象
				player=new Player(allStr[1],Integer.valueOf(allStr[2]),allStr[3],allStr[4],allStr[5],allStr[6]);
				player.setId(id);
				//保存player对象进缓存区
				
					PlayerBuffer.me.setElement(player.getId(), player);
				
				
				return player;
	}

	@Override
	public Player doSreach(String name) throws Exception {
		// TODO Auto-generated method stub
		//先查询缓存区
		Player player=PlayerBuffer.me.getElement(name);
		if(player!=null){
			return player;
		}
		
		in=new BufferedReader(new FileReader(filePath));
		String str = null;
		while((str=in.readLine())!=null){
			String[]allStr = str.split("\\|");
			//如果名字相同的话就取出来
			if(allStr[1].equals(name)){
				player=new Player(allStr[1],Integer.valueOf(allStr[2]),allStr[3],allStr[4],allStr[5],allStr[6]);
				player.setId(Integer.valueOf(allStr[0]));
				in.close();
				//把player保存在缓存区
				PlayerBuffer.me.setElement(player.getId(), player);
				return player;
			}
		}
		in.close();
		return null;
	}

	@Override
	public int getSize() throws Exception {
		// TODO Auto-generated method stub
		
		//先查询缓存区
		int size=PlayerBuffer.me.getSize();
		if(size!=-1){
			return size;
		}
		//size重置为0
		size=0;
		//遍历文件有多少行，就有多少个人
		in=new BufferedReader(new FileReader(filePath));
		while(in.readLine()!=null){
			size++;
		}
		in.close();
		//保存size进缓存区
		try {
			PlayerBuffer.me.setSize(size);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return size;
	}
	@Override
	public int getPhoto(int id, String path) throws Exception {
		// TODO 自动生成的方法存根
		//本地图片地址
		String photoPath="./data/img/photo_"+id+".jpg";
		//得到上传图片的路径
		File upFile = new File(path);
		FileInputStream inStream=new  FileInputStream(upFile);
		//防止与本地文件冲突，先存在备份文件
		File downFile = new File(photoPath+".tmp");
		downFile.createNewFile();
		FileOutputStream outStream=new FileOutputStream(downFile);
		int b=0;
		//复制图片
		byte[] c=new byte[1024];
			while((b=inStream.read(c))!=-1){
				outStream.write(c, 0, b);
			}	
		outStream.flush();
		inStream.close();
		outStream.close();
		//把以前的文件删除
		new File(photoPath).delete();
		//重命名
		downFile.renameTo(new File(photoPath));
 		return 100;
	}
}
