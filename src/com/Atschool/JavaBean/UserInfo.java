package com.Atschool.JavaBean;

/**
 * userInfo�û���Ϣ
 */
public class UserInfo {
	private String userName;//1.�û���
	private String password;//2.����
	private String email;	//3��������
	private String phoneNum;//4.�ֻ���	
	private int age=0;		//5.����
	private String birthday;//6.����
	private String constellation;//7.����
	private String address;//8.���ڵ�
	private String registerTime;//9.�˺�ע��ʱ��
	
	//1.�û���
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	//2.����
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//3��������
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//4.�ֻ���
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	//5.����
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	//6.����
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	//7.����
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	//8.���ڵ�
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	//9.�˺�ע��ʱ��
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
}
