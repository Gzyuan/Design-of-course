package com.controller;

public class ErrorController {
		private String error;
		public static final ErrorController me=new ErrorController();
		public ErrorController(String error) {
			this.error = error;
		}
		public ErrorController(){
			this.error="";
		}
		public String getError() {
			return error;
		}
		public void addError(String error) {
			this.error += error+"\n";
		}
		public void setError(String error){
			this.error=error;
		}
		public String checkName(String name){
			//拆分字符
			if(name==null||name.equals("")){
				this.addError("名字不能为空！");
				return "";
			}
			char[] c=name.toCharArray();
			for(int i=0;i<c.length;i++){
				try {
					Integer.parseInt(String.valueOf(c[i]));
				} catch (Exception e) {
					// TODO: handle exception
					continue;
				}
				this.addError("名字不能包含数字！");
				return "";
			}
			return name;
		}
		public int checkAge(String age){
			if(age==null||age.equals("")){
				this.addError("年龄不能为空！");
				return 0;
			}
			int i=0;
			try {
				i=Integer.parseInt(age);
				if(i<=0||i>100){
					this.addError("输入的年龄不合理！");
					return 0;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				this.addError("年龄需要数字！");
				return 0;
			}
			return i;
		}
		public String checkTele(String tele){
			if(tele==null||tele.equals("")){
				this.addError("电话不能为空！");
				return "";
			}
			//拆分字符
			char[] c=tele.toCharArray();
			//判断电话长度是否11位
			if(c.length!=11){
				this.addError("电话内容格式错误，请输入11位数字！");
				return "";
			}
			//每个字符遍历看看是否全是文字
			for(int i=0;i<c.length;i++){
				try {
					Integer.parseInt(String.valueOf(c[i]));
				} catch (Exception e) {
					// TODO: handle exception
					this.addError("电话内容格式错误！");
					return "";
				}
				
			}
			return tele;
		}
		public String checkPhoto(String path){
			if(path==null||path.equals("")){
				this.addError("头像不能为空！");
				return "";
			}
			return path;
		}
		public String checkAddress(String address){
			if(address==null||address.equals("")){
				this.addError("地址不能为空！");
				return "";
			}
			return address;
		}
		
}
