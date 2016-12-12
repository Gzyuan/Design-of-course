package test;

public class Test_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] a=new int[8][8];
		new Test_2().createTable(8, 3, a);
		
		for(int i =0;i<a.length;i++){
			for(int j=0;j<a[i].length;j++){
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
		
		int temp=1;
		for(int i=0;i<a.length;i++){
			if((5)==a[i][3]){
				break;
			}
			temp++;
		}
		System.out.println(temp);
	}
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

}
