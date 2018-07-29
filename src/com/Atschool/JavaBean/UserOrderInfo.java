package com.Atschool.JavaBean;

/**
 * userOrderInfo用户订单信息
 * @author Tigerkin
 *
 */
public class UserOrderInfo {
	private String userName;	//1.用户名
	private String consignee;	//2.收件人姓名
	private String phoneNum;	//3.收件人手机号
	private double cost=0;		//4.本次订单总费用
	private String address;		//5.收货地址
	private int orderState=0;		//6.订单状态（0：待付款    1：已付款/等待收货 2：已打印/等待收货  3：已收货）
	private String submitOrderTime;//7.提交订单时间
	private String completePrintTime;//8.打印完成时间
	private String completeOrderTime;//9.完成订单时间
	
	//1.用户名
	public void setUserName(String userName){
		this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
	//2.收件人姓名
	public void setConsignee(String consignee){
		this.consignee=consignee;
	}
	public String getConsignee(){
		return consignee;
	}
	//3.收件人手机号
	public void setPhoneNum(String phoneNum){
		this.phoneNum=phoneNum;
	}
	public String getPhoneNum(){
		return phoneNum;
	}
	//4.本次订单总费用
	public void setCost(double cost){
		this.cost=cost;
	}
	public double getCost(){
		return cost;
	}
	//5.收货地址
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	//6.订单状态（0：待付款    1：已付款/等待收货  2：已打印/等待收货  3：已收货）
	public void setOrderState(int orderState){
		this.orderState=orderState;
	}
	public int getOrderState(){
		return orderState;
	}
	//7.提交订单时间
	public void setSubmitOrderTime(String submitOrderTime){
		this.submitOrderTime=submitOrderTime;
	}
	public String getSubmitOrderTime(){
		return submitOrderTime;
	}
	//8.打印完成时间
	public void setCompletePrintTime(String completePrintTime){
		this.completePrintTime=completePrintTime;
	}
	public String getCompletePrintTime(){
		return completePrintTime;
	}
	//9.完成订单时间
	public void setCompleteOrderTime(String completeOrderTime){
		this.completeOrderTime=completeOrderTime;
	}
	public String getCompleteOrderTime(){
		return completeOrderTime;
	}
}
