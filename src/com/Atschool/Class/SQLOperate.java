package com.Atschool.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.Atschool.JavaBean.UserInfo;
import com.Atschool.JavaBean.UserOrderInfo;
import com.Atschool.JavaBean.UserPrintInfo;
import com.Atschool.JavaBean.UserPrintInfoAll;

/**
 * ���ݿ������
 */
public class SQLOperate {
	/**
	 * ���û�ע�᡿������û���Ϣ�����ݿ�----------------------------------------------��userInfo��-----------------
	 * @param userInfo �û�����
	 */
	public static boolean saveUserInfo(UserInfo userInfo) {
		LogInfo.output("SQL:�û�ע�ᣬ׼�����û���Ϣ���浽���ݿ���...");

		// ��ȡ���ݿ�����Connection����
		Connection conn = ConnectDB.getConnection();
		// �����û�ע����Ϣ��SQL���
		String sql = "insert into userInfo(userName,password,email,phoneNum,age,birthday,constellation,address) values(?,?,?,?,?,?,?,?)";
		try {
			// ��ȡPreparedStatement����
			PreparedStatement ps = conn.prepareStatement(sql);
			// ��SQL����ռλ���������ж�̬��ֵ
			ps.setString(1, userInfo.getUserName());	 //1.�û���
			ps.setString(2, userInfo.getPassword());	 //2.����
			ps.setString(3, userInfo.getEmail());		 //3.����
			ps.setString(4, userInfo.getPhoneNum());	 //4.�ֻ���
			ps.setString(5, String.valueOf(userInfo.getAge()));			 //5.����
			ps.setString(6, userInfo.getBirthday());	 //6.����
			ps.setString(7, userInfo.getConstellation());//7.����
			ps.setString(8, userInfo.getAddress());		 //8.���ڵ�
			
			// ִ�и��²���
			ps.executeUpdate();
			// �ͷŴ� PreparedStatement ��������ݿ�� JDBC ��Դ
			ps.close();
			LogInfo.output("SQL:�����û���Ϣ�����ݿ�ɹ�...");
		} catch (Exception e) {
			LogInfo.output("SQL:�����û���Ϣ�����ݿ�ʧ��...");
			e.printStackTrace();
			return false;
		} finally {
			// �ر����ݿ�����
			ConnectDB.closeConnection(conn);
		}
		return true;
	}

	/**
	 * �������û���Ϣ�����û����û���Ϣҳ�п��Ա༭������Ϣ
	 * @param userInfo
	 * @return
	 */
	public static boolean updateUserInfo(UserInfo userInfo){
		LogInfo.output("SQL:׼�������û���Ϣ...");
		//��ȡ�������û���Ϣ��������µ����ݿ���
		String userName=userInfo.getUserName();
		String phoneNum=userInfo.getPhoneNum();
		int age=userInfo.getAge();
		String birthday=userInfo.getBirthday();
		String constellation=userInfo.getConstellation();
		String address=userInfo.getAddress();
		//String registerTime=userInfo.getRegisterTime();
		
		Connection conn=ConnectDB.getConnection();
		String sql="update userInfo set"
				+ " phoneNum='"+phoneNum+"'"
				+ ", age='"+age+"'"
				+ ", birthday='"+birthday+"'"
				+ ", constellation='"+constellation+"'"
				+ ", address='"+address+"'"
				+ " where userName='"+userName+"'";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			ConnectDB.closeConnection(conn);
		}
		LogInfo.output("SQL:�û���Ϣ���³ɹ�");
		//���¶���״̬��Ϣ
		return true;
	}
	
	/**
	 * ��ƥ���û���Ϣ��:�����û����������ѯ���ݿ�
	 * 	  			���û����ڣ������û�����user
	 * 				����ƥ�������user=null;
	 * @param username �û���
	 * @param password ����
	 * @return UserInfo����
	 */
	public static UserInfo matchUserInfo(String userName, String password) {
		LogInfo.output("׼��ƥ���û����ݿ���Ϣ...");
		
		UserInfo userInfo = null;
		// ��ȡ���ݿ�����Connection����
		Connection conn = ConnectDB.getConnection();
		// �����û����������ѯ�û���Ϣ
		String sql = "select * from userInfo where userName = ? and password = ?";
		try {
			// ��ȡPreparedStatement����
			PreparedStatement ps = conn.prepareStatement(sql);
			// ��SQL����ռλ���������ж�̬��ֵ
			ps.setString(1, userName);
			ps.setString(2, password);
			// ִ�в�ѯ��ȡ�����
			ResultSet rs = ps.executeQuery();
			// �жϽ�����Ƿ���Ч
			if (rs.next()) {
				// ʵ����һ���û�����
				userInfo = new UserInfo();
				// ���û��������Ը�ֵ
				userInfo.setUserName(rs.getString("userName"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setPhoneNum(rs.getString("phoneNum"));
				userInfo.setAge(Integer.parseInt(rs.getString("age")));
				userInfo.setBirthday(rs.getString("birthday"));
				userInfo.setConstellation(rs.getString("constellation"));
				userInfo.setAddress(rs.getString("address"));
			}
			// �ͷŴ� ResultSet ��������ݿ�� JDBC ��Դ
			rs.close();
			// �ͷŴ� PreparedStatement ��������ݿ�� JDBC ��Դ
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر����ݿ�����
			ConnectDB.closeConnection(conn);
		}
		LogInfo.output("�û����ݿ���Ϣƥ�����");
		return userInfo;
	}

	/**
	 * ���ж��û��������ݿ����Ƿ���ڡ�:�����ڣ��򷵻���ʵ�����������ڣ��򷵻�null��
	 * @param username �û���
	 * @return ����ֵ
	 */
	public static UserInfo getUserInfo(String userName) {
		UserInfo userInfo=null;
		Connection conn = ConnectDB.getConnection();
		String sql = "select * from userInfo where userName = '"+userName+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userInfo=new UserInfo();
				userInfo.setUserName(rs.getString(1));
				userInfo.setPassword("******");		//����������Ϣ
				userInfo.setEmail(rs.getString(3));
				userInfo.setPhoneNum(rs.getString(4));
				userInfo.setAge(Integer.parseInt(rs.getString(5)));
				userInfo.setBirthday(rs.getString(6));
				userInfo.setConstellation(rs.getString(7));
				userInfo.setAddress(rs.getString(8));
			}
			rs.close();
			// �ͷŴ� PreparedStatement ��������ݿ�� JDBC ��Դ
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر����ݿ�����
			ConnectDB.closeConnection(conn);
		}
		return userInfo;
	}
	
	/**
	 * �������û��ļ���Ϣ�������û���Ҫ��ӡ���ļ���Ϣ��ӵ����ݿ�------------------------��userPrintInfo��-----------
	 * @param userPrintInfoAll
	 * @return
	 */
	public static boolean saveUserPrintInfo(UserPrintInfoAll userPrintInfoAll){
		Connection conn=ConnectDB.getConnection();
		try {
			//ѭ��д���û��������е��ļ���Ϣ
			for(int i=0;i<userPrintInfoAll.getFileNum();i++){
				LogInfo.output("SQL��׼����¼�û��ĵ�"+(i+1)+"���ļ�...");
				String sql = "insert into "
						+ "userPrintInfo(userName,fileName,pages,copies,collated,color,singleSide,costEach,submitOrderTime) "
						+ "values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps=conn.prepareStatement(sql);
				
				ps.setString(1, userPrintInfoAll.getInfo(i).getUserName());
				ps.setString(2, userPrintInfoAll.getInfo(i).getFileReamName());
				ps.setString(3, String.valueOf(userPrintInfoAll.getInfo(i).getPages()));
				ps.setString(4, String.valueOf(userPrintInfoAll.getInfo(i).getCopies()));
				ps.setString(5, String.valueOf(userPrintInfoAll.getInfo(i).getCollated()));
				ps.setString(6, String.valueOf(userPrintInfoAll.getInfo(i).getColor()));
				ps.setString(7, String.valueOf(userPrintInfoAll.getInfo(i).getSingleSide()));
				ps.setString(8, String.valueOf(userPrintInfoAll.getInfo(i).getCostEach()));
				ps.setString(9, userPrintInfoAll.getInfo(i).getSubmitOrderTime());
				//ps.setString(10, userPrintInfoAll.getInfo(i).getCompletePrintTime());
				//ps.setString(11, userPrintInfoAll.getInfo(i).getCompleteOrderTime());
				
				ps.executeUpdate();
				ps.close();
				LogInfo.output("SQL���û��ĵ�"+(i+1)+"���ļ��ɹ���¼");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			ConnectDB.closeConnection(conn);
		}
		LogInfo.output("SQL���û������ļ��ѳɹ����浽���ݿ���");
		return true;
		
	}
	
	/**
	 * ����ȡ�û���ӡ�ļ���Ϣ���������û�����ȡָ�������ύʱ����û��ļ���Ϣ
	 * @return ArrayList
	 */
	public static ArrayList<UserPrintInfo> getUserPrintInfo(String userName,String submitOrderTime){
		ArrayList<UserPrintInfo> userPrintInfos=new ArrayList<UserPrintInfo>();
		UserPrintInfo userPrintInfo=null;
		Connection conn=ConnectDB.getConnection();
		String sql="select * from userPrintInfo where userName='"+userName+"' and submitOrderTime='"+submitOrderTime+"'";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				userPrintInfo=new UserPrintInfo();
				userPrintInfo.setUserName(rs.getString(1));
				userPrintInfo.setFileName(rs.getString(2));
				userPrintInfo.setPages(rs.getInt(3));
				userPrintInfo.setCopies(rs.getInt(4));
				userPrintInfo.setCollated(rs.getInt(5));
				userPrintInfo.setColor(rs.getInt(6));
				userPrintInfo.setSingleSide(rs.getInt(7));
				userPrintInfo.setCostEach(rs.getDouble(8));
				userPrintInfo.setSubmitOrderTime(rs.getString(9));
				userPrintInfo.setCompletePrintTime(rs.getString(10));
				userPrintInfo.setCompleteOrderTime(rs.getString(11));
				userPrintInfos.add(userPrintInfo);
			}
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDB.closeConnection(conn);
		}
		return userPrintInfos;
	}
	
	/**
	 * �������û�������Ϣ�����û���д���ջ���Ϣ�ύ����ʱ�����û�������Ϣ���浽���ݿ�--------------------��userOrderInfo��-----------
	 * 						orderState=0��������    
	 * @param userOrderInfo
	 * @return 
	 */
	public static boolean saveUserOrderInfo(UserOrderInfo userOrderInfo){
		LogInfo.output("SQL:׼�����û�������Ϣ���浽���ݿ���...");
		Connection conn=ConnectDB.getConnection();
		String sql="insert into "
				+ "userOrderInfo(userName,consignee,phoneNum,cost,address,orderState,submitOrderTime) "
				+ "values(?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ps.setString(1, userOrderInfo.getUserName());
			ps.setString(2, userOrderInfo.getConsignee());
			ps.setString(3, userOrderInfo.getPhoneNum());
			ps.setString(4, String.valueOf(userOrderInfo.getCost()));
			ps.setString(5, userOrderInfo.getAddress());
			ps.setString(6, "0");				//orderState=0�������� 
			ps.setString(7, userOrderInfo.getSubmitOrderTime());
			//ps.setString(8, userOrderInfo.getCompletePrintTime());
			//ps.setString(9, userOrderInfo.getCompleteOrderTime());
			
			ps.executeUpdate();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			ConnectDB.closeConnection(conn);
		}
		LogInfo.output("SQL:�û�������Ϣ�ѳɹ����浽���ݿ���");
		return true;
	}
	
	/**
	 * ����ȡ�û�������Ϣ������ȡָ���û��ض�����״̬�����ж�����Ϣ
	 * @param userName �û���
	 * @param orderState ����״̬
	 * @return ArrayList����Ҫ������ж�����Ϣ
	 */
	public static ArrayList<UserOrderInfo> getUserOrderInfo(String userName,int orderState){
		ArrayList<UserOrderInfo> userOrderInfos=new ArrayList<UserOrderInfo>();
		UserOrderInfo userOrderInfo=null;
		Connection conn=ConnectDB.getConnection();
		String sql="select * from userOrderInfo where userName='"+userName+"' and orderState='"+orderState+"'";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				userOrderInfo=new UserOrderInfo();
				userOrderInfo.setUserName(rs.getString(1));
				userOrderInfo.setConsignee(rs.getString(2));
				userOrderInfo.setPhoneNum(rs.getString(3));
				userOrderInfo.setCost(rs.getDouble(4));
				userOrderInfo.setAddress(rs.getString(5));
				userOrderInfo.setOrderState(rs.getInt(6));
				userOrderInfo.setSubmitOrderTime(rs.getString(7));
				userOrderInfo.setCompletePrintTime(rs.getString(8));
				userOrderInfo.setCompleteOrderTime(rs.getString(9));
				userOrderInfos.add(userOrderInfo);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDB.closeConnection(conn);
		}
		return userOrderInfos;
	}
	/**
	 * ����ȡ���еȴ���ӡ�Ķ�����Ϣ�������ݶ���״̬��ȡ���з����������û��Ķ�����Ϣ
	 * @param orderState ����״̬
	 * @return ArrayList����Ҫ������ж�����Ϣ
	 */
	public static ArrayList<UserOrderInfo> getUserOrderInfo(int orderState){
		ArrayList<UserOrderInfo> userOrderInfos=new ArrayList<UserOrderInfo>();
		UserOrderInfo userOrderInfo=null;
		Connection conn=ConnectDB.getConnection();
		String sql="select * from userOrderInfo where orderState='"+orderState+"'";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				userOrderInfo=new UserOrderInfo();
				userOrderInfo.setUserName(rs.getString(1));
				userOrderInfo.setConsignee(rs.getString(2));
				userOrderInfo.setPhoneNum(rs.getString(3));
				userOrderInfo.setCost(rs.getDouble(4));
				userOrderInfo.setAddress(rs.getString(5));
				userOrderInfo.setOrderState(rs.getInt(6));
				userOrderInfo.setSubmitOrderTime(rs.getString(7));
				userOrderInfo.setCompletePrintTime(rs.getString(8));
				userOrderInfo.setCompleteOrderTime(rs.getString(9));
				userOrderInfos.add(userOrderInfo);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDB.closeConnection(conn);
		}
		return userOrderInfos;
	}
	
	
	/**
	 * �������ĵ���ӡ���ʱ�䡿������userName��submitOrderTimeȷ��Ҫ���µ���
	 * 							ͬʱ�����userPrintInfo��userOrderInfo�в����ĵ���ӡ���ʱ�䣬
	 * 							ͬʱ���±�userOrderInfo�еĶ���״̬updateOrderState(2)
	 * @param completePrintTime
	 */
	public static boolean updateCompletePrintTime(String userName,String submitOrderTime,String completePrintTime){
		LogInfo.output("SQL:׼�����¶�����ӡ���ʱ��...");
		Connection conn=ConnectDB.getConnection();
		String sql1="update userOrderInfo set completePrintTime='"+completePrintTime+"' "
				+ "where userName='"+userName+"' and submitOrderTime='"+submitOrderTime+"'";
		String sql2="update userPrintInfo set completePrintTime='"+completePrintTime+"' "
				+ "where userName='"+userName+"' and submitOrderTime='"+submitOrderTime+"'";
		try {
			PreparedStatement ps1=conn.prepareStatement(sql1);
			PreparedStatement ps2=conn.prepareStatement(sql2);
			ps1.executeUpdate();
			ps2.executeUpdate();
			ps1.close();
			ps2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			ConnectDB.closeConnection(conn);
		}
		LogInfo.output("SQL:������ӡ���ʱ����³ɹ�");
		//���¶���״̬��Ϣ   3����ӡ���/�ȴ��ջ�
		boolean boolUpdateOrderState = updateOrderState(userName, submitOrderTime, 3);
		if(boolUpdateOrderState == false){
			return false;
		}
		return true;
	}
	
	/**
	 * �����¶������ʱ�䣨�ջ�ʱ�䣩����ͬʱ�����userPrintInfo��userOrderInfo�в��붩�����ʱ�䣬
	 * 						ͬʱ���±�userOrderInfo�еĶ���״̬updateOrderState(3)
	 * @param completeOrderTime
	 * @return
	 */
	public static boolean updateCompleteOrderTime(String userName,String submitOrderTime,String completeOrderTime){
		LogInfo.output("SQL:׼�����¶������ʱ��...");
		Connection conn=ConnectDB.getConnection();
		String sql1="update userOrderInfo set completeOrderTime='"+completeOrderTime+"' "
				+ "where userName='"+userName+"' and submitOrderTime='"+submitOrderTime+"'";
		String sql2="update userPrintInfo set completeOrderTime='"+completeOrderTime+"' "
				+ "where userName='"+userName+"' and submitOrderTime='"+submitOrderTime+"'";
		try {
			PreparedStatement ps1=conn.prepareStatement(sql1);
			PreparedStatement ps2=conn.prepareStatement(sql2);
			ps1.executeUpdate();
			ps2.executeUpdate();
			ps1.close();
			ps2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			ConnectDB.closeConnection(conn);
		}
		LogInfo.output("SQL:�������ʱ����³ɹ�");
		//���¶���״̬��Ϣ  4�����ջ�
		boolean boolUpdateOrderState = updateOrderState(userName, submitOrderTime, 4);
		if(boolUpdateOrderState == false){
			return false;
		}
		return true;
	}
	
	/**
	 * �����¶���״̬��Ϣ��
	 * 0�������� -----------�ύ����ʱ����
	 * 1���Ѹ���/�ȴ���ӡ  --������ɺ����
	 * 2��������/������ӡ --������ɺ����
	 * 3���Ѵ�ӡ/�ȴ��ջ�  --��ӡ��ɺ����
	 * 4�����ջ�-----------�ջ���ɺ����
	 * @param orderState
	 * @return
	 */
	public static boolean updateOrderState(String userName,String submitOrderTime,int orderState){
		LogInfo.output("SQL:׼�����¶���״̬��Ϣ...");
		Connection conn=ConnectDB.getConnection();
		String sql="update userOrderInfo set orderState='"+orderState+"' where userName='"+userName+"' and submitOrderTime='"+submitOrderTime+"'";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			ConnectDB.closeConnection(conn);
		}
		LogInfo.output("SQL:����״̬��Ϣ���³ɹ�");
		return true;
	}
	
	/**
	 * �����:�����û�����Ҫ�ۿ�ķ���----------------------------------------��wallet��--------------------------
	 * @param userName
	 * @param cost 
	 * @return
	 */
	public static boolean pay(String userName,double cost){
		LogInfo.output("SQL:׼���ۿ�...");
		double balance=SQLOperate.getBalance(userName);//���
		
		//���û�������ʱ���ܳɹ��ۿ�
		if(balance>=cost){
			balance=balance-cost;
			Connection conn=ConnectDB.getConnection();
			String sql="update wallet set balance='"+balance+"' where userName='"+userName+"'";
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.executeUpdate();
				ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}finally {
				ConnectDB.closeConnection(conn);
			}
			LogInfo.output("SQL:�ۿ�ɹ�");
			return true;
		}else{
			LogInfo.output("SQL:�ۿ�ʧ�ܣ��û�����");
			return false;
		}
	}
	
	/**
	 * ����ȡ�û����������û�����ѯ�û����
	 * @param userName
	 * @return
	 */
	public static double getBalance(String userName){
		LogInfo.output("SQL:׼����ѯ���...");
		double balance=-1;//���
		
		Connection conn=ConnectDB.getConnection();
		String sql="select balance from wallet where userName='"+userName+"'";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				balance=rs.getDouble(1);
			}
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDB.closeConnection(conn);
		}
		DecimalFormat df=new DecimalFormat(".00");
		balance=Double.parseDouble(df.format(balance));
		LogInfo.output("SQL:����ѯ�ɹ�");
		return balance;
	}
	
	/**
	 * ����ֵ����ָ���û��ͳ�ֵ����
	 * @param userName
	 * @param figure ��ֵ���
	 * @return
	 */
	public static boolean charge(String userName,double figure){
		LogInfo.output("SQL:׼���������...");
		
		Connection conn=ConnectDB.getConnection();
		String sql=null;
		
		double balance=getBalance(userName);//��balance=-1�������ݿ���wallet����û�и��û�
		
		try {
			PreparedStatement ps=null;
			if(balance==-1){
				sql="insert into wallet(userName,balance) values('"+userName+"','"+figure+"')";//����ע���û��������1Ԫ��ӡ��
				ps=conn.prepareStatement(sql);
				ps.executeUpdate();
				LogInfo.output("����ע���û��������1Ԫ��ӡ��");
			}else{
				balance += figure;
				sql="update wallet set balance='"+balance+"' where userName='"+userName+"'";
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					balance=rs.getDouble(1);
				}
			}
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			ConnectDB.closeConnection(conn);
		}
		LogInfo.output("SQL:�����³ɹ�");
		return true;
	}
	
	
}
