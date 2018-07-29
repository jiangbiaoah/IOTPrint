package com.Atschool.JavaBean;

/**
 * userPrintInfoAll记录每个用户所有的文件信息
 */
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.Atschool.Class.FileOperate;

public class UserPrintInfoAll {
	private ArrayList<UserPrintInfo> userPrintInfos=new ArrayList<UserPrintInfo>();
	private String userName;
	private int fileNum=0;//文件个数
	private double cost=0;//总费用
	
	//添加用户文档数据
	public void addInfo(UserPrintInfo userPrintInfo){
		userPrintInfos.add(userPrintInfo);
	}
	//获取用户数据
	public UserPrintInfo getInfo(int i){
		UserPrintInfo userPrintInfo=new UserPrintInfo();
		if(i>=userPrintInfos.size()){
			return userPrintInfo;
		}else{
			return userPrintInfos.get(i);
		}
	}
	
	//获取用户名
	public String getUserName(){
		userName=userPrintInfos.get(0).getUserName();
		return userName;
	}
	//获取文件份数
	public int getFileNum(){
		fileNum=userPrintInfos.size();
		return fileNum;
	}
	
	//获取打印总费用
	public double getCost(){
		cost=0;
		for (UserPrintInfo userInfo : userPrintInfos) {
			cost += userInfo.getCostEach();
		}
		DecimalFormat df=new DecimalFormat(".00");
		cost=Double.parseDouble(df.format(cost));
		return cost;
	}
	
	//删除某个文档
	public boolean delSelectedDoc(int i){
		if(i<userPrintInfos.size()){
			String strFilePath=userPrintInfos.get(i).getFilePath();
			userPrintInfos.remove(i);
			FileOperate fileOperate=new FileOperate(strFilePath);
			fileOperate.deleteDoc(strFilePath);
			return true;
		}else {
			return false;
		}
	}
}
