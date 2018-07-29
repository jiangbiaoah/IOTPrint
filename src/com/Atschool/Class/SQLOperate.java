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
 * 数据库操作类
 */
public class SQLOperate {
	/**
	 * 【用户注册】：添加用户信息到数据库----------------------------------------------【userInfo】-----------------
	 * @param userInfo 用户对象
	 */
	public static boolean saveUserInfo(UserInfo userInfo) {
		LogInfo.output("SQL:用户注册，准备将用户信息保存到数据库中...");

		// 获取数据库连接Connection对象
		Connection conn = ConnectDB.getConnection();
		// 插入用户注册信息的SQL语句
		String sql = "insert into userInfo(userName,password,email,phoneNum,age,birthday,constellation,address) values(?,?,?,?,?,?,?,?)";
		try {
			// 获取PreparedStatement对象
			PreparedStatement ps = conn.prepareStatement(sql);
			// 对SQL语句的占位符参数进行动态赋值
			ps.setString(1, userInfo.getUserName());	 //1.用户名
			ps.setString(2, userInfo.getPassword());	 //2.密码
			ps.setString(3, userInfo.getEmail());		 //3.邮箱
			ps.setString(4, userInfo.getPhoneNum());	 //4.手机号
			ps.setString(5, String.valueOf(userInfo.getAge()));			 //5.年龄
			ps.setString(6, userInfo.getBirthday());	 //6.生日
			ps.setString(7, userInfo.getConstellation());//7.星座
			ps.setString(8, userInfo.getAddress());		 //8.所在地
			
			// 执行更新操作
			ps.executeUpdate();
			// 释放此 PreparedStatement 对象的数据库和 JDBC 资源
			ps.close();
			LogInfo.output("SQL:保存用户信息到数据库成功...");
		} catch (Exception e) {
			LogInfo.output("SQL:保存用户信息到数据库失败...");
			e.printStackTrace();
			return false;
		} finally {
			// 关闭数据库连接
			ConnectDB.closeConnection(conn);
		}
		return true;
	}

	/**
	 * 【更新用户信息】：用户在用户信息页中可以编辑个人信息
	 * @param userInfo
	 * @return
	 */
	public static boolean updateUserInfo(UserInfo userInfo){
		LogInfo.output("SQL:准备更新用户信息...");
		//获取传来的用户信息，将其更新到数据库中
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
		LogInfo.output("SQL:用户信息更新成功");
		//更新订单状态信息
		return true;
	}
	
	/**
	 * 【匹配用户信息】:根据用户名和密码查询数据库
	 * 	  			若用户存在，返回用户对象user
	 * 				若无匹配项，返回user=null;
	 * @param username 用户名
	 * @param password 密码
	 * @return UserInfo对象
	 */
	public static UserInfo matchUserInfo(String userName, String password) {
		LogInfo.output("准备匹配用户数据库信息...");
		
		UserInfo userInfo = null;
		// 获取数据库连接Connection对象
		Connection conn = ConnectDB.getConnection();
		// 根据用户名及密码查询用户信息
		String sql = "select * from userInfo where userName = ? and password = ?";
		try {
			// 获取PreparedStatement对象
			PreparedStatement ps = conn.prepareStatement(sql);
			// 对SQL语句的占位符参数进行动态赋值
			ps.setString(1, userName);
			ps.setString(2, password);
			// 执行查询获取结果集
			ResultSet rs = ps.executeQuery();
			// 判断结果集是否有效
			if (rs.next()) {
				// 实例化一个用户对象
				userInfo = new UserInfo();
				// 对用户对象属性赋值
				userInfo.setUserName(rs.getString("userName"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setPhoneNum(rs.getString("phoneNum"));
				userInfo.setAge(Integer.parseInt(rs.getString("age")));
				userInfo.setBirthday(rs.getString("birthday"));
				userInfo.setConstellation(rs.getString("constellation"));
				userInfo.setAddress(rs.getString("address"));
			}
			// 释放此 ResultSet 对象的数据库和 JDBC 资源
			rs.close();
			// 释放此 PreparedStatement 对象的数据库和 JDBC 资源
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库连接
			ConnectDB.closeConnection(conn);
		}
		LogInfo.output("用户数据库信息匹配完成");
		return userInfo;
	}

	/**
	 * 【判断用户名在数据库中是否存在】:若存在，则返回其实例；若不存在，则返回null。
	 * @param username 用户名
	 * @return 布尔值
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
				userInfo.setPassword("******");		//隐藏敏感信息
				userInfo.setEmail(rs.getString(3));
				userInfo.setPhoneNum(rs.getString(4));
				userInfo.setAge(Integer.parseInt(rs.getString(5)));
				userInfo.setBirthday(rs.getString(6));
				userInfo.setConstellation(rs.getString(7));
				userInfo.setAddress(rs.getString(8));
			}
			rs.close();
			// 释放此 PreparedStatement 对象的数据库和 JDBC 资源
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库连接
			ConnectDB.closeConnection(conn);
		}
		return userInfo;
	}
	
	/**
	 * 【保存用户文件信息】：将用户需要打印的文件信息添加到数据库------------------------【userPrintInfo】-----------
	 * @param userPrintInfoAll
	 * @return
	 */
	public static boolean saveUserPrintInfo(UserPrintInfoAll userPrintInfoAll){
		Connection conn=ConnectDB.getConnection();
		try {
			//循环写入用户本次所有的文件信息
			for(int i=0;i<userPrintInfoAll.getFileNum();i++){
				LogInfo.output("SQL：准备记录用户的第"+(i+1)+"个文件...");
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
				LogInfo.output("SQL：用户的第"+(i+1)+"个文件成功记录");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			ConnectDB.closeConnection(conn);
		}
		LogInfo.output("SQL：用户所有文件已成功保存到数据库中");
		return true;
		
	}
	
	/**
	 * 【读取用户打印文件信息】：根据用户名获取指定订单提交时间的用户文件信息
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
	 * 【保存用户订单信息】：用户填写完收货信息提交订单时，将用户订单信息保存到数据库--------------------【userOrderInfo】-----------
	 * 						orderState=0：待付款    
	 * @param userOrderInfo
	 * @return 
	 */
	public static boolean saveUserOrderInfo(UserOrderInfo userOrderInfo){
		LogInfo.output("SQL:准备将用户订单信息保存到数据库中...");
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
			ps.setString(6, "0");				//orderState=0：待付款 
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
		LogInfo.output("SQL:用户订单信息已成功保存到数据库中");
		return true;
	}
	
	/**
	 * 【读取用户订单信息】：获取指定用户特定订单状态的所有订单信息
	 * @param userName 用户名
	 * @param orderState 订单状态
	 * @return ArrayList符合要求的所有订单信息
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
	 * 【获取所有等待打印的订单信息】：根据订单状态获取所有符合条件的用户的订单信息
	 * @param orderState 订单状态
	 * @return ArrayList符合要求的所有订单信息
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
	 * 【更新文档打印完成时间】：根据userName和submitOrderTime确定要更新的行
	 * 							同时将向表userPrintInfo，userOrderInfo中插入文档打印完成时间，
	 * 							同时更新表userOrderInfo中的订单状态updateOrderState(2)
	 * @param completePrintTime
	 */
	public static boolean updateCompletePrintTime(String userName,String submitOrderTime,String completePrintTime){
		LogInfo.output("SQL:准备更新订单打印完成时间...");
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
		LogInfo.output("SQL:订单打印完成时间更新成功");
		//更新订单状态信息   3：打印完成/等待收货
		boolean boolUpdateOrderState = updateOrderState(userName, submitOrderTime, 3);
		if(boolUpdateOrderState == false){
			return false;
		}
		return true;
	}
	
	/**
	 * 【更新订单完成时间（收货时间）】：同时将向表userPrintInfo，userOrderInfo中插入订单完成时间，
	 * 						同时更新表userOrderInfo中的订单状态updateOrderState(3)
	 * @param completeOrderTime
	 * @return
	 */
	public static boolean updateCompleteOrderTime(String userName,String submitOrderTime,String completeOrderTime){
		LogInfo.output("SQL:准备更新订单完成时间...");
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
		LogInfo.output("SQL:订单完成时间更新成功");
		//更新订单状态信息  4：已收货
		boolean boolUpdateOrderState = updateOrderState(userName, submitOrderTime, 4);
		if(boolUpdateOrderState == false){
			return false;
		}
		return true;
	}
	
	/**
	 * 【更新订单状态信息】
	 * 0：待付款 -----------提交订单时更新
	 * 1：已付款/等待打印  --付款完成后更新
	 * 2：已下载/即将打印 --下载完成后更新
	 * 3：已打印/等待收货  --打印完成后更新
	 * 4：已收货-----------收货完成后更新
	 * @param orderState
	 * @return
	 */
	public static boolean updateOrderState(String userName,String submitOrderTime,int orderState){
		LogInfo.output("SQL:准备更新订单状态信息...");
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
		LogInfo.output("SQL:订单状态信息更新成功");
		return true;
	}
	
	/**
	 * 【付款】:传入用户名和要扣款的费用----------------------------------------【wallet】--------------------------
	 * @param userName
	 * @param cost 
	 * @return
	 */
	public static boolean pay(String userName,double cost){
		LogInfo.output("SQL:准备扣款...");
		double balance=SQLOperate.getBalance(userName);//余额
		
		//当用户余额充足时才能成功扣款
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
			LogInfo.output("SQL:扣款成功");
			return true;
		}else{
			LogInfo.output("SQL:扣款失败，用户余额不足");
			return false;
		}
	}
	
	/**
	 * 【获取用户余额】：根据用户名查询用户余额
	 * @param userName
	 * @return
	 */
	public static double getBalance(String userName){
		LogInfo.output("SQL:准备查询余额...");
		double balance=-1;//余额
		
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
		LogInfo.output("SQL:余额查询成功");
		return balance;
	}
	
	/**
	 * 【充值】：指定用户和充值费用
	 * @param userName
	 * @param figure 充值金额
	 * @return
	 */
	public static boolean charge(String userName,double figure){
		LogInfo.output("SQL:准备更新余额...");
		
		Connection conn=ConnectDB.getConnection();
		String sql=null;
		
		double balance=getBalance(userName);//若balance=-1，则数据库中wallet表中没有该用户
		
		try {
			PreparedStatement ps=null;
			if(balance==-1){
				sql="insert into wallet(userName,balance) values('"+userName+"','"+figure+"')";//初次注册用户免费赠送1元打印卷
				ps=conn.prepareStatement(sql);
				ps.executeUpdate();
				LogInfo.output("初次注册用户免费赠送1元打印卷");
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
		LogInfo.output("SQL:余额更新成功");
		return true;
	}
	
	
}
