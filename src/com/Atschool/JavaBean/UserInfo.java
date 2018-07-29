package com.Atschool.JavaBean;

/**
 * userInfo用户信息
 */
public class UserInfo {
	private String userName;//1.用户名
	private String password;//2.密码
	private String email;	//3电子邮箱
	private String phoneNum;//4.手机号	
	private int age=0;		//5.年龄
	private String birthday;//6.生日
	private String constellation;//7.星座
	private String address;//8.所在地
	private String registerTime;//9.账号注册时间
	
	//1.用户名
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	//2.密码
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//3电子邮箱
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//4.手机号
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	//5.年龄
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	//6.生日
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	//7.星座
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	//8.所在地
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	//9.账号注册时间
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
}
