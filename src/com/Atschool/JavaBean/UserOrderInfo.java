package com.Atschool.JavaBean;

/**
 * userOrderInfo�û�������Ϣ
 * @author Tigerkin
 *
 */
public class UserOrderInfo {
	private String userName;	//1.�û���
	private String consignee;	//2.�ռ�������
	private String phoneNum;	//3.�ռ����ֻ���
	private double cost=0;		//4.���ζ����ܷ���
	private String address;		//5.�ջ���ַ
	private int orderState=0;		//6.����״̬��0��������    1���Ѹ���/�ȴ��ջ� 2���Ѵ�ӡ/�ȴ��ջ�  3�����ջ���
	private String submitOrderTime;//7.�ύ����ʱ��
	private String completePrintTime;//8.��ӡ���ʱ��
	private String completeOrderTime;//9.��ɶ���ʱ��
	
	//1.�û���
	public void setUserName(String userName){
		this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
	//2.�ռ�������
	public void setConsignee(String consignee){
		this.consignee=consignee;
	}
	public String getConsignee(){
		return consignee;
	}
	//3.�ռ����ֻ���
	public void setPhoneNum(String phoneNum){
		this.phoneNum=phoneNum;
	}
	public String getPhoneNum(){
		return phoneNum;
	}
	//4.���ζ����ܷ���
	public void setCost(double cost){
		this.cost=cost;
	}
	public double getCost(){
		return cost;
	}
	//5.�ջ���ַ
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	//6.����״̬��0��������    1���Ѹ���/�ȴ��ջ�  2���Ѵ�ӡ/�ȴ��ջ�  3�����ջ���
	public void setOrderState(int orderState){
		this.orderState=orderState;
	}
	public int getOrderState(){
		return orderState;
	}
	//7.�ύ����ʱ��
	public void setSubmitOrderTime(String submitOrderTime){
		this.submitOrderTime=submitOrderTime;
	}
	public String getSubmitOrderTime(){
		return submitOrderTime;
	}
	//8.��ӡ���ʱ��
	public void setCompletePrintTime(String completePrintTime){
		this.completePrintTime=completePrintTime;
	}
	public String getCompletePrintTime(){
		return completePrintTime;
	}
	//9.��ɶ���ʱ��
	public void setCompleteOrderTime(String completeOrderTime){
		this.completeOrderTime=completeOrderTime;
	}
	public String getCompleteOrderTime(){
		return completeOrderTime;
	}
}
