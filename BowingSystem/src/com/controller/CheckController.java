package com.controller;

public class CheckController {
		private String error;

		public CheckController(String error) {
			this.error = error;
		}
		public CheckController(){
			
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			//便于
			this.error = error+"\n";
		}
		
		public void checkName(String name){
			//拆分字符
			char[] c=name.toCharArray();
			for(int i=0;i<c.length;i++){
				try {
					Integer.parseInt(String.valueOf(c[i]));
				} catch (Exception e) {
					// TODO: handle exception
					continue;
				}
				this.setError("名字不能包含数字");
				return;
			}
		}
		public void checkAge(String age){
			try {
				int i=Integer.parseInt(age);
				if(i<=0||i>100){
					this.setError("输入的年龄不合理");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				this.setError("年龄需要数字");
			}
			
		}
		public void checkTele(String tele){
			//拆分字符
			char[] c=tele.toCharArray();
			//判断是否11位
			if(c.length!=11){
				this.setError("电话格式错误，请输入11位");
			}
			//每个字符遍历看看是否全是文字
			for(int i=0;i<c.length;i++){
				try {
					Integer.parseInt(String.valueOf(c[i]));
				} catch (Exception e) {
					// TODO: handle exception
					this.setError("电话内容错误");
					return;
				}
				
			}
		}
		
}
