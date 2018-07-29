package com.Atschool.Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.Atschool.JavaBean.Configuration;
	/**
	 * 数据库连接
	 * database IOTPrint
	 * table userInfo
	 * table userPrintInfo
	 * table userOrderInfo
	 */
	public class ConnectDB {
		/**
		 * 获取数据库连接
		 * @return Connection对象
		 */
		public static Connection getConnection(){
			Connection conn = null;
			try {
				// 加载驱动
				Class.forName("com.mysql.jdbc.Driver");
				// 数据库连接url
				String sqlUrl = Configuration.sqlUrl;
				String sqlUserName=Configuration.sqlUserName;
				String sqlPassword=Configuration.sqlPassword;
				// 获取数据库连接
				conn = DriverManager.getConnection(sqlUrl, sqlUserName, sqlPassword);
				//LogInfo.output("SQL:驱动加载成功");
			} catch (Exception e) {
				//LogInfo.output("SQL:密码或驱动加载失败");
				e.printStackTrace();
			}
			return conn;
		}
		
		/**
		 * 关闭数据库连接
		 * @param conn Connection对象
		 */
		public static void closeConnection(Connection conn){
			// 判断conn是否为空
			if(conn != null){
				try {
					conn.close();	// 关闭数据库连接
					//LogInfo.output("SQL:数据库连接关闭成功");
				} catch (SQLException e) {
					LogInfo.output("SQL:数据库连接关闭失败");
					e.printStackTrace();
				}
			}
		}
	}
