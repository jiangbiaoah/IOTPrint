package com.Atschool.JavaBean;

/**
 * userPrintInfoAll��¼ÿ���û����е��ļ���Ϣ
 */
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.Atschool.Class.FileOperate;

public class UserPrintInfoAll {
	private ArrayList<UserPrintInfo> userPrintInfos=new ArrayList<UserPrintInfo>();
	private String userName;
	private int fileNum=0;//�ļ�����
	private double cost=0;//�ܷ���
	
	//����û��ĵ�����
	public void addInfo(UserPrintInfo userPrintInfo){
		userPrintInfos.add(userPrintInfo);
	}
	//��ȡ�û�����
	public UserPrintInfo getInfo(int i){
		UserPrintInfo userPrintInfo=new UserPrintInfo();
		if(i>=userPrintInfos.size()){
			return userPrintInfo;
		}else{
			return userPrintInfos.get(i);
		}
	}
	
	//��ȡ�û���
	public String getUserName(){
		userName=userPrintInfos.get(0).getUserName();
		return userName;
	}
	//��ȡ�ļ�����
	public int getFileNum(){
		fileNum=userPrintInfos.size();
		return fileNum;
	}
	
	//��ȡ��ӡ�ܷ���
	public double getCost(){
		cost=0;
		for (UserPrintInfo userInfo : userPrintInfos) {
			cost += userInfo.getCostEach();
		}
		DecimalFormat df=new DecimalFormat(".00");
		cost=Double.parseDouble(df.format(cost));
		return cost;
	}
	
	//ɾ��ĳ���ĵ�
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
