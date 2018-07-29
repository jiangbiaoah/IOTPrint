package com.Atschool.Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.Atschool.JavaBean.Configuration;
	/**
	 * ���ݿ�����
	 * database IOTPrint
	 * table userInfo
	 * table userPrintInfo
	 * table userOrderInfo
	 */
	public class ConnectDB {
		/**
		 * ��ȡ���ݿ�����
		 * @return Connection����
		 */
		public static Connection getConnection(){
			Connection conn = null;
			try {
				// ��������
				Class.forName("com.mysql.jdbc.Driver");
				// ���ݿ�����url
				String sqlUrl = Configuration.sqlUrl;
				String sqlUserName=Configuration.sqlUserName;
				String sqlPassword=Configuration.sqlPassword;
				// ��ȡ���ݿ�����
				conn = DriverManager.getConnection(sqlUrl, sqlUserName, sqlPassword);
				//LogInfo.output("SQL:�������سɹ�");
			} catch (Exception e) {
				//LogInfo.output("SQL:�������������ʧ��");
				e.printStackTrace();
			}
			return conn;
		}
		
		/**
		 * �ر����ݿ�����
		 * @param conn Connection����
		 */
		public static void closeConnection(Connection conn){
			// �ж�conn�Ƿ�Ϊ��
			if(conn != null){
				try {
					conn.close();	// �ر����ݿ�����
					//LogInfo.output("SQL:���ݿ����ӹرճɹ�");
				} catch (SQLException e) {
					LogInfo.output("SQL:���ݿ����ӹر�ʧ��");
					e.printStackTrace();
				}
			}
		}
	}
