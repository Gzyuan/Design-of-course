package com.controller;

import com.dao.factory.DAOFactory;

public class AgainstController {
	//抽象对象
	public static final AgainstController me=new AgainstController();
	//记录是否可以开始比赛
	private boolean ready=false;
	//保存对战表
	private int[][] table=null;
	//人数
	private int playerNum=0;
	public AgainstController() {
		// TODO Auto-generated constructor stub
		
	}
	//检查是能否开始比赛
	public void checkReady() throws Exception{
		//创建保存的数据
		this.setReady(false);
		this.setPlayerNum(DAOFactory.getIPlayerDAOInstance().getSize());
		this.setTable(new int[this.playerNum][this.playerNum]);
		//算出指数
		int k=(int)(Math.log(this.playerNum)/Math.log(2));
		//如果人数等于2的k次方就可以开始比赛
		if(this.playerNum==Math.pow(2, k)&&this.playerNum>=4){
			//开始比赛
			this.setReady(true);
			this.createTable(this.playerNum, k, this.table);
			return;
		}else{
			//清空对象
			this.setPlayerNum(0);
			this.setTable(null);
		}
	}
	//生成对战表的方法
	public void createTable(int n,int k,int[][]a){        
          
        for(int i=0;i<n;i++)  
        {  
            a[0][i]=i+1;  
        }  
        int m=1;  
        for(int s=1;s<=k;s++)  
        {  
            n/=2;         
            for(int t=1;t<=n;t++)  
            {  
                for(int i=m;i<2*m;i++)  
                {  
                    for(int j=m;j<2*m;j++)  
                    {  
                        a[i][j+(t-1)*m*2]=a[i-m][j+(t-1)*m*2-m];  
                        a[i][j+(t-1)*m*2-m]=a[i-m][j+(t-1)*m*2];  
                    }  
                }  
            }  
            m*=2;  
        }  
    }  
	//自动产生对手的成绩 形参是个长度为3的整形数组，分别保存id，天数，分数。
	public int[] getOpponentGrade(int[] a){
		if(this.ready==false){		
			return null;
			}
		//记录对手编号
		int temp=1;
		for(int i=0;i<this.table.length;i++){
			//遍历那天的对战，如果id相同就跳走
			if(a[0]==this.table[i][a[1]]){
				break;
			}
			temp++;
		}
		
		int[] b={temp,a[1],(2-a[2])};
		return b;
	}
	
	
	public int getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	public int[][] getTable() {
		return table;
	}
	public void setTable(int[][] table) {
		this.table = table;
	}
	public boolean isReady() {
		return ready;
	}
	private void setReady(boolean ready) {
		this.ready = ready;
	}
}
